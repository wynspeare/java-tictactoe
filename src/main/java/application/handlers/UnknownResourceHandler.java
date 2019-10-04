package application.handlers;

import HTTPcomponents.StatusCode;
import server.Response;
import server.handlers.IHandler;
import server.request.Request;

public class UnknownResourceHandler implements IHandler {
  @Override
  public Response buildResponse(Request request) {

    FileHandler fileHandler = new FileHandler("/unknown-resource.html");

    return new Response.Builder()
              .withStatusLine(StatusCode.NOT_FOUND)
              .withBody(fileHandler.stringifyFile())
              .build();
  }
}
