package main;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;

public class WebSocketService extends AbstractVerticle {

    @Override
    public void start(Future<Void> future) {
        vertx.eventBus().consumer("some.event").handler(message -> {
            Container.sockJSSocket.get(0).write(Buffer.buffer("[]"));
        });
        future.complete();
    }

    @Override
    public void stop(Future<Void> future) {
        future.complete();
    }
}
