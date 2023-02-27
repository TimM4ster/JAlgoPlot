package gui.graph;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangeValueStage<V> extends Stage {

    VBox root = new VBox();

    Label newValueLabel = new Label("New Value:");

    TextField newValueField = new TextField();

    Button saveButton = new Button("Save");

    Button cancelButton = new Button("Cancel");
    
    public ChangeValueStage(Vertex<V> vertex) {
        root.setSpacing(10);
        root.setPadding(new javafx.geometry.Insets(10));

        newValueField.setPromptText("New value for vertex " + vertex.getValue());

        saveButton.setOnAction(
            e -> {
                vertex.setValue((V) newValueField.getText());
                close();
            }
        );

        cancelButton.setOnAction(
            e -> {
                close();
            }
        );

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(saveButton, cancelButton);

        root.getChildren().addAll(newValueLabel, newValueField, buttonBox);

        setScene(new Scene(root));
        setTitle("Set Value");
        setResizable(false);
        show();
    }

}
