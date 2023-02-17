package gui.graph;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Graph<V, E> {
    
    private ArrayList<Vertex<V>> nodes = new ArrayList<>();

    private ArrayList<Edge<E>> edges = new ArrayList<>();

    private IntegerProperty numberOfVerticesProperty = new SimpleIntegerProperty(0);

    private IntegerProperty numberOfEdgesProperty = new SimpleIntegerProperty(0);

    private int numberOfUndirectedEdges = 0;

    private int numberOfDirectedEdges = 0;

    public static Graph<Integer, Integer> getEmptyIntegerGraph() {
        return new Graph<>();
    }

    public static Graph<Integer, Integer> getRandomIntegerGraph(int numberOfVertices, int numberOfEdges) {
        Graph<Integer, Integer> graph = new Graph<>();
        for (int i = 0; i < numberOfVertices; i++) {
            graph.addNode(i);
        }
        for (int i = 0; i < numberOfEdges; i++) {
            graph.addEdge(i, graph.getNodes().get((int) (Math.random() * numberOfVertices)), graph.getNodes().get((int) (Math.random() * numberOfVertices)));
        }
        return graph;
    }

    public static Graph<Double, Double> getEmptyDoubleGraph() {
        return new Graph<>();
    }

    public static Graph<String, String> getEmptyStringGraph() {
        return new Graph<>();
    }

    public ArrayList<Vertex<V>> getNodes() {
        return nodes;
    }

    public ArrayList<? extends Edge<E>> getEdges() {
        return edges;
    }

    public IntegerProperty numberOfVerticesProperty() {
        return numberOfVerticesProperty;
    }

    public int getNumberOfVertices() {
        return numberOfVerticesProperty.get();
    }

    public IntegerProperty numberOfEdgesProperty() {
        return numberOfEdgesProperty;
    }

    public int getNumberOfEdges() {
        return numberOfEdgesProperty.get();
    }

    public int getNumberOfUndirectedEdges() {
        return numberOfUndirectedEdges;
    }

    public int getNumberOfDirectedEdges() {
        return numberOfDirectedEdges;
    }

    public void addNode(V value) {
        nodes.add(new Vertex<V>(value));
        numberOfVerticesProperty.set(numberOfVerticesProperty.get() + 1);
        System.out.println(numberOfVerticesProperty.get());
    }

    public void addNode(V value, double x, double y) {
        nodes.add(new Vertex<V>(value, x, y));
        numberOfVerticesProperty.set(numberOfVerticesProperty.get() + 1);
    }

    public void addEdge(E value, Vertex<V> node1, Vertex<V> node2) {
        edges.add(
            new Edge<E>(
                value,
                node1,
                node2
            )
        );
        numberOfUndirectedEdges++;
        numberOfEdgesProperty.set(numberOfEdgesProperty.get() + 1);
        System.out.println(numberOfEdgesProperty.get());
    }

    public void addDirectedEdge(E value, Vertex<V> node1, Vertex<V> node2) {
        edges.add(
            new DirectedEdge<E>(
                value,
                node1,
                node2
            )
        );
        numberOfDirectedEdges++;
        numberOfEdgesProperty.set(numberOfEdgesProperty.get() + 1);
    }


}
