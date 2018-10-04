package Pieces;



public class Pawn extends Piece {

    public Pawn(int x, int y, boolean p) {
        super(x, y, p);
        allowed_moves.add(MoveType.DIAGONAL);
        allowed_moves.add(MoveType.VERTICAL);
    }

    public boolean move_check(int new_x_coord, int new_y_coord) {
        return false;
    }
}
