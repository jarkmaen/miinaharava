package minesweeper.logic;

import java.util.HashSet;
import java.util.Random;

/**
 * Class responsible for the game board logic
 */
public class Board {

    private String difficulty;
    private Tile[][] board;
    private int flags;
    private int mines;
    private int openTiles;
    private int xTiles;
    private int yTiles;

    public Board(int xTiles, int yTiles, String difficulty, int mines) {
        this.xTiles = xTiles;
        this.yTiles = yTiles;
        this.difficulty = difficulty;
        this.mines = mines;

        flags = 0;
        board = new Tile[xTiles][yTiles];
        openTiles = 0;

        addMinesToBoard();
        addNumbersToTiles();
    }

    /**
     * Randomly places a specified number of mines onto the board
     */
    private void addMinesToBoard() {
        HashSet<Integer> set = new HashSet<>();
        Random random = new Random();

        for (int i = 0; i < mines; i++) {
            if (!set.add(random.nextInt(xTiles * yTiles))) {
                i--;
            }
        }

        int counter = 0;

        for (int x = 0; x < xTiles; x++) {
            for (int y = 0; y < yTiles; y++) {
                boolean mine = set.contains(counter);
                board[x][y] = new Tile(x, y, mine);
                counter++;
            }
        }
    }

    /**
     * Sets a number to each tile on the board, indicating how many mines are
     * adjacent to it
     */
    private void addNumbersToTiles() {
        for (int x = 0; x < xTiles; x++) {
            for (int y = 0; y < yTiles; y++) {
                if (!board[x][y].hasMine()) {
                    int count = 0;

                    if (y - 1 >= 0 && board[x][y - 1].hasMine()) {
                        count++;
                    }

                    if (xTiles > x + 1 && y - 1 >= 0 && board[x + 1][y - 1].hasMine()) {
                        count++;
                    }

                    if (xTiles > x + 1 && board[x + 1][y].hasMine()) {
                        count++;
                    }

                    if (xTiles > x + 1 && yTiles > y + 1 && board[x + 1][y + 1].hasMine()) {
                        count++;
                    }

                    if (yTiles > y + 1 && board[x][y + 1].hasMine()) {
                        count++;
                    }

                    if (x - 1 >= 0 && yTiles > y + 1 && board[x - 1][y + 1].hasMine()) {
                        count++;
                    }

                    if (x - 1 >= 0 && board[x - 1][y].hasMine()) {
                        count++;
                    }

                    if (x - 1 >= 0 && y - 1 >= 0 && board[x - 1][y - 1].hasMine()) {
                        count++;
                    }

                    board[x][y].setAdjacentMinesCount(count);
                }
            }
        }
    }

    /**
     * Checks if all safe tiles have been revealed
     *
     * @return true if all safe tiles are revealed, false otherwise
     */
    public boolean isGameOver() {
        return openTiles == xTiles * yTiles - mines;
    }

    /**
     * Opens the tile at the given coordinates. If the tile has zero adjacent mines,
     * recursively opens all adjacent tiles that are not mines and have not been
     * opened yet
     *
     * @param x the x coordinate of the tile to open
     * @param y the y coordinate of the tile to open
     */
    public void openTile(int x, int y) {
        if (x < 0 || y < 0 || x >= xTiles || y >= yTiles) {
            return;
        }

        Tile tile = board[x][y];

        if (tile.isOpen() || tile.hasMine()) {
            return;
        }

        tile.setOpen();
        updateOpenTiles();

        if (tile.getAdjacentMinesCount() == 0) {
            for (int offsetX = -1; offsetX <= 1; offsetX++) {
                for (int offsetY = -1; offsetY <= 1; offsetY++) {
                    if (offsetX != 0 || offsetY != 0) {
                        openTile(x + offsetX, y + offsetY);
                    }
                }
            }
        }
    }

    /**
     * Updates the count of opened tiles
     */
    public void updateOpenTiles() {
        openTiles++;
    }

    /**
     * Updates the number of placed flags
     *
     * @param delta the change in the number of flags, either -1 or +1
     */
    public void updateFlags(int delta) {
        flags += delta;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Tile getTile(int x, int y) {
        return board[x][y];
    }

    public Tile[][] getBoard() {
        return board;
    }

    public int getBoardHeight() {
        return yTiles;
    }

    public int getBoardWidth() {
        return xTiles;
    }

    public int getFlags() {
        return flags;
    }

    public int getMines() {
        return mines;
    }
}
