package core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

//  @Test
//  public void canWriteProperlyFormattedJSONToAFile() {
//    JsonWriter jsonWriter = new JsonWriter();
//    ArrayList<String> board = new ArrayList<String>( Arrays.asList(" ", " ", " ", " ", " ", " ", " ", " ", " ") );
//    List<String> formattedCells = jsonWriter.formatCells(board);
//    List<String> formattedBoard = jsonWriter.formatBoard(formattedCells);
//
//    jsonWriter.writeToFile(formattedBoard);
//  }

  @Test
  public void canWriteProperlyFormatJsonWithGameStatus() {
    JsonWriter jsonWriter = new JsonWriter();
    ArrayList<String> board = new ArrayList<String>( Arrays.asList("C", "A", "R", "O", "L", "I", "N", "E", "!") );
    List<String> formattedCells = jsonWriter.formatCells(board);
    List<String> formattedBoard = jsonWriter.formatBoard(formattedCells);
    List<String> formattedBoardWithGameStatus = jsonWriter.addGameStatus(formattedBoard, "win", "X");

    System.out.println(formattedBoardWithGameStatus);
    jsonWriter.writeToFile(formattedBoardWithGameStatus);
  }

  @Test
  public void canWriteUpdatedBoardAndGameStatusToFile() {
    JsonWriter jsonWriter = new JsonWriter();
    ArrayList<String> board = new ArrayList<String>( Arrays.asList("O", "X", "X", "X", "X", "O", " ", "X", "!") );
    jsonWriter.updateFileWithGameStatus(board, "win", "X");
  }

}