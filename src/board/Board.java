package board;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Board {

  private int bno;
  private String btitle;
  private String bcontent;
  private String bwriter;
  private String bdate;


  public Board(String btitle, String bcontent, String bwriter) {
    this.btitle = btitle;
    this.bcontent = bcontent;
    this.bwriter = bwriter;
  }

  public Board(int bno) {
    this.bno = bno;
  }

}
