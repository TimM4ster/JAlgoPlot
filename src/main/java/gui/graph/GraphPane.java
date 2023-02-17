package gui.graph;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class GraphPane extends HBox {
    
    private final GraphVisualizerPane graphVisualizerPane = new GraphVisualizerPane();

    private final GraphModifierPane graphModifierPane = new GraphModifierPane();

    public GraphPane(Scene scene) {
        super();
        setPrefSize(700, 500);

        graphVisualizerPane.prefWidthProperty().bind(scene.widthProperty().subtract(graphModifierPane.getMinWidth()));

        getChildren().addAll(graphVisualizerPane, graphModifierPane);
    }

}
