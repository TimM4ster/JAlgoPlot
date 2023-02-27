package gui.newsorting;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SortingScene extends Scene {

    private final VBox root;

    private final Stage stage;

    private final ArrayPane arrayPane = new ArrayPane(this);
    
    public SortingScene(Stage stage) {
        super(new VBox());
        root = (VBox) getRoot();
        this.stage = stage;

        root.getChildren().add(arrayPane);
    }

}
