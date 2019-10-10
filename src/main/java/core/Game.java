package core;

public class Game {
  Board board;
  ComputerPlayer player;
  Rules rules = new Rules();

  public Game(Board board) {
    this.board = board;
    setPlayer();
  }

  private void setPlayer() {
    this.player = new ComputerPlayer();
  }

  public void turn() {
    if (rules.isOver(board, "X")) {
      updateGameStatus(board, "X");
    } else {
      Integer move = player.getMove(board);
      board.placeMarker(move, "O");

      updateGameStatus(board, "O");
    }
  }

  public void updateGameStatus(Board board, String currentMarker) {
    JsonWriter jsonWriter = new JsonWriter();
    String gameStatus = "";
    String winner = "";
//    jsonWriter.updateFile(board.cells);
    if (rules.isOver(board, currentMarker)) {
      if(rules.checkIfWon(board.cells, currentMarker)) {
        gameStatus = "win";
        winner = currentMarker;
      } else {
        gameStatus = "draw";
      }
    }
    jsonWriter.updateFileWithGameStatus(board.cells, gameStatus, winner);
  }


}
