package io.vertx.webrest.controller;

import com.google.gson.Gson;
import io.vertx.core.json.JsonArray;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.webrest.model.Artist;
import io.vertx.webrest.service.ArtistService;

import java.util.Arrays;

public class MainController  {

//    private final MainService mainService;
    private final ArtistService artistService;

    public MainController(ArtistService artistService){

        System.out.println("Maincontroller");
//        this.mainService = new MainService(new DbConnection());
        this.artistService = artistService;
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
        artistService
                .getArtist()
//                .subscribe();
//        System.out.println(artistService.getArtist().subscribe());
//                .forEach((k, v) -> arr.add(v))
                .subscribe(resultSet -> {
                    resultSet.forEach(
                            (v)-> {
                                JsonArray b = new JsonArray();
                                System.out.println(v.id+v.name);
//                                a.add(v.id.toString());
//                                a.add(v.name);
//                                a.add(v.artwork);
//                                a.add(v.created_at);
//                                a.add(v.updated_ap);
//                                arr.add(a);

                                Artist art = new Artist(
                                        v.id,
                                        v.name,
                                        v.artwork,
                                        v.created_at,
                                        v.updated_ap
                                );
//                                b.add(v.id.toString());
//                                b.add(v.name);
//                                b.add(v.artwork);
//                                b.add(v.created_at);
//                                b.add(v.updated_ap);
//                                System.out.println(b);
//                                arr.add(b);
//                                String final_msg = new Gson().toJson(Arrays.asList(new Artist(
//                                        v.id,
//                                        v.name,
//                                        v.artwork,
//                                        v.created_at,
//                                        v.updated_ap
//                                )));
                                String final_msg = new Gson().toJson(new Artist(
                                        v.id,
                                        v.name,
                                        v.artwork,
                                        v.created_at,
                                        v.updated_ap
                                ));
//                                String finalarr = new Gson().toJson(Arrays.asList(arr));
                                arr.add(final_msg);
//                                System.out.println(v);
                            }
                    );
                    routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
//                    String finalarr = new Gson().toJson(Arrays.asList(arr));
//                    routingContext.response().putHeader("content-type", "application/json").end(finalarr);
                    System.out.println("Results : " + arr);
                }, err -> {
                    System.out.println("Database problem");
                    err.printStackTrace();
                });


//                .subscribe(data -> this.ok(ctx, data), err -> this.catchError(ctx, err));


//        JsonArray arr = new JsonArray();
//
//        System.out.println( this.mainService.getArtist().toFuture());
//        System.out.println("controlador");
//
//        HashMap<String,Object> respuesta = new HashMap<>();
//        respuesta
//                .put("valor1",this.mainService.getArtist().getClass());
//
//        System.out.println(respuesta);
//        arr.add("C");
//        routingContext.response().putHeader("content-type", "application/json").end(arr.encodePrettily());
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
