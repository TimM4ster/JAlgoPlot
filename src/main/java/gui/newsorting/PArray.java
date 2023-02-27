package gui.newsorting;

import javafx.scene.Group;

public class PArray<V extends Object> extends Group {
    
    private ArrayComponent<V>[] arrayComponents;

    public PArray(ArrayComponent<V>[] arrayComponents) {
        this.arrayComponents = arrayComponents;
        getChildren().addAll(arrayComponents);
    }

    public static <V extends Object> PArray<V> createPArray(V[] array, double x, double y, double width, double height) {
        ArrayComponent<V>[] arrayComponents = new ArrayComponent[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayComponents[i] = new ArrayComponent<V>(array[i], x + i * width, y, width, height);
        }
        return new PArray<V>(arrayComponents);
    }

    public ArrayComponent<V>[] getArrayComponents() {
        return arrayComponents;
    }

}
