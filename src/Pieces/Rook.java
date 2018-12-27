package Pieces;

public class Rook extends Piece{
    public Rook(int x, int y, boolean p){
        super(x, y, p);
        allowedMoves.add(MoveType.HORIZONTAL);
        allowedMoves.add(MoveType.VERTICAL);
    }

    @Override
    public String toString() {
        return "R";
    }
}
