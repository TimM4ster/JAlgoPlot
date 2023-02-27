package gui.newsorting;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class ArrayPane extends Pane {
    
    private PArray<?> pArray;

    public ArrayPane(Scene scene) {
        Integer[] array = new Integer[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        String[] array2 = new String[10];
        for (int i = 0; i < array2.length; i++) {
            array2[i] = "a";
        }
        pArray = PArray.createPArray(array, 0, 0, 20, 50);

        getChildren().add(pArray);

        pArray.getArrayComponents()[0].setLayoutX(100);
    }

}
