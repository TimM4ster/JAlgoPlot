package gui;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class contains all constants used in the GUI.
 *
 * @since v0.9.0 (beta)
 */
public class GUI_Utils {

    /**
     * This method takes a screenshot of the current scene and saves it to the file system. The file name contains the
     * current date and time. All screenshots are saved in the "screenshots" folder in the root directory of the
     * project.
     *
     * @param scene The scene to take a screenshot of.
     * @since v0.9.0 (beta)
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
        File file = new File("screenshots", filename);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
