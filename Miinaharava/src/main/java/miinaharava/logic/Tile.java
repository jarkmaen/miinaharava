package miinaharava.logic;

public class Tile {

    private int x;
    private int y;
    private int number;
    private boolean hasMine;

    public Tile(int x, int y, boolean hasMine) {
        this.x = x;
        this.y = y;
        this.hasMine = hasMine;
    }

    public boolean hasMine() {
        return this.hasMine;
    }

    public void setNumber(int n) {
        this.number = n;
    }

    public int getNumber() {
        return this.number;
    }
}
