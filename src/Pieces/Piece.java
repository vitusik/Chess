package Pieces;
import Board.*;

import java.util.ArrayList;


public abstract class Piece {

    private int xCoord;
    private int yCoord;
    private boolean color;
    private boolean inStartingPosition;
    ArrayList<MoveType> allowedMoves;

    @Override
    public abstract String toString();

    /**
     * Piece Class constructor
     * @param x the column of the piece on the chess board receives values of 0-7
     * @param y the row of the piece on the chess board receives values of 0-7
     * @param c boolean parameter which represents the color of the player true for black player, false for white
     */
    public Piece(int x, int y, boolean c) {
        xCoord = x;
        yCoord = y;
        color = c;
        allowedMoves = new ArrayList<>();
        inStartingPosition = true;
        Board.board[x + Board.X_UPPER_BOUND * y] = this;
    }
    
    /**
     * Getter for the x coordinate of the piece 
     * @return the x coordinate of the piece
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     * Setter for the x coordinate of the piece 
     * @param xCoord the new x coordinate of the piece
     */
    private void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }
    
    /**
     * Getter for the y coordinate of the piece 
     * @return the y coordinate of the piece 
     */
    public int getyCoord() {
        return yCoord;
    }
    
    /**
     * Setter for the y coordinate of the piece 
     * @param yCoord the new y coordinate of the piece
     */
    private void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    /** Method which updates the XY coordinates on a piece and updates the board inside the Board class.
     * 
     * @param x the new x coordinate
     * @param y the new y coordinate
     */
    public void setXYcoord(int x, int y) {
        Board.board[this.xCoord + Board.X_UPPER_BOUND * this.yCoord] = null;
        this.setxCoord(x);
        this.setyCoord(y);
        Board.board[this.xCoord + Board.X_UPPER_BOUND * this.yCoord] = this;
    }
    
    /**
     * Getter for the color of the player
     * @return a boolean value which represents the color of the player
     */
    public boolean getColor() {
        return color;
    }

    /**
     * Getter for the boolean value which indicates in the piece is in it's starting position
     * @return boolean value which indicates in the piece is in it's starting position
     */
    boolean isInStartingPosition() {
        return inStartingPosition;
    }

    /**
     * Setter for the boolean value which indicates in the piece is in it's starting position
     * @param inStartingPosition boolean value which indicates in the piece is in it's starting position
     */
    void setInStartingPosition(boolean inStartingPosition) {
        this.inStartingPosition = inStartingPosition;
    }

    /**
     * method which takes new (x,y) position and returns the type of move that can be made from the current position
     * to get to the new position
     * @param destXCoord the new x coordinate of the piece
     * @param destYCoord the new y coordinate of the piece
     * @return returns a MoveType enum member, which represents horizontal, vertical, diagonal or knight move, or an
     * illegal move
     */
    public MoveType moveTypeChecker(int destXCoord, int destYCoord) {
        if (this.xCoord != destXCoord && this.yCoord == destYCoord) {
            return MoveType.HORIZONTAL;
        }
        if (this.xCoord == destXCoord && this.yCoord != destYCoord) {
            return MoveType.VERTICAL;
        }
        if (this.xCoord != destXCoord && this.yCoord != destYCoord)
        {
            if (Math.abs(destXCoord - this.xCoord) == Math.abs(destYCoord - this.yCoord)) {
                return MoveType.DIAGONAL;
            }
            // knight move creates a circle around the knight with the radius of 3, excluding horizontal, vertical and
            // diagonal positions on the circle relative to the knight
            // at this point of execution horizontal, vertical and vertical moves have been excluded
            if(Math.abs(destXCoord - this.xCoord) + Math.abs(destYCoord - this.yCoord) == 3) {
                return MoveType.KNIGHT;
            }
        }
        return MoveType.NO_MOVE;
    }

    /**
     * method which checks validity of a vertical move which ends at (current x coordinate, destYCoord)
     * @param destYCoord the end y coordinate of the vertical move
     * @return true if the move is legal, meaning there aren't any blocking pieces in the way, and that at the end
     * position there is an enemy piece or empty cell
     */
    private boolean verticalMoveCheck(int destYCoord) {
        int step = (destYCoord > this.yCoord)? 1 : -1;
        for (int i = this.yCoord + step ; i != destYCoord; i += step)
        {
            if (Board.getPiece(this.xCoord, i) != null)
            {
                return false;
            }
        }
        if (Board.getPiece(this.xCoord, destYCoord) == null) {
            return true;
        } else
        {
            Piece temp = Board.getPiece(this.xCoord, destYCoord);
            return temp.getColor() != this.getColor();
        }
    }

    /**
     * method which checks validity of a horizontal move which ends at (destXCoord, current y coordinate)
     * @param destXCoord the end x coordinate of the horizontal move
     * @return true if the move is legal, meaning there aren't any blocking pieces in the way, and that at the end
     * position there is an enemy piece or empty cell
     */
    private boolean horizontalMoveCheck(int destXCoord) {
        int step = (destXCoord > this.xCoord)? 1 : -1;
        for (int i = this.xCoord + step ; i != destXCoord; i += step)
        {
            if (Board.getPiece(i, this.yCoord) != null)
            {
                return false;
            }
        }
        if (Board.getPiece(destXCoord, this.yCoord) == null) {
            return true;
        } else
        {
            Piece temp = Board.getPiece(destXCoord, this.yCoord);
            return temp.getColor() != this.getColor();
        }
    }

    /**
     * method which checks validity of a diagonal move which ends at (destXCoord, destYCoord)
     * @param destXCoord the end x coordinate of the diagonal move
     * @param destYCoord the end y coordinate of the diagonal move
     * @return true if the move is legal, meaning there aren't any blocking pieces in the way, and that at the end
     * position there is an enemy piece or empty cell
     */
    private boolean diagonalMoveCheck(int destXCoord, int destYCoord) {
        int step_x = (destXCoord > this.xCoord)? 1 : -1;
        int step_y = (destYCoord > this.yCoord)? 1 : -1;
        int j = this.yCoord + step_y;
        for (int i = this.xCoord + step_x ; i != destXCoord; i += step_x)
        {
            if (Board.getPiece(i, j) != null)
            {
                return false;
            }
            j += step_y;
        }
        if (Board.getPiece(destXCoord, destYCoord) == null) {
            return true;
        } else
        {
            Piece temp = Board.getPiece(destXCoord, destYCoord);
            return temp.getColor() != this.getColor();
        }
    }

    /**
     * method which checks the validity of a knight type move
     * @param destXCoord the end x coordinate of the diagonal move
     * @param destYCoord the end y coordinate of the diagonal move
     * @return true if there is an enemy piece at the end position or an empty cell
     */
    private boolean knighMoveCheck(int destXCoord, int destYCoord) {
        return this.getColor() != Board.getPiece(destXCoord, destYCoord).getColor();
    }

    /**
     * Method that moves the calling piece to the destination coordinates, and then checks if the calling piece's king
     * piece is under threat from enemy piece, if so it undoes the change.
     * @param destXCoord the destination's x coordinate of the calling piece 
     * @param destYCoord the destination's y coordinate of the calling piece
     * @param kingXCoord the x coordinate of the calling piece's king 
     * @param kingYCoord the y coordinate of the calling piece's king 
     * @return true in case the move is successful, false in case the calling piece's king is under threat 
     */
    private boolean movePieceOnBoard(int destXCoord, int destYCoord, int kingXCoord, int kingYCoord){
        Piece pieceInDestCoords = Board.getPiece(destXCoord, destYCoord);
        // in case there is no piece in the destination, we keep a boolean flag
        boolean emptyTile = pieceInDestCoords == null;
        int currentX = this.getxCoord();
        int currentY = this.getyCoord();
        this.setXYcoord(destXCoord, destYCoord);
        ArrayList<Piece> threats = Board.getPlayer(!this.getColor()).getpieceList();
        if(Board.isUnderThreat(kingXCoord, kingYCoord, threats))
        {
            this.setXYcoord(currentX, currentY);
            if(!emptyTile) pieceInDestCoords.setXYcoord(destXCoord, destYCoord);
            return false;
        }
        if(!emptyTile)
        {
            // in case the destination coordinates aren't empty, there is a need to remove the reference to that piece
            // from the list of pieces of the enemy's list
            Player p = Board.getPlayer(!this.getColor());
            ArrayList<Piece> list_of_pieces = p.getpieceList();
            for(int i = 0; i < list_of_pieces.size(); i++)
            {
                if (list_of_pieces.get(i) == pieceInDestCoords)
                {
                    list_of_pieces.remove(i);
                    break;
                }
            }
        }
        return true;
    }

    /**
     * method which encapsulates all the previous methods and call the correct move validity check method
     * @param destXCoord the end x coordinate of the move
     * @param destYCoord the end y coordinate of the move
     * @return true if the move is legal, meaning the calling piece can make that type of move and that the path
     * of move is clear
     */
    public boolean moveCheck(int destXCoord, int destYCoord){
        MoveType curMoveType = this.moveTypeChecker(destXCoord, destYCoord);
        boolean isValidMove = false;
        if(!this.allowedMoves.contains(curMoveType))
        {
            return false;
        }
        switch (curMoveType){
            case HORIZONTAL:
                isValidMove = this.horizontalMoveCheck(destXCoord);
                break;
            case VERTICAL:
                isValidMove = this.verticalMoveCheck(destYCoord);
                break;
            case DIAGONAL:
                isValidMove = this.diagonalMoveCheck(destXCoord, destYCoord);
                break;
            case KNIGHT:
                isValidMove = this.knighMoveCheck(destXCoord, destYCoord);
                break;
            case NO_MOVE:
                break;
        }
        if (isValidMove && inStartingPosition) inStartingPosition = false;
        return isValidMove;
    }

    public boolean makeMove(int destXCoord, int destYCoord){
        boolean isValidMove = this.moveCheck(destXCoord, destYCoord);
        if(isValidMove)
        {
            int kingXCoord = Board.getPlayer(this.getColor()).getkingXCoord();
            int kingYCoord = Board.getPlayer(this.getColor()).getkingYCoord();
            boolean king_move = (this.getxCoord() == kingXCoord) && (this.getyCoord() == kingYCoord);
            if(king_move)
            {
                isValidMove = this.movePieceOnBoard(destXCoord, destYCoord, destXCoord, destYCoord);
                if(isValidMove)
                {
                    if (this.getColor()) Board.black_player.setKingXYCoords(destXCoord, destYCoord);
                    else Board.white_player.setKingXYCoords(destXCoord, destYCoord);
                }
            }
            else
            {
                isValidMove = this.movePieceOnBoard(destXCoord, destYCoord, kingXCoord, kingYCoord);
            }
        }
        return isValidMove;
    }
}
