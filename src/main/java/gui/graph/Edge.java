package gui.graph;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;

public class Edge<E extends Object> extends Group {
    
    private E value;

    protected final Line edge = new Line();

    private final Label label = new Label();

    public Edge(E value, Vertex<?> from, Vertex<?> to) {
        this.value = value;
        label.setText(value.toString());

        edge.startXProperty().bind(from.centerXProperty());
        edge.startYProperty().bind(from.centerYProperty());
        edge.endXProperty().bind(to.centerXProperty());
        edge.endYProperty().bind(to.centerYProperty());

        edge.setOnMousePressed(
            e -> {
                edge.setStroke(javafx.scene.paint.Color.RED);
            }
        );
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Line getEdge() {
        return edge;
    }

}
