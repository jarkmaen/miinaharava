package miinaharava.logic;

/**
 * Tallennettua aikaa kuvaava luokka
 */
public class Time {

    private String difficulty;
    private String name;
    private int minutes;
    private int seconds;

    public Time(String difficulty, String name, int minutes, int seconds) {
        this.difficulty = difficulty;
        this.name = name;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public String getName() {
        return this.name;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getSeconds() {
        return this.seconds;
    }
}
