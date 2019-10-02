package application.handlers;

import HTTPcomponents.StatusCode;
import server.Response;
import server.handlers.IHandler;
import server.request.Request;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class StaticHandler implements IHandler {

  @Override
  public Response buildResponse(Request request) {
    if (fileExists(request.getRequestPath())) {
      System.out.println("\nRESOURCE FOUND! for" + request.getRequestPath() + "\n");
      return new Response.Builder()
              .withStatusLine(StatusCode.OK)
//              .withHeader("Content-type: image/ico")
              .withBody(stringifyFile(request.getRequestPath()))
              .build();
    } else {
      return new Response.Builder()
              .withStatusLine(StatusCode.NOT_FOUND)
              .withBody("THIS FILE DOESN'T EXIST!")
              .build();
    }
  }

  public boolean fileExists(String fileName) {
    File requestedResource = new File("./assets/public" + fileName);
    return requestedResource.exists();
  }

  public String stringifyFile(String fileName) {
    InputStream in = this.getClass().getClassLoader()
            .getResourceAsStream("assets/public" + fileName);
    return new BufferedReader(new InputStreamReader(in))
            .lines().collect(Collectors.joining("\n"));
  }
}
