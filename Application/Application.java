import java.util.Scanner;
public class Application{
    public static void main(String[] args) {
        Board board = new Board();
        Guesses guesses = new Guesses();
        
        while (!board.checkWin()) {
            System.out.print(board);
            int guessRow = guesses.getGuessRow(board);
            int guessCol = guesses.getGuessCol(board);
            String checkBomb = guesses.bombOrFlag(board);
            
            if (checkBomb.equals("y")) board.checkForBomb(guessRow, guessCol);
            else board.plantFlag(guessRow, guessCol);
        }
        System.out.print("You win!");
    }
}
