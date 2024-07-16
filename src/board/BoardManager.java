package board;

import java.util.TreeSet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardManager extends BoardDBIO {

  @Getter
  private static final BoardManager instance = new BoardManager();

  public boolean insertBoard(Board board) {
    super.insertBoard();
    return true;
  }

  public void selectBoard() {
    super.selectBoard();
  }

  public boolean deleteBoardAll() {
    super.deleteBoardAll();
    return true;
  }

  public boolean updateBoard() {
    super.updateBoard();
    return true;
  }

  public TreeSet<Board> selectBoardAll() {
    super.selectBoardAll();
    return new TreeSet<>();
  }

  public boolean deleteBoard() {
    super.deleteBoard();
    return true;
  }

}
