package Tests;

import Board.*;
import Pieces.*;

import java.util.Random;


// these tests are for the various move checks in the Piece class
public class PieceTest {
    private static void test1() {
        //bound checks
        Random rand = new Random();
        // random x,y values in the range of [1,8] - meaning legal moves
        for(int i = 0; i  < 99;){
            int rand_x = rand.nextInt(8);
            int rand_y = rand.nextInt(8);
            if(!Board.boundCheck(rand_x,rand_y)){
                System.out.println("failed bound check with x val of " + rand_x + " and y val of " + rand_y);
                break;
            }
            else i++;
        }
        // random x,y values in the range of [9,108] - meaning illegal moves
        for(int i = 0; i  < 99;){
            int rand_x = rand.nextInt(100) + 9;
            int rand_y = rand.nextInt(100) + 9;
            if(Board.boundCheck(rand_x,rand_y)){
                System.out.println("failed bound check with x val of " + rand_x + " and y val of " + rand_y);
                break;
            }
            else i++;
        }
        // random x,y values in the range of [-100,-1] - meaning illegal moves
        for(int i = 0; i  < 99;){
            int rand_x = rand.nextInt(100) + 1;
            rand_x = rand_x * -1;
            int rand_y = rand.nextInt(100) + 1;
            rand_y = rand_y * -1;
            if(Board.boundCheck(rand_x,rand_y)){
                System.out.println("failed bound check with x val of " + rand_x + " and y val of " + rand_y);
                break;
            }
            else i++;
        }
    }
    private static void test2(){
        //move type checks for vertical moves
        Piece p = new Rook(4,4,true);
        Random rand = new Random();
        for( int i = 0; i < 99; i++)
        {
            int rand_y = rand.nextInt(7) + 1;
            if(p.moveTypeChecker(p.getxCoord(),rand_y) != MoveType.VERTICAL && rand_y != p.getyCoord())
            {
                System.out.println("failed vertical check with x val of " + p.getxCoord() + " and y val of " + rand_y);
            }
        }
        for( int i = 0; i < 99; i++)
        {
            int rand_x = rand.nextInt(4) + 5;
            int rand_y = rand.nextInt(7) + 1;
            if(p.moveTypeChecker(rand_x,rand_y) == MoveType.VERTICAL)
            {
                System.out.println("failed vertical check with x val of " + rand_x + " and y val of " + rand_y);
            }
        }
        for( int i = 0; i < 99; i++)
        {
            int rand_x = rand.nextInt(4);
            int rand_y = rand.nextInt(7) + 1;
            if(p.moveTypeChecker(rand_x,rand_y) == MoveType.VERTICAL)
            {
                System.out.println("failed vertical check with x val of " + rand_x + " and y val of " + rand_y);
            }
        }
        Board.clearBoard();
    }
    private static void test3(){
        //move type checks for horizontal moves
        Piece p = new Rook(4,4,true);
        Random rand = new Random();
        for( int i = 0; i < 99; i++)
        {
            int rand_x = rand.nextInt(7) + 1;
            if(p.moveTypeChecker(rand_x,p.getyCoord()) != MoveType.HORIZONTAL && rand_x != p.getxCoord())
            {
                System.out.println("failed horizontal check with x val of " + rand_x + " and y val of " + p.getyCoord());
            }
        }
        for( int i = 0; i < 99; i++)
        {
            int rand_x = rand.nextInt(7) + 1;
            int rand_y = rand.nextInt(4) + 5;
            if(p.moveTypeChecker(rand_x,rand_y) == MoveType.HORIZONTAL)
            {
                System.out.println("failed horizontal check with x val of " + rand_x + " and y val of " + rand_y);
            }
        }
        for( int i = 0; i < 99; i++)
        {
            int rand_x = rand.nextInt(7) + 1;
            int rand_y = rand.nextInt(4);
            if(p.moveTypeChecker(rand_x,rand_y) == MoveType.HORIZONTAL)
            {
                System.out.println("failed horizontal check with x val of " + rand_x + " and y val of " + rand_y);
            }
        }
        Board.clearBoard();
    }
    private static void test4(){
        // move type check for diagonal moves
        Piece p = new Bishop(4,4,true);
        Random rand = new Random();
        for(int i = 0; i < 99; i++){
            int rand_step = rand.nextInt(5) + 1 ;
            int new_x = p.getxCoord() + rand_step;
            int new_y = p.getyCoord() + rand_step;
            if(p.moveTypeChecker(new_x, new_y) != MoveType.DIAGONAL)
            {
                System.out.println("failed diagonal check with x val of " + new_x + " and y val of " + new_y);
                break;
            }
        }
        for(int i = 0; i < 99; i++){
            int rand_step = rand.nextInt(5) + 1 ;
            int new_x = p.getxCoord() - rand_step;
            int new_y = p.getyCoord() + rand_step;
            if(p.moveTypeChecker(new_x, new_y) != MoveType.DIAGONAL)
            {
                System.out.println("failed diagonal check with x val of " + new_x + " and y val of " + new_y);
                break;
            }
        }
        for(int i = 0; i < 99; i++){
            int rand_step = rand.nextInt(5) + 1 ;
            int new_x = p.getxCoord() - rand_step;
            int new_y = p.getyCoord() - rand_step;
            if(p.moveTypeChecker(new_x, new_y) != MoveType.DIAGONAL)
            {
                System.out.println("failed diagonal check with x val of " + new_x + " and y val of " + new_y);
                break;
            }
        }
        for(int i = 0; i < 99; i++){
            int rand_step = rand.nextInt(5) + 1 ;
            int new_x = p.getxCoord() + rand_step;
            int new_y = p.getyCoord() - rand_step;
            if(p.moveTypeChecker(new_x, new_y) != MoveType.DIAGONAL)
            {
                System.out.println("failed diagonal check with x val of " + new_x + " and y val of " + new_y);
                break;
            }
        }
        for(int i = 0; i < 99; i++){
            int rand_step_x = rand.nextInt(8) - 4 ;
            int rand_step_y = rand_step_x + 1 ;
            int new_x = p.getxCoord() + rand_step_x;
            int new_y = p.getyCoord() + rand_step_y;
            if(p.moveTypeChecker(new_x, new_y) == MoveType.DIAGONAL)
            {
                System.out.println("failed diagonal check with x val of " + new_x + " and y val of " + new_y);
                break;
            }
        }
        Board.clearBoard();

    }
    private static void test5(){
        // move type check for knight move
        Random rand = new Random();
        for(int i = 0; i < 99; i++)
        {
            int x = rand.nextInt(7) + 1;
            int y = rand.nextInt(7) + 1;
            Piece p = new Knight(x, y, true);
            if(     p.moveTypeChecker(x - 2, y + 1) != MoveType.KNIGHT ||
                    p.moveTypeChecker(x - 2, y - 1) != MoveType.KNIGHT ||
                    p.moveTypeChecker(x - 1, y + 2) != MoveType.KNIGHT ||
                    p.moveTypeChecker(x - 1, y - 2) != MoveType.KNIGHT ||
                    p.moveTypeChecker(x + 2, y + 1) != MoveType.KNIGHT ||
                    p.moveTypeChecker(x + 2, y - 1) != MoveType.KNIGHT ||
                    p.moveTypeChecker(x + 1, y + 2) != MoveType.KNIGHT ||
                    p.moveTypeChecker(x + 1, y - 2) != MoveType.KNIGHT )
            {
                System.out.println("failed knight check");
                break;
            }
            Board.clearBoard();
        }


    }
    private static void test6(){
        //horizontal move validity
        Board.black_player = new Player(Board.BLACK);
        Board.white_player = new Player(Board.WHITE);
        Piece attacking_piece = new Rook(0,0,Board.WHITE);
        Piece attacked_piece = new Rook(5,0,Board.BLACK);
        Board.board[attacking_piece.getxCoord() + attacking_piece.getyCoord() * Board.X_UPPER_BOUND] = attacking_piece;
        Board.board[attacked_piece.getxCoord() + attacked_piece.getyCoord() * Board.X_UPPER_BOUND] = attacked_piece;
        if(!attacking_piece.moveCheck(5, 0))
        {
            System.out.println("failed horizontal move check");
        }
        Board.clearBoard();
        attacking_piece = new Rook(0,0,Board.WHITE);
        attacked_piece = new Rook(5,0,Board.BLACK);
        Piece blocking_piece = new Pawn(4,0,Board.BLACK);
        // horizontal move validity when there is a blocking piece in the path
        Board.board[attacking_piece.getxCoord() + attacking_piece.getyCoord() * Board.X_UPPER_BOUND] = attacking_piece;
        Board.board[attacked_piece.getxCoord() + attacked_piece.getyCoord() * Board.X_UPPER_BOUND] = attacked_piece;
        Board.board[blocking_piece.getxCoord() + blocking_piece.getyCoord() * Board.X_UPPER_BOUND] = blocking_piece;
        if(attacking_piece.moveCheck(5, 0))
        {
            System.out.println("failed horizontal move check");
        }
    }
    private static void test7(){
        //vertical move validity
        Board.black_player = new Player(Board.BLACK);
        Board.white_player = new Player(Board.WHITE);
        Piece attacking_piece = new Rook(0,0,Board.WHITE);
        Piece attacked_piece = new Rook(0,5,Board.BLACK);
        Board.board[attacking_piece.getxCoord() + attacking_piece.getyCoord() * Board.X_UPPER_BOUND] = attacking_piece;
        Board.board[attacked_piece.getxCoord() + attacked_piece.getyCoord() * Board.X_UPPER_BOUND] = attacked_piece;
        if(!attacking_piece.moveCheck(0,5))
        {
            System.out.println("failed vertical move check");
        }
        Board.clearBoard();
        attacking_piece = new Rook(0,0,Board.WHITE);
        attacked_piece = new Rook(0,5,Board.BLACK);
        Piece blocking_piece = new Pawn(0,4,Board.BLACK);
        // vertical move validity when there is a blocking piece in the path
        Board.board[attacking_piece.getxCoord() + attacking_piece.getyCoord() * Board.X_UPPER_BOUND] = attacking_piece;
        Board.board[attacked_piece.getxCoord() + attacked_piece.getyCoord() * Board.X_UPPER_BOUND] = attacked_piece;
        Board.board[blocking_piece.getxCoord() + blocking_piece.getyCoord() * Board.X_UPPER_BOUND] = blocking_piece;
        if(attacking_piece.moveCheck(0,5))
        {
            System.out.println("failed vertical move check");
        }
        Board.clearBoard();
    }
    private static void test8(){
        // diagonal move validity
        Board.black_player = new Player(Board.BLACK);
        Board.white_player = new Player(Board.WHITE);
        Piece attacking_piece = new Queen(0,0,Board.WHITE);
        Piece attacked_piece = new Queen(5,5,Board.BLACK);
        Board.board[attacking_piece.getxCoord() + attacking_piece.getyCoord() * Board.X_UPPER_BOUND] = attacking_piece;
        Board.board[attacked_piece.getxCoord() + attacked_piece.getyCoord() * Board.X_UPPER_BOUND] = attacked_piece;
        if(!attacking_piece.moveCheck(5,5))
        {
            System.out.println("failed diagonal move check");
        }
        Board.clearBoard();
        attacking_piece = new Queen(0,0,Board.WHITE);
        attacked_piece = new Queen(5,5,Board.BLACK);
        Piece blocking_piece = new Pawn(2,2,Board.BLACK);
        Board.board[attacking_piece.getxCoord() + attacking_piece.getyCoord() * Board.X_UPPER_BOUND] = attacking_piece;
        Board.board[attacked_piece.getxCoord() + attacked_piece.getyCoord() * Board.X_UPPER_BOUND] = attacked_piece;
        Board.board[blocking_piece.getxCoord() + blocking_piece.getyCoord() * Board.X_UPPER_BOUND] = blocking_piece;
        if(attacking_piece.moveCheck(5,5))
        {
            System.out.println("failed diagonal move check");
        }
        Board.clearBoard();
    }
    public static void main(String[] args){
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
    }
}
