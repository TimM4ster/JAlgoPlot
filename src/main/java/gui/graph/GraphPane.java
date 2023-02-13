package gui.graph;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class GraphPane extends Pane {

    private final Graph<Integer, Integer> graph = new Graph<>();

    public GraphPane() {
        widthProperty().addListener(e -> paint());
        heightProperty().addListener(e -> paint());

        for (int i = 0; i < 10; i++) {
            graph.addNode(i);
        }

        graph.addEdge(1, graph.getNodes().get(0), graph.getNodes().get(1));

        setOnMousePressed(
            e -> {
                if (e.isSecondaryButtonDown()) {
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem addVertexItem = new MenuItem("Add Vertex");
                    addVertexItem.setOnAction(
                        event -> {
                            graph.addNode(0, e.getX(), e.getY());
                            paint();
                        }
                    );
                    contextMenu.getItems().add(addVertexItem);
                    contextMenu.show(this, e.getScreenX(), e.getScreenY());
                }
            }
        );
    }

    private void paint() {
        getChildren().clear();
        getChildren().addAll(graph.getEdges());
        getChildren().addAll(graph.getNodes());
    }
}
