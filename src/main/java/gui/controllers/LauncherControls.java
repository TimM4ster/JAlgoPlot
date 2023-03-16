/**
 *  JAlgoPlot - Made to make understanding algorithms easier.
 *     Copyright (C) 2022-2023  Tim-Michael Krieg
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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
 * @author Tim-Michael Krieg
 * @version {@value Launcher#VERSION}
 * @since v0.1.0
 */
public class LauncherControls {

    /**
     * Switches the scene of the stage to the scene displaying sorting algorithms.
     *
     * @param e The action event triggering the switch.
     * @since v0.1.0
     */
    public void switchToSortingScene(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        SortingScene sortingScene = new SortingScene(stage);
        stage.setScene(sortingScene.getScene());
        stage.setTitle("JAlgoPlot - Sorting");
    }

    /**
     * Exits the application. This method is assigned to the "Quit"-button in the launcher.
     *
     * @param e The action event evoking the exit.
     * @since v0.1.0
     */
    public void exitApplication(ActionEvent e) {
        Platform.exit();
    }

}
