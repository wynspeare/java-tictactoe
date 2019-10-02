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
  private String filePath;

  public ResourceHandler (String filePath) {
    this.filePath = filePath;
  }
  @Override
  public Response buildResponse(Request request) {


    Response indexPage = new Response.Builder()
            .withStatusLine(StatusCode.OK)
//            .withHeader("Content-type: text/html")
            .withBody(stringifyFile())
            .build();
    return indexPage;
  }

  private String stringifyFile() {
    InputStream in = this.getClass().getClassLoader()
            .getResourceAsStream(filePath);
    return new BufferedReader(new InputStreamReader(in))
            .lines().collect(Collectors.joining("\n"));
  }
}
