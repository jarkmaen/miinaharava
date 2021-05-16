package miinaharava.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import miinaharava.logic.Time;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class FileTimeDAOTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    FileTimeDAO fileTimeDAO;
    File timeFile;

    @Before
    public void setUp() throws Exception {
        timeFile = testFolder.newFile("testfile_times.txt");
        try (FileWriter file = new FileWriter(timeFile.getAbsolutePath())) {
            file.write("EASY,Test,1,1\n");
        }
        fileTimeDAO = new FileTimeDAO(timeFile.getAbsolutePath());
    }

    @Test
    public void timesAreReadCorrectlyFromFile() {
        List<Time> times = fileTimeDAO.getAll();
        assertEquals(1, times.size());
        Time time = times.get(0);
        assertEquals("EASY", time.getDifficulty());
        assertEquals("Test", time.getName());
        assertEquals(1, time.getMinutes());
        assertEquals(1, time.getSeconds());
    }

    @Test
    public void createdTimesAreListed() throws Exception {
        fileTimeDAO.create(new Time("MEDIUM", "Test", 4, 20));
        List<Time> times = fileTimeDAO.getAll();
        assertEquals(2, times.size());
        Time time = times.get(1);
        assertEquals("MEDIUM", time.getDifficulty());
        assertEquals("Test", time.getName());
        assertEquals(4, time.getMinutes());
        assertEquals(20, time.getSeconds());
    }

    @After
    public void tearDown() {
        timeFile.delete();
    }
}
