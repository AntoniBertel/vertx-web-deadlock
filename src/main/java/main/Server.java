package main;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;

public class Server extends AbstractVerticle {

    @Override
    public void start(Future<Void> future) {
        Router mainApi = Router.router(vertx);

        SockJSHandlerOptions options = new SockJSHandlerOptions().setHeartbeatInterval(2000);
        SockJSHandler sockJSHandler = SockJSHandler.create(vertx, options);
        sockJSHandler.socketHandler(sockJSSocket -> {
            Container.sockJSSocket.add(sockJSSocket);
            sockJSSocket.handler((data) -> {
                sockJSSocket.write(Buffer.buffer("{}"));
                vertx.eventBus().publish("some.event", data);
            });
        });
        mainApi.route("/test/*").handler(sockJSHandler);

        mainApi.route().handler(StaticHandler.create());
        vertx.createHttpServer().requestHandler(request -> mainApi.accept(request)).listen(82);
        future.complete();
    }

    @Override
    public void stop(Future<Void> future) {
        future.complete();
    }

}
