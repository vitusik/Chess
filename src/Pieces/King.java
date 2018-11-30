package Pieces;

import Board.Board;

public class King extends Piece {
    public King(int x, int y, boolean p){
        super(x, y, p);
        allowed_moves.add(MoveType.HORIZONTAL);
        allowed_moves.add(MoveType.VERTICAL);
        allowed_moves.add(MoveType.DIAGONAL);
    }

    @Override
    public String toString() {
        return "Ki";
    }

    @Override
    public boolean move_check(int new_x_coord, int new_y_coord) {
        /*
            1. after each move check to see that the king is not threatened by an enemy piece
            2. castling
         */
        // The king is allowed to travel in any direction with a maximum distance of 1 from is current position
        if(Math.abs(this.getX_coord() - new_x_coord) <= 1 && Math.abs(this.getY_coord() - new_y_coord) <= 1)
        {
            if(Board.board[new_x_coord + Board.X_UPPER_BOUND * new_y_coord] == null)
            {
                this.setIn_starting_pos(false);
                return true;
            }
            return false;
        }
        return false;
    }


}
