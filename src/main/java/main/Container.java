package main;


import io.vertx.ext.web.handler.sockjs.SockJSSocket;

import java.util.ArrayList;
import java.util.List;

public class Container {
    public static List<SockJSSocket> sockJSSocket = new ArrayList<>();
}
