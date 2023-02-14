package gui.graph;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class GraphScene extends Scene {

    private final GraphPane graphPane;

    private final Stage stage;

    public GraphScene(Stage stage) {
        super(new GraphPane());
        graphPane = (GraphPane) getRoot();
        graphPane.setPrefSize(500, 500);
        this.stage = stage;
        setOnKeyPressed(
            e -> {
                if (e.getCode().equals(KeyCode.SHIFT)) {
                    System.out.println("Shift pressed");
                    graphPane.dragging = true;
                }
            }
        );

        setOnKeyReleased(
            e -> {
                if (e.getCode().equals(KeyCode.SHIFT)) {
                    System.out.println("Shift released");
                    graphPane.dragging = false;
                }
            }
        );
    }

}
