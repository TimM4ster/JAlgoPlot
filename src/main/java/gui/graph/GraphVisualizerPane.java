package gui.graph;

import javafx.geometry.Point2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class GraphVisualizerPane extends Pane {

    private final Graph<Integer, Integer> graph;

    private boolean onVertex = false;

    private Point2D draggingOrigin;

    public GraphVisualizerPane(Graph<Integer, Integer> graph) {
        this.graph = graph;
        setPrefSize(500, 500);

        //widthProperty().addListener(e -> paint());
        //heightProperty().addListener(e -> paint());

        paint();

        /* graph.addDirectedEdge(1, graph.getNodes().get(0), graph.getNodes().get(1));
        graph.addDirectedEdge(3, graph.getNodes().get(0), graph.getNodes().get(2)); */

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

                if (e.isPrimaryButtonDown() && !onVertex) {
                    draggingOrigin = new Point2D(e.getX(), e.getY());
                }
            }
        );

        setOnMouseDragged(
            e -> {
                if (!onVertex && draggingOrigin != null) {
                    Point2D current = new Point2D(e.getX(), e.getY());
                    Point2D delta = draggingOrigin.subtract(current);
                    
                    // Move all nodes
                    for (Vertex<?> vertex : graph.getNodes()) {
                        vertex.setLayoutX(vertex.getLayoutX() - delta.getX());
                        vertex.setLayoutY(vertex.getLayoutY() - delta.getY());
                    }

                    draggingOrigin = current;
                }
            }
        );

        setOnScroll(
            e -> {
                if (e.isControlDown()) {
                    double scale = e.getDeltaY() > 0 ? 1.1 : 1 / 1.1;
                    setScaleX(getScaleX() * scale);
                    setScaleY(getScaleY() * scale);
                    for (Vertex<?> vertex : graph.getNodes()) {
                        vertex.setZoomFactor(getScaleX());
                    }
                    e.consume();
                }
            }
        );
    }

    private void paint() {
        getChildren().clear();

        for (Vertex<Integer> vertex : graph.getNodes()) {
            vertex.isDraggingProperty().addListener(
                e -> {
                    if (vertex.isDraggingProperty().get()) {
                        onVertex = true;
                    } else {
                        onVertex = false;
                    }
                }
            );
        }

        getChildren().addAll(graph.getEdges());
        getChildren().addAll(graph.getNodes());
    }

    public Graph<Integer, Integer> getGraph() {
        return graph;
    }
}
