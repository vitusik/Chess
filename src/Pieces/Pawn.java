package Pieces;


import Board.Board;

public class Pawn extends Piece {


    public Pawn(int x, int y, boolean p) {
        super(x, y, p);
        allowed_moves.add(MoveType.DIAGONAL);
        allowed_moves.add(MoveType.VERTICAL);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean move_check(int new_x_coord, int new_y_coord) {
        /*
        white player can only go up, so his cur y coordinate is lower than the new one
        the black player is the exact opposite
        white player is boolean false, black is boolean true
         */
        boolean valid_move = false;
        boolean cur_move_valid_dir = (new_y_coord < this.getY_coord() == this.getColor());
        MoveType moveType = move_type_checker(new_x_coord, new_y_coord);
        if(!Board.bound_check(new_x_coord,new_y_coord))
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
                if(Board.get_piece(new_x_coord, new_y_coord) == null)
                {
                    // only from the starting position a pawn can make a vertical 2 step move
                    if(this.isIn_starting_pos() && Math.abs(this.getY_coord() - new_y_coord) == 2)
                    {
                        // pawn cannot jump over another piece when making a 2 step move, so we need to check
                        // that the cell between the cur position and the final position is empty
                        int y_dir = this.getColor()? -1:1;
                        if(Board.get_piece(new_x_coord, new_y_coord + y_dir) == null)
                        {
                            this.setIn_starting_pos(false);
                            valid_move = true;
                            break;
                        }
                        else return false;
                    }

                    if(Math.abs(this.getY_coord() - new_y_coord) == 1)
                    {
                        if(this.isIn_starting_pos())
                        {
                            this.setIn_starting_pos(false);
                        }
                        valid_move = true;
                        break;
                    }
                    else return false;
                }
                else return false;
                
            case DIAGONAL:
                // a diagonal move is an attacking only move
                Piece attacked_piece = Board.get_piece(new_x_coord, new_y_coord);
                if(attacked_piece != null && attacked_piece.getColor() != this.getColor())
                {
                    if (Math.abs(this.getY_coord() - new_y_coord) == 1 && Math.abs(this.getX_coord() - new_x_coord) == 1)
                    {
                        if(this.isIn_starting_pos())
                        {
                            this.setIn_starting_pos(false);
                        }
                        valid_move = true;
                        break;
                    }
                    else return false;
                }
                else return false;
                
            default:
                return false;
        }
        if(valid_move)
        {
            int king_x = Board.get_player(this.getColor()).getKing_x_coord();
            int king_y = Board.get_player(this.getColor()).getKing_y_coord();
            valid_move = this.make_move_and_update(new_x_coord, new_y_coord, king_x, king_y);
        }
        return valid_move;
    }
}
