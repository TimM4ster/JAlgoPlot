package gui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;

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

    /**
     * Exits the application.
     *
     * @param e The action event evoking the exit.
     */
    public void exitApplication(ActionEvent e) {
        Platform.exit();
    }

}
