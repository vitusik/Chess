package Pieces;


import Board.Board;

public class Pawn extends Piece {


    public Pawn(int x, int y, boolean p) {
        super(x, y, p);
        allowedMoves.add(MoveType.DIAGONAL);
        allowedMoves.add(MoveType.VERTICAL);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean moveCheck(int xDestCoord, int yDestCoord) {
        /*
        white player can only go up, so his cur y coordinate is lower than the new one
        the black player is the exact opposite
        white player is boolean false, black is boolean true
         */
        boolean isValidMove = false;
        boolean isCurMoveValidDirection = (yDestCoord < this.getyCoord() == this.getColor());
        MoveType moveType = moveTypeChecker(xDestCoord, yDestCoord);
        if(!isCurMoveValidDirection)
        {
            return false;
        }
        switch (moveType){
            case VERTICAL:
                // a vertical move is a non attacking move
                if(Board.getPiece(xDestCoord, yDestCoord) == null)
                {
                    // only from the starting position a pawn can make a vertical 2 step move
                    if(this.isInStartingPosition() && Math.abs(this.getyCoord() - yDestCoord) == 2)
                    {
                        // pawn cannot jump over another piece when making a 2 step move, so we need to check
                        // that the cell between the cur position and the final position is empty
                        int yDirection = this.getColor()? -1:1;
                        if(Board.getPiece(xDestCoord, yDestCoord + yDirection) == null)
                        {
                            this.setInStartingPosition(false);
                            isValidMove = true;
                            break;
                        }
                        else return false;
                    }

                    if(Math.abs(this.getyCoord() - yDestCoord) == 1)
                    {
                        if(this.isInStartingPosition())
                        {
                            this.setInStartingPosition(false);
                        }
                        isValidMove = true;
                        break;
                    }
                    else return false;
                }
                else return false;
                
            case DIAGONAL:
                // a diagonal move is an attacking only move
                Piece attackedPiece = Board.getPiece(xDestCoord, yDestCoord);
                if(attackedPiece != null && attackedPiece.getColor() != this.getColor())
                {
                    if (Math.abs(this.getyCoord() - yDestCoord) == 1 && Math.abs(this.getxCoord() - xDestCoord) == 1)
                    {
                        if(this.isInStartingPosition())
                        {
                            this.setInStartingPosition(false);
                        }
                        isValidMove = true;
                        break;
                    }
                    else return false;
                }
                else return false;
                
            default:
                return false;
        }
        return isValidMove;
    }
}
