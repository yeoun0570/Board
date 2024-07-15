package board;

import boardInterface.BoardIO;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import lib.ObjectDBIO;

public class BoardDBIO extends ObjectDBIO implements BoardIO {

  Scanner sc = new Scanner(System.in);

  @Override
  public boolean insertBoard() {
    System.out.print("제목 : ");
    String title = sc.nextLine();
    System.out.print("내용 : ");
    String content = sc.nextLine();
    System.out.print("작성자 : ");
    String writer = sc.nextLine();
    System.out.println("-----------------");
    System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
    System.out.print("메뉴 선택 : ");
    int selectMenu = sc.nextInt();

    if (selectMenu == 1) {

      Board board = new Board(title, content, writer);
      String query = "INSERT INTO board VALUES(NULL,?,?,?,now())";

      super.execute(query, board);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean deleteBoardAll() {
    return false;
  }

  @Override
  public ArrayList<Board> selectBoard() {
    ArrayList<Board> boardlist = new ArrayList<>();
    String query = "SELECT * FROM board WHERE bno = ?";
    ResultSet rs = null;

    System.out.print("bno : ");
    int bno = sc.nextInt();
    String btitle = "";
    String bcontent = "";
    String bwriter= "";
    String bdate= "";

    rs = super.execute(query, rs, bno);
    try {

      while (rs.next()) {
        bno = rs.getInt(1);
         btitle = rs.getString(2);
         bcontent = rs.getString(3);
         bwriter = rs.getString(4);
         bdate = rs.getString(5);

        Board board = new Board(bno, btitle, bcontent, bwriter, bdate);
        boardlist.add(board);

      }
      System.out.println("번호 : " + bno);
      System.out.println("제목 : " + btitle);
      System.out.println("내용 : " + bcontent);
      System.out.println("작가 : " + bwriter);
      System.out.println("날짜 : " + bdate);

      rs.close();
      super.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }

    return null;
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
