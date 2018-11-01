package Pieces;

public class Bishop extends Piece{
    public Bishop(int x, int y, boolean p){
        super(x, y, p);
        allowed_moves.add(MoveType.DIAGONAL);
    }
}
