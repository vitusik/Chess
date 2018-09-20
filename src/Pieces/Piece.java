package Pieces;

import java.util.ArrayList;

public abstract class Piece {

    protected enum MoveTypes {VERTICAL, HORIZONTAL, DIAGONAL, KNIGHT, PAWN_MOVE, PAWN_ATTACK}
    protected enum Player {BLACK, WHITE}

    public final int X_UPPER_BOUND = 7;
    public final int Y_UPPER_BOUND = 7;
    public final int X_LOWER_BOUND = 0;
    public final int Y_LOWER_BOUND = 0;

    private int x_coord;
    private int y_coord;
    private Player player;
    private ArrayList<MoveTypes> allowed_moves;

    Piece(int x, int y, Player p)
    {
        x_coord = x;
        y_coord = y;
        player = p;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList getAllowed_moves() {
        return allowed_moves;
    }

    public void setAllowed_moves(ArrayList<MoveTypes> allowed_moves) {
        this.allowed_moves = allowed_moves;
    }

    public boolean move_bound_check(int new_x_coord, int new_y_coord)
    {
        if(new_x_coord > X_UPPER_BOUND || new_x_coord < X_LOWER_BOUND || new_y_coord > Y_UPPER_BOUND ||
                new_y_coord < Y_LOWER_BOUND) return false;
        return true;
    }

    public abstract boolean move_check(int new_x_coord, int new_y_coord);
}
