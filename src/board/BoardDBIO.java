package board;

import boardInterface.BoardIO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import lib.ObjectDBIO;

public class BoardDBIO extends ObjectDBIO implements BoardIO {
  Scanner sc = new Scanner(System.in);

  @Override
  public boolean insertBoard() {
    System.out.println("제목 입력 : ");
    String title = sc.next();
    System.out.println("내용 입력 : ");
    String content = sc.next();
    System.out.println("작가 입력 :");
    String writer = sc.next();

    Board board = new Board(title, content, writer);
    String query = "INSERT INTO board VALUES(NULL,?,?,?,now())";

    super.execute(query, board);
    return true;
  }

  @Override
  public boolean deleteBoardAll() {
    return false;
  }

  @Override
  public ArrayList<Board> selectBoard() {
    return null;
  }

  @Override
  public ArrayList<Board> selectBoardAll() {
    ArrayList<Board> boardlist = new ArrayList<Board>();
    String query = "SELECT * FROM board";
    ResultSet rs = null;

    try {
      rs = super.execute(query, rs);
      while(rs.next()) {
        int bno = rs.getInt(1);
        String btitle = rs.getString(2);
        String bcontent = rs.getString(3);
        String bwriter = rs.getString(4);
        String bdate = rs.getString(5);

        Board board = new Board(bno,btitle,bcontent,bwriter,bdate);
        boardlist.add(board);
      }

      rs.close();
      super.close();

    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return boardlist;
  }

  @Override
  public boolean deleteBoard() {
    return false;
  }

  @Override
  public boolean updateBoard() {
    return false;
  }

}
