package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ObjectDBIO {
  //Connection
  private Connection con = null;

  //연결에 활용할 url, id, pwd
  private String url = "jdbc:mysql://localhost:3306/디비";
  private String id = "root";
  private String pwd = "1234";

  //연결 open()
  private boolean open() {
    try {
      con = DriverManager.getConnection(url, id, pwd);
      return true;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }
  //끊기 close()
  private boolean close() {
    try {
      con.close();
      return true;
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      return false;
    }
  }

}
