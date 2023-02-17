package gui.graph;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Vertex<V extends Object> extends StackPane {
    
    private V value;

    private final Circle node = new Circle();

    private final Label label = new Label();

    private final DoubleProperty centerXProperty = new SimpleDoubleProperty();

    private final DoubleProperty centerYProperty = new SimpleDoubleProperty();

    private final Paint defaultFill = Paint.valueOf("white");

    private final int defaultRadius = 15;

    private double zoomFactor = 1;

    private BooleanProperty isDraggingProperty = new SimpleBooleanProperty();

    private double draggingOffsetX = 0;
    private double draggingOffsetY = 0;

    public Vertex(V value) {
        this(value, Math.random() * 500, Math.random() * 500);
    }

    public Vertex(V value, double x, double y) {
        super();
        this.value = value;

        setLayoutX(x - defaultRadius);
        setLayoutY(y - defaultRadius);

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

    public BooleanProperty isDraggingProperty() {
        return isDraggingProperty;
    }

    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
    }

}
