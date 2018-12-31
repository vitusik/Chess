import Board.*;
import Pieces.*;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckMateTest {
    private static void test1()
    {
        // Fool's mate
        Board.boardInit();
        Board.getPiece(4,6).setXYcoord(4,4);
        Board.getPiece(3,7).setXYcoord(7,3);
        Board.getPiece(5,1).setXYcoord(5,2);
        Board.getPiece(6,1).setXYcoord(6,3);
        assert CheckmateChecker.lookForCheckmate(Board.WHITE);
        assert !CheckmateChecker.lookForCheckmate(Board.BLACK);
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
        assert !CheckmateChecker.lookForCheckmate(Board.WHITE);
        assert CheckmateChecker.lookForCheckmate(Board.BLACK);
        Board.clearBoard();
    }

    private static void test3(){
        King black_king = new King(7,4, Board.BLACK);
        King white_king = new King(5,4, Board.WHITE);
        Rook white_rook = new Rook(7,0,Board.WHITE);

        Board.black_player = new Player(Board.BLACK);
        Board.white_player = new Player(Board.WHITE);
        Board.black_player.setKingXYCoords(7,4);
        ArrayList<Piece> black_list = new ArrayList<>(Arrays.asList(black_king));
        Board.black_player.setpieceList(black_list);
        Board.white_player.setKingXYCoords(5,4);
        ArrayList<Piece> white_list = new ArrayList<>(Arrays.asList(white_king, white_rook));
        Board.white_player.setpieceList(white_list);
        assert CheckmateChecker.lookForCheckmate(Board.BLACK);
    }
    public static void main(String[] args){
        test1();
        test2();
        test3();
    }
}
