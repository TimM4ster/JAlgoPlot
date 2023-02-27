package gui.graph;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;

public class GraphModifierPane extends VBox {

    private final GraphPane graphPane;

    private final Graph<Integer, Integer> graph;

    private final Label numberOfVerticesLabel;

    private final Label numberOfEdgesLabel;

    private final Tooltip numberOfEdgesTooltip;

    private final Button addVertexButton = new Button("Add Vertex");

    private final Button addEdgeButton = new Button("Add Edge");

    private final Button testRunButton = new Button("Test Run");

    private final Button testShuffleButton = new Button("Test Shuffle");
    
    public GraphModifierPane(GraphPane graphPane, Graph<Integer, Integer> graph) {
        super();
        this.graphPane = graphPane;
        this.graph = graph;

        numberOfVerticesLabel = new Label("Number of Vertices: " + graph.numberOfVerticesProperty().get());

        numberOfEdgesLabel = new Label("Number of Edges: " + graph.numberOfEdgesProperty().get());

        numberOfEdgesTooltip = new Tooltip("Undirected: " + graph.getNumberUndirectedEdges() + "\nDirected: " + graph.getNumberDirectedEdges());
        
        setMinWidth(200);
        setBackground(
            new Background(
                new BackgroundFill(
                    javafx.scene.paint.Color.LIGHTGRAY,
                    CornerRadii.EMPTY,
                    Insets.EMPTY
                )
            )
        );

        graph.numberOfVerticesProperty().addListener(
            (observable, oldValue, newValue) -> {
                numberOfVerticesLabel.setText("Number of Vertices: " + newValue);
            }
        );

        graph.numberOfEdgesProperty().addListener(
            (observable, oldValue, newValue) -> {
                numberOfEdgesLabel.setText("Number of Edges: " + newValue);
                numberOfEdgesTooltip.setText(
                    "Undirected: " + graph.getNumberUndirectedEdges() + "\nDirected: " + graph.getNumberDirectedEdges()
                );
            }
        );

        numberOfEdgesLabel.setTooltip(numberOfEdgesTooltip);

        testRunButton.setOnAction(
            event -> {
                FruchtermanReingold fr = new FruchtermanReingold(graph, graphPane.getWidth() - 200, graphPane.getHeight(), 1.0);

                fr.run(20);
            }
        );

        testShuffleButton.setOnAction(
            event -> {
                graph.randomizeVertexPositions();
            }
        );


        getChildren().addAll(
            numberOfVerticesLabel,
            numberOfEdgesLabel,
            addVertexButton,
            addEdgeButton,
            testRunButton,
            testShuffleButton
        );
    }

}
