package Pieces;

public class Queen extends  Piece {
    public Queen(int x, int y, boolean p){
        super(x, y, p);
        allowedMoves.add(MoveType.HORIZONTAL);
        allowedMoves.add(MoveType.VERTICAL);
        allowedMoves.add(MoveType.DIAGONAL);
    }

    @Override
    public String toString() {
        return "Q";
    }
}
