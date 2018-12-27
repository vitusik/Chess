import Pieces.*;
import Board.*;

import java.util.ArrayList;
import java.util.Arrays;

public class KingTest {
    /*
            tests:
            1. simple move in any direction, init king at the row of the paws
            2. simple move that ends with a check
            3. simple move by another piece that leads to a check on its own king
            4. castling in both sides
            5. castling when the way is threatened
         */

    private static void setup() {
        King black_king = new King(4,7, Board.BLACK);
        King white_king = new King(4,0, Board.WHITE);
        Rook rook_b_1 = new Rook(0,7, Board.BLACK);
        Rook rook_b_2 = new Rook(7,7, Board.BLACK);
        Rook rook_w_1 = new Rook(0,0, Board.WHITE);
        Rook rook_w_2 = new Rook(7,0, Board.WHITE);

        Board.black_player = new Player(Board.BLACK);
        Board.white_player = new Player(Board.WHITE);
        Board.black_player.setKingXYCoords(4,7);
        ArrayList<Piece> black_list = new ArrayList<>(Arrays.asList(black_king, rook_b_1, rook_b_2));
        Board.black_player.setpieceList(black_list);
        Board.white_player.setKingXYCoords(4,0);
        ArrayList<Piece> white_list = new ArrayList<>(Arrays.asList(white_king, rook_w_1, rook_w_2));
        Board.white_player.setpieceList(white_list);


    }

    private static void test1() {
        setup();
        Piece king = Board.getPiece(4 ,0);
        int old_x = king.getxCoord();
        int old_y = king.getyCoord();
        king.makeMove(4,1);
        assert Board.board[king.getxCoord() + Board.X_UPPER_BOUND * king.getyCoord()] == king;
        assert Board.board[old_x + Board.X_UPPER_BOUND * old_y] == null;
        assert Board.white_player.getkingXCoord() == king.getxCoord();
        assert Board.white_player.getkingYCoord() == king.getyCoord();
        king = Board.getPiece(4,7);
        old_x = king.getxCoord();
        old_y = king.getyCoord();
        king.makeMove(4,6);
        assert Board.board[king.getxCoord() + Board.X_UPPER_BOUND * king.getyCoord()] == king;
        assert Board.board[old_x + Board.X_UPPER_BOUND * old_y] == null;
        assert Board.black_player.getkingXCoord() == king.getxCoord();
        assert Board.black_player.getkingYCoord() == king.getyCoord();
        Board.clearBoard();
    }

    private static void test2(){
        setup();
        Board.getPiece(0,7).setXYcoord(4,4);
        Piece white_king = Board.getPiece(4,0);
        white_king.makeMove(4,1);
        assert Board.board[4] == white_king;
        assert Board.white_player.getkingXCoord() == 4;
        assert Board.white_player.getkingYCoord() == 0;
        assert Board.board[4 + 1 * Board.X_UPPER_BOUND] == null;
        Board.clearBoard();
    }

    private static void test3() {
        setup();
        Board.getPiece(0,7).setXYcoord(4,4);
        Board.getPiece(0,0).setXYcoord(4, 2);
        Piece white_rook = Board.getPiece(4,2);
        white_rook.makeMove(0,2);
        assert white_rook.getxCoord() == 4;
        assert Board.getPiece(0,2) == null;
        assert Board.getPiece(4,2) != null;
        Board.clearBoard();
    }

    private static void test4(){
        setup();
        Piece white_king = Board.getPiece(4,0);
        Piece white_rook = Board.getPiece(0,0);
        white_king.makeMove(2,0);
        assert white_king.getxCoord() == 2;
        assert white_rook.getxCoord() == 3;
        Board.clearBoard();
        setup();
        white_king = Board.getPiece(4,0);
        white_rook = Board.getPiece(7,0);
        white_king.makeMove(6,0);
        assert white_king.getxCoord() == 6;
        assert white_rook.getxCoord() == 5;
        Board.clearBoard();

        setup();
        Piece black_king = Board.getPiece(4,7);
        Piece black_rook = Board.getPiece(0,7);
        black_king.makeMove(2,7);
        assert black_king.getxCoord() == 2;
        assert black_rook.getxCoord() == 3;
        Board.clearBoard();
        setup();
        black_king = Board.getPiece(4,7);
        black_rook = Board.getPiece(7,7);
        black_king.makeMove(6,7);
        assert black_king.getxCoord() == 6;
        assert black_rook.getxCoord() == 5;
        Board.clearBoard();
    }

    private static void test5()
    {
        setup();
        Board.getPiece(0,7).setXYcoord(3,7);
        Piece white_king = Board.getPiece(4,0);
        Piece white_rook = Board.getPiece(0,0);
        white_king.makeMove(2,0);
        assert white_king.getxCoord() == 4;
        assert white_rook.getxCoord() == 0;
    }
    public static void main(String[] args)
    {
        test1();
        test2();
        test3();
        test4();
        test5();
    }
}
