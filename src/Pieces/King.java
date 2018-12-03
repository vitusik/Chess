package Pieces;

import Board.*;

import java.util.ArrayList;

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
        boolean valid_move = false;
        if(Math.abs(this.getX_coord() - new_x_coord) <= 1 && Math.abs(this.getY_coord() - new_y_coord) <= 1)
        {
            if(Board.get_piece(new_x_coord, new_y_coord) == null)
            {
                this.setIn_starting_pos(false);
                valid_move = true;
            }
            else return false;
        }
        // a castling move
        if(this.getY_coord() == new_y_coord && (Math.abs(this.getX_coord() - new_x_coord) == 2))
        {
            /*
            the king and the rook has'nt moved, there is no need to check if the other piece is a rook
            or if the piece is the same color of the king, because if it isn't than it has obliviously moved*/
            Player cur = this.getColor()? Board.black_player:Board.white_player;
            // cant castle if king is checked
            if (cur.isChecked()) return false;
            int step = (new_x_coord > this.getX_coord())? 1 : -1;
            int rook_x_coord = step == 1? 7:0;
            if(this.isIn_starting_pos() && Board.get_piece(rook_x_coord, new_y_coord).isIn_starting_pos())
            {
                Player enemy = this.getColor()? Board.white_player: Board.black_player;
                for(int i = this.getX_coord() + step; i != new_x_coord + step; i += step)
                {
                    // first part checks that the path to the rook is empty
                    // second part checks that the path isn't threatened by the enemy player
                    if(Board.get_piece(i, new_y_coord) != null || Board.is_under_threat(i, new_y_coord, enemy.getPiece_list()))
                    {
                        return false;
                    }
                }
                Piece king = Board.get_piece(this.getX_coord(), this.getY_coord());
                Piece rook = Board.board[rook_x_coord + Board.X_UPPER_BOUND * this.getY_coord()];
                king.setXYcoord(new_x_coord, new_y_coord);
                rook.setXYcoord(new_x_coord - step, rook.getY_coord());
                return true;
            }
        }
        if (valid_move)
        {
            valid_move = this.king_make_move_and_update(new_x_coord, new_y_coord);
        }
        return valid_move;
    }

    private boolean king_make_move_and_update(int new_x_coord, int new_y_coord) {
        Piece piece_in_final_positon = Board.get_piece(new_x_coord, new_y_coord);
        int cur_x = this.getX_coord();
        int cur_y = this.getY_coord();
        this.setXYcoord(new_x_coord, new_y_coord);
        ArrayList<Piece> threats = this.getColor() ? Board.white_player.getPiece_list() : Board.black_player.getPiece_list();
        if (Board.is_under_threat(new_x_coord, new_y_coord, threats)) {
            this.setXYcoord(cur_x, cur_y);
            piece_in_final_positon.setXYcoord(new_x_coord, new_y_coord);
            return false;
        }
        if(this.getColor()) Board.black_player.setKing_x_y_coord(new_x_coord, new_y_coord);
        else Board.white_player.setKing_x_y_coord(new_x_coord, new_y_coord);
        return true;
    }
}
