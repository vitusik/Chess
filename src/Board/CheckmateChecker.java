package Board;

import Pieces.MoveType;
import Pieces.Piece;

import java.util.ArrayList;

public class CheckmateChecker {
    private static final boolean UP = true;
    private static final boolean DOWN = false;
    private static final boolean RIGHT = true;
    private static final boolean LEFT = false;

    /**
     * Method that checks if a player can continue playing or that he's lost
     * @param color the color of the player we wish to check
     * @return true in case there is a checkmate in the game
     */
    public static boolean lookForCheckmate(boolean color)
    {
        // this method is basically a brute force search
        Player player = Board.getPlayer(color);
        ArrayList<Piece> curPlayerListOfPieces = player.getpieceList();
        for(Piece p : curPlayerListOfPieces){
            if(p.getxCoord() == player.getkingXCoord() && p.getyCoord() == player.getkingYCoord()) {
                // go over around the king and checks all the tiles
                int initialXCoord = p.getxCoord();
                int initialYCoord = p.getyCoord();
                int[] xCoords = {initialXCoord - 1, initialXCoord - 1, initialXCoord - 1, initialXCoord, initialXCoord,
                                 initialXCoord + 1, initialXCoord + 1, initialXCoord + 1};
                int[] yCoords = {initialYCoord - 1, initialYCoord, initialYCoord + 1, initialYCoord - 1,
                        initialYCoord + 1, initialYCoord - 1, initialYCoord, initialYCoord + 1};
                ArrayList<Piece> threats = Board.getPlayer(!color).getpieceList();
                // this is done in order to avoid a situation where the current king's coordinates blocking the new ones
                Board.board[initialXCoord + Board.X_UPPER_BOUND * initialYCoord] = null;
                for(int i = 0; i < xCoords.length; i++)
                {
                    if(Board.boundCheck(xCoords[i], yCoords[i]))
                    {
                        if(Board.getPiece(xCoords[i],yCoords[i]) == null)
                        {
                            if(!Board.isUnderThreat(xCoords[i], yCoords[i], threats)){
                                Board.board[initialXCoord + Board.X_UPPER_BOUND * initialYCoord] = p;
                                return false;
                            }
                        }
                        else {
                            if(Board.getPiece(xCoords[i],yCoords[i]).getColor() != color){
                                Piece temp = Board.getPiece(xCoords[i],yCoords[i]);
                                // done in order to check if an enemy's piece is defended by another piece
                                Board.board[xCoords[i] + Board.X_UPPER_BOUND * yCoords[i]] = null;
                                if(!Board.isUnderThreat(xCoords[i], yCoords[i], threats)){
                                    Board.board[initialXCoord + Board.X_UPPER_BOUND * initialYCoord] = temp;
                                    return false;
                                }
                                Board.board[initialXCoord + Board.X_UPPER_BOUND * initialYCoord] = temp;
                            }
                        }
                    }
                }
                Board.board[initialXCoord + Board.X_UPPER_BOUND * initialYCoord] = p;
            }
            else{
                for(MoveType move: p.getAllowedMoves()){
                    switch (move){
                        case VERTICAL:
                            if(vertCheck(p, UP) || vertCheck(p, DOWN)) return false;
                            break;
                        case HORIZONTAL:
                            if(horizontalCheck(p, RIGHT) || horizontalCheck(p, LEFT)) return false;
                            break;
                        case DIAGONAL:
                            if(diagonoalCheck(p, RIGHT, UP) || diagonoalCheck(p, LEFT, UP) ||
                                    diagonoalCheck(p, RIGHT, DOWN) || diagonoalCheck(p, LEFT, DOWN)) return false;
                            break;
                        case KNIGHT:
                            if (knigtCheck(p)) return false;
                            break;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Helper method which checks if a piece has at least one legal move in the vertical direction
     * @param current the piece on which the check is made
     * @param direction boolean parameter, true is UP, false is DOWN
     * @return true if there is a legal move that the current piece can make
     */
    private static boolean vertCheck(Piece current, boolean direction){
        int initialYCoord = current.getyCoord();
        int step = direction? 1: -1;
        int i = current.getyCoord() + step;
        while(i < Board.Y_UPPER_BOUND && i >= Board.Y_LOWER_BOUND - 1){
            Piece potentialPiece = Board.getPiece(current.getxCoord(), i);
            if(potentialPiece == null)
            {
                if(current.makeMove(current.getxCoord(), i)){
                    current.setXYcoord(current.getxCoord(), initialYCoord);
                    return true;
                }
            }
            else{
                if(potentialPiece.getColor() != current.getColor())
                {
                    if(current.makeMove(current.getxCoord(), i)){
                        current.setXYcoord(current.getxCoord(), initialYCoord);
                        potentialPiece.setXYcoord(current.getxCoord(), i);
                        Board.getPlayer(potentialPiece.getColor()).getpieceList().add(potentialPiece);
                        return true;
                    }
                }
                // if there is a piece in the tile, there is no need to check another one, since its blocked
                return false;
            }
            i += step;
        }
        return false;
    }

    /**
     * Helper method which checks if a piece has at least one legal move in the horizontal direction
     * @param current the piece on which the check is made
     * @param direction boolean parameter, true is RIGHT, false is LEFT
     * @return true if there is a legal move that the current piece can make
     */
    private static boolean horizontalCheck(Piece current, boolean direction){
        int initialXCoord = current.getxCoord();
        int step = direction? 1: -1;
        int i = current.getxCoord() + step;
        while(i < Board.X_UPPER_BOUND && i >= Board.X_LOWER_BOUND - 1){
            Piece potentialPiece = Board.getPiece(i, current.getyCoord());
            if(potentialPiece == null)
            {
                if(current.makeMove(i, current.getyCoord())){
                    current.setXYcoord(initialXCoord, current.getyCoord());
                    return true;
                }
            }
            else{
                if(potentialPiece.getColor() != current.getColor())
                {
                    if(current.makeMove(i, current.getyCoord())){
                        current.setXYcoord(initialXCoord, current.getyCoord());
                        potentialPiece.setXYcoord(i, current.getyCoord());
                        Board.getPlayer(potentialPiece.getColor()).getpieceList().add(potentialPiece);
                        return true;
                    }
                }
                // if there is a piece in the tile, there is no need to check another one, since its blocked
                return false;
            }
            i += step;
        }
        return false;
    }

    /**
     * Helper method which checks if a piece has at least one legal move in the diagonal direction
     * @param current the piece on which the check is made
     * @param xDirection boolean parameter, true is RIGHT, false is LEFT
     * @param yDirection boolean parameter, true is UP, false is DOWN
     * @return true if there is a legal move that the current piece can make
     */
    private static boolean diagonoalCheck(Piece current, boolean xDirection, boolean yDirection){
        int initialXCoord = current.getxCoord();
        int initialYCoord = current.getyCoord();
        int xStep = xDirection? 1: -1;
        int yStep = yDirection? 1: -1;
        int i = initialXCoord + xStep;
        int j = initialYCoord + yStep;
        while((i < Board.X_UPPER_BOUND && i >= Board.X_LOWER_BOUND - 1) && (j < Board.Y_UPPER_BOUND && j >= Board.Y_LOWER_BOUND - 1)){
            Piece potentialPiece = Board.getPiece(i, j);
            if(potentialPiece == null)
            {
                if(current.makeMove(i, j)){
                    current.setXYcoord(initialXCoord, initialYCoord);
                    return true;
                }
            }
            else{
                if(potentialPiece.getColor() != current.getColor())
                {
                    if(current.makeMove(i, j)){
                        current.setXYcoord(initialXCoord, initialYCoord);
                        potentialPiece.setXYcoord(i, j);
                        Board.getPlayer(potentialPiece.getColor()).getpieceList().add(potentialPiece);
                        return true;
                    }
                }
                // if there is a piece in the tile, there is no need to check another one, since its blocked
                return false;
            }
            i = i + xStep;
            j = j + yStep;
        }
        return false;
    }

    /**
     * Helper method which checks if a piece at least one legal knight move to make
     * @param current the piece on which the check is made
     * @return true if there is a legal move that the current piece can make
     */
    private static boolean knigtCheck(Piece current){
        int initialXCoord = current.getxCoord();
        int initialYCoord = current.getyCoord();
        int[] xCoords = {initialXCoord - 2, initialXCoord - 2, initialXCoord - 2, initialXCoord - 2,
                         initialXCoord + 1, initialXCoord + 1, initialXCoord + 1, initialXCoord + 1};
        int[] yCoords = {initialYCoord - 2, initialYCoord + 2, initialYCoord - 1, initialYCoord + 1,
                         initialYCoord - 1, initialYCoord + 1, initialYCoord - 2, initialYCoord + 2};
        for(int i = 0; i < xCoords.length; i++)
        {
            if(Board.boundCheck(xCoords[i], yCoords[i])){
                Piece potentialPiece = Board.getPiece(xCoords[i], yCoords[i]);
                if(potentialPiece == null){
                    if(current.makeMove(xCoords[i], yCoords[i]))
                    {
                        current.setXYcoord(initialXCoord, initialYCoord);
                        return true;
                    }
                }
                else{
                    if(potentialPiece.getColor() != current.getColor()){
                        current.setXYcoord(initialXCoord, initialYCoord);
                        potentialPiece.setXYcoord(xCoords[i], yCoords[i]);
                        Board.getPlayer(potentialPiece.getColor()).getpieceList().add(potentialPiece);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
