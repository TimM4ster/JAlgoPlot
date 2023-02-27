package gui.graph;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;

/**
 * This class represents a graph. Each graph is composed of the following attributes:
 * <ul>
 *    <li>nodes: the list of vertices in the graph</li>
 *    <li>edges: the list of edges in the graph</li>
 *    <li>numberOfVerticesProperty: the number of vertices in the graph</li>
 *    <li>numberOfEdgesProperty: the number of edges in the graph</li>
 *    <li>numberOfUndirectedEdges: the number of undirected edges in the graph</li>
 *    <li>numberOfDirectedEdges: the number of directed edges in the graph</li>
 * </ul>
 * 
 * @param <V> the type of the value of the vertices in the graph
 * @param <E> the type of the value of the edges in the graph
 * 
 * @author Tim
 * @since v1.1.1
 * @version v1.1.1
 */
public class Graph<V, E> {
    
    /**
     * The list of vertices in the graph.
     */
    private ArrayList<Vertex<V>> vertices = new ArrayList<>();
    
    /**
     * The list of edges in the graph.
     */
    private ArrayList<Edge<E>> edges = new ArrayList<>();

    /**
     * The number of vertices in the graph encapsulated in a simple integer property.
     */
    private IntegerProperty numberVerticesProperty = new SimpleIntegerProperty(0);

    /**
     * The number of edges in the graph encapsulated in a simple integer property.
     */
    private IntegerProperty numberEdgesProperty = new SimpleIntegerProperty(0);

    /**
     * The number of undirected edges in the graph.
     */
    private int numberUndirectedEdges = 0;

    /**
     * The number of directed edges in the graph.
     */
    private int numberDirectedEdges = 0;

    /**
     * Returns the list of vertices in the graph.
     * 
     * @return  the list of vertices in the graph
     * @since v1.1.1
     */
    public ArrayList<Vertex<V>> getVertices() {
        return vertices;
    }

    /**
     * Returns the list of edges in the graph.
     * 
     * @return  the list of edges in the graph
     * @since v1.1.1
     */
    public ArrayList<? extends Edge<E>> getEdges() {
        return edges;
    }

    /**
     * Returns the number of vertices in the graph.
     * 
     * @return  the number of vertices in the graph
     * @since v1.1.1
     */
    public int getNumberOfVertices() {
        return vertices.size();
    }

    /**
     * Returns the number of edges in the graph.
     * 
     * @return  the number of edges in the graph
     * @since v1.1.1
     */
    public int getNumberOfEdges() {
        return edges.size();
    }

    /**
     * Returns the number of vertices in the graph encapsulated in a simple integer property.
     * 
     * @return  the number of vertices in the graph encapsulated in a simple integer property
     * @since v1.1.1
     */
    public IntegerProperty numberOfVerticesProperty() {
        return numberVerticesProperty;
    }

    /**
     * Returns the number of edges in the graph encapsulated in a simple integer property.
     * 
     * @return  the number of edges in the graph encapsulated in a simple integer property
     * @since v1.1.1
     */
    public IntegerProperty numberOfEdgesProperty() {
        return numberEdgesProperty;
    }

    /**
     * Returns the number of undirected edges in the graph.
     * 
     * @return  the number of undirected edges in the graph
     * @since v1.1.1
     */
    public int getNumberUndirectedEdges() {
        return numberUndirectedEdges;
    }

    /**
     * Returns the number of directed edges in the graph.
     * 
     * @return  the number of directed edges in the graph
     * @since v1.1.1
     */
    public int getNumberDirectedEdges() {
        return numberDirectedEdges;
    }

    /**
     * Adds a vertex to the graph.
     * 
     * @param value the value of the vertex
     * @since v1.1.1
     */
    public void addNode(V value) {
        vertices.add(new Vertex<V>(value));
        numberVerticesProperty.set(numberVerticesProperty.get() + 1);
    }

    /**
     * Adds a vertex to the graph at the specified position.
     * 
     * @param value the value of the vertex
     * @param x the x-coordinate of the vertex
     * @param y the y-coordinate of the vertex
     * @since v1.1.1
     */
    public void addNode(V value, double x, double y) {
        vertices.add(new Vertex<V>(value, x, y));
        numberVerticesProperty.set(numberVerticesProperty.get() + 1);
    }

    /**
     * Adds an undirected edge to the graph.
     * 
     * @param value the value of the edge
     * @param node1 the first vertex of the edge
     * @param node2 the second vertex of the edge
     * @since v1.1.1
     */
    public void addEdge(E value, Vertex<V> node1, Vertex<V> node2) {
        edges.add(
            new Edge<E>(
                value,
                node1,
                node2
            )
        );
        numberUndirectedEdges++;
        numberEdgesProperty.set(numberUndirectedEdges + numberDirectedEdges);
    }

    /**
     * Adds a directed edge to the graph.
     * 
     * @param value the value of the edge
     * @param node1 the first vertex of the edge
     * @param node2 the second vertex of the edge
     * @since v1.1.1
     */
    public void addDirectedEdge(E value, Vertex<V> node1, Vertex<V> node2) {
        edges.add(
            new DirectedEdge<E>(
                value,
                node1,
                node2
            )
        );
        numberDirectedEdges++;
        numberEdgesProperty.set(numberDirectedEdges + numberUndirectedEdges);
    }

    /**
     * Randomizes the positions of the vertices in the graph.
     * 
     * @since v1.1.1
     */
    public void randomizeVertexPositions() {
        for (Vertex<V> node : vertices) {
            node.setPosition(
                new Point2D(Math.random() * 500, Math.random() * 500)
            );
        }
    }


}
