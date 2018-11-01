import Pieces.*;
import Board.*;
import java.util.Random;

public class PawnTest {
    public static void test1()
    {
        Random rand = new Random();
        Boolean p = rand.nextBoolean();
        int step = p? -1 : 1;
        int rand_x = rand.nextInt(8);
        int rand_y = rand.nextInt(6 ) + 1;
        Piece p1 = new Pawn(rand_x,rand_y, p);
        Board.board[p1.getX_coord() + p1.getY_coord() * Board.X_UPPER_BOUND] = p1;
        if(!p1.move_check(rand_x,rand_y + step))
        {
            System.out.println("failed simple Pawn 1 step");
        }
        Board.clear_board();
    }
    public static void test2()
    {
        Random rand = new Random();
        Boolean p = rand.nextBoolean();
        int rand_x = rand.nextInt(8);
        int rand_y = p? 6 : 1;
        int step = p? -2 : 2;
        Piece p1 = new Pawn(rand_x,rand_y, p);
        Board.board[p1.getX_coord() + p1.getY_coord() * Board.X_UPPER_BOUND] = p1;
        if(!p1.move_check(rand_x,rand_y + step))
        {
            System.out.println("failed simple Pawn 2 step");
        }
        Board.clear_board();
    }
    public static void test3()
    {
        Random rand = new Random();
        Boolean p = rand.nextBoolean();
        int rand_x = rand.nextInt(8);
        // pawn's attack interval is the rows 1-6
        int rand_y = rand.nextInt(6) + 1;
        int x_side_of_attack = rand.nextBoolean()? -1:1;
        // if the attacking pawn is located at the side of the board we need to choose the
        // correct position of the attacked piece
        x_side_of_attack = rand_x == 7? -1: x_side_of_attack;
        x_side_of_attack = rand_x == 0? 1: x_side_of_attack;
        // if the attacking pawn is white the attacked pawn must be position above it
        int y_side_of_attack = p? -1:1;
        Piece attacking_pawn = new Pawn(rand_x, rand_y, p);
        Piece attacked_pawn = new Pawn(rand_x + x_side_of_attack, rand_y + y_side_of_attack, !p);
        Board.board[attacked_pawn.getX_coord() + attacked_pawn.getY_coord() * Board.X_UPPER_BOUND] = attacked_pawn;
        Board.board[attacking_pawn.getX_coord() + attacking_pawn.getY_coord() * Board.X_UPPER_BOUND] = attacking_pawn;
        if(!attacking_pawn.move_check(rand_x + x_side_of_attack, rand_y + y_side_of_attack))
        {
            System.out.println("failed attack move");
        }
        Board.clear_board();

    }
    public static void main(String[] args){
        for (int i = 0; i< 100;i++)
        {
            test1();
            test2();
            test3();
        }


    }
}
