package miinaharava.logic;

import java.util.List;
import miinaharava.dao.TimeDAO;

public class TimeService {

    private TimeDAO timeDAO;

    public TimeService(TimeDAO timeDAO) {
        this.timeDAO = timeDAO;
    }

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
