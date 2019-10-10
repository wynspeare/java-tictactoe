package core;

import java.util.List;
import java.util.Random;

public class ComputerPlayer {

  public Integer getMove(Board board)
  {
    Random random = new Random();
    List<Integer> availableSpaces = board.getAvailableCells();
    return availableSpaces.get(random.nextInt(availableSpaces.size()));
  }
}
