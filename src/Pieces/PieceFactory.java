package Pieces;

import Board.Board;

import java.util.ArrayList;
import java.util.Arrays;

public class PieceFactory {
    private final static int INITAL_Y_COORD_WHITE_PLAYER_PAWN = 1;
    public final static int INITAL_Y_COORD_WHITE_PLAYER_OTHER = 0;
    private final static int DIST_BETWEEN_PAWS = 5;
    public final static int DIST_BETWEEN_PIECES = 7;
    private final static int QUEEN_INITIAL_X = 3;
    public final static int KING_INITIAL_X = 4;
    public static ArrayList<Piece> create_pieces()
    {
        ArrayList<Piece> piece_array = new ArrayList<>();
        /*
            each player has:
            8 Pawns
            2 Knights
            2 Bishops
            2 Rooks
            1 Queen
            1 king
         */

        for(int i = 0; i < Board.X_UPPER_BOUND; i += 1)
        {
            Piece white_pawn = new Pawn(i, INITAL_Y_COORD_WHITE_PLAYER_PAWN, Board.WHITE);
            Piece black_pawn = new Pawn(i, INITAL_Y_COORD_WHITE_PLAYER_PAWN + DIST_BETWEEN_PAWS, Board.BLACK);
            piece_array.add(white_pawn);
            piece_array.add(black_pawn);
        }

        for(int i = 0; i < 2; i++)
        {
            Piece white_rook = new Rook(i * Board.X_UPPER_BOUND - i, INITAL_Y_COORD_WHITE_PLAYER_OTHER, Board.WHITE);
            Piece white_knight = new Knight(i * Board.X_UPPER_BOUND - 3 * i + 1, INITAL_Y_COORD_WHITE_PLAYER_OTHER, Board.WHITE);
            Piece white_bishop = new Bishop(i * Board.X_UPPER_BOUND - 5 * i + 2, INITAL_Y_COORD_WHITE_PLAYER_OTHER, Board.WHITE);
            Piece black_rook = new Rook(i * Board.X_UPPER_BOUND - i, INITAL_Y_COORD_WHITE_PLAYER_OTHER + DIST_BETWEEN_PIECES, Board.BLACK);
            Piece black_knight = new Knight(i * Board.X_UPPER_BOUND - 3 * i + 1, INITAL_Y_COORD_WHITE_PLAYER_OTHER + DIST_BETWEEN_PIECES, Board.BLACK);
            Piece black_bishop = new Bishop(i * Board.X_UPPER_BOUND - 5 * i + 2, INITAL_Y_COORD_WHITE_PLAYER_OTHER + DIST_BETWEEN_PIECES, Board.BLACK);
            piece_array.addAll(Arrays.asList(white_rook, white_knight, white_bishop,black_rook,black_knight,black_bishop));
        }
        Piece white_king = new King(KING_INITIAL_X, INITAL_Y_COORD_WHITE_PLAYER_OTHER, Board.WHITE);
        Piece black_king = new King(KING_INITIAL_X, INITAL_Y_COORD_WHITE_PLAYER_OTHER + DIST_BETWEEN_PIECES, Board.BLACK);
        Piece white_queen = new Queen(QUEEN_INITIAL_X, INITAL_Y_COORD_WHITE_PLAYER_OTHER, Board.WHITE);
        Piece black_queen = new Queen(QUEEN_INITIAL_X, INITAL_Y_COORD_WHITE_PLAYER_OTHER + DIST_BETWEEN_PIECES, Board.BLACK);
        piece_array.addAll(Arrays.asList(white_king, white_queen, black_king, black_queen));
        return piece_array;
    }
}
