package application.handlers;

import HTTPcomponents.StatusCode;
import server.Response;
import server.handlers.IHandler;
import server.request.Request;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ResourceHandler implements IHandler {
  @Override
  public Response buildResponse(Request request) {

    InputStream in = this.getClass().getClassLoader()
            .getResourceAsStream("assets/public/index.html");
    String body = new BufferedReader(new InputStreamReader(in))
            .lines().collect(Collectors.joining("\n"));

    Response indexPage = new Response.Builder()
            .withStatusLine(StatusCode.OK)
            .withHeader("Content-type: text/html")
            .withBody(body)
            .build();

    return indexPage;
  }
}
