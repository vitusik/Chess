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
        Board.black_player.setKing_x_y_coord(4,7);
        ArrayList<Piece> black_list = new ArrayList<>(Arrays.asList(black_king, rook_b_1, rook_b_2));
        Board.black_player.setPiece_list(black_list);
        Board.white_player.setKing_x_y_coord(4,0);
        ArrayList<Piece> white_list = new ArrayList<>(Arrays.asList(white_king, rook_w_1, rook_w_2));
        Board.white_player.setPiece_list(white_list);


    }

    private static void test1() {
        setup();
        Piece king = Board.get_piece(4 ,0);
        int old_x = king.getX_coord();
        int old_y = king.getY_coord();
        king.move_check(4,1);
        assert Board.board[king.getX_coord() + Board.X_UPPER_BOUND * king.getY_coord()] == king;
        assert Board.board[old_x + Board.X_UPPER_BOUND * old_y] == null;
        assert Board.white_player.getKing_x_coord() == king.getX_coord();
        assert Board.white_player.getKing_y_coord() == king.getY_coord();
        king = Board.get_piece(4,7);
        old_x = king.getX_coord();
        old_y = king.getY_coord();
        king.move_check(4,6);
        assert Board.board[king.getX_coord() + Board.X_UPPER_BOUND * king.getY_coord()] == king;
        assert Board.board[old_x + Board.X_UPPER_BOUND * old_y] == null;
        assert Board.black_player.getKing_x_coord() == king.getX_coord();
        assert Board.black_player.getKing_y_coord() == king.getY_coord();
        Board.clear_board();
    }

    private static void test2(){
        setup();
        Board.get_piece(0,7).setXYcoord(4,4);
        Piece white_king = Board.get_piece(4,0);
        white_king.move_check(4,1);
        assert Board.board[4] == white_king;
        assert Board.white_player.getKing_x_coord() == 4;
        assert Board.white_player.getKing_y_coord() == 0;
        assert Board.board[4 + 1 * Board.X_UPPER_BOUND] == null;
        Board.clear_board();
    }

    private static void test3() {
        setup();
        Board.get_piece(0,7).setXYcoord(4,4);
        Board.get_piece(0,0).setXYcoord(4, 2);
        Piece white_rook = Board.get_piece(4,2);
        white_rook.move_check(0,2);
        assert white_rook.getX_coord() == 4;
        assert Board.get_piece(0,2) == null;
        assert Board.get_piece(4,2) != null;
        Board.clear_board();
    }

    private static void test4(){
        setup();
        Board.get_piece(0,0).setXYcoord(4,1);
        Board.get_piece(0,7).setXYcoord(4,6);
        Piece rook = Board.get_piece(4,1);
        rook.move_check(3,1);
        assert rook.getX_coord() == 4;
        Board.clear_board();
    }

    private static void test5(){
        setup();
        Piece white_king = Board.get_piece(4,0);
        Piece white_rook = Board.get_piece(0,0);
        white_king.move_check(2,0);
        assert white_king.getX_coord() == 2;
        assert white_rook.getX_coord() == 3;
        Board.clear_board();
        setup();
        white_king = Board.get_piece(4,0);
        white_rook = Board.get_piece(7,0);
        white_king.move_check(6,0);
        assert white_king.getX_coord() == 6;
        assert white_rook.getX_coord() == 5;
        Board.clear_board();

        setup();
        Piece black_king = Board.get_piece(4,7);
        Piece black_rook = Board.get_piece(0,7);
        black_king.move_check(2,7);
        assert black_king.getX_coord() == 2;
        assert black_rook.getX_coord() == 3;
        Board.clear_board();
        setup();
        black_king = Board.get_piece(4,7);
        black_rook = Board.get_piece(7,7);
        black_king.move_check(6,7);
        assert black_king.getX_coord() == 6;
        assert black_rook.getX_coord() == 5;
        Board.clear_board();
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
