package gui.graph;

import javafx.scene.shape.Circle;

public class Node extends Circle {
    
    private String label;

    private double initialX = 0;

    private double draggingOffset = 0;

    public Node(String label) {
        super();
        this.label = label;
        setRadius(10);

        setOnMousePressed(
            e -> {
                initialX = getCenterX();
                draggingOffset = e.getSceneX() - getCenterX();
            }
        );

        setOnMouseDragged(
            e -> {
                setCenterX(e.getSceneX() - draggingOffset);
                setCenterY(e.getSceneY() - draggingOffset);
            }
        );

        setOnMouseReleased(
            e -> {
                //TODO: snap to edge if out of bounds

                //TODO: Add a listener to the scene width and height
                

                if (Math.abs(initialX - getCenterX()) < 5) {
                    setCenterX(initialX);
                }
            }
        );
    }

}
