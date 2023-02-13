package gui.graph;

import java.util.ArrayList;

import javafx.scene.shape.Line;

public class Graph {
    
    private ArrayList<Vertex<String>> nodes = new ArrayList<>();

    private ArrayList<DirectedEdge<String>> edges = new ArrayList<>();

    public Graph(int size) {
        for (int i = 0; i < size; i++) {
            nodes.add(new Vertex<String>(
                String.valueOf(i)
            ));
        }

        /* for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                addEdge(nodes.get(i), nodes.get(j));
            }
        } */

        addDirectedEdge(nodes.get(0), nodes.get(1));
    }

    public ArrayList<Vertex<String>> getNodes() {
        return nodes;
    }

    public ArrayList<DirectedEdge<String>> getEdges() {
        return edges;
    }

    private void addDirectedEdge(Vertex<String> node1, Vertex<String> node2) {
        edges.add(
            new DirectedEdge<String>(
                "Test",
                node1,
                node2,
                true
            )
        );
    }

    private void addEdge(Vertex<String> node1, Vertex<String> node2) {
        Line edge = new Line();

        edge.startXProperty().bind(node1.centerXProperty());
        edge.startYProperty().bind(node1.centerYProperty());
        edge.endXProperty().bind(node2.centerXProperty());
        edge.endYProperty().bind(node2.centerYProperty());

        edge.setOnMousePressed(
            e -> {
                edge.setStroke(javafx.scene.paint.Color.RED);
            }
        );

        //edge.setMouseTransparent(true);

        //edges.add(edge);
    }
}
