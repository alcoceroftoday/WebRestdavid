package io.vertx.webrest.rest;

import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.webrest.util.DBUtils;

import io.vertx.webrest.rest.Rest;

public class GetArtis {

  public static void main(String[] args) {
//    DBUtils dbUtils = new DBUtils();
//    MySQLConnectOptions connectOptions = new MySQLConnectOptions()
//      .setPort(3306)
//      .setHost(dbUtils.host)
//      .setDatabase(dbUtils.db)
//      .setUser(dbUtils.user)
//      .setPassword(dbUtils.pass);
//    PoolOptions poolOptions = new PoolOptions()
//      .setMaxSize(5);
//
//    MySQLPool client = MySQLPool.pool(connectOptions, poolOptions);
//
//    client.query("SELECT * FROM artist ", ar -> {
//      if (ar.succeeded()) {
//        RowSet<Row> result = ar.result();
//        for (Row row : result) {
//          System.out.println("title " + row.getString(1) + " img " + row.getString(2));
//          System.out.println(dbUtils.user);
//          Rest res= new Rest();
//          res.addArtists(new JsonObject()
//            .put("id", "2")
//            .put("name", "Tame Impala2")
//            .put("artwork", "https://www.yaconic.com/wp-content/uploads/2019/10/images.jpg")
//            .put("created_at", "2020-02-17 11:15:03")
//            .put("updated_ap", "2020-02-17 11:15:03")
//          );
//
//        }
//      } else {
//        System.out.println("Failure: " + ar.cause().getMessage());
//      }
//      client.close();
//    });
  }
}
