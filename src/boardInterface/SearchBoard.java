package boardInterface;

import board.Board;
import java.util.ArrayList;

public interface SearchBoard extends UpdateBoard, BoardOutput, DeleteBoard{
  public ArrayList<Board> selectBoard();
}
