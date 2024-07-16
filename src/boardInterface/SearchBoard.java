package boardInterface;

import board.Board;
import java.util.ArrayList;

public interface SearchBoard extends UpdateBoard, DeleteBoard, BoardOutput{
  public void selectBoard();
}
