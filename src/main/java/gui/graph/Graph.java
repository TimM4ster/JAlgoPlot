package gui.graph;

import java.util.ArrayList;

public class Graph<V, E> {
    
    private ArrayList<Vertex<V>> nodes = new ArrayList<>();

    private ArrayList<Edge<E>> edges = new ArrayList<>();

    public ArrayList<Vertex<V>> getNodes() {
        return nodes;
    }

    public ArrayList<? extends Edge<E>> getEdges() {
        return edges;
    }

    public void addNode(V value) {
        nodes.add(new Vertex<V>(value));
    }

    public void addNode(V value, double x, double y) {
        nodes.add(new Vertex<V>(value, x, y));
    }

    public void addEdge(E value, Vertex<V> node1, Vertex<V> node2) {
        edges.add(
            new Edge<E>(
                value,
                node1,
                node2
            )
        );
    }
}
