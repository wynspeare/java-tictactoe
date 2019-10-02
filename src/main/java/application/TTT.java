package application;

import application.handlers.ResourceHandler;
import application.handlers.StaticHandler;
import server.HTTPServer;
import server.Router;
import server.handlers.DefaultHandler;

public class TTT {
  private static int defaultPort = 5000;
  private static ServerLogger serverLogger = createLogger("Logs");

  public static ServerLogger createLogger(String directory) {
    return new ServerLogger(directory);
  }

  public static void main(String[] args) {
    HTTPServer httpServer = new HTTPServer();
    httpServer.buildServerSocket(defaultPort);
    Router router = createRouter();
    httpServer.serve(router);
  }

  public static Router createRouter() {
    Router router = new Router(serverLogger);
    router.addRoute("GET", "/", new DefaultHandler());
    router.addRoute("GET", "/ttt", new ResourceHandler("assets/public/index.html"));
    router.addRoute("GET", "/styles.css", new StaticHandler());
    router.addRoute("GET", "/favicon.ico", new StaticHandler());
    router.addRoute("GET", "/ttt.png", new StaticHandler());


    return router;
  }
}
