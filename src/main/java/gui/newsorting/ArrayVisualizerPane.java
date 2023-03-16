package gui.newsorting;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class ArrayVisualizerPane extends Pane {

    private final double padding = 10;
    
    private Array<?> array;

    public ArrayVisualizerPane(Scene scene) {
        setPrefSize(500, 500);

        final int length = 10;

        Integer[] integerArray = new Integer[length];
        for (int i = 0; i < integerArray.length; i++) {
            integerArray[i] = i;
        }

        String[] stringArray = new String[length];
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = "a";
        }
        
        /* array = Array.createArray(
            integerArray, 
            10, 
            10, 
            (getPrefWidth() - 2 * padding - 18) / length, 
            getPrefHeight() - 2 * padding
        ); */

        array = Array.createSimpleIntegerArray(length, this);

        widthProperty().addListener((observable, oldValue, newValue) -> {
            array.repaint(newValue.doubleValue(), getPrefHeight());
        });
        heightProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Height changed");
            array.repaint(getPrefWidth(), newValue.doubleValue());
        });

        getChildren().add(array);
    }

    public double getDefaultPadding() {
        return padding;
    }

}
