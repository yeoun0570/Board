package boardInterface;

import board.Board;
import java.util.ArrayList;

public interface SearchBoard extends UpdateBoard, DeleteBoard{
  public ArrayList<Board> selectBoard();
}
