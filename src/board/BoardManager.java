package board;

import java.util.ArrayList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardManager extends BoardDBIO {

  private static final BoardManager instance = new BoardManager();

  public boolean insertBoard(Board board) {
    return false;
  }

  public ArrayList<Board> selectBoard() {
    return new ArrayList<Board>();
  }

  public boolean deleteBoardAll() {
    return false;
  }

  public boolean updateBoard() {
    return false;
  }

  public ArrayList<Board> selectBoardAll() {
    return new ArrayList<Board>();
  }

  public boolean deleteBoard() {
    return false;
  }

}
