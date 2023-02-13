package gui.graph;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
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

        Circle point = new Circle();
        point.setRadius(3);
        point.setFill(javafx.scene.paint.Color.RED);

        // Set the point to the position at which the edge intersects the circle
        point.centerXProperty().bind(edge.endXProperty().subtract(15));
        point.centerYProperty().bind(edge.endYProperty().subtract(15));

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
