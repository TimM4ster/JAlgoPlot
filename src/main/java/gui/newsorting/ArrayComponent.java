package gui.newsorting;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ArrayComponent<V extends Object & Comparable<? super V>> extends StackPane {
    
    private V value;

    private final Rectangle rectangle;

    private final TextField label;

    public ArrayComponent(V value) {
        this.value = value;
        rectangle = new Rectangle();
        label = new TextField(value.toString());

        rectangle.widthProperty().bind(widthProperty());
        rectangle.heightProperty().bind(heightProperty());
        
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        label.prefWidthProperty().bind(rectangle.widthProperty());


        //TODO: Rework when components are resizable
        if (getWidth() / 4 > getTextWidth()) {
            label.setVisible(true);
        } else {
            label.setVisible(false);
        }
        widthProperty().addListener((observable, oldValue, newValue) -> {
            if (getTextWidth() > newValue.doubleValue()) {
                label.setVisible(false);
            } else {
                label.setVisible(true);
            }
        });

        // TODO: Make value update when textfield is changed
        if (value instanceof Number) {
            label.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    label.setText(oldValue);
                }
            });
        }

        getChildren().addAll(rectangle, label);

        setAlignment(label, Pos.BOTTOM_CENTER);
    }

    public ArrayComponent(V value, double x, double y, double width, double height) {
        this(value);
        setWidth(width);
        setHeight(height);
        setLayoutX(x);
        setLayoutY(y);
    }

    private final double getTextWidth() {
        final Text text = new Text(label.getText());
        return text.getLayoutBounds().getWidth(); 
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
        label.setText(value.toString());
    }

}
