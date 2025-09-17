package miinaharava.logic;

/**
 * Yksittäistä ruutua kuvaava luokka
 */
public class Tile {

    private boolean hasMine;
    private int adjacentMinesCount;
    private int x;
    private int y;

    public Tile(int x, int y, boolean hasMine) {
        this.x = x;
        this.y = y;
        this.hasMine = hasMine;
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

    public boolean hasMine() {
        return hasMine;
    }

    public void setAdjacentMinesCount(int adjacentMinesCount) {
        this.adjacentMinesCount = adjacentMinesCount;
    }
}
