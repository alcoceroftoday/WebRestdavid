package io.vertx.webrest.rest;

import io.vertx.core.DeploymentOptions;
import io.vertx.reactivex.core.Vertx;

public class Rest{


  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();
//    DeploymentOptions options = new DeploymentOptions().setInstances(Runtime.getRuntime().availableProcessors());
    DeploymentOptions options = new DeploymentOptions();
    System.out.println(" main ");
    vertx.deployVerticle(RestVerticle::new, options);

//    Runner.runExample(RestVerticle.class);
  }



}
