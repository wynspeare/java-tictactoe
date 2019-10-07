package application;

import application.handlers.SaveDataHandler;
import application.handlers.StaticHandler;
import server.HTTPServer;
import server.IController;
import server.Router;
import server.TTTController;
import server.handlers.RedirectHandler;

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
    IController controller = new TTTController();
    Router router = new Router(serverLogger, controller);

    router.addRoute("GET", "/ttt", new StaticHandler("/index.html"));
    router.addRoute("GET", "/", new RedirectHandler("http://127.0.0.1:5000/ttt"));
    router.addRoute("GET", "/styles.css", new StaticHandler());
    router.addRoute("GET", "/favicon.ico", new StaticHandler());
    router.addRoute("GET", "/script.js", new StaticHandler());
    router.addRoute("GET", "/board.json", new StaticHandler());
    router.addRoute("POST", "/board.json", new SaveDataHandler());

    return router;
  }
}
