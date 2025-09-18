package minesweeper.dao;

import java.util.ArrayList;
import java.util.List;

import minesweeper.logic.GameResult;

public class FakeGameResultDAO implements GameResultDAO {

    List<GameResult> gameResults;

    public FakeGameResultDAO() {
        gameResults = new ArrayList<>();
    }

    @Override
    public GameResult create(GameResult gameResult) {
        gameResults.add(gameResult);
        return gameResult;
    }

    @Override
    public List<GameResult> getAll() {
        return gameResults;
    }
}
