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

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * This class contains all constants and reusable methods used in the graphical representations of the algorithms.
 *
 * @author Tim-Michael Krieg
 * @version 1.1.0
 * @since v1.0.0
 */
public class GUI_Utils {

    public final static double DEFAULT_SPACING = 10;

    public final static double DEFAULT_PADDING = 10;

    public final static Insets DEFAULT_INSETS = new Insets(DEFAULT_PADDING);

    public final static Duration DEFAULT_ANIMATION_DURATION = Duration.millis(500);


    /**
     * This method takes a screenshot of the current scene and saves it to the file system. The file name contains the
     * current date and time. All screenshots are saved in the "screenshots" folder in the root directory of the
     * project.
     *
     * @param scene The scene to take a screenshot of.
     * @since v1.0.0
     */
    public static void takeScreenshot(Scene scene) {
        WritableImage image = scene.snapshot(null);
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the date and time as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String timestamp = now.format(formatter);

        // Use the timestamp to create the filename
        String filename = "screenshot-" + timestamp + ".png";
        if (!new File("screenshots").exists()) {
            new File("screenshots").mkdir();
        }
        File file = new File("screenshots", filename);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
