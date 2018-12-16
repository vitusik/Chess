package Pieces;
import Board.*;

import java.util.ArrayList;


public abstract class Piece {

    private int x_coord;
    private int y_coord;
    private boolean color;
    private boolean in_starting_pos;
    ArrayList<MoveType> allowed_moves;

    @Override
    public abstract String toString();

    /**
     * Piece Class constructor
     * @param x the column of the piece on the chess board receives values of 0-7
     * @param y the row of the piece on the chess board receives values of 0-7
     * @param c boolean parameter which represents the color of the player true for black player, false for white
     */
    public Piece(int x, int y, boolean c) {
        x_coord = x;
        y_coord = y;
        color = c;
        allowed_moves = new ArrayList<>();
        in_starting_pos = true;
        Board.board[x + Board.X_UPPER_BOUND * y] = this;
    }
    /**
     * x coordinate getter
     * @return the x coordinate of the piece
     */
    public int getX_coord() {
        return x_coord;
    }

    /**
     * x coordinate setter
     * @param x_coord the new x coordinate of the piece
     */
    public void setX_coord(int x_coord) {
        this.x_coord = x_coord;
    }
    /**
     * y coordinate getter
     * @return the y coordinate of the piece
     */
    public int getY_coord() {
        return y_coord;
    }
    /**
     * y coordinate setter
     * @param y_coord the new y coordinate of the piece
     */
    public void setY_coord(int y_coord) {
        this.y_coord = y_coord;
    }

    public void setXYcoord(int x, int y)
    {
        Board.board[this.x_coord + Board.X_UPPER_BOUND * this.y_coord] = null;
        this.setX_coord(x);
        this.setY_coord(y);
        Board.board[this.x_coord + Board.X_UPPER_BOUND * this.y_coord] = this;
    }
    /**
     * player type getter
     * @return a boolean value which represents the type of the player
     */
    public boolean getColor() {
        return color;
    }

    /**
     * player type setter
     * @param player boolean which represents the type of the player
     */
    public void setColor(boolean player) {
        this.color = player;
    }

    boolean isIn_starting_pos() {
        return in_starting_pos;
    }

    void setIn_starting_pos(boolean in_starting_pos) {
        this.in_starting_pos = in_starting_pos;
    }

    /**
     * method which checks if the given (x,y) coordinate is within the bounds of the board
     * @param x_coord the x coordinate
     * @param y_coord the y coordinate
     * @return true if the coordinate is in bounds
     */
    boolean bound_check(int x_coord, int y_coord) {
        // the x,y coordinates ranges between 0 and 7 in the board array and because of that they need to be adjusted
        return (x_coord + 1 <= Board.X_UPPER_BOUND && x_coord + 1 >= Board.X_LOWER_BOUND &&
                y_coord + 1 <= Board.Y_UPPER_BOUND && y_coord + 1 >= Board.Y_LOWER_BOUND);
    }

    /**
     * method which takes new (x,y) position and returns the type of move that can be made from the current position
     * to get to the new position
     * @param end_x_coord the new x coordinate of the piece
     * @param end_y_coord the new y coordinate of the piece
     * @return returns a MoveType enum member, which represents horizontal, vertical, diagonal or knight move, or an
     * illegal move
     */
    public MoveType move_type_checker(int end_x_coord, int end_y_coord) {
        if (this.x_coord != end_x_coord && this.y_coord == end_y_coord) {
            return MoveType.HORIZONTAL;
        }
        if (this.x_coord == end_x_coord && this.y_coord != end_y_coord) {
            return MoveType.VERTICAL;
        }
        if (this.x_coord != end_x_coord && this.y_coord != end_y_coord)
        {
            if (Math.abs(end_x_coord - this.x_coord) == Math.abs(end_y_coord - this.y_coord)) {
                return MoveType.DIAGONAL;
            }
            // knight move creates a circle around the knight with the radius of 3, excluding horizontal, vertical and
            // diagonal positions on the circle relative to the knight
            // at this point of execution horizontal, vertical and vertical moves have been excluded
            if(Math.abs(end_x_coord - this.x_coord) + Math.abs(end_y_coord - this.y_coord) == 3) {
                return MoveType.KNIGHT;
            }
        }
        return MoveType.NO_MOVE;
    }

    /**
     * method which checks validity of a vertical move which ends at (current x coordinate, new_y_coord)
     * @param new_y_coord the end y coordinate of the vertical move
     * @return true if the move is legal, meaning there aren't any blocking pieces in the way, and that at the end
     * position there is an enemy piece or empty cell
     */
    private boolean vertical_move_check(int new_y_coord) {
        int step = (new_y_coord > this.y_coord)? 1 : -1;
        for (int i = this.y_coord + step ; i != new_y_coord; i += step)
        {
            if (Board.get_piece(this.x_coord, i) != null)
            {
                return false;
            }
        }
        if (Board.get_piece(this.x_coord, new_y_coord) == null) {
            return true;
        } else
        {
            Piece temp = Board.get_piece(this.x_coord, new_y_coord);
            return temp.getColor() != this.getColor();
        }
    }

