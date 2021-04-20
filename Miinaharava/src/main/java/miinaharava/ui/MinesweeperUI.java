package miinaharava.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import miinaharava.logic.Board;

public class MinesweeperUI extends Application {

    private Parent gameWindow() {
        Board board = new Board(9, 9, 10);
        GridPane root = new GridPane();
        root.setPrefSize(271, 271);

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                StackPane tile = new StackPane();

                Rectangle rectangle = new Rectangle(30, 30);
                rectangle.setFill(Color.LIGHTGRAY);
                rectangle.setStroke(Color.GRAY);

                Text flag = new Text();
                flag.setFont(Font.font(16));
                flag.setVisible(false);
                flag.setText("P");

                Text value = new Text();
                value.setText(board.getGrid()[x][y].hasMine() ? "X" : String.valueOf(board.getGrid()[x][y].getNumber()));
                value.setFont(Font.font(16));
                value.setVisible(false);

                EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
                    if (e.getButton() == MouseButton.PRIMARY && !flag.isVisible()) {
                        rectangle.setFill(Color.WHITE);
                        value.setVisible(true);
                    } else if (e.getButton() == MouseButton.SECONDARY && !value.isVisible()) {
                        flag.setVisible(!flag.isVisible());
                    }
                };

                tile.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
                tile.getChildren().addAll(rectangle, value, flag);
                tile.setAlignment(Pos.CENTER);
                tile.setTranslateX(x * 30);
                tile.setTranslateY(y * 30);

                root.getChildren().add(tile);
            }
        }

        return root;
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(gameWindow()));
        stage.setTitle("Miinaharava");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
