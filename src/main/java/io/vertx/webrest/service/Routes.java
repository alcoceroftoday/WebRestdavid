package io.vertx.webrest.service;

import io.vertx.core.json.JsonArray;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.webrest.rest.RestVerticle;

public class Routes extends RestVerticle {

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
        arr.add("david");
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
