package core.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class JsonReader {

  public String removeKey(String jsonBoard) {
    jsonBoard = jsonBoard.replace("{\n" +
            "  \"board\": [\n", "");
    return jsonBoard.replace("  ]\n" +
            "}\n", "");
  }

  public String[] splitBoard(String jsonBoard) {
    return jsonBoard.split(",");
  }

  public ArrayList<String> formatCells(String[] incomingBoard) {
    ArrayList<String> board = new ArrayList<String>(Arrays.asList(incomingBoard));

    for (int i = 0; i < board.size(); i++) {
      String cell = board.get(i);
      if (cell.contains("X")) {
        board.set(i, "X");
      } else if (cell.contains("O")) {
        board.set(i, "O");
      } else {
        board.set(i, " ");
      }
    }
    return board;
  }

  public ArrayList<String> parseJson(String jsonBoard) {
    String formattedJson = removeKey(jsonBoard);
    String[] separatedBoard = splitBoard(formattedJson);

    return(formatCells(separatedBoard));
  }

}
