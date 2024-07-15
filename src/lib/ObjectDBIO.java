package lib;

import board.Board;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public abstract class ObjectDBIO {

  Scanner sc = new Scanner(System.in);

  //Connection
  private Connection con = null;

  //연결에 활용할 url, id, pwd
  private String url = "jdbc:mysql://localhost:3306/employees";
  private String id = "root";
  private String pwd = "1234";

  //연결 open()
  protected boolean open() {
    try {
      con = DriverManager.getConnection(url, id, pwd);
      return true;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

  //끊기 close()
  protected boolean close() {
    try {
      con.close();
      return true;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

  //select query 실행하기
  protected ResultSet execute(String query, ResultSet rs, int bno) {
    try {
      open();
      PreparedStatement pstmt = con.prepareStatement(query);
      pstmt.setInt(1, bno);
      rs = pstmt.executeQuery();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return rs;
  }

  //insert, update, delete* 실행하기
  protected boolean execute(String query, Board board) {

    String btitle = board.getBtitle();
    String bcontent = board.getBcontent();
    String bwriter = board.getBwriter();

    boolean result1 = false;
    try {
      open();
      PreparedStatement pstmt = con.prepareStatement(query);
      pstmt.setString(1, btitle);
      pstmt.setString(2, bcontent);
      pstmt.setString(3, bwriter);

      int result = pstmt.executeUpdate();

      if (result == 1) {
        result1 = true;
      } else if (result == 0) {
        result1 = false;
      }

      pstmt.close();
      close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return result1;
    }
    return result1;
  }


}
