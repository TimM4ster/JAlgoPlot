package gui.settings;

import gui.GUI_Utils;
import gui.sorting.ArrayUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ColorSettingsScene extends Scene {

    private final VBox root;

    private final double width = 500;

    private final double height = 500;

    private final Label defaultColorLabel = new Label("Default Color:");

    private final ColorPicker defaultColorPicker = new ColorPicker(ArrayUtils.DEFAULT_COLOR);

    private final Label highlightColorLabel = new Label("Highlighting Color:");

    private final ColorPicker highlightColorPicker = new ColorPicker(ArrayUtils.SELECTED_COLOR);

    private final Button saveButton = new Button("Save");

    private final Button cancelButton = new Button("Cancel");

    public ColorSettingsScene() {
        super(new VBox());

        root = (VBox) getRoot();
        root.setPrefSize(width, height);
        root.setPadding(GUI_Utils.DEFAULT_INSETS);
        root.setSpacing(GUI_Utils.DEFAULT_SPACING);

        init();
    }

    private void init() {
        VBox defaultColorBox = new VBox();
        defaultColorBox.getChildren().addAll(defaultColorLabel, defaultColorPicker);

        VBox highlightColorBox = new VBox();
        highlightColorBox.getChildren().addAll(highlightColorLabel, highlightColorPicker);

        HBox buttonsBox = new HBox();
        buttonsBox.setSpacing(GUI_Utils.DEFAULT_SPACING);
        buttonsBox.getChildren().addAll(saveButton, cancelButton);

        root.getChildren().addAll(
                defaultColorBox,
                highlightColorBox,
                buttonsBox
        );
    }

}
