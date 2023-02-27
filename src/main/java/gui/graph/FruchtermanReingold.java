package gui.graph;

import javafx.geometry.Point2D;

public class FruchtermanReingold {

    private final Graph<?, ?> G;

    private final double k;

    private final double w;

    private final double h;

    private double t;

    public FruchtermanReingold(Graph<?, ?> G, double width, double height, final double c) {
        this.G = G;
        double area = width * height;
        this.k = c * Math.sqrt(area / G.getNumberOfVertices());
        this.w = width;
        this.h = height;
        this.t = width / 10;
    }

    public void run(int iterations) {
        for (int i = 0; i < iterations; i++) {
            // Calculate repulsive forces
            for (Vertex<?> v : G.getVertices()) {
                v.resetDisposition();
                for (Vertex<?> u : G.getVertices()) {
                    if (!v.equals(u)) {
                        Point2D diff = v.getPosition().subtract(u.getPosition());

                        Point2D diff_norm = diff.normalize();

                        double diff_magnitude = diff.magnitude();
                        double repulsion = f_r(diff_magnitude);

                        Point2D addition = diff_norm.multiply(repulsion);

                        Point2D old_disposition = v.getDisposition();

                        Point2D new_disposition = old_disposition.add(addition);

                        v.setDisposition(new_disposition);
                    }
                }
            }

            // Calculate attractive forces
            for (Edge<?> e : G.getEdges()) {
                Point2D diff = e.getFrom().getPosition().subtract(e.getTo().getPosition());

                Point2D diff_norm = diff.normalize();

                double diff_magnitude = diff.magnitude();
                double attraction = f_a(diff_magnitude);

                Point2D change = diff_norm.multiply(attraction);

                Point2D old_disposition_from = e.getFrom().getDisposition();
                Point2D old_disposition_to = e.getTo().getDisposition();

                Point2D new_disposition_from = old_disposition_from.subtract(change);
                Point2D new_disposition_to = old_disposition_to.add(change);

                e.getFrom().setDisposition(new_disposition_from);
                e.getTo().setDisposition(new_disposition_to);
            }

            // Limit the maximum displacement to the temperature t and
            // prevent from being displaced outside the frame
            for (Vertex<?> v : G.getVertices()) {
                Point2D disp_norm = v.getDisposition().normalize();

                double disp_magnitude = v.getDisposition().magnitude();

                double limited_disp_magnitude = Math.min(disp_magnitude, t);

                Point2D limited_disp = disp_norm.multiply(limited_disp_magnitude);

                Point2D old_position = v.getPosition();

                Point2D new_position = old_position.add(limited_disp);

                Point2D new_position_fixed = new Point2D(
                    Math.min(w - v.getRadius(), Math.max(0 + v.getRadius(), new_position.getX())),
                    Math.min(h - v.getRadius(), Math.max(0 + v.getRadius(), new_position.getY()))
                );

                v.setPosition(new_position_fixed);
            }

            // Reduce the temperature
            t = cool(t);
        }
    }

    private double f_a(double x) {
        return x * x / k;
    }

    private double f_r(double x) {
        return k * k / (x + 0.0000001);
    }

    private double cool(double t) {
        // Inverse linear decay
        return Math.max(t - 0.1, 0);
    }
    
}
