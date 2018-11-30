package Pieces;

public class Knight extends Piece {
    Knight(int x, int y, boolean p){
        super(x, y, p);
        allowed_moves.add(MoveType.KNIGHT);
    }

    @Override
    public String toString() {
        return "K";
    }
}
