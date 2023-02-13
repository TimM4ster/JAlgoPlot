package gui.graph;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.shape.Line;

public class DirectedEdge<E extends Object> extends Edge<E> {

    public DirectedEdge(E value, Vertex<?> from, Vertex<?> to) {
        super(value, from, to);
        Arrow arrow = new Arrow();

        arrow.startXProperty().bind(edge.endXProperty());
        arrow.startYProperty().bind(edge.endYProperty());

        arrow.endXProperty().bind(edge.startXProperty());
        arrow.endYProperty().bind(edge.startYProperty());
        
        arrow.setMouseTransparent(true);
        getChildren().add(arrow);
    }
    

    private class Arrow extends Group {
        private final Line line;
    
        private static final double arrowLength = 20;
        private static final double arrowWidth = 15;

        private Arrow() {
            this(new Line(), new Line(), new Line());
        }
    
        private Arrow(Line line, Line arrow1, Line arrow2) {
            super(line, arrow1, arrow2);
            this.line = line;
            InvalidationListener updater = o -> {
                double ex = getEndX();
                double ey = getEndY();
                double sx = getStartX();
                double sy = getStartY();
    
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
                    System.out.println("factor: " + factor + " factorO: " + factorO);
    
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
    
            // add updater to properties
            startXProperty().addListener(updater);
            startYProperty().addListener(updater);
            endXProperty().addListener(updater);
            endYProperty().addListener(updater);
            updater.invalidated(null);
        }
    
        // start/end properties
    
        public final double getStartX() {
            return line.getStartX();
        }
    
        public final DoubleProperty startXProperty() {
            return line.startXProperty();
        }
        
        public final double getStartY() {
            return line.getStartY();
        }
    
        public final DoubleProperty startYProperty() {
            return line.startYProperty();
        }
        
        public final double getEndX() {
            return line.getEndX();
        }
    
        public final DoubleProperty endXProperty() {
            return line.endXProperty();
        }
        
        public final double getEndY() {
            return line.getEndY();
        }
    
        public final DoubleProperty endYProperty() {
            return line.endYProperty();
        }
    }
}