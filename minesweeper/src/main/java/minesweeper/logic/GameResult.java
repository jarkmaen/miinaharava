package minesweeper.logic;

/**
 * Class responsible for storing game result data
 */
public class GameResult implements Comparable<GameResult> {

    private String difficulty;
    private String name;
    private int minutes;
    private int seconds;

    public GameResult(String difficulty, String name, int minutes, int seconds) {
        this.difficulty = difficulty;
        this.name = name;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getName() {
        return name;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    @Override
    public int compareTo(GameResult compareResult) {
        if (this.minutes != compareResult.getMinutes()) {
            return this.minutes - compareResult.getMinutes();
        } else {
            return this.seconds - compareResult.getSeconds();
        }
    }
}
