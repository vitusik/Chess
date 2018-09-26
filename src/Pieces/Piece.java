package Pieces;
import Board.Board;
import java.util.ArrayList;

public abstract class Piece {

    protected enum Player {BLACK, WHITE}

    private int x_coord;
    private int y_coord;
    private Player player;
    protected ArrayList<MoveType> allowed_moves;

    Piece(int x, int y, Player p)
    {
        x_coord = x;
        y_coord = y;
        player = p;
        allowed_moves = new ArrayList<>();
    }

    public int getx_coord() {
        return x_coord;
    }

    public void setx_coord(int x_coord) {
        this.x_coord = x_coord;
    }

    public int getY_coord() {
        return y_coord;
    }

    public void setY_coord(int y_coord) {
        this.y_coord = y_coord;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void movePiece(int x, int y){
        setx_coord(x);
        setY_coord(y);
    }
    public boolean move_bound_check(int new_x_coord, int new_y_coord)
    {
        return (new_x_coord <= Board.X_UPPER_BOUND && new_x_coord >= Board.X_LOWER_BOUND &&
                new_y_coord <= Board.Y_UPPER_BOUND && new_y_coord >= Board.Y_LOWER_BOUND);
    }

    public MoveType move_type_chercker(int end_x_coord, int end_y_coord)
    {
        if (this.x_coord == end_x_coord && this.y_coord != end_y_coord) return MoveType.HORIZONTAL;
        if (this.x_coord != end_x_coord && this.y_coord == end_y_coord) return MoveType.VERTICAL;
        if (this.x_coord != end_x_coord && this.y_coord != end_y_coord)
        {
            if (Math.abs(end_x_coord - this.x_coord) == Math.abs(end_y_coord - this.y_coord)) 
                return MoveType.DIAGONAL;
            if(Math.abs((x_coord + end_y_coord) - (this.x_coord + this.y_coord)) == 3) 
                return MoveType.KNIGHT;
        }
        return MoveType.NO_MOVE;
    }

    public boolean horizontal_move_check(int new_x_coord, int new_y_coord)
    {
        
        return true;
    }
    public abstract boolean move_check(int new_x_coord, int new_y_coord);

}
