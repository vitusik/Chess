package Pieces;

public class Knight extends Piece {
    public Knight(int x, int y, boolean p){
        super(x, y, p);
        allowedMoves.add(MoveType.KNIGHT);
    }

    @Override
    public String toString() {
        return "K";
    }
}
