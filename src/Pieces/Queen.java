package Pieces;

public class Queen extends  Piece {
    Queen(int x, int y, boolean p){
        super(x, y, p);
        allowed_moves.add(MoveType.HORIZONTAL);
        allowed_moves.add(MoveType.VERTICAL);
        allowed_moves.add(MoveType.DIAGONAL);
    }

    @Override
    public String toString() {
        return "Q";
    }
}
