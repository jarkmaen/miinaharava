package miinaharava.logic;

import java.util.HashSet;
import java.util.Random;

/**
 * Pelilaudan logiikasta vastaava luokka
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
     * Asettaa tietyn määrän miinoja satunnaisesti kentälle
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
                boolean hasMine = set.contains(counter);
                board[x][y] = new Tile(x, y, hasMine);
                counter++;
            }
        }
    }

    /**
     * Asettaa jokaiselle ruudulle numeron, mikä kertoo kuinka monta miinaa sen
     * ympärillä on
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
     * Tarkistaa ovatko kaikki turvalliset ruudut avattu
     *
     * @return true jos on, muuten false
     */
    public boolean isGameOver() {
        return openTiles == xTiles * yTiles - mines;
    }

    /**
     * Päivittää avattujen ruutujen määrän
     */
    public void updateOpenTiles() {
        openTiles++;
    }

    /**
     * Päivittää asetettujen lippujen määrän
     * 
     * @param delta lippujen määrän muutos, joka on -1 tai +1
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
