package miinaharava.logic;

import java.util.List;

import miinaharava.dao.GameResultDAO;

/**
 * Pelitulosten hallinnasta vastaava luokka
 */
public class GameResultService {

    private GameResultDAO gameResultDAO;

    public GameResultService(GameResultDAO gameResultDAO) {
        this.gameResultDAO = gameResultDAO;
    }

    /**
     * Luo uuden pelituloksen ja tallentaa sen tekstitiedostoon
     *
     * @param difficulty pelin vaikeustaso
     * @param name       pelaajan nimi
     * @param minutes    pelin läpäisyyn kuluneet minuutit
     * @param seconds    pelin läpäisyyn kuluneet sekunnit
     * @return true jos tallennus onnistui, muuten false
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
