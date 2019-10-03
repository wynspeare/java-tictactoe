package application.handlers;

import HTTPcomponents.StatusCode;
import server.Response;
import server.handlers.IHandler;
import server.request.Request;


public class StaticHandler implements IHandler {

  @Override
  public Response buildResponse(Request request) {
    String requestedResource = request.getRequestPath();
    FileHandler fileHandler = new FileHandler(requestedResource);

    if (fileHandler.fileExists()) {
      System.out.println("\nRESOURCE FOUND! for" + requestedResource + "\n");
      return new Response.Builder()
              .withStatusLine(StatusCode.OK)
//              .withHeader("Content-type: image/ico")
              .withBody(fileHandler.stringifyFile())
              .build();
    } else {
      IHandler unknownResource = new UnknownResourceHandler();
        return unknownResource.buildResponse(request);
    }
  }
}
