package Board;

import Pieces.Piece;

import java.util.ArrayList;

public class Player {
    private boolean color;
    private ArrayList<Piece> piece_list;
    private boolean checked;
    private int king_x_coord;
    private int king_y_coord;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getKing_x_coord() {
        return king_x_coord;
    }

    public void setKing_x_coord(int king_x_coord) {
        this.king_x_coord = king_x_coord;
    }

    public int getKing_y_coord() {
        return king_y_coord;
    }

    public void setKing_y_coord(int king_y_coord) {
        this.king_y_coord = king_y_coord;
    }

    public void setKing_x_y_coord(int x, int y)
    {
        this.setKing_x_coord(x);
        this.setKing_y_coord(y);
    }

    public Player(boolean color) {
        this.color = color;
        this.checked = false;
    }

    public void setPiece_list(ArrayList<Piece> piece_list) {
        this.piece_list = piece_list;
    }

    public ArrayList<Piece> getPiece_list(){
        return this.piece_list;
    }

}
