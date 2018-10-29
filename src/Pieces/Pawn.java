package Pieces;


import Board.Board;

public class Pawn extends Piece {
    private boolean in_starting_pos;

    public Pawn(int x, int y, boolean p) {
        super(x, y, p);
        allowed_moves.add(MoveType.DIAGONAL);
        allowed_moves.add(MoveType.VERTICAL);
        in_starting_pos = true;
    }

    @Override
    public boolean move_check(int new_x_coord, int new_y_coord) {
        /*
        white player can only go up, so his cur y coordinate is higher than the new one 
        the black player is the exact opposite
        white player is boolean true, black is boolean false
         */ 
        boolean cur_move_valid_dir = (new_y_coord < this.getY_coord() == this.getPlayer());
        MoveType moveType = move_type_checker(new_x_coord, new_y_coord);
        if(!move_bound_check(new_x_coord,new_y_coord))
        {
            return false;
        }
        if(!cur_move_valid_dir)
        {
            return false;
        }
        switch (moveType){
            case VERTICAL:
                // a vertical move is a non attacking move
                if(Board.board[new_x_coord + Board.X_UPPER_BOUND * new_y_coord] == null)
                {
                    // only from the starting position a pawn can make a vertical 2 step move
                    if(vertical_move_check(new_y_coord) && this.in_starting_pos && Math.abs(this.getY_coord() - new_y_coord) == 2)
                    {
                        this.in_starting_pos = false;
                        return true;
                    }
                    if(vertical_move_check(new_y_coord) && Math.abs(this.getY_coord() - new_y_coord) == 1)
                    {
                        if(this.in_starting_pos)
                        {
                            this.in_starting_pos = false;
                        }
                        return true;
                    }
                    else return false;
                }
                else return false;
                
            case DIAGONAL:
                // a diagonal move is an attacking only move
                Piece attacked_piece = Board.board[new_x_coord + Board.X_UPPER_BOUND * new_y_coord];
                if(attacked_piece != null && attacked_piece.getPlayer() != this.getPlayer())
                {
                    if (Math.abs(this.getY_coord() - new_y_coord) == 1 && Math.abs(this.getX_coord() - new_x_coord) == 1)
                    {
                        if(this.in_starting_pos)
                        {
                            this.in_starting_pos = false;
                        }
                        return true;
                    }
                    else return false;
                }
                else return false;
                
            default:
                return false;
        }
    }
}
