package Board;

import Pieces.Piece;

import java.util.ArrayList;

public class Player {
    private boolean color;
    private ArrayList<Piece> pieceList;
    private boolean checked;
    private int kingXCoord;
    private int kingYCoord;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getkingXCoord() {
        return kingXCoord;
    }

    public void setkingXCoord(int kingXCoord) {
        this.kingXCoord = kingXCoord;
    }

    public int getkingYCoord() {
        return kingYCoord;
    }

    public void setkingYCoord(int kingYCoord) {
        this.kingYCoord = kingYCoord;
    }

    public void setKingXYCoords(int x, int y)
    {
        this.setkingXCoord(x);
        this.setkingYCoord(y);
    }

    public Player(boolean color) {
        this.color = color;
        this.checked = false;
        this.pieceList = new ArrayList<>();
    }

    public void setpieceList(ArrayList<Piece> pieceList) {
        this.pieceList = pieceList;
    }

    public ArrayList<Piece> getpieceList(){
        return this.pieceList;
    }

}
