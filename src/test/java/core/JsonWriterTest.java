package core;

import core.utils.JsonWriter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class JsonWriterTest {
  @Test
  public void canCreateFile() {
    JsonWriter jsonWriter = new JsonWriter();
    ArrayList<String> board = new ArrayList<String>( Arrays.asList("X", "O", "X") );

    jsonWriter.writeToFile(board);
  }

  @Test
  public void canFormatCellsFromArrayList() {
    JsonWriter jsonWriter = new JsonWriter();
    ArrayList<String> board = new ArrayList<>(Arrays.asList("X", "O", "X"));
    ArrayList<String> expected = new ArrayList<>( Arrays.asList("  \"X\",", "  \"O\",", "  \"X\""));

    assertEquals(expected, jsonWriter.formatCells(board));
  }

  @Test
  public void canFormatBoardArrayListAsJSON() {
    JsonWriter jsonWriter = new JsonWriter();
    ArrayList<String> formattedCells = new ArrayList<>( Arrays.asList("  \"X\",", "  \"O\",", "  \"X\""));
    ArrayList<String> expected = new ArrayList<>( Arrays.asList("{\n  \"board\": [", "  \"X\",", "  \"O\",", "  \"X\"", "  ],"));

    assertEquals(expected, jsonWriter.formatBoard(formattedCells));
  }

  @Test
  public void canWriteUpdatedBoardAndGameStatusToFile() {
    JsonWriter jsonWriter = new JsonWriter();
    ArrayList<String> board = new ArrayList<String>( Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ") );
    jsonWriter.updateFileWithGameStatus(board, "win", "X", "");
  }
}