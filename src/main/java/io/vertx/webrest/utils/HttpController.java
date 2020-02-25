package io.vertx.webrest.utils;

import com.google.gson.Gson;

import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.webrest.EnvContainer;

public abstract class HttpController {

    private final EnvContainer env;

    public HttpController() {
        this.env = new EnvContainer();
    }

    public HttpController(EnvContainer env) {
        this.env = env;
    }

    public abstract void routes(Router router);


    protected void catchError(RoutingContext ctx, Throwable ex) {
        if (env.isDev()) {
            ex.printStackTrace();
            this.internalError(ctx, ex);
        } else {
            this.internalError(ctx, new Throwable("Internal error"));
        }
    }



    protected <T> void ok(io.vertx.ext.web.RoutingContext ctx, T body) {
        String final_msg = new Gson().toJson(body);

        ctx.response().setStatusCode(200).putHeader("content-type", "application/json").end(final_msg);
    }

    protected <T> void ok(RoutingContext ctx, T body) {
        this.ok(ctx.getDelegate(), body);
    }

    protected <T> void successCreated(RoutingContext ctx, T body) {
        String final_msg = new Gson().toJson(body);
        ctx.response().setStatusCode(201).putHeader("content-type", "application/json").end(final_msg);
    }

    protected void successEmpty(RoutingContext ctx) {
        ctx.response().setStatusCode(204).putHeader("content-type", "application/json").end();
    }

    protected void unauthorizedRequest(RoutingContext contenxt) {
        contenxt
                .response()
                .setStatusCode(401)
                .putHeader("content-type", "application/json")
                .end(new JsonObject().put("message", "unauthorized").encodePrettily());
    }

    protected void badRequest(RoutingContext context) {
        context
                .response()
                .setStatusCode(400)
                .putHeader("content-type", "application/json")
                .end(new JsonObject().put("error", "client error").encodePrettily());
    }

    protected void badRequest(RoutingContext context, Throwable ex) {
        context
                .response()
                .setStatusCode(400)
                .putHeader("content-type", "application/json")
                .end(new JsonObject().put("error", ex.getMessage()).encodePrettily());
    }

    protected void forbiddenRequest(RoutingContext context) {
        context.response().setStatusCode(403).putHeader("content-type", "application/json").end();
    }

    protected void notFound(RoutingContext context) {
        context
                .response()
                .setStatusCode(404)
                .putHeader("content-type", "application/json")
                .end(new JsonObject().put("message", "not_found").encodePrettily());
    }

    protected void internalError(RoutingContext context, Throwable ex) {
        context
                .response()
                .setStatusCode(500)
                .putHeader("content-type", "application/json")
                .end(new JsonObject().put("error", ex.getMessage()).encodePrettily());
    }

    protected void notImplemented(RoutingContext context) {
        context
                .response()
                .setStatusCode(501)
                .putHeader("content-type", "application/json")
                .end(new JsonObject().put("message", "not_implemented").encodePrettily());
    }

    protected void badGateway(Throwable ex, RoutingContext context) {
        ex.printStackTrace();
        context
                .response()
                .setStatusCode(502)
                .putHeader("content-type", "application/json")
                .end(
                        new JsonObject()
                                .put("error", "bad_gateway")
                                // .put("message", ex.getMessage())
                                .encodePrettily());
    }

    protected void serviceUnavailable(RoutingContext context) {
        context.fail(503);
    }

    protected void serviceUnavailable(RoutingContext context, Throwable ex) {
        context
                .response()
                .setStatusCode(503)
                .putHeader("content-type", "application/json")
                .end(new JsonObject().put("error", ex.getMessage()).encodePrettily());
    }

    protected void serviceUnavailable(RoutingContext context, String cause) {
        context
                .response()
                .setStatusCode(503)
                .putHeader("content-type", "application/json")
                .end(new JsonObject().put("error", cause).encodePrettily());
    }
}
