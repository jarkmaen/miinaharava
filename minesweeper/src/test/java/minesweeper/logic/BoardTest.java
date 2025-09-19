package minesweeper.logic;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    @Test
    public void constructorInitializesBoard() {
        board = new Board(9, 9, "BEGINNER", 10);

        assertNotNull(board);
        assertEquals(9, board.getBoardWidth());
        assertEquals(9, board.getBoardHeight());
        assertEquals("BEGINNER", board.getDifficulty());
        assertEquals(10, board.getMines());
    }

    @Test
    public void boardContainsCorrectNumberOfMines() {
        board = new Board(9, 9, "BEGINNER", 10);
        int counter = 0;

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board.getBoard()[x][y].hasMine()) {
                    counter++;
                }
            }
        }

        assertTrue(counter == 10);
    }

    @Test
    public void adjacentMinesAreCountedCorrectly() {
        board = new Board(2, 2, "BEGINNER", 1);
        int mineX = -1;
        int mineY = -1;

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                if (board.getBoard()[x][y].hasMine()) {
                    mineX = x;
                    mineY = y;
                }
            }
        }

        boolean allCorrect = true;

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                if (!(x == mineX && y == mineY)) {
                    if (board.getBoard()[x][y].getAdjacentMinesCount() != 1) {
                        allCorrect = false;
                    }
                }
            }
        }

        assertTrue(allCorrect);
    }

    @Test
    public void gameEndsWhenAllNonMineTilesAreCleared() {
        board = new Board(9, 9, "BEGINNER", 10);
        int safeTiles = board.getBoardWidth() * board.getBoardHeight() - board.getMines();

        for (int i = 0; i < safeTiles; i++) {
            board.updateOpenTiles();
        }

        assertTrue(board.isGameOver() == true);
    }

    @Test
    public void openingTileWithNoAdjacentMinesOpensAllConnectedSafeTiles() {
        board = new Board(3, 3, "CUSTOM", 0);

        board.openTile(1, 1);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertTrue(board.getBoard()[x][y].isOpen());
            }
        }
    }
}
