package gui;

import gui.controllers.LauncherControls;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The entry point to the application. Layout of the components inside the launcher is taken from the <a href="/scenes/
 * Launcher.fxml">Launcher.fxml</a> file.
 *
 * @since v0.9.0 (beta)
 */
public class Launcher extends Application {

    /**
     * The width of the launcher.
     *
     * @since v0.9.0 (beta)
     */
    public static final int WIDTH = 500;

    /**
     * The height of the launcher.
     *
     * @since v0.9.0 (beta)
     */
    public static final int HEIGHT = 450;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/Launcher.fxml")));

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        stage.setTitle("JAlgoPlot");
        stage.setScene(scene);
        stage.sizeToScene();

        /*stage.showingProperty().addListener((observable, oldValue, showing) -> {
            if(showing) {

            }
        });*/

        stage.show();
        stage.setResizable(false);
    }
}
