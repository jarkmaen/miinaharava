package minesweeper.logic;

import java.util.List;

import minesweeper.dao.GameResultDAO;

/**
 * Class responsible for managing game results
 */
public class GameResultService {

    private GameResultDAO gameResultDAO;

    public GameResultService(GameResultDAO gameResultDAO) {
        this.gameResultDAO = gameResultDAO;
    }

    /**
     * Creates a new game result and saves it to a text file
     *
     * @param difficulty the chosen difficulty level ("BEGINNER", "INTERMEDIATE" or
     *                   "EXPERT")
     * @param name       the player's name
     * @param minutes    minutes taken to complete the game
     * @param seconds    seconds taken to complete the game
     * @return true if saving was successful, false otherwise
     */
    public boolean createGameResult(String difficulty, String name, int minutes, int seconds) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        GameResult gameResult = new GameResult(difficulty, name, minutes, seconds);

        try {
            gameResultDAO.create(gameResult);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<GameResult> getGameResults() {
        return gameResultDAO.getAll();
    }
}
