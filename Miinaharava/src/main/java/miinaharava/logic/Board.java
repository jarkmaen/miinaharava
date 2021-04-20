package miinaharava.logic;

import java.util.HashSet;
import java.util.Random;

public class Board {

    private int xTiles;
    private int yTiles;
    private int mines;
    private Tile[][] grid;

    public Board(int xTiles, int yTiles, int mines) {
        this.xTiles = xTiles;
        this.yTiles = yTiles;
        this.mines = mines;
        this.grid = new Tile[xTiles][yTiles];

        addMines();
        addNumbers();
    }

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

    public Tile[][] getGrid() {
        return this.grid;
    }
}
