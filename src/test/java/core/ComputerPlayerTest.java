package core;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerPlayerTest {
  @Test
  public void aCompPlayerCanSelectAnAvailableMove() {
    ComputerPlayer player = new ComputerPlayer();
    Board board = new Board();
    board.placeMarker(1, "X");

    Integer min = 2;
    Integer max = 9;
    Integer move = player.getMove(board);

    assertTrue(min <= move && move <= max);
  }

  @Test
  public void aCompPlayerCanSelectTheLastMove() {
    ComputerPlayer player = new ComputerPlayer();
    Board board = new Board();
    board.placeMarker(1, "X");
    board.placeMarker(2, "O");
    board.placeMarker(3, "X");
    board.placeMarker(4, "X");
    board.placeMarker(5, "O");
    board.placeMarker(6, "X");
    board.placeMarker(7, "X");
    board.placeMarker(8, "O");

    Integer expected = 9;
    assertEquals(expected, player.getMove(board));
  }

  @Test
  public void aCompPlayerCanSelectAMoveAndPlaceMarker() {
    ComputerPlayer player = new ComputerPlayer();
    Board board = new Board();
    board.placeMarker(1, "X");

    Integer move = player.getMove(board);
    board.placeMarker(move, "O");

    assertEquals(board.markerAtLocation(move), "O");
  }
}