package core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class RulesTest {
  @Test
  public void canTakeInABoardAndSeeIfThereIsAWinner() {
    Rules rules = new Rules();
    Board board = new Board();
    board.placeMarker(1, "X");
    board.placeMarker(2, "O");
    board.placeMarker(7, "X");
    board.placeMarker(4, "X");

    assertTrue(rules.checkIfWon(board.cells, "X"));
  }

  @Test
  public void canCheckToSeeIfARowIsComplete() {
    Rules rules = new Rules();
    ArrayList<String> row = new ArrayList<String>( Arrays.asList("X", "X", "X") );

    assertTrue(rules.isRowComplete(row, "X"));
  }

  @Test
  public void canCheckToSeeIfARowIsNotComplete() {
    Rules rules = new Rules();
    ArrayList<String> row = new ArrayList<String>( Arrays.asList("X", "O", "X") );

    assertFalse(rules.isRowComplete(row, "X"));
  }

  @Test
  public void canCheckToSeeIfGameIsADraw() {
    Rules rules = new Rules();
    Board board = new Board();
    board.placeMarker(1, "X");
    board.placeMarker(2, "O");
    board.placeMarker(3, "X");
    board.placeMarker(4, "O");
    board.placeMarker(5, "O");
    board.placeMarker(6, "X");
    board.placeMarker(7, "O");
    board.placeMarker(8, "X");
    board.placeMarker(9, "O");

    assertTrue(rules.checkIfDraw(board, "X"));
  }

  @Test
  public void canCheckToSeeIfADrawnGameIsOver() {
    Rules rules = new Rules();
    Board board = new Board();
    board.placeMarker(1, "X");
    board.placeMarker(2, "O");
    board.placeMarker(3, "X");
    board.placeMarker(4, "O");
    board.placeMarker(5, "O");
    board.placeMarker(6, "X");
    board.placeMarker(7, "O");
    board.placeMarker(8, "X");
    board.placeMarker(9, "O");

    assertTrue(rules.isOver(board, "X"));
  }

  @Test
  public void canCheckToSeeIfAWonGameIsOver() {
    Rules rules = new Rules();
    Board board = new Board();
    board.placeMarker(1, "X");
    board.placeMarker(2, "O");
    board.placeMarker(3, "X");
    board.placeMarker(4, "O");
    board.placeMarker(5, "O");
    board.placeMarker(6, "X");
    board.placeMarker(7, "O");
    board.placeMarker(8, " ");
    board.placeMarker(9, "X");

    assertTrue(rules.isOver(board, "X"));
  }
}