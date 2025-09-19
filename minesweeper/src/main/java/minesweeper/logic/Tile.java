package minesweeper.logic;

/**
 * Class representing a single tile on the board
 */
public class Tile {

    private boolean mine;
    private boolean open;
    private int adjacentMinesCount;
    private int x;
    private int y;

    public Tile(int x, int y, boolean mine) {
        this.x = x;
        this.y = y;
        this.mine = mine;

        open = false;
    }

    public int getAdjacentMinesCount() {
        return adjacentMinesCount;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean hasMine() {
        return mine;
    }

    public void setAdjacentMinesCount(int adjacentMinesCount) {
        this.adjacentMinesCount = adjacentMinesCount;
    }

    public void setOpen() {
        open = true;
    }
}
