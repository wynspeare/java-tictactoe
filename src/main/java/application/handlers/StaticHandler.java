package application.handlers;

import HTTPcomponents.StatusCode;
import server.Response;
import server.handlers.IHandler;
import server.request.Request;

public class StaticHandler implements IHandler {
  private String file;

  public StaticHandler (String filePath) {
    this.file = filePath;
  }

  public StaticHandler () {
    this(null);
  }

  @Override
  public Response buildResponse(Request request) {
    String requestedResource = request.getRequestPath();

    FileHandler fileHandler = getFileHandler(requestedResource);

    if (fileHandler.fileExists()) {
      System.out.println("\nRESOURCE FOUND! for" + requestedResource + "\n");
      return new Response.Builder()
              .withStatusLine(StatusCode.OK)
              .withHeader("Content-type: " + fileHandler.getMIMEType())
              .withBody(fileHandler.getFileAsBytes())
              .build();
    } else {
      IHandler unknownResource = new UnknownResourceHandler();
      return unknownResource.buildResponse(request);
    }
  }

  public FileHandler getFileHandler(String requestedFile) {
    return new FileHandler((file == null) ? requestedFile : file);
  }
}