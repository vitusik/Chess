package Pieces;

public class Bishop extends Piece{
    public Bishop(int x, int y, boolean p){
        super(x, y, p);
        allowedMoves.add(MoveType.DIAGONAL);
    }

    @Override
    public String toString() {
        return "B";
    }
}
