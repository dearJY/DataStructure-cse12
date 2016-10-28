

//package hw4

public class Square {

    private int type;
    private int row;
    private int col;
    private Square pre = null;
    private boolean isvisited = false;
    private boolean isexplored = false;

    /**
     * A constructor that takes the row, col and type of
     * the square as parameters and creates a new Square object.
     */
    Square(int row,int col,int type) {

        this.row = row;
        this.col = col;
        this.type = type;

    }

    public String toString() {

        switch(type) {

            case 0: return "_";//empty space
            case 1: return "#";//wall
            case 2: return "S";//start
            case 3: return "E";//exit
            case 4: return "o";//on solver work list
            case 5: return ".";//has been explored
            case 6: return "x";//final path to the exit
            default: return "";
            // solver work list??
        }
    }

    public int getRow() {

        return this.row;
    }

    public int getCol() {

        return this.col;
    }

    public int getType() {

        return this.type;
    }

    public boolean isVisited() {

        return this.isvisited;
    }

    public boolean isFinish() {

        if(this.type == 3) {
            return true;
        }else {
            return false;
        }
    }

    public boolean isStart() {

        if(this.type == 2) {
            return true;
        }else {
            return false;
        }
    }

    public boolean isValid() {

        if(this.type == 0) {
            return true;
        }else {
            return false;
        }
    }

    public boolean isExplored() {

        return this.isexplored;
    }

    void setVisited() {

        this.isvisited = true;
    }

    void setExplored() {

        this.isexplored = true;
    }

    void clearVisited() {
        this.isvisited = false;
    }

    public void changeType(int type) {

        this.type = type;
    }

    public Square getPre() {

        return this.pre;
    }

    public void setPre(Square pre) {

        this.pre = pre;
    }

    public void clear() {

        if(this.type >= 4 ) {
            this.type = 0;
            this.pre = null;
        }
        this.clearVisited();
    }

    public String coordinate() {

        int row = getRow();
        int col = getCol();
        String cor = " [ " + row + ", " + col + " ], ";
        return cor;
    }
}
