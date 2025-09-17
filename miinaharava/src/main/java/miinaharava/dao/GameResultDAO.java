package miinaharava.dao;

import java.util.List;

import miinaharava.logic.GameResult;

public interface GameResultDAO {

    GameResult create(GameResult gameResult) throws Exception;

    List<GameResult> getAll();
}
