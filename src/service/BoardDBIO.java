package service;

import board.Board;
import boardInterface.BoardIO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;
import lib.ObjectDBIO;

public class BoardDBIO extends ObjectDBIO implements BoardIO {

  Scanner sc = new Scanner(System.in);

  int bno;
  String btitle;
  String bcontent;
  String bwriter;
  String bdate;

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
      super.executeInsert(query, board);

      return true;
    } else {
      return false;
    }
  }

  @Override
  public void selectBoard() {
    ArrayList<Board> boardlist = new ArrayList<>();
    String query = "SELECT bno, btitle, bcontent, bwriter, DATE_FORMAT(bdate, '%Y.%m.%d') FROM board WHERE bno = ?";
    ResultSet rs = null;

    System.out.print("bno : ");
    bno = sc.nextInt();

    rs = super.executeSelect(query, rs, bno);
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
      System.out.println("작성자 : " + bwriter);
      System.out.println("날짜 : " + bdate);

      rs.close();
      super.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    System.out.println("보조 메뉴 : 1.Update | 2.Delete | 3.List");
    System.out.print("메뉴 선택 : ");
    int selectMenu = sc.nextInt();

    if (selectMenu == 1) {
      updateBoard();
    } else if (selectMenu == 2) {
      deleteBoard();
    }

  }

  @Override
  public boolean updateBoard() {
    sc.nextLine();
    System.out.print("제목 : ");
    String title = sc.nextLine();
    System.out.print("내용 : ");
    String content = sc.nextLine();
    System.out.print("작성자 : ");
    String writer = sc.nextLine();

    Board board = new Board(title, content, writer, bno);

    StringBuilder query = new StringBuilder();
    query.append("UPDATE board SET btitle = ?, ")
        .append("bcontent = ?, ")
        .append("bwriter = ? ")
        .append("WHERE bno = ?");

    super.executeUpdate(String.valueOf(query), board);

    return true;
  }

  @Override
  public boolean deleteBoard() {

    Board board = new Board(bno);
    String query = "DELETE FROM board WHERE bno = ?";
    super.executeDelete(query, board);
    return true;

  }

  @Override
  public boolean deleteBoardAll() {
    System.out.println("-----------------");
    System.out.println("보조 메뉴: 1.Ok | 2.Cancel");
    System.out.print("메뉴 선택 : ");
    int selectMenu = sc.nextInt();

    if (selectMenu == 1) {
      String query = "DELETE FROM board";
      super.executeDeleteAll(query);

      return true;
    } else {
      return false;
    }
  }

  @Override
  public TreeSet<Board> selectBoardAll() {
    TreeSet<Board> boardlist = new TreeSet<>();
    String query = "SELECT bno, btitle, bcontent, bwriter, DATE_FORMAT(bdate, '%Y.%m.%d') FROM board;";
    ResultSet rs = null;

    try {
      rs = super.executeSelectAll(query, rs);
      while (rs.next()) {
        bno = rs.getInt(1);
        btitle = rs.getString(2);
        bcontent = rs.getString(3);
        bwriter = rs.getString(4);
        bdate = rs.getString(5);

        Board board = new Board(bno, btitle, bcontent, bwriter, bdate);
        boardlist.add(board);

      }

      rs.close();
      super.close();

    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }

    for (Board board : boardlist) {
      System.out.printf("%s %10s %20s %30s\n",
          board.getBno(),
          board.getBwriter(),
          board.getBdate(),
          board.getBtitle());
    }

    return boardlist;
  }
}
