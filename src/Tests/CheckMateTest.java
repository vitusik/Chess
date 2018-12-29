import Board.*;

public class CheckMateTest {
    private static void test1()
    {
        // fool's mate
        Board.boardInit();
        Board.getPiece(4,6).setXYcoord(4,4);
        Board.getPiece(3,7).setXYcoord(7,3);
        Board.getPiece(5,1).setXYcoord(5,2);
        Board.getPiece(6,1).setXYcoord(6,3);
        assert CheckmateChecker.lookForCheckmate(Board.getPlayer(Board.WHITE));
        assert !CheckmateChecker.lookForCheckmate(Board.getPlayer(Board.BLACK));
    }

    public static void main(String[] args){
        test1();
    }
}
