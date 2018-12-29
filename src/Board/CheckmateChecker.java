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
     * @param player the player we wish to check
     * @return true in case there is a checkmate in the game
     */
    public static boolean lookForCheckmate(Player player)
    {
        // this method is basically a brute force search
        ArrayList<Piece> curPlayerListOfPieces = player.getpieceList();
        for(Piece p : curPlayerListOfPieces){
            for(MoveType move: p.getAllowedMoves()){
                switch (move){
                    case VERTICAL:
                        if(!vertCheck(p, UP) || !vertCheck(p, DOWN)) return true;
                        break;
                    case HORIZONTAL:
                        if(!horizontalCheck(p, RIGHT) || !horizontalCheck(p, LEFT)) return true;
                        break;
                    case DIAGONAL:
                        if(!diagonoalCheck(p, RIGHT, UP) || !diagonoalCheck(p, LEFT, UP) ||
                            !diagonoalCheck(p, RIGHT, DOWN) || !diagonoalCheck(p, LEFT, DOWN)) return true;
                        break;
                    case KNIGHT:
                        if (!knigtCheck(p)) return true;
                        break;
                }
            }
        }
        return false;
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
        int yBound = direction? Board.Y_UPPER_BOUND: Board.Y_LOWER_BOUND;
        for(int i = current.getyCoord() + step; i != yBound; i += yBound){
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
        int xBound = direction? Board.X_UPPER_BOUND: Board.X_LOWER_BOUND;
        for(int i = current.getxCoord() + step; i != xBound; i += xBound){
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
        int xBound = xDirection? Board.X_UPPER_BOUND: Board.X_LOWER_BOUND;
        int yBound = yDirection? Board.Y_UPPER_BOUND: Board.Y_LOWER_BOUND;
        int i = initialXCoord + xStep;
        int j = initialYCoord + yStep;
        while(i != xBound && j != yBound){
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
        int[] xCoords = {initialXCoord - 2, initialXCoord - 2, initialXCoord - 1, initialXCoord - 1,
                         initialXCoord + 1, initialXCoord + 1, initialXCoord + 2, initialXCoord + 2};
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