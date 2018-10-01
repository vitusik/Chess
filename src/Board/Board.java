package Board;

import Pieces.*;

public class Board {
    public static final int X_UPPER_BOUND = 8;
    public static final int Y_UPPER_BOUND = 8;
    public static final int X_LOWER_BOUND = 1;
    public static final int Y_LOWER_BOUND = 1;

    public static Piece[] board = new Piece[X_UPPER_BOUND * Y_UPPER_BOUND];
}
