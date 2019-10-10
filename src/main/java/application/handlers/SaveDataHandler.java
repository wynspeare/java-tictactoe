package application.handlers;

import HTTPcomponents.StatusCode;
import core.Board;
import core.ComputerPlayer;
import server.Response;
import server.handlers.IHandler;
import server.request.Request;

public class SaveDataHandler implements IHandler {
  @Override
  public Response buildResponse(Request request) {
    updateBoard(request);

    FileHandler fileHandler = new FileHandler("/board.json");

    Response response = new Response.Builder()
            .withStatusLine(StatusCode.OK)
            .withBody(fileHandler.getFileAsBytes())
            .build();
    return response;
  }

  public void updateBoard(Request request) {
    String updatedBoard = request.getRequestBody();

    Board board = new Board();
    board.setCells(updatedBoard);

    if(!board.isBoardEmpty()) {
      ComputerPlayer player = new ComputerPlayer();

      Integer move = player.getMove(board);
      board.placeMarker(move, "O");
    }

//    board.updateJsonBoard();
  }
}
