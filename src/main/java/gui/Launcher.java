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
package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The entry point to the application. Layout of the components inside the launcher is taken from the <a href="/scenes/
 * Launcher.fxml">Launcher.fxml</a> file. NOTE: The Launcher.fxml file will be removed in future versions. It will be
 * replaced by a more flexible and dynamic layout.
 *
 * @author Tim-Michael Krieg
 * @version 1.0.0
 * @since v1.0.0
 */
public class Launcher extends Application {

    /**
     * The width of the launcher.
     *
     * @since v1.0.0
     */
    public static final int WIDTH = 500;

    /**
     * The height of the launcher.
     *
     * @since v1.0.0
     */
    public static final int HEIGHT = 450;

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
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/scenes/Launcher.fxml"))
        );  // Load the layout from the fxml file. Will be replaced by actual implementation in future versions.

        Scene scene = new Scene(root, WIDTH, HEIGHT);  // Create the scene with the layout and the specified dimensions.

        stage.setTitle("JAlgoPlot");  // Set the title of the stage.

        stage.setScene(scene);  // Set the scene of the stage.

        stage.sizeToScene();  // Resize the stage to the size of the scene.

        stage.setResizable(false);  // Disable resizing of the stage.

        stage.show();  // Show the stage.
    }
}
