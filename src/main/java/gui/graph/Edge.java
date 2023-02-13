package gui.graph;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Edge<E extends Object> extends Group {
    
    private E value;

    protected final Line edge = new Line();

    private final Label label = new Label();

    protected Circle point = new Circle();

    protected final Vertex<?> from;

    protected final Vertex<?> to;

    public Edge(E value, Vertex<?> from, Vertex<?> to) {
        this.value = value;
        this.from = from;
        this.to = to;
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

        getChildren().addAll(edge, point);
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
