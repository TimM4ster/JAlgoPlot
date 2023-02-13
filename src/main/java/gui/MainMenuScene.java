package gui;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenuScene extends Scene {

    private final Stage stage;

    private final BorderPane root;

    private final double width = 500.0;

    private final double height = 450.0;

    private final String title = "JAlgoPlot";

    private final String subTitle = "Understanding Algorithms Made Easy";

    private final Button switchToSortingSceneButton = new Button("Sorting");

    private final Button switchToGraphSceneButton = new Button("Graph");

    private final Button soon = new Button("Soon");

    private final Button wikiButton = new Button("Wiki");

    private final Button aboutButton = new Button("About");

    private final Button quitButton = new Button("Quit");

    public MainMenuScene(Stage stage) {
        super(new BorderPane());
        this.stage = stage;

        root = (BorderPane) getRoot();
        root.setPrefSize(width, height);
        root.setPadding(GUI_Utils.DEFAULT_INSETS);

        VBox titleBox = new VBox();
        titleBox.setAlignment(javafx.geometry.Pos.CENTER);
        titleBox.setSpacing(5);
        Text titleText = new Text(title);
        titleText.setStyle("-fx-font-size: 30px;");
        Text subTitleText = new Text(subTitle);
        subTitleText.setStyle("-fx-font-size: 15px;");
        titleBox.getChildren().addAll(titleText, subTitleText);

        VBox buttonBox = new VBox();
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
        buttonBox.setSpacing(10);
        switchToSortingSceneButton.setPrefSize(250, 50);
        switchToSortingSceneButton.setOnAction(e -> {
            SceneSwitcher.switchToSortingDisplay(stage);
        });
        switchToGraphSceneButton.setPrefSize(250, 50);
        switchToGraphSceneButton.setOnAction(e -> {
            SceneSwitcher.switchToGraphDisplay(stage);
        });
        soon.setPrefSize(250, 50);
        soon.setDisable(true);
        wikiButton.setPrefSize(250, 50);
        wikiButton.setDisable(true);
        aboutButton.setPrefSize(250, 50);
        aboutButton.setDisable(true);
        quitButton.setPrefSize(250, 50);
        quitButton.setOnAction(e -> {
            Platform.exit();
        });
        buttonBox.getChildren().addAll(
                switchToSortingSceneButton,
                switchToGraphSceneButton,
                soon,
                wikiButton,
                aboutButton,
                quitButton
        );

        Text versionText = new Text("Version: " + getVersion());
        versionText.setStyle("-fx-font-size: 10px;");
        BorderPane.setAlignment(versionText, javafx.geometry.Pos.BOTTOM_RIGHT);

        root.setTop(titleBox);
        root.setCenter(buttonBox);
        root.setBottom(versionText);

        root.getStylesheets().add("gui/MainMenu.css");

        // Set rounded corners
        //root.setStyle("-fx-background-radius: 20px;");
        //setFill(Color.TRANSPARENT);
        //stage.initStyle(javafx.stage.StageStyle.TRANSPARENT);
    }

    private String getVersion() {

        // Get version from version file

        return "v1.1.0";
    }

}
