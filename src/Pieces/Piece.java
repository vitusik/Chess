package Pieces;
import Board.Board;
import java.util.ArrayList;

public abstract class Piece {


    private int x_coord;
    private int y_coord;
    private boolean player;
    protected ArrayList<MoveType> allowed_moves;

    public Piece(int x, int y, boolean p) {
        x_coord = x;
        y_coord = y;
        player = p;
        allowed_moves = new ArrayList<>();
    }

    public int getX_coord() {
        return x_coord;
    }

    public void setX_coord(int x_coord) {
        this.x_coord = x_coord;
    }

    public int getY_coord() {
        return y_coord;
    }

    public void setY_coord(int y_coord) {
        this.y_coord = y_coord;
    }

    public boolean getPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    public void setXY_coords(int x, int y){
        setX_coord(x);
        setY_coord(y);
    }

    public boolean move_bound_check(int new_x_coord, int new_y_coord) {
        return (new_x_coord <= Board.X_UPPER_BOUND && new_x_coord >= Board.X_LOWER_BOUND &&
                new_y_coord <= Board.Y_UPPER_BOUND && new_y_coord >= Board.Y_LOWER_BOUND);
    }

    public MoveType move_type_checker(int end_x_coord, int end_y_coord) {
        if (this.x_coord != end_x_coord && this.y_coord == end_y_coord) {
            return MoveType.HORIZONTAL;
        }
        if (this.x_coord == end_x_coord && this.y_coord != end_y_coord) {
            return MoveType.VERTICAL;
        }
        if (this.x_coord != end_x_coord && this.y_coord != end_y_coord)
        {
            if (Math.abs(end_x_coord - this.x_coord) == Math.abs(end_y_coord - this.y_coord)) {
                return MoveType.DIAGONAL;
            }
            if(Math.abs(end_x_coord - this.x_coord) + Math.abs(end_y_coord - this.y_coord) == 3) {
                return MoveType.KNIGHT;
            }
        }
        return MoveType.NO_MOVE;
    }

    public boolean horizontal_move_check(int new_y_coord) {
        int step = (new_y_coord > this.y_coord)? 1 : -1;
        for (int i = this.y_coord + step ; i != new_y_coord; i += step)
        {
            if (Board.board[this.x_coord + Board.X_UPPER_BOUND * i] != null)
            {
                return false;
            }
        }
        if (Board.board[this.x_coord + Board.X_UPPER_BOUND * new_y_coord] == null) {
            return true;
        } else
        {
            Piece temp = Board.board[this.x_coord + Board.X_UPPER_BOUND * new_y_coord];
            return temp.getPlayer() != this.getPlayer();
        }
    }

    public boolean vertical_move_check(int new_x_coord) {
        int step = (new_x_coord > this.x_coord)? 1 : -1;
        for (int i = this.x_coord + step ; i != new_x_coord; i += step)
        {
            if (Board.board[i + Board.X_UPPER_BOUND * this.y_coord] != null)
            {
                return false;
            }
        }
        if (Board.board[new_x_coord + Board.X_UPPER_BOUND * this.y_coord] == null) {
            return true;
        } else
        {
            Piece temp = Board.board[new_x_coord + Board.X_UPPER_BOUND * this.y_coord];
            return temp.getPlayer() != this.getPlayer();
        }
    }

    public boolean diagonal_move_check(int new_x_coord, int new_y_coord) {
        int step_x = (new_x_coord > this.x_coord)? 1 : -1;
        int step_y = (new_y_coord > this.y_coord)? 1 : -1;
        int j = this.y_coord + step_y;
        for (int i = this.x_coord + step_x ; i != new_x_coord; i += step_x)
        {
            if (Board.board[i + Board.X_UPPER_BOUND * j] != null)
            {
                return false;
            }
            j += step_y;
        }
        if (Board.board[new_x_coord + Board.X_UPPER_BOUND * new_y_coord] == null) {
            return true;
        } else
        {
            Piece temp = Board.board[new_x_coord + Board.X_UPPER_BOUND * new_y_coord];
            return temp.getPlayer() != this.getPlayer();
        }
    }

    public abstract boolean move_check(int new_x_coord, int new_y_coord);

}
