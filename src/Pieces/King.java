package Pieces;

import Board.*;

public class King extends Piece {
    public King(int x, int y, boolean p) {
        super(x, y, p);
        allowedMoves.add(MoveType.HORIZONTAL);
        allowedMoves.add(MoveType.VERTICAL);
        allowedMoves.add(MoveType.DIAGONAL);
    }

    @Override
    public String toString() {
        return "Ki";
    }

    @Override
    public boolean moveCheck(int xDestCoord, int yDestCoord) {
        boolean isValidMove = false;
        if (Math.abs(this.getxCoord() - xDestCoord) <= 1 && Math.abs(this.getyCoord() - yDestCoord) <= 1) {
            if (Board.getPiece(xDestCoord, yDestCoord) == null) {
                this.setInStartingPosition(false);
                isValidMove = true;
            } else return false;
        }
        // a castling move
        if (this.getyCoord() == yDestCoord && (Math.abs(this.getxCoord() - xDestCoord) == 2)) {
            /*
            the king and the rook has'nt moved, there is no need to check if the other piece is a rook
            or if the piece is the same color of the king, because if it isn't than it has obliviously moved*/
            Player currentPlayer = Board.getPlayer(this.getColor());
            // cant castle if king is checked
            if (currentPlayer.isChecked()) return false;
            int step = (xDestCoord > this.getxCoord()) ? 1 : -1;
            int rookXCoord = step == 1 ? 7 : 0;
            if (this.isInStartingPosition() && Board.getPiece(rookXCoord, yDestCoord).isInStartingPosition()) {
                Player enemy = Board.getPlayer(!this.getColor());
                for (int i = this.getxCoord() + step; i != xDestCoord + step; i += step) {
                    // first part checks that the path to the rook is empty
                    // second part checks that the path isn't threatened by the enemy player
                    if (Board.getPiece(i, yDestCoord) != null || Board.isUnderThreat(i, yDestCoord, enemy.getpieceList())) {
                        return false;
                    }
                }
                Piece king = Board.getPiece(this.getxCoord(), this.getyCoord());
                Piece rook = Board.board[rookXCoord + Board.X_UPPER_BOUND * this.getyCoord()];
                king.setXYcoord(xDestCoord, yDestCoord);
                rook.setXYcoord(xDestCoord - step, rook.getyCoord());
                return true;
            }
        }
        return isValidMove;
    }
}
