package miinaharava.logic;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimeServiceTest {

    FakeTimeDAO timeDAO;
    TimeService timeService;

    @Before
    public void setUp() {
        timeDAO = new FakeTimeDAO();
        timeService = new TimeService(timeDAO);
        timeDAO.create(new Time("EASY", "Test", 1, 1));
    }

    @Test
    public void listContainsInitializedTime() {
        List<Time> times = timeService.getTimes();
        assertEquals(1, times.size());
        Time time = times.get(0);
        assertEquals("EASY", time.getDifficulty());
        assertEquals("Test", time.getName());
        assertEquals(1, time.getMinutes());
        assertEquals(1, time.getSeconds());
    }

    @Test
    public void listContainsAddedTime() {
        addTime("MEDIUM", "Test", 4, 20);
        List<Time> times = timeService.getTimes();
        assertEquals(2, times.size());
        Time time = times.get(1);
        assertEquals("MEDIUM", time.getDifficulty());
        assertEquals("Test", time.getName());
        assertEquals(4, time.getMinutes());
        assertEquals(20, time.getSeconds());
    }

    // d = difficulty, n = name, m = minutes, s = seconds
    private void addTime(String d, String n, int m, int s) {
        timeService.createTime(d, n, m, s);
    }
}
