package miinaharava;

import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {

    public int x;
    public int y;
    public boolean hasBomb;
    public boolean isOpen = false;

    public Tile(int x, int y, boolean hasBomb) {
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;

        Rectangle rectangle = new Rectangle(30, 30);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(null);

        Text text = new Text();
        text.setText(hasBomb ? "X" : "1");
        text.setFont(Font.font(16));
        text.setVisible(false);

        getChildren().addAll(rectangle, text);
        setAlignment(Pos.CENTER);
        setTranslateX(x * 30);
        setTranslateY(y * 30);

        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                text.setVisible(true);
            }
        });
    }
}
