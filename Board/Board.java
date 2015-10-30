import java.util.*;
public class Board {
    public final int sideLength;
    public Cell[][] cells;
    
    // make board
    public Board() {
        sideLength = setBoardSize();
        cells = new Cell[sideLength][sideLength];
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                cells[i][j] = new Cell();
            }
        }
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                if (!cells[i][j].checkForBomb()) cells[i][j].setSurroundingBombs(countSurroundingBombs(i, j));
            }
        }
    }
    
    // gets user input to determine board size
    public int setBoardSize() {
        System.out.println("Welcome to Minesweeper. What size board do you want:");
        listBoardSizes();
        Scanner input = new Scanner(System.in);
        String userBoardChoice = input.next().toLowerCase();
        while (! (userBoardChoice.equals("a") || userBoardChoice.equals("b") ||userBoardChoice.equals("c"))) {
            System.out.println("Your selection was invalid. Please enter one of the following choices.");
            listBoardSizes();
            userBoardChoice = input.next().toLowerCase();
            
        }
        if (userBoardChoice.equals("a")) return 7;
        if (userBoardChoice.equals("b")) return 10;
        return 13;
    }
    
    // lists board sizes
    public void listBoardSizes() {
        System.out.println("A: 7x7");
        System.out.println("B: 10x10");
        System.out.println("C: 13x13");
    }
    
    // count surrounding bombs
    public int countSurroundingBombs(int row, int col) {
        int count = 0;
        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                if (i > -1 && i < sideLength && j > -1 && j < sideLength) {
                    if (cells[i][j].checkForBomb()) count++;
                }
            }
        }
        return count;
    }

    // return sideLength
    public int getSideLength() {
        return this.sideLength;
    }
    
    // reset all the Cell values in the board
    public void reset(Cell[][] cells) {
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                cells[i][j].reset();
            }
        }
    }
    
    // print board  
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("  ");
        for (int k = 0; k < sideLength; k++) {
            if (k < 9) builder.append("  " + (k + 1) + " ");
            else builder.append(" " + (k + 1) + " ");
        }
        builder.append("\n");
        for (int i = 0; i < sideLength; i++) {
            if (i < 9) builder.append((i + 1) + "  ");
            else builder.append((i + 1) + " ");
            for (int j = 0; j < sideLength; j++) {
                builder.append(cells[i][j].toString());
            }
            builder.append("\n");
        }
        builder.append("\n");
        return builder.toString();
    }
    
    // check for bombs
    public void checkForBomb(int guessRow, int guessCol) {
        if (cells[guessRow][guessCol].checkForBomb()) {
            cells[guessRow][guessCol].expand();
            System.out.println(this);
            System.out.println("You hit a bomb. You lose!");
            System.exit(0);
        }
        else expand(guessRow, guessCol);
    }
    
    // expand cells where there is no bomb
    public void expand(int row, int col) {
        if (row < 0 || row >= sideLength || col < 0 || col >= sideLength || cells[row][col].checkExpanded() == true) return;
        cells[row][col].expand();
        if (cells[row][col].getSurroundingBombs() == 0) {
            expand(row - 1, col - 1);
            expand(row - 1, col);
            expand(row - 1, col + 1);
            expand(row, col - 1);
            expand(row, col + 1);
            expand(row + 1, col - 1);
            expand(row + 1, col);
            expand(row + 1, col + 1);
        }
    }
    
    // plant flag on particular cell
    public void plantFlag(int row, int col) {
        cells[row][col].plantFlag();
    }
    
    // count all bombs on the board
    public int countTotalBombs() {
        int totalBombs = 0;
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                if (cells[i][j].checkForBomb()) totalBombs++;
            }
        }
        return totalBombs;
    }
    
    // see if user has navigated all bombs
    public boolean checkWin() {
        int totalBombs = countTotalBombs();
        int notBombGuess = 0;
        int correctFlags = 0;
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                if (!cells[i][j].checkForBomb() && cells[i][j].checkExpanded()) notBombGuess++; // check correct guesses
                if (cells[i][j].checkForBomb() && cells[i][j].checkFlag()) correctFlags++;   // check correct flags
                if (!cells[i][j].checkForBomb() && cells[i][j].checkFlag()) correctFlags--;   // check wrong flags
            }
        }
        if (correctFlags == totalBombs) return true;                 // all bombs are flagged
        if (notBombGuess == sideLength * sideLength - totalBombs) return true; // all non-bomb spaces are guessed
        return false;
    }
}   