package Pieces;

public class King extends Piece {
    private boolean checked;
    public King(int x, int y, boolean p){
        super(x, y, p);
        allowed_moves.add(MoveType.HORIZONTAL);
        allowed_moves.add(MoveType.VERTICAL);
        allowed_moves.add(MoveType.DIAGONAL);
        checked = false;
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean move_check(int new_x_coord, int new_y_coord) {
        /*
            1. after each move check to see that the king is not threatened by an enemy piece
            2. castling
         */
        return false;
    }
}
