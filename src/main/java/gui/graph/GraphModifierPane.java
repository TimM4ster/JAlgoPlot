package gui.graph;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;

public class GraphModifierPane extends VBox {
    
    public GraphModifierPane() {
        super();
        setMinWidth(200);
        setBackground(
            new Background(
                new BackgroundFill(
                    javafx.scene.paint.Color.LIGHTGRAY,
                    CornerRadii.EMPTY,
                    Insets.EMPTY
                )
            )
        );
    }

}
