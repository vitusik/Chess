package Pieces;

public class Bishop extends Piece{
    Bishop(int x, int y, boolean p){
        super(x, y, p);
        allowed_moves.add(MoveType.DIAGONAL);
    }

    @Override
    public String toString() {
        return "B";
    }
}
