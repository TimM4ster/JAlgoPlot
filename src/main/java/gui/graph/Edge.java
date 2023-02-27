package gui.graph;

import javafx.scene.Group;
import javafx.scene.control.Tooltip;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Edge<E extends Object> extends Group {
    
    private E value;

    protected final Line edge = new Line();

    private final Tooltip tooltip = new Tooltip();

    protected Vertex<?> from;

    protected Vertex<?> to;

    public Edge(E value, Vertex<?> from, Vertex<?> to) {
        this.value = value;
        this.from = from;
        this.to = to;
        tooltip.setText("Weight: " + value.toString());

        tooltip.setShowDelay(Duration.seconds(0));
        Tooltip.install(edge, tooltip);

        setFrom(from);
        setTo(to);

        edge.setStrokeWidth(2);
    
        edge.setOnMousePressed(
            e -> {
                edge.setStroke(javafx.scene.paint.Color.RED);
            }
        );

        getChildren().addAll(edge);
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

    public Vertex<?> getFrom() {
        return from;
    }

    public void setFrom(Vertex<?> from) {
        this.from = from;
        edge.startXProperty().bind(
            from.layoutXProperty().add(from.getRadius())
        );

        edge.startYProperty().bind(
            from.layoutYProperty().add(from.getRadius())
        );
    }

    public Vertex<?> getTo() {
        return to;
    }

    public void setTo(Vertex<?> to) {
        this.to = to;
        edge.endXProperty().bind(
            to.layoutXProperty().add(to.getRadius())
        );

        edge.endYProperty().bind(
            to.layoutYProperty().add(to.getRadius())
        );
    }

}
