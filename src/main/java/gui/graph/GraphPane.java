package gui.graph;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class GraphPane extends HBox {

    //private final Graph<Integer, Integer> graph = Graph.getEmptyIntegerGraph();
    private final Graph<Integer, Integer> graph = Graph.getRandomIntegerGraph(40, 20);

    private final GraphModifierPane graphModifierPane = new GraphModifierPane(this, graph);
    
    private final GraphVisualizerPane graphVisualizerPane = new GraphVisualizerPane(graph);

    public GraphPane(Scene scene) {
        super();
        setPrefSize(700, 500);

        graphVisualizerPane.prefWidthProperty().bind(scene.widthProperty().subtract(graphModifierPane.getMinWidth()));

        getChildren().addAll(graphVisualizerPane, graphModifierPane);
    }

}
