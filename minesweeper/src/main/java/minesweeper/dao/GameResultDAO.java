package minesweeper.dao;

import java.util.List;

import minesweeper.logic.GameResult;

public interface GameResultDAO {

    GameResult create(GameResult gameResult) throws Exception;

    List<GameResult> getAll();
}
