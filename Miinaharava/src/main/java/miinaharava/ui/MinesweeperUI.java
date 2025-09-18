package miinaharava.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import miinaharava.dao.GameResultTextFileDAO;
import miinaharava.logic.Board;
import miinaharava.logic.Tile;
import miinaharava.logic.GameResult;
import miinaharava.logic.GameResultService;

/**
 * Class responsible for the game's user interface.
 */
public class MinesweeperUI extends Application {

    private Board board;
    private GameResultService gameResultService;
    private Scene gameOverScene;
    private Text timer;
    private Timeline timeline;
    private int minutes = 0;
    private int seconds = 0;

    private Parent getMainMenuWindow(Stage stage) {
        VBox vBox = new VBox();

        Text text = new Text("MINESWEEPER");
        text.setFont(Font.font(44));

        HBox beginnerHBox = new HBox();
        Button beginnerButton = new Button("BEGINNER: 9x9 area / 10 mines");
        beginnerButton.setPrefWidth(270);
        beginnerButton.setOnAction(value -> {
            board = new Board(9, 9, "BEGINNER", 10);
            stage.setScene(new Scene(getBoardWindow(stage)));
        });
        ComboBox<String> beginnerComboBox = new ComboBox<>();
        addPersonalBestResults(beginnerComboBox, "BEGINNER");
        beginnerComboBox.setPromptText("PB");
        beginnerComboBox.setPrefWidth(60);
        beginnerComboBox.setMaxWidth(60);
        beginnerHBox.getChildren().addAll(beginnerButton, beginnerComboBox);
        beginnerHBox.setAlignment(Pos.CENTER);
        beginnerHBox.setSpacing(10);

        HBox intermediateHBox = new HBox();
        Button intermediateButton = new Button("INTERMEDIATE: 16x16 area / 40 mines");
        intermediateButton.setPrefWidth(270);
        intermediateButton.setOnAction(value -> {
            board = new Board(16, 16, "INTERMEDIATE", 40);
            stage.setScene(new Scene(getBoardWindow(stage)));
        });
        ComboBox<String> intermediateComboBox = new ComboBox<>();
        addPersonalBestResults(intermediateComboBox, "INTERMEDIATE");
        intermediateComboBox.setPromptText("PB");
        intermediateComboBox.setPrefWidth(60);
        intermediateComboBox.setMaxWidth(60);
        intermediateHBox.getChildren().addAll(intermediateButton, intermediateComboBox);
        intermediateHBox.setAlignment(Pos.CENTER);
        intermediateHBox.setSpacing(10);

        HBox expertHBox = new HBox();
        Button expertButton = new Button("EXPERT: 30x16 area / 99 mines");
        expertButton.setPrefWidth(270);
        expertButton.setOnAction(value -> {
            board = new Board(30, 16, "EXPERT", 99);
            stage.setScene(new Scene(getBoardWindow(stage)));
        });
        ComboBox<String> expertComboBox = new ComboBox<>();
        addPersonalBestResults(expertComboBox, "EXPERT");
        expertComboBox.setPromptText("PB");
        expertComboBox.setPrefWidth(60);
        expertComboBox.setMaxWidth(60);
        expertHBox.getChildren().addAll(expertButton, expertComboBox);
        expertHBox.setAlignment(Pos.CENTER);
        expertHBox.setSpacing(10);

        vBox.getChildren().addAll(text, beginnerHBox, intermediateHBox, expertHBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(600, 400);
        vBox.setSpacing(10);

        return vBox;
    }

    private Parent getBoardWindow(Stage stage) {
        BorderPane headerPane = new BorderPane();
        Button button = new Button("Main menu");
        button.setOnAction(value -> {
            stage.setScene(new Scene(getMainMenuWindow(stage)));
        });
        Text minesLeft = new Text("X: " + board.getMines());
        timer = new Text("0:0");
        headerPane.setLeft(button);
        headerPane.setCenter(minesLeft);
        headerPane.setRight(timer);
        BorderPane.setAlignment(timer, Pos.CENTER);
        headerPane.setPadding(new Insets(5, 5, 5, 5));
        headerPane.setPrefHeight(35);

        GridPane gridPane = new GridPane();
        int tileSize = 30;

        for (int y = 0; y < board.getBoardHeight(); y++) {
            for (int x = 0; x < board.getBoardWidth(); x++) {
                StackPane tile = new StackPane();

                Rectangle rectangle = new Rectangle(30, 30);
                rectangle.setFill(Color.LIGHTGRAY);
                rectangle.setStroke(Color.GRAY);

                Text flag = new Text();
                flag.setFont(Font.font(16));
                flag.setVisible(false);
                flag.setText("P");

                Text tileContent = new Text();
                tileContent.setText(
                        board.getBoard()[x][y].hasMine() ? "X"
                                : String.valueOf(board.getBoard()[x][y].getAdjacentMinesCount()));
                tileContent.setFont(Font.font(16));
                tileContent.setVisible(false);

                EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
                    if (e.getButton() == MouseButton.PRIMARY && !flag.isVisible() && !tileContent.isVisible()) {
                        rectangle.setFill(Color.WHITE);
                        tileContent.setVisible(true);
                        board.updateOpenTiles();

                        if (board.isGameOver()) {
                            timeline.stop();
                            stage.setScene(new Scene(getVictoryWindow(stage)));
                        }
                    } else if (e.getButton() == MouseButton.SECONDARY && !tileContent.isVisible()) {
                        flag.setVisible(!flag.isVisible());

                        if (flag.isVisible()) {
                            board.updateFlags(1);
                        } else {
                            board.updateFlags(-1);
                        }

                        minesLeft.setText("X: " + (board.getMines() - board.getFlags()));
                    }
                };

                tile.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                tile.getChildren().addAll(rectangle, tileContent, flag);
                tile.setAlignment(Pos.CENTER);
                tile.setTranslateX(x * tileSize);
                tile.setTranslateY(y * tileSize);

                gridPane.getChildren().add(tile);
            }
        }

        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                Tile tile = board.getTile((int) (e.getX() / tileSize), (int) (e.getY() / tileSize));

                if (tile.hasMine()) {
                    timeline.stop();
                    stage.setScene(gameOverScene);
                }
            }
        };

        gridPane.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(headerPane);
        borderPane.setCenter(gridPane);
        int prefW = tileSize * board.getBoardWidth() + 1;
        int prefH = tileSize * board.getBoardHeight() + 35 + 1;
        borderPane.setPrefSize(prefW, prefH);

        minutes = 0;
        seconds = 0;
        timeline.play();

        return borderPane;
    }

    private Parent getGameOverWindow(Stage stage) {
        VBox vBox = new VBox();

        Text text = new Text();
        text.setText("GAME OVER");
        text.setFont(Font.font(22));

        Button button = new Button("Return to main menu");
        button.setOnAction(value -> {
            stage.setScene(new Scene(getMainMenuWindow(stage)));
        });

        vBox.getChildren().addAll(text, button);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(300, 200);
        vBox.setSpacing(10);

        return vBox;
    }

    private Parent getVictoryWindow(Stage stage) {
        VBox vBox = new VBox();

        Text youWon = new Text();
        youWon.setText("YOU WON");
        youWon.setFont(Font.font(22));

        Text time = new Text();
        time.setText("TIME: " + minutes + ":" + seconds);
        time.setFont(Font.font(16));

        HBox hBox = new HBox();
        TextField textField = new TextField();
        textField.setPromptText("Enter your name");
        Button saveButton = new Button("Save and return to main menu");
        saveButton.setOnAction(value -> {
            if (!textField.getText().isEmpty()) {
                gameResultService.createGameResult(board.getDifficulty(), textField.getText(), minutes, seconds);
                stage.setScene(new Scene(getMainMenuWindow(stage)));
            }
        });
        hBox.getChildren().addAll(textField, saveButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);

        Button button = new Button("Return to main menu");
        button.setOnAction(value -> {
            stage.setScene(new Scene(getMainMenuWindow(stage)));
        });

        vBox.getChildren().addAll(youWon, time, hBox, button);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(400, 300);
        vBox.setSpacing(10);

        return vBox;
    }

    private void addPersonalBestResults(ComboBox<String> comboBox, String difficulty) {
        List<GameResult> gameResults = gameResultService.getGameResults();
        Collections.sort(gameResults);

        for (GameResult gameResult : gameResults) {
            if (gameResult.getDifficulty().equals(difficulty)) {
                String record = gameResult.getName() + " " + gameResult.getMinutes() + ":" + gameResult.getSeconds();
                comboBox.getItems().add(record);
            }
        }
    }

    private void stopwatch() {
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (seconds == 60) {
                    minutes++;
                    seconds = 0;
                }

                timer.setText(minutes + ":" + seconds);
                seconds++;
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        File file = new File("config.properties");

        if (!file.exists()) {
            try (final OutputStream output = new FileOutputStream("config.properties")) {
                properties.setProperty("gameResultsFile", "game_results.txt");
                properties.store(output, null);
            } catch (IOException e) {
                throw new RuntimeException("Could not create the config.properties file...", e);
            }
        }

        properties.load(new FileInputStream("config.properties"));
        String gameResultsFile = properties.getProperty("gameResultsFile");
        GameResultTextFileDAO gameResultTextFileDAO = new GameResultTextFileDAO(gameResultsFile);
        gameResultService = new GameResultService(gameResultTextFileDAO);
    }

    @Override
    public void start(Stage stage) {
        gameOverScene = new Scene(getGameOverWindow(stage));
        stage.setScene(new Scene(getMainMenuWindow(stage)));
        stage.setTitle("Minesweeper");
        stage.show();
        stopwatch();
    }
}
