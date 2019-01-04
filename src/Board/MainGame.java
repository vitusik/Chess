package Board;

import Pieces.Piece;

import java.util.Scanner;

public class MainGame {
    private static boolean curColor;

    /**
     * Method that takes the input from the user and reformat it before returning
     * @param scanningXCoord boolean value which tells the function if the current scanned var is X or Y
     * @return an int that represents the X coord or the Y coord
     */
    private static int scanInput(boolean scanningXCoord)
    {
        Scanner scanner = new Scanner(System.in);
        if(scanningXCoord)
        {
            // the ASCII code of A is 65, the lower bound of the X coordinate is 0
            return scanner.next().toUpperCase().charAt(0) -65;
        }
        // in case of an int as an input decrement 1 because all arrays began with a 0 index
        return scanner.nextInt() - 1;
    }

    /**
     * Method that in charge of selecting a Piece for the move
     * @return the selected piece
     */
    private static Piece pieceSelection(){
        while (true)
        {
            // stays in infinite loop until the input is within the bounds
            System.out.println("Choose X coordinate of the selected piece");
            int pieceXCoord = scanInput(true);
            // I'm making an assumption about the type of the input which is input when I scan for the Y coordinate
            System.out.println("Choose Y coordinate of the selected piece");
            int pieceYCoord = scanInput(false);
            if(Board.boundCheck(pieceXCoord, pieceYCoord))
            {
                Piece potentialPiece = Board.getPiece(pieceXCoord, pieceYCoord);
                if(potentialPiece != null){
                    // after validating that the coordinates are of a real piece, need to check that this piece belongs
                    // to the right player
                    if(potentialPiece.getColor() == curColor) return potentialPiece;
                }
            }
        }
    }

    /**
     * Method which in charge of making a legal move given a piece
     * @param piece the piece which will be making the move
     */
    private static void moveSelection(Piece piece)
    {
        int destXCoord;
        int destYCoord;
        boolean validMove = false;
        do{
            // infinite loop until the move is legal
            System.out.println("Choose X coordinate of dest");
            destXCoord = scanInput(true);
            // again same assumption about the inputs
            System.out.println("Choose Y coordinate of dest");
            destYCoord = scanInput(false);
            if(Board.boundCheck(destXCoord, destYCoord))
            {
                validMove = piece.makeMove(destXCoord, destYCoord);
            }
        }while (!validMove);
    }

    /**
     * Method which in charge of initializing the game, meaning all the different objects that needs to be initialized
     * before the game, in order for the game to function
     */
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
        while(true){
            System.out.println("Current player is " + (curColor? "Black":"White"));
            curPlayer = Board.getPlayer(curColor);
            isChecked = Board.isUnderThreat(curPlayer.getkingXCoord(), curPlayer.getkingYCoord(),
                    Board.getPlayer(!curColor).getpieceList());
            if(isChecked){
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
