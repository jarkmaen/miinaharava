package miinaharava;

import java.util.HashSet;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Board extends Application {

    public int xTiles = 9;
    public int yTiles = 9;
    public int mines = 10;
    public Tile[][] grid;

    public Parent createBoard() {
        GridPane root = new GridPane();
        root.setPrefSize(181, 181);

        HashSet<Integer> set = new HashSet<>();
        Random random = new Random();

        for (int i = 0; i < mines; i++) {
            if (!set.add(random.nextInt((xTiles * yTiles - 0) + 1) + 0)) {
                i--;
            }
        }

        grid = new Tile[xTiles][yTiles];
        int counter = 0;

        for (int y = 0; y < yTiles; y++) {
            for (int x = 0; x < xTiles; x++) {
                boolean hasBomb = set.contains(counter);
                Tile tile = new Tile(y, x, hasBomb);
                grid[x][y] = tile;
                root.getChildren().add(tile);
                counter++;
            }
        }

        return root;
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(createBoard()));
        stage.setTitle("Miinaharava");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
