package gui.graph;

import javafx.geometry.Point2D;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class GraphVisualizerPane extends Pane {

    private final Graph<Integer, Integer> graph;

    private boolean onVertex = false;

    private Vertex<Integer> selectedVertex;

    private Point2D draggingOrigin;

    public GraphVisualizerPane(Graph<Integer, Integer> graph) {
        this.graph = graph;
        setPrefSize(500, 500);

        paint();

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

                    if (onVertex) {
                        MenuItem setValItem = new MenuItem("Set Value");
                        setValItem.setOnAction(
                            event -> {
                                new ChangeValueStage<Integer>(selectedVertex);
                            }
                        );
                        contextMenu.getItems().add(setValItem);
                    }
                    
                    contextMenu.show(this.getScene().getWindow(), e.getScreenX(), e.getScreenY());
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
                    for (Vertex<?> vertex : graph.getVertices()) {
                        vertex.setPosition(
                            vertex.getPosition().subtract(delta)
                        );
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
                    for (Vertex<?> vertex : graph.getVertices()) {
                        vertex.setZoomFactor(getScaleX());
                    }
                    e.consume();
                }
            }
        );
    }

    private void paint() {
        getChildren().clear();

        for (Vertex<Integer> vertex : graph.getVertices()) {
            vertex.isDraggingProperty().addListener(
                e -> {
                    onVertex = vertex.isDraggingProperty().get();
                    selectedVertex = vertex;
                }
            );
        }

        getChildren().addAll(graph.getEdges());
        getChildren().addAll(graph.getVertices());
    }

    public Graph<Integer, Integer> getGraph() {
        return graph;
    }
}
