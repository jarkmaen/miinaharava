
import miinaharava.Board;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void tilesExist() {
        Board board = new Board();
        board.createBoard();
        assertTrue(board.grid != null || board.grid.length != 0 || board.grid[0].length != 0);
    }
}
