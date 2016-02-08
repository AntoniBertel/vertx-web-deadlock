package main;

import io.vertx.core.AbstractVerticle;

public class Starter extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.deployVerticle("java:main.Server");
        vertx.deployVerticle("java:main.WebSocketService");
    }

}
