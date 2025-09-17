package miinaharava.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.*;

import miinaharava.logic.GameResult;

public class GameResultTextFileDAOTest {

    private GameResultTextFileDAO gameResultTextFileDAO;
    private File gameResultsFile;

    @Rule
    private TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        gameResultsFile = temporaryFolder.newFile("game_results_test.txt");

        try (FileWriter file = new FileWriter(gameResultsFile.getAbsolutePath())) {
            file.write("EASY,Test,1,1\n");
        }

        gameResultTextFileDAO = new GameResultTextFileDAO(gameResultsFile.getAbsolutePath());
    }

    @Test
    public void gameResultsAreReadCorrectlyFromTextFile() {
        List<GameResult> gameResults = gameResultTextFileDAO.getAll();
        assertEquals(1, gameResults.size());

        GameResult gameResult = gameResults.get(0);
        assertEquals("EASY", gameResult.getDifficulty());
        assertEquals("Test", gameResult.getName());
        assertEquals(1, gameResult.getMinutes());
        assertEquals(1, gameResult.getSeconds());
    }

    @Test
    public void createdGameResultIsAddedToTextFile() throws Exception {
        gameResultTextFileDAO.create(new GameResult("MEDIUM", "Test", 2, 50));

        List<GameResult> gameResults = gameResultTextFileDAO.getAll();
        assertEquals(2, gameResults.size());

        GameResult gameResult = gameResults.get(1);
        assertEquals("MEDIUM", gameResult.getDifficulty());
        assertEquals("Test", gameResult.getName());
        assertEquals(2, gameResult.getMinutes());
        assertEquals(50, gameResult.getSeconds());
    }

    @After
    public void tearDown() {
        gameResultsFile.delete();
    }
}
