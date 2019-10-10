package core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
  public void aBoardCanFindItsAvailableSpaces() {
    Board board = new Board();
    board.placeMarker(1, "X");
    board.placeMarker(2, "O");
    board.placeMarker(3, "X");

    ArrayList<Integer> expected = new ArrayList<>( Arrays.asList(4, 5, 6, 7, 8, 9));
    assertEquals(expected, board.getAvailableCells());
  }

  @Test
  public void canFindIfABoardIsFilled() {
      Board board = new Board();
      board.placeMarker(1, "X");
      board.placeMarker(2, "O");
      board.placeMarker(3, "X");
      board.placeMarker(4, "X");
      board.placeMarker(5, "O");
      board.placeMarker(6, "X");
      board.placeMarker(7, "X");
      board.placeMarker(8, "O");
      board.placeMarker(9, "X");

      assertTrue(board.isBoardFilled());
    }

  @Test
  public void canFindIfABoardIsNotFilled() {
      Board board = new Board();
      board.placeMarker(1, "X");
      board.placeMarker(2, "O");
      board.placeMarker(3, "X");
      board.placeMarker(4, "X");
      board.placeMarker(5, "O");
      board.placeMarker(6, "X");
      board.placeMarker(7, " ");
      board.placeMarker(8, "O");
      board.placeMarker(9, "X");

      assertFalse(board.isBoardFilled());
  }
}