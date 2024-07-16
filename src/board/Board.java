package board;

import java.util.Comparator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Board implements Comparable<Board> {

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

  public Board(String btitle, String bcontent, String bwriter, int bno) {
    this.btitle = btitle;
    this.bcontent = bcontent;
    this.bwriter = bwriter;
    this.bno = bno;
  }

  @Override
  public int compareTo(Board o) {
    if (this.bno == o.bno) {
      return 0;
    } else if (this.bno < o.bno) {
      return 1;
    } else {
      return -1;
    }
  }
}
