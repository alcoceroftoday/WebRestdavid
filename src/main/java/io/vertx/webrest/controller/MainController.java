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
        String artistID = routingContext.request().getParam("artistID");
        musicService
                .getArtist(artistID)
                .subscribe(resultSet -> {
                    routingContext.response().putHeader("content-type", "application/json").end(new Gson().toJson(resultSet));
                    System.out.println("Results : "+ new Gson().toJson(resultSet));
                }, err -> {
                    System.out.println("Database problem");
                    err.printStackTrace();
                });
    }
    private void handleGetalbum(RoutingContext routingContext) {
       String albumID = routingContext.request().getParam("albumID");
        musicService
                .getAlbum(albumID)
                .subscribe(resultSet -> {
                    routingContext.response().putHeader("content-type", "application/json").end(new Gson().toJson(resultSet));
                    System.out.println("Results : "+ new Gson().toJson(resultSet));
                }, err -> {
                    System.out.println("Database problem");
                    err.printStackTrace();
                });
    }
}
