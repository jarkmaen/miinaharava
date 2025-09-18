package miinaharava.logic;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import miinaharava.dao.FakeGameResultDAO;

public class GameResultServiceTest {

    private FakeGameResultDAO fakeGameResultDAO;
    private GameResultService gameResultService;

    @Before
    public void setUp() {
        fakeGameResultDAO = new FakeGameResultDAO();
        gameResultService = new GameResultService(fakeGameResultDAO);
        fakeGameResultDAO.create(new GameResult("BEGINNER", "Test", 1, 1));
    }

    @Test
    public void getGameResultsReturnsCorrectlyInitializedList() {
        List<GameResult> gameResults = gameResultService.getGameResults();
        assertEquals(1, gameResults.size());

        GameResult gameResult = gameResults.get(0);
        assertEquals("BEGINNER", gameResult.getDifficulty());
        assertEquals("Test", gameResult.getName());
        assertEquals(1, gameResult.getMinutes());
        assertEquals(1, gameResult.getSeconds());
    }

    @Test
    public void getGameResultsReturnsListWithNewlyAddedResult() {
        addGameResult("INTERMEDIATE", "Test", 2, 50);

        List<GameResult> gameResults = gameResultService.getGameResults();
        assertEquals(2, gameResults.size());

        GameResult gameResult = gameResults.get(1);
        assertEquals("INTERMEDIATE", gameResult.getDifficulty());
        assertEquals("Test", gameResult.getName());
        assertEquals(2, gameResult.getMinutes());
        assertEquals(50, gameResult.getSeconds());
    }

    private void addGameResult(String difficulty, String name, int minutes, int seconds) {
        gameResultService.createGameResult(difficulty, name, minutes, seconds);
    }
}
