package minesweeper.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import minesweeper.logic.GameResult;

/**
 * Class responsible for saving and loading game results to and from a text file
 */
public class GameResultTextFileDAO implements GameResultDAO {

    private List<GameResult> gameResults;
    private String file;

    public GameResultTextFileDAO(String file) throws Exception {
        this.file = file;
        gameResults = new ArrayList<>();

        try {
            Scanner reader = new Scanner(new File(file));

            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(",");

                GameResult gameResult = new GameResult(parts[0],
                        parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]));

                gameResults.add(gameResult);
            }

            reader.close();
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (GameResult gameResult : gameResults) {
                writer.write(gameResult.getDifficulty() + ","
                        + gameResult.getName() + ","
                        + gameResult.getMinutes() + ","
                        + gameResult.getSeconds() + "\n");
            }
        }
    }

    @Override
    public GameResult create(GameResult gameResult) throws Exception {
        gameResults.add(gameResult);
        save();
        return gameResult;
    }

    @Override
    public List<GameResult> getAll() {
        return new ArrayList<>(gameResults);
    }
}
