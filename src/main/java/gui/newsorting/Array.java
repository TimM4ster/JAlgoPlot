package gui.newsorting;

import javafx.scene.Group;

public class Array<V extends Object & Comparable<? super V>> extends Group {
    
    private ArrayComponent<V>[] arrayComponents;

    public Array(ArrayComponent<V>[] arrayComponents) {
        this.arrayComponents = arrayComponents;
        getChildren().addAll(arrayComponents);
    }

    public static <V extends Object & Comparable<? super V>> Array<V> createArray(V[] array, double x, double y, double width, double height) {
        ArrayComponent<V>[] arrayComponents = new ArrayComponent[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayComponents[i] = new ArrayComponent<V>(
                array[i], 
                x + i * (width + 2), 
                y, 
                width, 
                height
            );
        }
        return new Array<V>(arrayComponents);
    }

    public static Array<Integer> createSimpleIntegerArray(int size, ArrayVisualizerPane pane) {
        ArrayComponent<Integer>[] arrayComponents = new ArrayComponent[size];

        final double padding = pane.getDefaultPadding();
        final double paneWidth = pane.getPrefWidth();
        final double maxHeight = pane.getPrefHeight() - 2 * padding;
        
        for (int i = 0; i < size; i++) {
            final double width = (paneWidth - 2 * padding - 18) / size;

            final double x = padding + i * (width + 2);

            final double height = (maxHeight / size) * (i + 1);

            final double y = maxHeight - height + padding;

            arrayComponents[i] = new ArrayComponent<>(
                Integer.valueOf(i),
                x,
                y,
                width,
                height
            );
        }

        return new Array<Integer>(arrayComponents);
    }

    public void repaint(double paneWidth, double paneHeight) {
        final double padding = 10;
        final double maxHeight = paneHeight - 2 * padding;
        for (int i = 0; i < arrayComponents.length; i++) {
            final double width = (paneWidth - 2 * padding - 18) / arrayComponents.length;

            final double x = padding + i * (width + 2);

            final double height = (maxHeight / arrayComponents.length) * (i + 1);

            final double y = maxHeight - height + padding;

            arrayComponents[i].setLayoutX(x);
            arrayComponents[i].setLayoutY(y);
            arrayComponents[i].setPrefSize(width, height);
        }
    }

    public ArrayComponent<V>[] getArrayComponents() {
        return arrayComponents;
    }

}
