package Pieces;



public class Pawn extends Piece {

    public Pawn(int x, int y, boolean p) {
        super(x, y, p);
        allowed_moves.add(MoveType.DIAGONAL);
        allowed_moves.add(MoveType.VERTICAL);
    }

    public boolean move_check(int new_x_coord, int new_y_coord) {
        /*
            the pawn piece can make the following moves:
            1. take one step forward, in case the cell is empty.
            2. take 2 step forward, in case the cells are empty and that the pawn is at its starting position.
            3. diagonal step forward, in case there is an enemy piece in that cell.
            the white player starts at the bottom so from his point of view forward is up
            the black player starts at the top of the board so forward from his point of view is down.
         */
        return false;
    }
}
