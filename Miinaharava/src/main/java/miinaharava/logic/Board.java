package miinaharava.logic;

import java.util.HashSet;
import java.util.Random;

/**
 * Pelilaudan logiikasta vastaava luokka
 */
public class Board {

    private int xTiles;
    private int yTiles;
    private int mines;
    private String difficulty;
    private int tilesOpen;
    private int flagsSet;
    private Tile[][] grid;

    public Board(int xTiles, int yTiles, int mines, String difficulty) {
        this.xTiles = xTiles;
        this.yTiles = yTiles;
        this.mines = mines;
        this.difficulty = difficulty;
        this.tilesOpen = 0;
        this.flagsSet = 0;
        this.grid = new Tile[xTiles][yTiles];

        addMines();
        addNumbers();
    }

    /**
     * Asettaa satunnaisesti tietyn määrän miinoja kentälle
     */
    private void addMines() {
        HashSet<Integer> set = new HashSet<>();
        Random random = new Random();
        int counter = 0;

        for (int i = 0; i < mines; i++) {
            if (!set.add(random.nextInt(xTiles * yTiles - 1))) {
                i--;
            }
        }

        for (int x = 0; x < xTiles; x++) {
            for (int y = 0; y < yTiles; y++) {
                grid[x][y] = new Tile(x, y, set.contains(counter));
                counter++;
            }
        }
    }

    /**
     * Asettaa jokaiselle ruudulle numeron, mikä kertoo kuinka monta pommia on
     * sen ympärillä
     */
    private void addNumbers() {
        for (int x = 0; x < xTiles; x++) {
            for (int y = 0; y < yTiles; y++) {
                if (!grid[x][y].hasMine()) {
                    int mineCount = 0;
                    if (y - 1 >= 0 && grid[x][y - 1].hasMine()) {
                        mineCount++;
                    }
                    if (xTiles > x + 1 && y - 1 >= 0 && grid[x + 1][y - 1].hasMine()) {
                        mineCount++;
                    }
                    if (xTiles > x + 1 && grid[x + 1][y].hasMine()) {
                        mineCount++;
                    }
                    if (xTiles > x + 1 && yTiles > y + 1 && grid[x + 1][y + 1].hasMine()) {
                        mineCount++;
                    }
                    if (yTiles > y + 1 && grid[x][y + 1].hasMine()) {
                        mineCount++;
                    }
                    if (x - 1 >= 0 && yTiles > y + 1 && grid[x - 1][y + 1].hasMine()) {
                        mineCount++;
                    }
                    if (x - 1 >= 0 && grid[x - 1][y].hasMine()) {
                        mineCount++;
                    }
                    if (x - 1 >= 0 && y - 1 >= 0 && grid[x - 1][y - 1].hasMine()) {
                        mineCount++;
                    }
                    grid[x][y].setNumber(mineCount);
                }
            }
        }
    }

    /**
     * Tarkistaa onko kaikki turvalliset ruudut avattu
     *
     * @return true jos on, muuten false
     */
    public boolean isGameOver() {
        return tilesOpen == xTiles * yTiles - mines;
    }

    /**
     * Päivittää avattujen ruutujen määrän
     */
    public void updateOpenTiles() {
        tilesOpen++;
    }

    /**
     * Päivittää asetettujen lippujen määrän
     */
    public void updateFlagsSet(int x) {
        flagsSet = flagsSet + x;
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public Tile getTile(int x, int y) {
        return grid[x][y];
    }

    public int getWidth() {
        return xTiles;
    }

    public int getHeight() {
        return yTiles;
    }

    public int getMinesCount() {
        return mines;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getFlagsSet() {
        return flagsSet;
    }
}
