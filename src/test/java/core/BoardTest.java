package core;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
  @Test
  public void aNewBoardIsEmpty() {
    {
      Board board = new Board();
      assertTrue(board.isBoardEmpty());
    }
  }

  @Test
  public void aBoardCanMarkASpace() {
    Board board = new Board();
    board.placeMarker(5, "X");

    assertEquals("X", board.cells.get(4));
    assertEquals("X", board.markerAtLocation(5));
  }

  @Test
  public void aBoardCanUpdateJsonFile() {
    Board board = new Board();
    board.placeMarker(5, "X");
    board.placeMarker(2, "O");
    board.placeMarker(1, "X");

    board.updateJsonBoard();
  }

}