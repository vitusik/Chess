package Pieces;

public class Rook extends Piece{
    Rook(int x, int y, boolean p){
        super(x, y, p);
        allowed_moves.add(MoveType.HORIZONTAL);
        allowed_moves.add(MoveType.VERTICAL);
    }

    @Override
    public String toString() {
        return "R";
    }
}
