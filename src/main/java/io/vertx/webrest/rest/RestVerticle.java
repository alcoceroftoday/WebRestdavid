package io.vertx.webrest.rest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.http.HttpServerResponse;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.core.Vertx;
import io.vertx.webrest.controller.MainController;
import io.vertx.webrest.model.DbConnection;
import io.vertx.webrest.service.ArtistService;

public class RestVerticle extends AbstractVerticle {
    private static Logger log = LoggerFactory.getLogger(AbstractVerticle.class);


    @Override
    public void start(Future<Void> startFuture) {

        Vertx vertx = Vertx.currentContext().owner();

        //creacion de router
        Router router = Router.router(vertx);

        //cofnig de controllers (config router)
        MainController ctrl = new MainController(new ArtistService(new DbConnection()));
        ctrl.routes(router);

        // creacion de servidor
        vertx.createHttpServer().requestHandler(router).rxListen(8081)
                .doOnError(r -> {
                    log.debug("Server error!");
                })
                .subscribe((r)-> {
                    log.debug("Server on!");
                });
    }
    private void sendError(int statusCode, HttpServerResponse response) {
        response.setStatusCode(statusCode).end();
    }
}
