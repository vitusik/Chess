import Board.*;
import Pieces.*;

public class CheckMateTest {
    private static void test1()
    {
        // Fool's mate
        Board.boardInit();
        Board.getPiece(4,6).setXYcoord(4,4);
        Board.getPiece(3,7).setXYcoord(7,3);
        Board.getPiece(5,1).setXYcoord(5,2);
        Board.getPiece(6,1).setXYcoord(6,3);
        assert CheckmateChecker.lookForCheckmate(Board.getPlayer(Board.WHITE));
        assert !CheckmateChecker.lookForCheckmate(Board.getPlayer(Board.BLACK));
        Board.clearBoard();
    }

    private static void test2(){
        // Scholar's mate
        Board.boardInit();
        Board.getPiece(1,7).setXYcoord(2,5);
        Board.getPiece(6,7).setXYcoord(5,5);
        Board.getPiece(4,6).setXYcoord(4,4);
        Board.getPiece(5,1).setXYcoord(5,3);
        Board.getPiece(5,0).setXYcoord(2,3);
        Board.getPiece(3,0).setXYcoord(5,6);
        assert !CheckmateChecker.lookForCheckmate(Board.getPlayer(Board.WHITE));
        assert CheckmateChecker.lookForCheckmate(Board.getPlayer(Board.BLACK));
    }

    private static void test3(){

    }
    public static void main(String[] args){
        test1();
        test2();
    }
}
