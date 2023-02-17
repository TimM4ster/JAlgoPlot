package gui.graph;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * This class represents a vertex in a graph. Each vertex is composed of the following attributes:
 * <ul>
 *    <li>value: the internal value of the vertex</li>
 *    <li>node: the graphical representation of the vertex as a circle</li>
 *    <li>label: the label showing the value of the vertex ontop of the node</li>
 * </ul>
 * 
 * @param <V> the type of the value of the vertex
 * 
 * @author Tim
 * @since v1.1.1
 * @version v1.1.1
 */
public class Vertex<V extends Object> extends StackPane {
    
    /**
     * The internal value of the vertex.
     */
    private V value;

    /**
     * The graphical representation of the vertex as a circle.
     */
    private final Circle node = new Circle();

    /**
     * The label showing the value of the vertex ontop of the node.
     */
    private final Label label = new Label();

    /**
     * The current position of the center of the vertex.
     */
    private final Point2D center;

    private final DoubleProperty centerXProperty = new SimpleDoubleProperty();

    private final DoubleProperty centerYProperty = new SimpleDoubleProperty();

    /**
     * The current layout position of the vertex (that is, the center minus the radius of the circle).
     */
    private final Point2D layoutPosition;

    private final Paint defaultFill = Paint.valueOf("white");

    private final int defaultRadius = 15;

    private double zoomFactor = 1;

    private BooleanProperty isDraggingProperty = new SimpleBooleanProperty();

    /**
     * The current disposition of the vertex.
     */
    private Point2D disposition = new Point2D(0, 0);

    private double draggingOffsetX = 0;
    private double draggingOffsetY = 0;

    public Vertex(V value) {
        this(value, Math.random() * 500, Math.random() * 500);
    }

    public Vertex(V value, double x, double y) {
        super();
        this.value = value;

        center = new Point2D(x, y);
        layoutPosition = new Point2D(x - defaultRadius, y - defaultRadius);

        setLayoutX(layoutPosition.getX());
        setLayoutY(layoutPosition.getY());

        initMouseBehaviour();
        initNode();
        initLabel();

        getChildren().addAll(node, label);
    }

    private void initNode() {
        node.setRadius(defaultRadius);
        node.setFill(defaultFill);
        node.setStroke(Paint.valueOf("black"));
        node.setStrokeWidth(1);

        centerXProperty.bind(layoutXProperty().add(node.radiusProperty()));
        centerYProperty.bind(layoutYProperty().add(node.radiusProperty()));
    }

    private void initLabel() {
        label.setText(value.toString());
        label.setMouseTransparent(true);
    }

    private void initMouseBehaviour() {
        node.setOnMousePressed(
            e -> {
                isDraggingProperty.set(true);
                draggingOffsetX = e.getSceneX() / zoomFactor - getLayoutX();
                draggingOffsetY = e.getSceneY() / zoomFactor - getLayoutY();
                toFront();
            }
        );


        node.setOnMouseDragged(
            e -> {
                double x = (e.getSceneX() / zoomFactor - draggingOffsetX);
                double y = (e.getSceneY() / zoomFactor - draggingOffsetY);
                setLayoutX(x);
                setLayoutY(y);
            }
        );

        node.setOnMouseReleased(
            e -> {
                //TODO: snap to edge if out of bounds

                //TODO: Add a listener to the scene width and height

                isDraggingProperty.set(false);
            }
        );
    }

    public Circle getNode() {
        return node;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int getRadius() {
        return defaultRadius;
    }

    public DoubleProperty centerXProperty() {
        return centerXProperty;
    }

    public DoubleProperty centerYProperty() {
        return centerYProperty;
    }

    public void setDisposition(Point2D disposition) {
        this.disposition = disposition;
    }

    public BooleanProperty isDraggingProperty() {
        return isDraggingProperty;
    }

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
    }

}
