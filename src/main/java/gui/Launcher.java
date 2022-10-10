package gui;

import gui.controllers.LauncherControls;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/Launcher.fxml")));

        Scene scene = new Scene(root, LauncherControls.WIDTH, LauncherControls.HEIGHT);

        stage.setTitle("Launcher");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
