package Board;

import Pieces.*;
import java.util.ArrayList;


public class Board {
    public static final int X_UPPER_BOUND = 8;
    public static final int Y_UPPER_BOUND = 8;
    public static final int X_LOWER_BOUND = 1;
    public static final int Y_LOWER_BOUND = 1;

    public static final boolean WHITE = false;
    public static final boolean BLACK = true;

    public static Piece[] board = new Piece[X_UPPER_BOUND * Y_UPPER_BOUND];

    public static void clear_board(){
        for(int i = 0; i < Board.X_UPPER_BOUND * Board.Y_UPPER_BOUND ; i++){
            Board.board[i] = null;
        }
    }

    public static void board_init()
    {
        ArrayList<Piece> piece_array = PieceFactory.create_pieces();
        for(Piece p : piece_array)
        {
            board[p.getX_coord() + p.getY_coord() * X_UPPER_BOUND] = p;
        }
    }

    @Override
    public String toString() {
        String board_string = "";
        board_string = board_string + (new String(new char [49]).replace("\0","*"));
        board_string = board_string + "\n";
        for(int y = 0; y < Y_UPPER_BOUND; y++)
        {
            board_string = board_string + "*";
            for (int x = 0; x < X_UPPER_BOUND; x++)
            {
                Piece p = Board.board[x + y * X_UPPER_BOUND];
                if(p != null)
                {
                    board_string = board_string + "  "+ p + " " + (p.toString().length() == 1? " ":"");
                }
                else
                {
                    board_string = board_string + "     ";
                }
                board_string = board_string + "*";
            }
            board_string = board_string + "\n" + (new String(new char [49]).replace("\0","*")) + "\n";
        }
        return board_string;
    }
}
