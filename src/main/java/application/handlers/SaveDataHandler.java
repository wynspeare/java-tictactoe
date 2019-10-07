package application.handlers;

import HTTPcomponents.StatusCode;
import core.Board;
import server.Response;
import server.handlers.IHandler;
import server.request.Request;

public class SaveDataHandler implements IHandler {
  @Override
  public Response buildResponse(Request request) {
    updateBoard(request);

    Response response = new Response.Builder()
            .withStatusLine(StatusCode.OK)
            .build();
    return response;
  }

  public void updateBoard(Request request) {
    String updatedBoard = request.getRequestBody();

    Board board = new Board();
    board.setCells(updatedBoard);

    // Hardcoded move to simulate comp's move
    board.placeMarker(9, "O");

    board.updateJsonBoard();
  }
}
