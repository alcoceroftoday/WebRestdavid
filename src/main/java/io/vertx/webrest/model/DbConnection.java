package io.vertx.webrest.model;

import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.jdbc.JDBCClient;
import io.vertx.reactivex.core.Vertx;
import javax.inject.Singleton;

@Singleton
public class DbConnection {
    public final JDBCClient db;
    public DbConnection(){
        db =
                JDBCClient.createShared(Vertx.currentContext().owner(), new JsonObject()
                        .put("url", "jdbc:mysql://localhost:3306/prueba")
                        .put("driver_class", "org.hsqldb.jdbcDriver")
                        .put("max_pool_size", 30)
                        .put("user", "david")
                        .put("password", "david"));
        db.getConnection(res -> {
            if (res.succeeded()) {
                System.out.println("SE conecto");
            } else {
                System.out.println("Error");
            }
        });
    }
}
