package io.vertx.webrest.controller;

import com.google.gson.internal.bind.util.ISO8601Utils;
import io.vertx.core.json.JsonArray;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.webrest.model.DbConnection;
import io.vertx.webrest.service.MainService;

import java.util.HashMap;

public class MainController {

    private final MainService mainService;

    public MainController(){
        System.out.println("Maincontroller");
        this.mainService = new MainService(new DbConnection());
    }

    public void routes(Router routes) {
        routes.get("/artists").handler(this::handleListArtists);
        routes.get("/artists/:artistID").handler(this::handleGetArtist);
        routes.get("/albums").handler(this::handleListAlbums);
        routes.get("/albums/:albumID").handler(this::handleGetalbum);
        routes.get("/tracks").handler(this::handleListTracks);
    }

    private void handleListArtists(RoutingContext routingContext) {
        JsonArray arr = new JsonArray();
//        artists.forEach((k, v) -> arr.add(v));
//       List<Artist> db = new DbConnection();
        System.out.println("controlador");

        HashMap<String,Object> respuesta = new HashMap<>();
        respuesta
                .put("valor1",this.mainService.getArtist().getClass());

        System.out.println(respuesta);
        arr.add("C");
        routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
    }
    private void handleListAlbums(RoutingContext routingContext) {
        JsonArray arr = new JsonArray();
//        albums.forEach((k, v) -> arr.add(v));
        routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
    }
    private void handleListTracks(RoutingContext routingContext) {
        JsonArray arr = new JsonArray();
//        tracks.forEach((k, v) -> arr.add(v));
        routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
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
