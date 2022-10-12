package gui.controllers;

import gui.scenes.SortingScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This class represents the controller of the launcher that launches the application.
 */
public class LauncherControls {

    /**
     * The height of the launcher.
     */
    public static final double WIDTH = 500;

    /**
     * The width of the launcher.
     */
    public static final double HEIGHT = 450;

    public void switchToSortingScene(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        SortingScene sortingScene = new SortingScene(stage);
        stage.setScene(sortingScene.getScene());
        stage.show();
    }

    /**
     * Exits the application.
     *
     * @param e The action event evoking the exit.
     */
    public void exitApplication(ActionEvent e) {
        Platform.exit();
    }

}
