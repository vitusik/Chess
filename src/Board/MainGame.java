package Board;

import Pieces.Piece;

import java.util.Scanner;

public class MainGame {
    private static boolean curColor;
    private static int scanInput(boolean scanningXCoord)
    {
        Scanner scanner = new Scanner(System.in);
        if(scanningXCoord)
        {
            return scanner.next().toUpperCase().charAt(0) -65;
        }
        return scanner.nextInt() - 1;
    }

    private static Piece pieceSelection(){
        while (true)
        {
            System.out.println("Choose X coordinate of the selected piece");
            int pieceXCoord = scanInput(true);
            System.out.println("Choose Y coordinate of the selected piece");
            int pieceYCoord = scanInput(false);
            if(Board.boundCheck(pieceXCoord, pieceYCoord))
            {
                Piece potentialPiece = Board.getPiece(pieceXCoord, pieceYCoord);
                if(potentialPiece != null){
                    if(potentialPiece.getColor() == curColor) return potentialPiece;
                }
            }
        }
    }

    private static void moveSelection(Piece piece)
    {
        int destXCoord;
        int destYCoord;
        boolean validMove = false;
        do{
            System.out.println("Choose X coordinate of dest");
            destXCoord = scanInput(true);
            System.out.println("Choose Y coordinate of dest");
            destYCoord = scanInput(false);
            if(Board.boundCheck(destXCoord, destYCoord))
            {
                validMove = piece.makeMove(destXCoord, destYCoord);
            }
        }while (!validMove);
    }

    private static void initGame()
    {
        Board.boardInit();
        curColor = Board.WHITE;
        Board.printBoard();
    }

    public static void main(String[] args){
        initGame();
        boolean isChecked;
        Player curPlayer;
        //game loop
        while(true){
            System.out.println("cur player is " + (curColor? "Black":"White"));
            curPlayer = Board.getPlayer(curColor);
            System.out.println("cur player king x is:" + curPlayer.getkingXCoord() + "\ncur player king y is:" + curPlayer.getkingYCoord());

            isChecked = Board.isUnderThreat(curPlayer.getkingXCoord(), curPlayer.getkingYCoord(),
                    Board.getPlayer(!curColor).getpieceList());
            if(isChecked){
                System.out.println("hi");
                if(CheckmateChecker.lookForCheckmate(curColor)){
                    System.out.println("end game");
                    return;
                }
            }
            Piece potentialPiece = pieceSelection();
            // as soon as a player chose a piece to move, he must move that piece, according to the touch move rule
            moveSelection(potentialPiece);
            Board.printBoard();
            curColor = !curColor;
        }
    }
}
