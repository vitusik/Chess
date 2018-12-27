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

    /**
     * Method which clears the board array, and clears the players pieces lists
     */
    public static void clearBoard(){
        for(int i = 0; i < Board.X_UPPER_BOUND * Board.Y_UPPER_BOUND ; i++){
            Board.board[i] = null;
        }
        white_player.setpieceList(null);
        black_player.setpieceList(null);
    }

    /**
     * Method which initializes the board object, including creating all the pieces and inserting them in the correct 
     * piece list, and placing the pieces on the board
     */
    public static void boardInit() {
        ArrayList<Piece> white_piece_list = new ArrayList<>();
        ArrayList<Piece> black_piece_list = new ArrayList<>();
        ArrayList<Piece> piece_array = PieceFactory.createPieces();
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
        white_player.setpieceList(white_piece_list);
        white_player.setKingXYCoords(PieceFactory.KING_INITIAL_X, PieceFactory.INITAL_Y_COORD_WHITE_PLAYER_OTHER);
        black_player.setpieceList(black_piece_list);
        black_player.setKingXYCoords(PieceFactory.KING_INITIAL_X, PieceFactory.INITAL_Y_COORD_WHITE_PLAYER_OTHER + PieceFactory.DIST_BETWEEN_PIECES);
    }

    /**
     * Method which prints the board's current state onto the console
     */
    public static void printBoard() {
        int separatorLen = 67;
        StringBuilder boardString = new StringBuilder();
        boardString.append(new String(new char [separatorLen]).replace("\0","*"));
        boardString.append("\n");
        for(int y = Y_UPPER_BOUND - 1; y >= 0; y--)
        {
            boardString.append(y + 1).append(" ");
            boardString.append("*");
            for (int x = 0; x < X_UPPER_BOUND; x++)
            {
                Piece p = Board.board[x + y * X_UPPER_BOUND];
                if(p != null)
                {
                    boardString.append("  ").append(p).append(p.getColor()? "-B":"-W").append(" ").append((p.toString().length() == 1? " ":""));
                }
                else
                {
                    boardString.append("       ");
                }
                boardString.append("*");
            }
            boardString.append("\n").append((new String(new char [separatorLen]).replace("\0","*"))).append("\n");
        }
        boardString.append("  ");
        for(char c = 'A'; c <= 'H';c++ )
        {
            boardString.append("*   ").append(c).append("   ");
        }
        boardString.append("*\n");
        System.out.println(boardString);
    }

    /**
     * Method which receives xy coordinates and returns the Piece that is in that xy coordinate 
     * @param x the x coordinate of the piece 
     * @param y the y coordinate of the piece
     * @return Piece that is in that xy coordinate, Null in case the xy coordinate doesn't hold a Piece object
     */
    public static Piece getPiece(int x, int y)
    {
        return Board.board[x + X_UPPER_BOUND * y];
    }

    /**
     * method which checks if the given (x,y) coordinate is within the bounds of the board
     * @param xCoord the x coordinate
     * @param yCoord the y coordinate
     * @return true if the coordinate is in bounds
     */
    public static boolean boundCheck(int xCoord, int yCoord) {
        // the x,y coordinates ranges between 0 and 7 in the board array and because of that they need to be adjusted
        return (xCoord + 1 <= Board.X_UPPER_BOUND && xCoord + 1 >= Board.X_LOWER_BOUND &&
                yCoord + 1 <= Board.Y_UPPER_BOUND && yCoord + 1 >= Board.Y_LOWER_BOUND);
    }

    /**
     * Method that returns the Player that corresponds to the right color
     * @param color the color of the player 
     * @return Player
     */
    public static Player getPlayer(boolean color){
        if(color == BLACK) return black_player;
        return white_player;
    }

    /**
     * Method that checks if a xy coordinate is under threat from other pieces 
     * @param x the x coordinate
     * @param y the y coordinate
     * @param listOfThreats list of pieces which could possibly threat the desired xy coordinate
     * @return true if the given xy coordinate is threatened by at least one piece from the threats list
     */
    public static boolean isUnderThreat(int x, int y, ArrayList<Piece> listOfThreats){
        if (listOfThreats == null) return false;
        for(Piece piece : listOfThreats)
        {
            if(piece.moveCheck(x, y)) return true;
        }
        return false;
    }
}
