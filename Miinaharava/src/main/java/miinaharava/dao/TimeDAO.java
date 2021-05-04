package miinaharava.dao;

import java.util.List;
import miinaharava.logic.Time;

public interface TimeDAO {

    Time create(Time time) throws Exception;

    List<Time> getAll();
}
