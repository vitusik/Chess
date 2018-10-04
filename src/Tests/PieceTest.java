import Board.*;
import Pieces.*;

import java.util.Random;


// theses tests are for the various move checks in the Piece class
public class PieceTest {
    public static void test1() {
        //bound checks
        Piece p = new Pawn(1,1, true);
        Random rand = new Random();
        // random x,y valuess in the range of [1,8] - meaning legal moves
        for(int i = 0; i  < 99; i++){
            int rand_x = rand.nextInt(8) + 1;
            int rand_y = rand.nextInt(8) + 1;
            if(!p.move_bound_check(rand_x, rand_y)){
                System.out.println("failed bound check with x val of " + rand_x + " and y val of " + rand_y);
                break;
            }
        }
        // random x,y values in the range of [9,108] - meaning illegal moves
        for(int i = 0; i  < 99; i++){
            int rand_x = rand.nextInt(100) + 9;
            int rand_y = rand.nextInt(100) + 9;
            if(p.move_bound_check(rand_x, rand_y)){
                System.out.println("failed bound check with x val of " + rand_x + " and y val of " + rand_y);
                break;
            }
        }
        // random x,y values in the range of [-100,-1] - meaning illegal moves
        for(int i = 0; i  < 99; i++){
            int rand_x = rand.nextInt(100) + 1;
            rand_x = rand_x * -1;
            int rand_y = rand.nextInt(100) + 1;
            rand_y = rand_y * -1;
            if(p.move_bound_check(rand_x, rand_y)){
                System.out.println("failed bound check with x val of " + rand_x + " and y val of " + rand_y);
                break;
            }
        }

    }
    public static void test2(){
        //move type checks for vertical moves
        Piece p = new Pawn(4,4,true);
        Random rand = new Random();
        for( int i = 0; i < 99; i++)
        {
            int rand_y = rand.nextInt(8) + 1;
            if(p.move_type_checker(p.getX_coord(),rand_y) != MoveType.VERTICAL && rand_y != p.getY_coord())
            {
                System.out.println("failed vertical check with x val of " + p.getX_coord() + " and y val of " + rand_y);
            }
        }
        for( int i = 0; i < 99; i++)
        {
            int rand_x = rand.nextInt(4) + 5;
            int rand_y = rand.nextInt(8) + 1;
            if(p.move_type_checker(rand_x,rand_y) == MoveType.VERTICAL)
            {
                System.out.println("failed vertical check with x val of " + rand_x + " and y val of " + rand_y);
            }
        }
        for( int i = 0; i < 99; i++)
        {
            int rand_x = rand.nextInt(4);
            int rand_y = rand.nextInt(8) + 1;
            if(p.move_type_checker(rand_x,rand_y) == MoveType.VERTICAL)
            {
                System.out.println("failed vertical check with x val of " + rand_x + " and y val of " + rand_y);
            }
        }
    }
    public static void test3(){
        //move type checks for horizontal moves
        Piece p = new Pawn(4,4,true);
        Random rand = new Random();
        for( int i = 0; i < 99; i++)
        {
            int rand_x = rand.nextInt(8) + 1;
            if(p.move_type_checker(rand_x,p.getY_coord()) != MoveType.HORIZONTAL && rand_x != p.getX_coord())
            {
                System.out.println("failed horizontal check with x val of " + rand_x + " and y val of " + p.getY_coord());
            }
        }
        for( int i = 0; i < 99; i++)
        {
            int rand_x = rand.nextInt(8) + 1;
            int rand_y = rand.nextInt(4) + 5;
            if(p.move_type_checker(rand_x,rand_y) == MoveType.HORIZONTAL)
            {
                System.out.println("failed horizontal check with x val of " + rand_x + " and y val of " + rand_y);
            }
        }
        for( int i = 0; i < 99; i++)
        {
            int rand_x = rand.nextInt(8) + 1;
            int rand_y = rand.nextInt(4);
            if(p.move_type_checker(rand_x,rand_y) == MoveType.HORIZONTAL)
            {
                System.out.println("failed horizontal check with x val of " + rand_x + " and y val of " + rand_y);
            }
        }
    }
    public static void test4(){
        // move type check for diagonal moves
        Piece p = new Pawn(4,4,true);
        Random rand = new Random();
        for(int i = 0; i < 99; i++){
            int rand_step = rand.nextInt(5) + 1 ;
            int new_x = p.getX_coord() + rand_step;
            int new_y = p.getY_coord() + rand_step;
            if(p.move_type_checker(new_x, new_y) != MoveType.DIAGONAL)
            {
                System.out.println("failed horizontal check with x val of " + new_x + " and y val of " + new_y);
                break;
            }
        }
    }
    public static void main(String[] args){
        test1();
        test2();
        test3();
        test4();
    }
}
