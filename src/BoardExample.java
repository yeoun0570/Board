import board.Board;
import board.BoardDBIO;
import board.BoardManager;
import java.util.Scanner;

public class BoardExample {

  static BoardManager boardManager = BoardManager.getInstance();
  static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {

    BoardDBIO boardDBIO = new BoardDBIO();

    while(true) {
      viewList();
      int selectMenu = sc.nextInt();

      if(selectMenu == 1) {
        boardManager.insertBoard();
      } else if (selectMenu == 2) {
        boardManager.selectBoard();
      } else if (selectMenu == 3) {
        boardManager.deleteBoardAll();
      } else if(selectMenu == 4){
        System.out.println("** 게시판 종료 **");
        break;
      }

    }

  }

  private static void viewList () {
    System.out.println("[게시물 목록]");
    System.out.println("--------------------------------------------------------------------------------------");
    System.out.printf("%s %10s %20s %30s\n", "no", "writer", "date", "title");
    System.out.println("--------------------------------------------------------------------------------------");
    boardManager.selectBoardAll();
    System.out.println();
    System.out.println("--------------------------------------------------------------------------------------");
    System.out.println("메인 메뉴 : 1.Create | 2.Read | 3.Clear | 4.Exit");
    System.out.print("메뉴 선택 : ");
  }


}