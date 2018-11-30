package Board;

import Pieces.Piece;

import java.util.ArrayList;

public class Player {
    private boolean color;
    private ArrayList<Piece> piece_list;
    private boolean checked;
    private int king_x_coord;
    private int king_y_coord;

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

    public static boolean is_under_threat(int x, int y, ArrayList<Piece> possible_threats){

        for(Piece piece : possible_threats)
        {
            if(piece.move_check(x, y))
            {
                return true;
            }
        }
        return false;
    }
}
