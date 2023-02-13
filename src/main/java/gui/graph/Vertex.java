package gui.graph;

import javafx.beans.property.DoubleProperty;
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

    private double initialX = 0;
    private double initialY = 0;

    private double draggingOffsetX = 0;
    private double draggingOffsetY = 0;

    public Vertex(V value) {
        super();
        this.value = value;

        setLayoutX(Math.random() * 500);
        setLayoutY(Math.random() * 500);

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
        setOnMousePressed(
            e -> {
                initialX = getLayoutX();
                initialY = getLayoutY();
                draggingOffsetX = e.getSceneX() - getLayoutX();
                draggingOffsetY = e.getSceneY() - getLayoutY();
            }
        );

        setOnMouseDragged(
            e -> {
                setLayoutX(e.getSceneX() - draggingOffsetX);
                setLayoutY(e.getSceneY() - draggingOffsetY);
            }
        );

        setOnMouseReleased(
            e -> {
                //TODO: snap to edge if out of bounds

                //TODO: Add a listener to the scene width and height

                if (Math.abs(initialX - getLayoutX()) < 5) {
                    setLayoutX(initialX);
                }
                if (Math.abs(initialY - getLayoutY()) < 5) {
                    setLayoutY(initialY);
                }
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

}
