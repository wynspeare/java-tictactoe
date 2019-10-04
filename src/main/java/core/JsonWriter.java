package core;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class JsonWriter {

  public Path writeToFile(List<String> board) {
    Path file = Paths.get("./assets/public/board.json");
    try {
      Files.write(file, board, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return file;
  }

  public List<String> formatCells(List<String> board) {
    board = board.stream().map(cell -> "  \"" + cell + "\",").collect(Collectors.toList());
    String lastCell = board.get(board.size() - 1).replace(",", "");
    board.set(board.size() - 1, lastCell);

//    for (int i = 0; i < board.size() - 1; i++) {
//      String cell = board.get(i);
//      board.set(i, "  \"" + cell + "\"," );
//    }
//    String lastCell = board.get(board.size() - 1);
//    board.set(board.size() - 1, "  \"" + lastCell + "\"" );

    return board;
  }

  public List<String> formatBoard(List<String> board) {
    board.add(0, "{\n  \"board\": [");
    board.add(board.size(), "  ]\n}");
    return board;
  }

  public void updateFile(List<String> board) {
    List<String> formattedCells = formatCells(board);
    List<String> formattedBoard = formatBoard(formattedCells);

    writeToFile(formattedBoard);
  }

}