import java.util.Scanner;
public class Guesses{
    public int row, col;
    public String bombOrFlag;
    
    public Guesses() {
        row = this.row;
        col = this.col;
        bombOrFlag = this.bombOrFlag;
    }
    
    // input row guess
    public int getGuessRow(Board board) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a row between 1 and " + board.getSideLength());
        int guessRow = input.nextInt() - 1;
        while (guessRow < 0 || guessRow >= board.getSideLength()) {
            System.out.println("You didn't follow directions. Please enter a number between 1 and " + board.getSideLength());
            guessRow = input.nextInt();
        }
        return guessRow;
    }
    
    // input column guess
    public int getGuessCol(Board board) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a column between 1 and " + board.getSideLength());
        int guessCol = input.nextInt() - 1;
        while (guessCol < 0 || guessCol >= board.getSideLength()) {
            System.out.println("You didn't follow directions. Please enter a number between 1 and " + board.getSideLength());
            guessCol = input.nextInt();
        }
        return guessCol;
    }
    
    // input bomb/flag guess
    
    
    public String bombOrFlag(Board board) {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to check for a bomb or plant a flag? Write 'Y' to check for a bomb, 'N' to plant a flag");
        String bombOrFlag = input.next().toLowerCase();
        while (! (bombOrFlag.equals("y") || bombOrFlag.equals("n"))) {
            System.out.println("You didn't follow directions. Please enter Y or N");
            bombOrFlag = input.next();
        }
        return bombOrFlag;
    }
}