package io.vertx.webrest.rest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.reactivex.ext.sql.SQLConnection;
import io.vertx.reactivex.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.reactivex.core.Vertx;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.webrest.util.DBUtils;

import java.util.HashMap;
import java.util.Map;

public class RestVerticle extends AbstractVerticle {

    private static Logger log = LoggerFactory.getLogger(AbstractVerticle.class);

    private Map<String, JsonObject> artists = new HashMap<>();
    private Map<String, JsonObject> albums = new HashMap<>();
    private Map<String, JsonObject> tracks = new HashMap<>();

    @Override
    public void start(Future<Void> startFuture) {

        Vertx vertx = Vertx.currentContext().owner();

        System.out.println(" verticle ");

        DBUtils dbUtils = new DBUtils();

        final JDBCClient client = JDBCClient.createShared(vertx, new JsonObject()
                .put("url", "jdbc:mysql://localhost:3306/prueba")
                .put("driver_class", "org.hsqldb.jdbcDriver")
                .put("max_pool_size", 30)
                .put("user", "david")
                .put("password", "david"));
        client.getConnection(res -> {
            if (res.succeeded()) {

                SQLConnection connection = res.result();

                connection.query("SELECT * FROM artist", res2 -> {
                    if (res2.succeeded()) {
                        ResultSet rs = res2.result();
                        for (JsonArray line : rs.getResults()) {
//                            line.getJsonArray(0);
                            System.out.println(line.encode());
                        }
                        System.out.println("salio bien");
                    }
                });
            } else {
                System.out.println("Error");
            }
        });



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

        vertx.createHttpServer().requestHandler(router).rxListen(8081)
                .doOnError(r -> {
                    log.debug("Server error!");
                })
                .subscribe((r)-> {
                    log.debug("Server on!");
                });
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
