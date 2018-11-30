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
        /*
            1. after each move check to see that the king is not threatened by an enemy piece
            2. castling
         */
        // The king is allowed to travel in any direction with a maximum distance of 1 from is current position
        boolean valid_move = false;
        if(Math.abs(this.getX_coord() - new_x_coord) <= 1 && Math.abs(this.getY_coord() - new_y_coord) <= 1)
        {
            if(Board.board[new_x_coord + Board.X_UPPER_BOUND * new_y_coord] == null)
            {
                this.setIn_starting_pos(false);
                valid_move = true;
            }
            else return false;
        }
        // a castling move
        if(this.getY_coord() == new_y_coord && (new_x_coord == 0 || new_x_coord == 7))
        {
            /*
            the king and the rook has'nt moved, there is no need to check if the other piece is a rook
            or if the piece is the same color of the king, because if it isn't than it has obliviously moved*/
            if(this.isIn_starting_pos() && Board.board[new_x_coord + Board.X_UPPER_BOUND * new_y_coord].isIn_starting_pos())
            {
                int step = (new_x_coord > this.getX_coord())? 1 : -1;
                for(int i = this.getX_coord() + step; i != new_x_coord; i += step)
                {
                    Player enemy = this.getColor()? Board.white_player: Board.black_player;
                    // first part checks that the path to the rook is empty
                    // second part checks that the path isn't threatened by the enemy player
                    if(Board.board[i + Board.X_UPPER_BOUND * new_y_coord] != null || Player.is_under_threat(i, new_y_coord, enemy.getPiece_list()))
                    {
                        return false;
                    }
                }
                valid_move = true;
            }
        }
        if (valid_move)
        {
            valid_move = this.king_make_move_and_update(new_x_coord, new_y_coord);
        }
        return valid_move;
    }

    boolean king_make_move_and_update(int new_x_coord, int new_y_coord) {
        Piece piece_in_final_positon = Board.board[new_x_coord + Board.X_UPPER_BOUND * new_y_coord];
        Board.board[new_x_coord + Board.X_UPPER_BOUND * new_y_coord] = this;
        int cur_x = this.getX_coord();
        int cur_y = this.getY_coord();
        Board.board[cur_x + Board.X_UPPER_BOUND * cur_y] = null;
        this.setXYcoord(new_x_coord, new_y_coord);
        ArrayList<Piece> threats = this.getColor() ? Board.white_player.getPiece_list() : Board.black_player.getPiece_list();
        if (Player.is_under_threat(new_x_coord, new_y_coord, threats)) {
            this.setXYcoord(cur_x, cur_y);
            Board.board[cur_x + Board.X_UPPER_BOUND * cur_y] = this;
            Board.board[new_x_coord + Board.X_UPPER_BOUND * new_y_coord] = piece_in_final_positon;
            return false;
        }
        if(this.getColor()) Board.black_player.setKing_x_y_coord(new_x_coord, new_y_coord);
        else Board.white_player.setKing_x_y_coord(new_x_coord, new_y_coord);
        return true;
    }
}
