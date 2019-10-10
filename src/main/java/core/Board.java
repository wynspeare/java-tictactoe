package core;

import java.util.ArrayList;
import java.util.List;

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

  public boolean isBoardEmpty() {
    return cells.stream().allMatch(val -> val == " ");
  }

  public void placeMarker(int location, String playerMarker)
  {
    cells.set(location - 1, playerMarker);
  }

  public String markerAtLocation(int location) {
    return cells.get(location - 1);
  }

  public void setCells(String updatedBoard) {
    JsonReader jsonReader = new JsonReader();
    this.cells = jsonReader.parseJson(updatedBoard);
  }

  public void updateJsonBoard() {
    JsonWriter jsonWriter = new JsonWriter();
    jsonWriter.updateFile(cells);
  }
}
