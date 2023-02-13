package gui.graph;

import javafx.scene.layout.Pane;

public class GraphPane extends Pane {

    public GraphPane() {
        widthProperty().addListener(e -> paint());
        heightProperty().addListener(e -> paint());
    }

    private void paint() {
        getChildren().clear();
        Graph graph = new Graph(16);
        getChildren().addAll(graph.getEdges());
        getChildren().addAll(graph.getNodes());
    }
}
