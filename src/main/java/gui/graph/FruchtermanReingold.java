package gui.graph;

public class FruchtermanReingold {

    private final Graph<?, ?> G;

    private final double k;

    private final int iterations;

    public FruchtermanReingold(Graph<?, ?> G, double width, double height, int iterations) {
        this.G = G;
        double area = width * height;
        this.k = Math.sqrt(area / G.numberOfVerticesProperty().get());
        this.iterations = iterations;
    }

    public void run() {
        for (int i = 0; i < iterations; i++) {
            // Calculate repulsive forces
            for (Vertex<?> v : G.getNodes()) {
                v.setDisposition(null);
                for (Vertex<?> u : G.getNodes()) {
                    if (!v.equals(u)) {
                        
                    }
                }
            }
        }
    }

    private double f_a(double x) {
        return x * x / k;
    }

    private double f_r(double x) {
        return k * k / x;
    }
    
}
