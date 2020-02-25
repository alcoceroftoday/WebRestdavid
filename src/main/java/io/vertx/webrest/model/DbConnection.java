package io.vertx.webrest.model;

import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.jdbc.JDBCClient;
import io.vertx.reactivex.core.Vertx;
import io.vertx.webrest.utils.DBUtils;

import javax.inject.Singleton;

@Singleton
public class DbConnection {
    public final JDBCClient db;
    public DbConnection(){
        DBUtils util = new DBUtils();
        db =
                JDBCClient.createShared(Vertx.currentContext().owner(), new JsonObject()
                        .put("url", "jdbc:mysql://"+util.host+"/"+util.db)
                        .put("driver_class", "org.hsqldb.jdbcDriver")
                        .put("max_pool_size", 30)
                        .put("user", util.user)
                        .put("password", util.pass));
        db.getConnection(res -> {
            if (res.succeeded()) {
                System.out.println("SE conecto");
            } else {
                System.out.println("Error");
            }
        });
    }
}
