package Board;

import Pieces.*;

public class Board {
    public static final int X_UPPER_BOUND = 7;
    public static final int Y_UPPER_BOUND = 7;
    public static final int X_LOWER_BOUND = 0;
    public static final int Y_LOWER_BOUND = 0;

    public static Piece[] board = new Piece[(X_UPPER_BOUND + 1) * (Y_UPPER_BOUND + 1)];
}
