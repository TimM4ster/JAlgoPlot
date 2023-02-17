package gui.graph;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraphScene extends Scene {

    private final VBox root;

    private final GraphPane graphPane = new GraphPane(this);

    private final Stage stage;

    public GraphScene(Stage stage) {
        super(new VBox());
        this.stage = stage;

        root = (VBox) getRoot();
        root.getChildren().add(graphPane);
    }

}
