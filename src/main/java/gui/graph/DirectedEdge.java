package gui.graph;

import javafx.beans.InvalidationListener;
import javafx.scene.shape.Line;

public class DirectedEdge<E extends Object> extends Edge<E> {

    private final Line arrow1 = new Line();
    private final Line arrow2 = new Line();

    private static final double arrowLength = 6;
    private static final double arrowWidth = 3;

    public DirectedEdge(E value, Vertex<?> from, Vertex<?> to) {
        super(value, from, to);

        InvalidationListener updater = o -> {
            double distance = Math.sqrt(
                Math.pow(edge.getStartX() - edge.getEndX(), 2) +
                Math.pow(edge.getStartY() - edge.getEndY(), 2)
            );
            double directionX = edge.getEndX() - edge.getStartX();
            double directionY = edge.getEndY() - edge.getStartY();

            double distanceRatio = (distance - from.getRadius()) / distance;
            
            double ex = from.centerXProperty().doubleValue() + (distanceRatio * directionX);
            double ey = from.centerYProperty().doubleValue() + (distanceRatio * directionY);

            double sx = edge.getStartX();
            double sy = edge.getStartY();

            arrow1.setEndX(ex);
            arrow1.setEndY(ey);
            arrow2.setEndX(ex);
            arrow2.setEndY(ey);

            if (ex == sx && ey == sy) {
                // arrow parts of length 0
                arrow1.setStartX(ex);
                arrow1.setStartY(ey);
                arrow2.setStartX(ex);
                arrow2.setStartY(ey);
            } else {
                double factor = arrowLength / Math.hypot(sx-ex, sy-ey);
                double factorO = arrowWidth / Math.hypot(sx-ex, sy-ey);

                // part in direction of main line
                double dx = (sx - ex) * factor;
                double dy = (sy - ey) * factor;

                // part ortogonal to main line
                double ox = (sx - ex) * factorO;
                double oy = (sy - ey) * factorO;

                arrow1.setStartX(ex + dx - oy);
                arrow1.setStartY(ey + dy + ox);
                arrow2.setStartX(ex + dx + oy);
                arrow2.setStartY(ey + dy - ox);
            }
        };

        edge.startXProperty().addListener(updater);
        edge.startYProperty().addListener(updater);
        edge.endXProperty().addListener(updater);
        edge.endYProperty().addListener(updater);

        updater.invalidated(null);

        arrow1.strokeProperty().bind(edge.strokeProperty());
        arrow2.strokeProperty().bind(edge.strokeProperty());

        getChildren().addAll(arrow1, arrow2);
    }
}
