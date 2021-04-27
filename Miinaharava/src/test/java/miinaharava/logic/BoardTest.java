package miinaharava.logic;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    Board board;

    @Test
    public void boardExists() {
        board = new Board(9, 9, 10);
        assertTrue(board != null);
    }

    @Test
    public void correctAmountOfMines() {
        board = new Board(9, 9, 10);
        int counter = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board.getGrid()[x][y].hasMine()) {
                    counter++;
                }
            }
        }
        assertTrue(counter == 10);
    }

    @Test
    public void tilesAreNumberedCorrectly() {
        board = new Board(2, 2, 1);
        boolean failed = false;
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                if (!board.getGrid()[x][y].hasMine() && board.getGrid()[x][y].getNumber() != 1) {
                    failed = true;
                }
            }
        }
        assertTrue(failed == false);
    }

    @Test
    public void gameEndsWhenAllSafeTilesAreOpen() {
        board = new Board(9, 9, 10);
        int safeTiles = board.getWidth() * board.getHeight() - board.getMinesCount();
        for (int i = 0; i < safeTiles; i++) {
            board.updateOpenTiles();
        }
        assertTrue(board.isGameOver() == true);
    }
}
