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
        StringBuilder board_string = new StringBuilder();
        board_string.append(new String(new char [49]).replace("\0","*"));
        board_string.append("\n");
        for(int y = 0; y < Y_UPPER_BOUND; y++)
        {
            board_string.append("*");
            for (int x = 0; x < X_UPPER_BOUND; x++)
            {
                Piece p = Board.board[x + y * X_UPPER_BOUND];
                if(p != null)
                {
                    board_string.append("  ").append(p).append(" ").append((p.toString().length() == 1? " ":""));
                }
                else
                {
                    board_string.append("     ");
                }
                board_string.append("*");
            }
            board_string.append("\n").append((new String(new char [49]).replace("\0","*"))).append("\n");
        }
        return board_string.toString();
    }
}
