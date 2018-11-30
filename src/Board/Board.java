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
    public static Player white_player = new Player(WHITE);
    public static Player black_player = new Player(BLACK);

    public static void clear_board(){
        for(int i = 0; i < Board.X_UPPER_BOUND * Board.Y_UPPER_BOUND ; i++){
            Board.board[i] = null;
        }
        white_player.setPiece_list(null);
        black_player.setPiece_list(null);
    }

    public static void board_init() {
        ArrayList<Piece> white_piece_list = new ArrayList<>();
        ArrayList<Piece> black_piece_list = new ArrayList<>();
        ArrayList<Piece> piece_array = PieceFactory.create_pieces();
        for(Piece p : piece_array)
        {
            board[p.getX_coord() + p.getY_coord() * X_UPPER_BOUND] = p;
            if(p.getColor())
            {
                black_piece_list.add(p);
            }
            else
            {
                white_piece_list.add(p);
            }
        }
        white_player.setPiece_list(white_piece_list);
        black_player.setPiece_list(black_piece_list);
    }

    public static void print_board() {
        int seperatorLen = 67;
        StringBuilder board_string = new StringBuilder();
        board_string.append(new String(new char [seperatorLen]).replace("\0","*"));
        board_string.append("\n");
        for(int y = Y_UPPER_BOUND - 1; y >= 0; y--)
        {
            board_string.append(y + 1).append(" ");
            board_string.append("*");
            for (int x = 0; x < X_UPPER_BOUND; x++)
            {
                Piece p = Board.board[x + y * X_UPPER_BOUND];
                if(p != null)
                {
                    board_string.append("  ").append(p).append(p.getColor()? "-B":"-W").append(" ").append((p.toString().length() == 1? " ":""));
                }
                else
                {
                    board_string.append("       ");
                }
                board_string.append("*");
            }
            board_string.append("\n").append((new String(new char [seperatorLen]).replace("\0","*"))).append("\n");
        }
        board_string.append("  ");
        for(char c = 'A'; c <= 'H';c++ )
        {
            board_string.append("*   ").append(c).append("   ");
        }
        board_string.append("*");
        System.out.println(board_string);
    }

}
