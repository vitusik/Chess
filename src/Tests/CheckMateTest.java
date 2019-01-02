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
        assert !CheckmateChecker.lookForCheckmate(Board.WHITE);
        Board.clearBoard();
    }

    private static void test4(){
        King black_king = new King(0,5, Board.BLACK);
        King white_king = new King(2,5, Board.WHITE);
        Queen white_queen = new Queen(1,5,Board.WHITE);

        Board.black_player = new Player(Board.BLACK);
        Board.white_player = new Player(Board.WHITE);
        Board.black_player.setKingXYCoords(0,5);
        ArrayList<Piece> black_list = new ArrayList<>(Arrays.asList(black_king));
        Board.black_player.setpieceList(black_list);
        Board.white_player.setKingXYCoords(2,5);
        ArrayList<Piece> white_list = new ArrayList<>(Arrays.asList(white_king, white_queen));
        Board.white_player.setpieceList(white_list);
        assert CheckmateChecker.lookForCheckmate(Board.BLACK);
        assert !CheckmateChecker.lookForCheckmate(Board.WHITE);
        Board.clearBoard();
    }

    private static void test5(){
        King black_king = new King(0,7, Board.BLACK);
        King white_king = new King(1,5, Board.WHITE);
        Bishop white_bishop_1 = new Bishop(2,5, Board.WHITE);
        Bishop white_bishop_2 = new Bishop(2,6, Board.WHITE);

        Board.black_player = new Player(Board.BLACK);
        Board.white_player = new Player(Board.WHITE);
        Board.black_player.setKingXYCoords(0,5);
        ArrayList<Piece> black_list = new ArrayList<>(Arrays.asList(black_king));
        Board.black_player.setpieceList(black_list);
        Board.white_player.setKingXYCoords(1,5);
        ArrayList<Piece> white_list = new ArrayList<>(Arrays.asList(white_king, white_bishop_1, white_bishop_2));
        Board.white_player.setpieceList(white_list);
        assert CheckmateChecker.lookForCheckmate(Board.BLACK);
        assert !CheckmateChecker.lookForCheckmate(Board.WHITE);
        Board.clearBoard();
    }

    private static void test6()
    {
        King black_king = new King(6,7, Board.BLACK);
        King white_king = new King(1,5, Board.WHITE);
        Rook white_rook = new Rook(4, 7, Board.WHITE);
        Pawn blakc_pawn_1 = new Pawn(5,6,Board.BLACK);
        Pawn blakc_pawn_2 = new Pawn(6,6,Board.BLACK);
        Pawn blakc_pawn_3 = new Pawn(7,6,Board.BLACK);
        Board.black_player = new Player(Board.BLACK);
        Board.white_player = new Player(Board.WHITE);
        Board.black_player.setKingXYCoords(6,7);
        ArrayList<Piece> black_list = new ArrayList<>(Arrays.asList(black_king, blakc_pawn_1, blakc_pawn_2, blakc_pawn_3));
        Board.black_player.setpieceList(black_list);
        Board.white_player.setKingXYCoords(1,5);
        ArrayList<Piece> white_list = new ArrayList<>(Arrays.asList(white_king, white_rook));
        Board.white_player.setpieceList(white_list);
        assert CheckmateChecker.lookForCheckmate(Board.BLACK);
        assert !CheckmateChecker.lookForCheckmate(Board.WHITE);
        Board.clearBoard();
    }
    public static void main(String[] args){
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }
}
