import java.util.*;
public class Cell {
    public boolean bomb, flag, expanded;
    public int surroundingBombs;
    public int row, col;
    
    // make cell
    public Cell()
    { bomb = makeBomb(); expanded = this.expanded; surroundingBombs = this.surroundingBombs; }
    
    // assign bomb to cell
    public boolean makeBomb() {
        if (Math.random() < .2) return true;
        return false;
    }
    
    // return bomb status to board
    public boolean checkForBomb() {
        if (this.bomb == true) return true;
        return false;
    }
    
    // expand cell
    public void expand() {
        this.expanded = true;
    }
    
    // return expanded status to board
    public boolean checkExpanded() {
        if (this.expanded == true) return true;
        return false;
    }
    
    // assign surrounding bombs integer to cell
    public void setSurroundingBombs(int surroundingBombs) {
        this.surroundingBombs = surroundingBombs;
    }
    
    // return expanded bombs integer to board
    public int getSurroundingBombs() {
        return surroundingBombs;
    }

    // assign flag value
    public void plantFlag() {
        this.flag = true;
    }
    
    // return flag status to board
    public boolean checkFlag() {
        if (this.flag == true) return true;
        return false;
    }
    
    // reset the board (for a new game when I feel like writing that part)
    public void reset() {
        this.bomb = false;
        this.flag = false;
        this.expanded = false;
        this.surroundingBombs = 0;
    }
    
    // print cell
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (this.expanded == false && this.flag == false) return builder.append("[ ] ").toString();
        if (this.expanded == false && this.flag == true) return builder.append(" ?  ").toString();
        if (this.expanded == true && this.bomb == true) return builder.append(" X  ").toString();
        if (this.expanded == true && this.bomb == false && this.surroundingBombs == 0) return builder.append("___ ").toString();
        return builder.append(" " + this.surroundingBombs + "  ").toString();
    }
}