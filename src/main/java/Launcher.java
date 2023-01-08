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

import gui.MainMenuScene;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The entry point to the application.
 *
 * @author Tim-Michael Krieg
 * @version 1.1.0
 * @since v1.0.0
 */
public class Launcher extends Application {

    /**
     * Main entry point to the application.
     *
     * @param args  The command line arguments.
     * @since v1.0.0
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the application.
     *
     * @param stage The stage to be shown.
     * @since v1.0.0
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("JAlgoPlot");  // Set the title of the stage.
        stage.setScene(new MainMenuScene(stage));  // Set the scene of the stage.
        stage.show();  // Show the stage.
    }
}
