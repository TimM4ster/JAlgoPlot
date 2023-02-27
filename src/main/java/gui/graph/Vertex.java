package gui.graph;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.effect.Light.Point;
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

    ObjectProperty<Point2D> positionProperty;

    private static final Paint DEFAULT_FILL = Paint.valueOf("white");

    private static final int DEFAULT_RADIUS = 15;

    private final IntegerProperty radiusProperty = new SimpleIntegerProperty(DEFAULT_RADIUS);

    private double zoomFactor = 1;

    private BooleanProperty isDraggingProperty = new SimpleBooleanProperty();

    /**
     * The current disposition of the vertex.
     */
    private Point2D disposition = new Point2D(0, 0);

    public Vertex(V value) {
        this(value, Math.random() * 500, Math.random() * 500);
    }

    public Vertex(V value, double x, double y) {
        super();
        this.value = value;
        positionProperty = new SimpleObjectProperty<>(new Point2D(x, y));

        DoubleBinding layoutXBinding = Bindings.createDoubleBinding(
            () -> positionProperty.get().getX() - radiusProperty.get(),
            positionProperty
        );

        DoubleBinding layoutYBinding = Bindings.createDoubleBinding(
            () -> positionProperty.get().getY() - radiusProperty.get(),
            positionProperty
        );

        layoutXProperty().bind(layoutXBinding);
        layoutYProperty().bind(layoutYBinding);

        initMouseBehaviour();
        initNode();
        initLabel();

        getChildren().addAll(node, label);
    }

    private void initNode() {
        node.radiusProperty().bind(radiusProperty);
        node.setFill(DEFAULT_FILL);
        node.setStroke(Paint.valueOf("black"));
        node.setStrokeWidth(2);
    }

    private void initLabel() {
        label.setText(value.toString());
        label.setMouseTransparent(true);
    }

    private void initMouseBehaviour() {
        node.setOnMousePressed(
            e -> {
                isDraggingProperty.set(true);
                disposition = new Point2D(
                    e.getSceneX() / zoomFactor - getPosition().getX(), 
                    e.getSceneY() / zoomFactor - getPosition().getY()
                );
                toFront();
            }
        );


        node.setOnMouseDragged(
            e -> {
                setPosition(
                    new Point2D(
                        e.getSceneX() / zoomFactor, 
                        e.getSceneY() / zoomFactor
                    ).subtract(disposition)
                );
            }
        );

        node.setOnMouseReleased(
            e -> {
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
        label.setText(value.toString());
    }

    public Point2D getPosition() {
        return positionProperty.get();
    }

    public void setPosition(Point2D position) {
        positionProperty.set(position);
    }

    public ObjectProperty<Point2D> positionProperty() {
        return positionProperty;
    }

    public Point2D getDisposition() {
        return disposition;
    }  
    
    public void setDisposition(Point2D disposition) {
        this.disposition = disposition;
    }

    public void resetDisposition() {
        setDisposition(new Point2D(0, 0));
    }    

    public int getRadius() {
        return radiusProperty.get();
    }

    public void setRadius(int radius) {
        radiusProperty.set(radius);
    }

    public BooleanProperty isDraggingProperty() {
        return isDraggingProperty;
    }

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
    }

}