    /**
     * method which checks validity of a horizontal move which ends at (new_x_coord, current y coordinate)
     * @param new_x_coord the end x coordinate of the horizontal move
     * @return true if the move is legal, meaning there aren't any blocking pieces in the way, and that at the end
     * position there is an enemy piece or empty cell
     */
    private boolean horizontal_move_check(int new_x_coord) {
        int step = (new_x_coord > this.x_coord)? 1 : -1;
        for (int i = this.x_coord + step ; i != new_x_coord; i += step)
        {
            if (Board.get_piece(i, this.y_coord) != null)
            {
                return false;
            }
        }
        if (Board.get_piece(new_x_coord, this.y_coord) == null) {
            return true;
        } else
        {
            Piece temp = Board.get_piece(new_x_coord, this.y_coord);
            return temp.getColor() != this.getColor();
        }
    }

    /**
     * method which checks validity of a diagonal move which ends at (new_x_coord, new_y_coord)
     * @param new_x_coord the end x coordinate of the diagonal move
     * @param new_y_coord the end y coordinate of the diagonal move
     * @return true if the move is legal, meaning there aren't any blocking pieces in the way, and that at the end
     * position there is an enemy piece or empty cell
     */
    private boolean diagonal_move_check(int new_x_coord, int new_y_coord) {
        int step_x = (new_x_coord > this.x_coord)? 1 : -1;
        int step_y = (new_y_coord > this.y_coord)? 1 : -1;
        int j = this.y_coord + step_y;
        for (int i = this.x_coord + step_x ; i != new_x_coord; i += step_x)
        {
            if (Board.get_piece(i, j) != null)
            {
                return false;
            }
            j += step_y;
        }
        if (Board.get_piece(new_x_coord, new_y_coord) == null) {
            return true;
        } else
        {
            Piece temp = Board.get_piece(new_x_coord, new_y_coord);
            return temp.getColor() != this.getColor();
        }
    }

    /**
     * method which checks the validity of a knight type move
     * @param new_x_coord the end x coordinate of the diagonal move
     * @param new_y_coord the end y coordinate of the diagonal move
     * @return true if there is an enemy piece at the end position or an empty cell
     */
    private boolean knight_move_check(int new_x_coord, int new_y_coord) {
        return this.getColor() != Board.get_piece(new_x_coord, new_y_coord).getColor();
    }


    boolean make_move_and_update(int new_x_coord, int new_y_coord){
        Piece piece_in_final_positon = Board.get_piece(new_x_coord, new_y_coord);
        boolean empty_tile = piece_in_final_positon == null;
        int cur_x = this.getX_coord();
        int cur_y = this.getY_coord();
        this.setXYcoord(new_x_coord, new_y_coord);
        int king_x = this.color? Board.black_player.getKing_x_coord() : Board.white_player.getKing_x_coord();
        int king_y = this.color? Board.black_player.getKing_y_coord() : Board.white_player.getKing_y_coord();
        ArrayList<Piece> threats = this.color? Board.white_player.getPiece_list(): Board.black_player.getPiece_list();
        if(Board.is_under_threat(king_x, king_y, threats))
        {
            this.setXYcoord(cur_x, cur_y);
            if(!empty_tile) piece_in_final_positon.setXYcoord(new_x_coord, new_y_coord);
            return false;
        }
        return true;
    }
    /**
     * method which encapsulates all the previous methods and call the correct move validity check method
     * @param new_x_coord the end x coordinate of the move
     * @param new_y_coord the end y coordinate of the move
     * @return true if the move is legal, meaning its not out of bounds, the calling piece can make that type of move
     * and the move is valid
     */
    public boolean move_check(int new_x_coord, int new_y_coord){
        MoveType cur_move = this.move_type_checker(new_x_coord, new_y_coord);
        boolean valid_move = false;
        if(!this.bound_check(new_x_coord, new_y_coord))
        {
            return false;
        }
        if(!this.allowed_moves.contains(cur_move))
        {
            return false;
        }
        switch (cur_move){
            case HORIZONTAL:
                valid_move = this.horizontal_move_check(new_x_coord);
                break;
            case VERTICAL:
                valid_move = this.vertical_move_check(new_y_coord);
                break;
            case DIAGONAL:
                valid_move = this.diagonal_move_check(new_x_coord, new_y_coord);
                break;
            case KNIGHT:
                valid_move = this.knight_move_check(new_x_coord, new_y_coord);
                break;
            case NO_MOVE:
                break;
        }
        if (valid_move && in_starting_pos) in_starting_pos = false;
        if (valid_move)
        {
            valid_move = this.make_move_and_update(new_x_coord, new_y_coord);
        }
        return valid_move;
    }

}
