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
        white_player.setKing_x_y_coord(PieceFactory.KING_INITIAL_X, PieceFactory.INITAL_Y_COORD_WHITE_PLAYER_OTHER);
        black_player.setPiece_list(black_piece_list);
        black_player.setKing_x_y_coord(PieceFactory.KING_INITIAL_X, PieceFactory.INITAL_Y_COORD_WHITE_PLAYER_OTHER + PieceFactory.DIST_BETWEEN_PIECES);
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
        board_string.append("*\n");
        System.out.println(board_string);
    }

    public static Piece get_piece(int x, int y)
    {
        return Board.board[x + X_UPPER_BOUND * y];
    }

    /**
     * method which checks if the given (x,y) coordinate is within the bounds of the board
     * @param x_coord the x coordinate
     * @param y_coord the y coordinate
     * @return true if the coordinate is in bounds
     */
    public static boolean bound_check(int x_coord, int y_coord) {
        // the x,y coordinates ranges between 0 and 7 in the board array and because of that they need to be adjusted
        return (x_coord + 1 <= Board.X_UPPER_BOUND && x_coord + 1 >= Board.X_LOWER_BOUND &&
                y_coord + 1 <= Board.Y_UPPER_BOUND && y_coord + 1 >= Board.Y_LOWER_BOUND);
    }

    public static Player get_player(boolean color){
        if(color == BLACK) return black_player;
        return white_player;
    }

    public static boolean is_under_threat(int x, int y, ArrayList<Piece> possible_threats){
        if (possible_threats == null) return false;
        Piece threatened = Board.get_piece(x, y);
        boolean empty_tile = threatened == null;
        for(Piece piece : possible_threats)
        {
            int cur_x = piece.getX_coord();
            int cur_y = piece.getY_coord();
            if(piece.move_check(x, y))
            {
                //if true than the attacking piece moves to the place of the threatened piece
                piece.setXYcoord(cur_x, cur_y);
                if(!empty_tile) threatened.setXYcoord(x, y);
                return true;
            }
        }
        return false;
    }
}
