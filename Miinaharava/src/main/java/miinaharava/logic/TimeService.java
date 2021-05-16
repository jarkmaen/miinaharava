package miinaharava.logic;

import java.util.List;
import miinaharava.dao.TimeDAO;

/**
 * Aikojen tallentamisesta vastaava luokka
 */
public class TimeService {

    private TimeDAO timeDAO;

    public TimeService(TimeDAO timeDAO) {
        this.timeDAO = timeDAO;
    }

    /**
     * Uuden ajan lisääminen times.txt tiedostoon
     *
     * @param difficulty valittu vaikeustaso
     * @param name syötetty nimi
     * @param minutes kuinka monta minuuttia kului
     * @param seconds kuinka monta sekunttia kului
     * @return true jos lisäys onnistui
     */
    public boolean createTime(String difficulty, String name, int minutes, int seconds) {
        Time time = new Time(difficulty, name, minutes, seconds);
        try {
            timeDAO.create(time);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public List<Time> getTimes() {
        return timeDAO.getAll();
    }
}
