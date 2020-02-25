package io.vertx.webrest.controller;

import com.google.gson.Gson;
import io.vertx.core.json.JsonArray;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.webrest.service.MusicService;

public class MainController  {

    private final MusicService musicService;

    public MainController(MusicService musicService){
        this.musicService = musicService;
    }

    public void routes(Router routes) {
        routes.get("/artists").handler(this::handleListArtists);
        routes.get("/artists/:artistID").handler(this::handleGetArtist);
        routes.get("/albums").handler(this::handleListAlbums);
        routes.get("/albums/:albumID").handler(this::handleGetalbum);
        routes.get("/tracks").handler(this::handleListTracks);
    }

    private void handleListArtists(RoutingContext routingContext) {
        musicService
                .getArtist()
                .subscribe(resultSet -> {
                    routingContext.response().putHeader("content-type", "application/json").end(new Gson().toJson(resultSet));
                    System.out.println("Results : "+ new Gson().toJson(resultSet));
                }, err -> {
                    System.out.println("Database problem");
                    err.printStackTrace();
                });
    }
    private void handleListAlbums(RoutingContext routingContext) {
        musicService
                .getAlbum()
                .subscribe(resultSet -> {
                    routingContext.response().putHeader("content-type", "application/json").end(new Gson().toJson(resultSet));
                    System.out.println("Results : "+ new Gson().toJson(resultSet));
                }, err -> {
                    System.out.println("Database problem");
                    err.printStackTrace();
                });
    }
    private void handleListTracks(RoutingContext routingContext) {
        musicService
                .getTrack()
                .subscribe(resultSet -> {
                    routingContext.response().putHeader("content-type", "application/json").end(new Gson().toJson(resultSet));
                    System.out.println("Results : "+ new Gson().toJson(resultSet));
                }, err -> {
                    System.out.println("Database problem");
                    err.printStackTrace();
                });
    }
    private void handleGetArtist(RoutingContext routingContext) {
//        String artistID = routingContext.request().getParam("artistID");
//        HttpServerResponse response = routingContext.response();
//        if (artistID == null) {
//            sendError(400, response);
//        } else {
//            JsonObject product = artists.get(artistID);
//            if (product == null) {
//                sendError(404, response);
//            } else {
//                response.putHeader("content-type", "application/json").end(product.encodePrettily());
//            }
//        }
    }
    private void handleGetalbum(RoutingContext routingContext) {
//        String albumID = routingContext.request().getParam("albumID");
//        HttpServerResponse response = routingContext.response();
//        if (albumID == null) {
//            sendError(400, response);
//        } else {
//            JsonObject product = albums.get(albumID);
//            if (product == null) {
//                sendError(404, response);
//            } else {
//                response.putHeader("content-type", "application/json").end(product.encodePrettily());
//            }
//        }
    }
}
