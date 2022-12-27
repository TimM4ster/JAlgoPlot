package gui.controllers;

import gui.scenes.SortingScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * This class represents the controller of the launcher that launches the application. It offers all methods that change
 * the scene accordingly. Action events triggered by the buttons of the launcher are processed here.
 *
 * @since v0.9.0 (beta)
 */
public class LauncherControls {

    /**
     * Switches the scene of the stage to the scene displaying sorting algorithms.
     *
     * @param e The action event triggering the switch.
     * @since v0.9.0 (beta)
     */
    public void switchToSortingScene(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        SortingScene sortingScene = new SortingScene(stage);
        stage.setScene(sortingScene.getScene());
        stage.setTitle("JAlgoPlot - Sorting");
        stage.setMaximized(true);
    }

    /**
     * Exits the application. This method is assigned to the "Quit"-button in the launcher.
     *
     * @param e The action event evoking the exit.
     * @since v0.9.0 (beta)
     */
    public void exitApplication(ActionEvent e) {
        Platform.exit();
    }

}
