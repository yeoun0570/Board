package lib;

import board.Board;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public abstract class ObjectDBIO {

  //Connection
  private Connection con = null;

  //연결에 활용할 url, id, pwd
  /*private String url = "jdbc:mysql://localhost:3306/employees";
  private String id = "root";
  private String pwd = "1234";*/

  private String url;
  private String id;
  private String pwd;


  //Properties url, id, pwd
  public void createDBInfo() {
    Properties DBInfo = new Properties();
    try {
      DBInfo.load(ObjectDBIO.class.getResourceAsStream("rootInfo.properties"));
    } catch (IOException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

    String url = DBInfo.getProperty("url");
    String id = DBInfo.getProperty("id");
    String pwd = DBInfo.getProperty("pwd");

    this.url = url;
    this.id = id;
    this.pwd = pwd;
  }

  //연결 open()
  protected boolean open() {
    createDBInfo();
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

  //insert
  protected boolean executeInsert(String query, Board board) {

    int bno = board.getBno();
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

  //select query 실행하기
  protected ResultSet executeSelect(String query, ResultSet rs, int bno) {
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



  //update
  protected boolean executeUpdate(String query, Board board) {

    int bno = board.getBno();
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
      pstmt.setInt(4, bno);

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

  public boolean executeDelete(String query, Board board) {

    int bno = board.getBno();

    boolean result1 = false;
    try {
      open();
      PreparedStatement pstmt = con.prepareStatement(query);
      pstmt.setInt(1, bno);

      int result = pstmt.executeUpdate();
      pstmt.close();
      close();
      if (result == 1) {
        result1 = true;
      } else if (result == 0) {
        result1 = false;
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }

    return result1;
  }

  public boolean executeDeleteAll(String query) {

    boolean result1 = false;

    try {
      open();
      PreparedStatement pstmt = con.prepareStatement(query);
      int result = pstmt.executeUpdate();

      pstmt.close();
      close();

      if (result == 1) {
        result1 = true;
      } else if (result == 0) {
        result1 = false;
      }

    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }

    return result1;

  }

  protected ResultSet executeSelectAll(String query, ResultSet rs) {

    try {
      open();
      Statement stmt = con.createStatement();
      rs = stmt.executeQuery(query);

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    return rs;

  }
}
