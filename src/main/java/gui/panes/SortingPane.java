package gui.panes;

import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SortingPane extends GridPane {

    private int[] array;

    private final double TOP_OFFSET = 5.0;

    private final double RIGHT_OFFSET = 10.0;

    private final double BOTTOM_OFFSET = 5.0;

    private final double LEFT_OFFSET = 10.0;

    private final Insets PADDING = new Insets(TOP_OFFSET, RIGHT_OFFSET, BOTTOM_OFFSET, LEFT_OFFSET);

    public SortingPane() {
        setPadding(PADDING);
        setHgap(10);
    }

    public void setArray(int[] array) {
        this.array = array;
        drawRectangles();
    }

    private void drawRectangles() {
        Rectangle[] rectangles = new Rectangle[array.length];
        for (int i = 0; i < array.length; i++) {
            rectangles[i] = new Rectangle(400.0 / array.length, 300.0 * (double) array[i] / array.length);
            setValignment(rectangles[i], VPos.BOTTOM);
            add(rectangles[i], i, 0);
        }
        setStyle("-fx-border-color: black");
    }
}
