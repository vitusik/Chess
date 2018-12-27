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
    public static ArrayList<Piece> createPieces()
    {
        ArrayList<Piece> pieceList = new ArrayList<>();
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
            Piece whitePawn = new Pawn(i, INITAL_Y_COORD_WHITE_PLAYER_PAWN, Board.WHITE);
            Piece blackPawn = new Pawn(i, INITAL_Y_COORD_WHITE_PLAYER_PAWN + DIST_BETWEEN_PAWS, Board.BLACK);
            pieceList.add(whitePawn);
            pieceList.add(blackPawn);
        }

        for(int i = 0; i < 2; i++)
        {
            Piece whiteRook = new Rook(i * Board.X_UPPER_BOUND - i, INITAL_Y_COORD_WHITE_PLAYER_OTHER, Board.WHITE);
            Piece whiteKnight = new Knight(i * Board.X_UPPER_BOUND - 3 * i + 1, INITAL_Y_COORD_WHITE_PLAYER_OTHER, Board.WHITE);
            Piece whiteBishop = new Bishop(i * Board.X_UPPER_BOUND - 5 * i + 2, INITAL_Y_COORD_WHITE_PLAYER_OTHER, Board.WHITE);
            Piece blackRook = new Rook(i * Board.X_UPPER_BOUND - i, INITAL_Y_COORD_WHITE_PLAYER_OTHER + DIST_BETWEEN_PIECES, Board.BLACK);
            Piece blackKnight = new Knight(i * Board.X_UPPER_BOUND - 3 * i + 1, INITAL_Y_COORD_WHITE_PLAYER_OTHER + DIST_BETWEEN_PIECES, Board.BLACK);
            Piece blackBishop = new Bishop(i * Board.X_UPPER_BOUND - 5 * i + 2, INITAL_Y_COORD_WHITE_PLAYER_OTHER + DIST_BETWEEN_PIECES, Board.BLACK);
            pieceList.addAll(Arrays.asList(whiteRook, whiteKnight, whiteBishop,blackRook,blackKnight,blackBishop));
        }
        Piece whiteKing = new King(KING_INITIAL_X, INITAL_Y_COORD_WHITE_PLAYER_OTHER, Board.WHITE);
        Piece blackKing = new King(KING_INITIAL_X, INITAL_Y_COORD_WHITE_PLAYER_OTHER + DIST_BETWEEN_PIECES, Board.BLACK);
        Piece whiteQueen = new Queen(QUEEN_INITIAL_X, INITAL_Y_COORD_WHITE_PLAYER_OTHER, Board.WHITE);
        Piece blackQueen = new Queen(QUEEN_INITIAL_X, INITAL_Y_COORD_WHITE_PLAYER_OTHER + DIST_BETWEEN_PIECES, Board.BLACK);
        pieceList.addAll(Arrays.asList(whiteKing, whiteQueen, blackKing, blackQueen));
        return pieceList;
    }
}
