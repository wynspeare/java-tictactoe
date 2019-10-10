package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rules {
  public String winningCombo = "";

  private int[][] winCombinations = {
    { 0, 1, 2 },
    { 3, 4, 5 },
    { 6, 7, 8 },
    { 0, 4, 8 },
    { 2, 4, 6 },
    { 0, 3, 6 },
    { 1, 4, 7 },
    { 2, 5, 8 },
  };

  public boolean checkIfWon(List<String> board, String currentMarker)
  {
    List<String> tempRow = new ArrayList<String>();
    boolean isWon = false;

    for (int i = 0; i < winCombinations.length; i++)
    {
      for (int j = 0; j < winCombinations[1].length; j++)
      {
        int index = winCombinations[i][j];
        tempRow.add(board.get(index));
      }
      if (isRowComplete(tempRow, currentMarker))
      {
        winningCombo = Arrays.toString(winCombinations[i]);
        isWon = true;
      }
      tempRow.clear();
    }
    return isWon;
  }

  public boolean isRowComplete(List<String> row, String marker)
  {
    return row.stream().allMatch(val -> val == marker);
  }

  public boolean checkIfDraw(Board board, String currentMarker)
  {
    return board.isBoardFilled() && !checkIfWon(board.cells, currentMarker);
  }

  public boolean isOver(Board board, String currentMarker)
  {
    return checkIfWon(board.cells, currentMarker) || checkIfDraw(board, currentMarker);
  }

}
