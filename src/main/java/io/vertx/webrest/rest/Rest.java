package io.vertx.webrest.rest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.webrest.util.Runner;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import  io.vertx.webrest.util.DBUtils;

import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;


import java.util.HashMap;
import java.util.Map;

public class Rest extends AbstractVerticle {

  public static void main(String[] args) {


    DBUtils dbUtils = new DBUtils();
    MySQLConnectOptions connectOptions = new MySQLConnectOptions()
      .setPort(3306)
      .setHost(dbUtils.host)
      .setDatabase(dbUtils.db)
      .setUser(dbUtils.user)
      .setPassword(dbUtils.pass);
    PoolOptions poolOptions = new PoolOptions()
      .setMaxSize(5);

    MySQLPool client = MySQLPool.pool(connectOptions, poolOptions);

    client.query("SELECT * FROM artist ", ar -> {
      if (ar.succeeded()) {
        RowSet<Row> result = ar.result();
        for (Row row : result) {
          System.out.println(row.getLocalDateTime(3));
          dbUtils.putArtits(
            row.getInteger(0).toString(),
            row.getString(1),
            row.getString(2),
            row.getLocalDateTime(3).toString(),row.getLocalDateTime(4).toString()
          );
        }
      } else {
        System.out.println("Failure: " + ar.cause().getMessage());
      }

    });
    client.query("SELECT * FROM albums ", ar -> {
      if (ar.succeeded()) {
        RowSet<Row> result = ar.result();
        for (Row row : result) {
          dbUtils.putAlbums(
            row.getInteger(0).toString(),
            row.getString(1),
            row.getString(2),
            row.getInteger(3).toString(),
            row.getLocalDateTime(4).toString(),row.getLocalDateTime(5).toString()

          );
        }
      } else {
        System.out.println("Failure: " + ar.cause().getMessage());
      }

    });
    client.query("SELECT * FROM tracks ", ar -> {
      if (ar.succeeded()) {
        RowSet<Row> result = ar.result();
        for (Row row : result) {
          dbUtils.putTracks(
            row.getInteger(0).toString(),
            row.getString(1),
            row.getInteger(2).toString(),
            row.getInteger(3).toString(),
            row.getString(4),
            row.getInteger(5).toString(),
            row.getLocalDateTime(6).toString(),row.getLocalDateTime(7).toString()
          );
        }
      } else {
        System.out.println("Failure: " + ar.cause().getMessage());
      }
      client.close();
    });
    Runner.runExample(Rest.class);
  }

  private Map<String, JsonObject> artists = new HashMap<>();
  private Map<String, JsonObject> albums = new HashMap<>();
  private Map<String, JsonObject> tracks = new HashMap<>();
  @Override
  public void start() {
    DBUtils dbUtils = new DBUtils();
    artists = dbUtils.getArtists();
    albums = dbUtils.getAlbums();
    tracks = dbUtils.getTracks();
    Router router = Router.router(vertx);

    router.route().handler(BodyHandler.create());
    router.get("/artists").handler(this::handleListArtists);
    router.get("/artists/:artistID").handler(this::handleGetArtist);
    router.get("/albums").handler(this::handleListAlbums);
    router.get("/albums/:albumID").handler(this::handleGetalbum);
    router.get("/tracks").handler(this::handleListTracks);

    vertx.createHttpServer().requestHandler(router).listen(8081);
  }

  private void handleGetArtist(RoutingContext routingContext) {
    String artistID = routingContext.request().getParam("artistID");
    HttpServerResponse response = routingContext.response();
    if (artistID == null) {
      sendError(400, response);
    } else {
      JsonObject product = artists.get(artistID);
      if (product == null) {
        sendError(404, response);
      } else {
        response.putHeader("content-type", "application/json").end(product.encodePrettily());
      }
    }
  }
  private void handleGetalbum(RoutingContext routingContext) {
    String albumID = routingContext.request().getParam("albumID");
    HttpServerResponse response = routingContext.response();
    if (albumID == null) {
      sendError(400, response);
    } else {
      JsonObject product = albums.get(albumID);
      if (product == null) {
        sendError(404, response);
      } else {
        response.putHeader("content-type", "application/json").end(product.encodePrettily());
      }
    }
  }


  private void handleListArtists(RoutingContext routingContext) {
    JsonArray arr = new JsonArray();
    artists.forEach((k, v) -> arr.add(v));
    routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
  }
  private void handleListAlbums(RoutingContext routingContext) {
    JsonArray arr = new JsonArray();
    albums.forEach((k, v) -> arr.add(v));
    routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
  }
  private void handleListTracks(RoutingContext routingContext) {
    JsonArray arr = new JsonArray();
    tracks.forEach((k, v) -> arr.add(v));
    routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
  }

  private void sendError(int statusCode, HttpServerResponse response) {
    response.setStatusCode(statusCode).end();
  }

}
