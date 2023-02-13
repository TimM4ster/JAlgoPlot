package gui.graph;

import java.util.ArrayList;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Graph {
    
    private ArrayList<Circle> nodes = new ArrayList<Circle>();

    private ArrayList<Line> edges = new ArrayList<Line>();

    private double draggingOffset = 0;
    private double initialX = 0;
    public Graph(int size) {
        for (int i = 0; i < size; i++) {
            nodes.add(new Circle());
        }

        for (Circle node : nodes) {
            node.setRadius(10);
            node.setCenterX(Math.random() * 500);
            node.setCenterY(Math.random() * 500);
            node.setOnMousePressed(
                e -> {
                    initialX = node.getCenterX();
                    draggingOffset = e.getSceneX() - node.getCenterX();
                }
            );

            node.setOnMouseDragged(
                e -> {
                    node.setCenterX(e.getSceneX() - draggingOffset);
                    node.setCenterY(e.getSceneY() - draggingOffset);
                }
            );

            node.setOnMouseReleased(
                e -> {
                    if (Math.abs(initialX - node.getCenterX()) < 5) {
                        node.setCenterX(initialX);
                    }
                }
            );
        }

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                addEdge(nodes.get(i), nodes.get(j));
            }
        }
    }

    public ArrayList<Circle> getNodes() {
        return nodes;
    }

    public ArrayList<Line> getEdges() {
        return edges;
    }

    private void addEdge(Circle node1, Circle node2) {
        Line edge = new Line();

        edge.startXProperty().bind(node1.centerXProperty());
        edge.startYProperty().bind(node1.centerYProperty());
        edge.endXProperty().bind(node2.centerXProperty());
        edge.endYProperty().bind(node2.centerYProperty());

        edge.setMouseTransparent(true);

        edges.add(edge);
    }
}
