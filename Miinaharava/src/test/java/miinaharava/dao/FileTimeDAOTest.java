package miinaharava.dao;

import org.junit.Test;
import static org.junit.Assert.*;

public class FileTimeDAOTest {

    FileTimeDAO fileTimeDAO;

    @Test
    public void readingFileDoesNotThrowException() throws Exception {
        String timeFile = "EASY,Test,0,0";
        fileTimeDAO = new FileTimeDAO(timeFile);
        assertTrue(fileTimeDAO != null);
    }
}
