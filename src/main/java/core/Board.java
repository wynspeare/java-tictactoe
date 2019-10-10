package core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
  public List<String> cells = new ArrayList<>();

  public Board() {
    createBoard();
  }

  public void createBoard() {
    for (int i = 1; i <= 9; i++)
    {
      this.cells.add(" ");
    }
  }

  public void setCells(String updatedBoard) {
    JsonReader jsonReader = new JsonReader();
    this.cells = jsonReader.parseJson(updatedBoard);
  }

  public boolean isBoardEmpty() {
    return cells.stream().allMatch(val -> val == " ");
  }

  public boolean isBoardFilled() {
    return cells.stream().allMatch(val -> val != " ");
  }

  public void placeMarker(int location, String playerMarker)
  {
    cells.set(location - 1, playerMarker);
  }

  public String markerAtLocation(int location) {
    return cells.get(location - 1);
  }

  public List<Integer> getAvailableCells() {
    return IntStream.range(0, cells.size())
            .filter(i -> cells.get(i) == " ")
            .mapToObj(i -> i + 1)
            .collect(Collectors.toList());
  }

//  public void updateJsonBoard() {
//    JsonWriter jsonWriter = new JsonWriter();
//    jsonWriter.updateFile(cells);
//  }
}
