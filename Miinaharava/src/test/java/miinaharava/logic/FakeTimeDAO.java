package miinaharava.logic;

import java.util.ArrayList;
import java.util.List;
import miinaharava.dao.TimeDAO;

public class FakeTimeDAO implements TimeDAO {

    List<Time> times;

    public FakeTimeDAO() {
        times = new ArrayList<>();
    }

    @Override
    public Time create(Time time) {
        times.add(time);
        return time;
    }

    @Override
    public List<Time> getAll() {
        return times;
    }

}
