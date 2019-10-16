package application.handlers;

import HTTPcomponents.StatusCode;
import application.utils.FileHandler;
import core.Board;
import core.Game;
import core.utils.JsonWriter;
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

    Game game = new Game(board);

    if(!board.isBoardEmpty()) {
      game.turn();
    } else {
      JsonWriter jsonWriter = new JsonWriter();
      jsonWriter.updateFileWithGameStatus(board.cells, "", "", "");
    }
  }
}
