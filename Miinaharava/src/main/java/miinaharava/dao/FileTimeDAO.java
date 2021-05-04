package miinaharava.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import miinaharava.logic.Time;

public class FileTimeDAO implements TimeDAO {

    private List<Time> times;
    private String file;

    public FileTimeDAO(String file) throws Exception {
        times = new ArrayList<>();
        this.file = file;
        System.out.println(file);
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(",");
                Time t = new Time(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                times.add(t);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Time time : times) {
                writer.write(time.getDifficulty() + "," + time.getName() + "," + time.getMinutes() + "," + time.getSeconds() + "\n");
            }
        }
    }

    @Override
    public Time create(Time time) throws Exception {
        times.add(time);
        save();
        return time;
    }

    @Override
    public List<Time> getAll() {
        return times;
    }
}
