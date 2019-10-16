package core;

import core.utils.JsonReader;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class JsonReaderTest {
  @Test
  public void incomingJsonBoardCanBeSplitIntoArray() {
    String incomingBoard = "{\n" +
            "  \"board\": [\n" +
            "  \"C\",\n" +
            "  \"A\",\n" +
            "  \"R\",\n" +
            "  \"O\",\n" +
            "  \"L\",\n" +
            "  \"I\",\n" +
            "  \"N\",\n" +
            "  \"E\",\n" +
            "  \"!\"\n" +
            "  ]\n" +
            "}\n";
    JsonReader jsonReader = new JsonReader();
    String[] board = jsonReader.splitBoard(incomingBoard);

    assertEquals(9, board.length);
  }

  @Test
  public void canRemoveBoardKeyFromIncomingJsonBoard() {
    String incomingBoard = "{\n" +
            "  \"board\": [\n" +
            "  \"X\",\n" +
            "  \"A\",\n" +
            "  \"R\",\n" +
            "  \" \",\n" +
            "  \"L\",\n" +
            "  \"I\",\n" +
            "  \"N\",\n" +
            "  \"E\",\n" +
            "  \"!\"\n" +
            "  ]\n" +
            "}\n";
    JsonReader jsonReader = new JsonReader();
    String board = jsonReader.removeKey(incomingBoard);
    String expected = "  \"X\",\n" +
            "  \"A\",\n" +
            "  \"R\",\n" +
            "  \" \",\n" +
            "  \"L\",\n" +
            "  \"I\",\n" +
            "  \"N\",\n" +
            "  \"E\",\n" +
            "  \"!\"\n";

    assertEquals(expected, board);
  }

  @Test
  public void canFormatCellsToGrabPlayersMarkers() {
    String[] incomingBoard = {
     "  \" \",\n",
            "  \"X\",\n",
            "  \"O\",\n",
            "  \"O\",\n",
            "  \" \",\n",
            "  \" \",\n",
            "  \"A\",\n",
            "  \"X\",\n",
            "  \"!\"\n"};
    JsonReader jsonReader = new JsonReader();
    ArrayList<String> board = jsonReader.formatCells(incomingBoard);
    ArrayList<String> expected = new ArrayList<>( Arrays.asList(" ", "X", "O", "O", " ", " ", " ", "X", " "));
    assertEquals(expected, board);
  }

  @Test
  public void canParseIncomingJsonBoardIntoArrayList() {
    String incomingBoard = "{\n" +
            "  \"board\": [\n" +
            "  \" \",\n" +
            "  \"X\",\n" +
            "  \"O\",\n" +
            "  \"O\",\n" +
            "  \"L\",\n" +
            "  \"I\",\n" +
            "  \"N\",\n" +
            "  \"X\",\n" +
            "  \"!\"\n" +
            "  ]\n" +
            "}\n";
    JsonReader jsonReader = new JsonReader();
    ArrayList<String> board = jsonReader.parseJson(incomingBoard);
    ArrayList<String> expected = new ArrayList<>( Arrays.asList(" ", "X", "O", "O", " ", " ", " ", "X", " "));
    assertEquals(expected, board);
  }
}