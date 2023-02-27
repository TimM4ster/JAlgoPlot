package gui.graph;

public class GraphGenerator {

    public static Graph<Integer, Integer> getEmptyIntegerGraph() {
        return new Graph<>();
    }

    public static Graph<Integer, Integer> getFullyConnectedIntegerGraph(int numberOfVertices) {
        Graph<Integer, Integer> graph = new Graph<>();
        for (int i = 0; i < numberOfVertices; i++) {
            graph.addNode(i);
        }
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = i + 1; j < numberOfVertices; j++) {
                graph.addEdge(i, graph.getVertices().get(i), graph.getVertices().get(j));
            }
        }
        return graph;
    }

    public static Graph<Integer, Integer> getRandomIntegerGraph(int numberOfVertices, int numberOfEdges) {
        Graph<Integer, Integer> graph = new Graph<>();
        for (int i = 0; i < numberOfVertices; i++) {
            graph.addNode(i);
        }
        for (int i = 0; i < numberOfEdges; i++) {
            graph.addDirectedEdge(i, graph.getVertices().get((int) (Math.random() * numberOfVertices)), graph.getVertices().get((int) (Math.random() * numberOfVertices)));
        }
        return graph;
    }

    public static Graph<Double, Double> getEmptyDoubleGraph() {
        return new Graph<>();
    }

    public static Graph<String, String> getEmptyStringGraph() {
        return new Graph<>();
    }
    
}
