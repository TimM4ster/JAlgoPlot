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
package gui.scenes;

import gui.GUI_Utils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * This class represents the scene of the sorting algorithms. It offers all methods that change the scene accordingly.
 * Further, action events triggered by the buttons of the sorting scene are processed here.
 *
 * @author Tim-Michael Krieg
 * @version {@value Launcher#VERSION}
 * @since v0.1.0
 */
public class SortingScene {

    /*
    --- General attributes ---
     */

    /**
     * List of all algorithms that can be displayed by the scene. The list is added to the combo box. It will be updated
     * regularly to ensure that the combo box always contains the latest algorithms. In future versions, the list will
     * be replaced by a file containing the algorithms.
     *
     * @since v0.1.0
     */
    private final String[] ALGORITHMS = {
            "Bubble Sort",
            "Soon more..."
    };


    /*
    --- JavaFX attributes ---
     */

    /**
     * The stage object that the scene is added to. Can be used to extract information about the size of the stage.
     *
     * @since v0.1.0
     */
    private final Stage STAGE;

    /**
     * The scene that this class creates.
     *
     * @since v0.1.0
     */
    private final Scene SCENE;

    /**
     * The pane that contains the array which is displayed by the scene and on which the algorithm is executed.
     *
     * @since v0.1.0
     */
    private ArrayPane array_pane;


    /**
     * The menu bar at the top of the scene.
     *
     * @since v0.1.0
     */
    private MenuBar menu_bar;

    /**
     * The combo box from which the algorithm can be chosen.
     *
     * @since v0.1.0
     */
    private ComboBox<String> combo_box;

    /**
     * The text field from which the array length is read.
     *
     * @since v0.1.0
     */
    private TextField text_field;

    /**
     * The button that starts the display of the algorithm.
     *
     * @since v0.1.0
     */
    private Button run_algo_button;

    /**
     * Button that initializes the array with the length set in the text field.
     *
     * @since v0.1.0
     */
    private Button init_array_button;

    /**
     * Button that shuffles the array displayed. Only active after array was initialized.
     *
     * @since v0.1.0
     */
    private Button shuffle_array_button;

    /**
     * Button that reverses the array displayed. Only active after array was initialized.
     *
     * @since v0.1.0
     */
    private Button rev_array_button;

    /**
     * Button that flips the alignment of the rectangles displayed in the scene. Only active after array was initialized.
     *
     * @since v0.1.0
     */
    private Button flip_align_button;

    /**
     * Constructor initializing the stage object that the scene is added to. It further initializes the actual scene
     * with its components. The scene can then be accessed by the {@link #getScene() getScene}-method. In future
     * versions, the scene will extend the Scene class from JavaFx.
     *
     * @param stage The stage this scene is set in.
     * @since v0.1.0
     */
    public SortingScene(Stage stage) {
        stage.setTitle("Sorting Pane");  // Set title of the stage
        stage.setResizable(true);  // Allow resizing of the stage
        this.STAGE = stage;  // Set the stage object of this class to the stage object passed to the constructor

        VBox box = new VBox();  // Create a new VBox to add all components to

        createMenuBar();  // Create the menu bar
        box.getChildren().addAll(menu_bar, createMainPane());  // Add the menu bar and the main pane to the VBox

        SCENE = new Scene(box);  // Create the scene with the VBox as root node

        array_pane.prefHeightProperty().bind(stage.heightProperty().subtract(100));
    }

    /**
     * Returns the scene that is initialized in the constructor of this class. Will be removed in the future.
     *
     * @return  The stage.
     * @since v0.1.0
     */
    public Scene getScene() {
        return SCENE;  // Return the scene
    }

    /**
     * Creates the main pane of the scene that all components are added to and returns it after creation.
     *
     * @return  The main pane of the scene.
     * @since v0.1.0
     */
    private BorderPane createMainPane() {
        BorderPane main_pane = new BorderPane();  // Create a new BorderPane to add all components to

        HBox settings_box = createSettingsBox();  // Create the settings box
        main_pane.setTop(settings_box);  // Add the settings box to the top of the main pane

        array_pane = new ArrayPane(this, settings_box.getWidth());
        main_pane.setCenter(array_pane);

        return main_pane;  // Return the main pane
    }

    /**
     * Creates the menu bar located at the top of the scene. Offers the following items:
     * <ul>
     *    <li>Home
     * </ul>
     *
     * @since v0.1.0
     */
    private void createMenuBar() {
        menu_bar = new MenuBar();  // Create a new menu bar
        menu_bar.getMenus().addAll(
            createHomeMenu()
        );  // Add all menus to the menu bar
        menu_bar.prefWidthProperty().bind(
                STAGE.widthProperty()
        );  // Bind the width of the menu bar to the width of the stage
    }

    /**
     * Creates the "Home" menu which is the first menu in the menu bar of the scene. It offers the following
     * functionalities:
     * <ul>
     *    <li>Main Menu: return to the main menu
     *    <li>Exit: exit the application
     * </ul>
     *
     * @return  The "Home" menu.
     * @since v0.1.0
     */
    private Menu createHomeMenu() {
        Menu home_menu = new Menu("Home");  // Create the "Home" menu

        MenuItem main_menu = new MenuItem("Main Menu");  // Create the "Main Menu" menu item
        main_menu.setOnAction(
                e -> {
                    Parent root;
                    try {
                        root = FXMLLoader.load(
                                Objects.requireNonNull(getClass().getResource("/scenes/Launcher.fxml"))
                        );  // Load the FXML file for the main menu
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    SCENE.setRoot(root);  // Set the root node of the scene to the root node of the FXML file
                    STAGE.sizeToScene();
                    STAGE.setTitle("JAlgoPlot");
                }  // Set the action of the menu item to return to the main menu
        );

        MenuItem exit = new MenuItem("Exit");  // Create the "Exit" menu item
        exit.setOnAction(event -> Platform.exit());  // Set the action of the menu item to exit the application

        home_menu.getItems().addAll(
                main_menu,
                exit
        );  // Add all menu items to the "Home" menu

        return home_menu;  // Return the "Home" menu
    }

    /**
     * Creates the upper portion of the scene. That is, it creates a horizontal box containing setting functionalities.
     *
     * @return  The HBox.
     * @since v0.1.0
     */
    private HBox createSettingsBox() {
        createRunButton();  // Create the run button
        createComboBox();  // Create the combo box
        createInitializeArrayButton();  // Create the initialize array button
        createShuffleButton();  // Create the shuffle button
        createArrayLengthReader();      // Create the array length reader
        createReverseButton();  // Create the reverse button
        createFlipButton();  // Create the flip button

        HBox h_box = new HBox();  // Create a new HBox to add all components to
        h_box.setPadding(new Insets(10, 10, 10, 10));  // Set the padding of the HBox
        h_box.setSpacing(10);  // Set the spacing of the HBox

        Button screenshot = new Button("Screenshot");
        screenshot.setOnAction(e -> {
            GUI_Utils.takeScreenshot(SCENE);
        });

        h_box.getChildren().addAll(
                combo_box,
                run_algo_button,
                text_field,
                init_array_button,
                shuffle_array_button,
                rev_array_button,
                //flip_align_button,  TODO: Fix this
                screenshot
        );  // Add all components to the HBox
        h_box.setStyle("-fx-background-color: #336699;");  // Set the background color of the HBox

        return h_box;
    }

    /**
     * Creates the combo box that is used to select the algorithm for display. All available algorithms are stored in
     * the ALGORITHMS attribute. It is disabled by default until an array is initialized.
     *
     * @since v0.1.0
     */
    private void createComboBox() {
        combo_box = new ComboBox<>(FXCollections.observableArrayList(ALGORITHMS));
        combo_box.setDisable(true);
        combo_box.setPromptText("Initialize Array");
        combo_box.setOnAction(
                event -> {
                    array_pane.setAlgorithm(combo_box.getValue());
                    run_algo_button.setDisable(array_pane.getAlgorithm() == null);
                }
        );
    }

    /**
     * Creates the button that starts the display of the chosen algorithm. It is disabled by default until an algorithm
     * is chosen.
     *
     * @since v0.9.0 (beta)
     */
    private void createRunButton() {
        run_algo_button = new Button("Run");
        run_algo_button.setDisable(true);
        run_algo_button.setOnAction(
                event -> {
                    if (run_algo_button.getText().equals("Run")) {
                        array_pane.startAlgorithm();
                        disableButtons(true);
                        run_algo_button.setText("Stop");
                    } else {
                        array_pane.stopAlgorithm();
                        disableButtons(false);
                        run_algo_button.setText("Run");
                    }
                }
        );
    }

    /**
     * Disables or enables all buttons except the run button.
     *
     * @since v0.1.0
     */
    private void disableButtons(boolean disable) {
        shuffle_array_button.setDisable(disable);
        rev_array_button.setDisable(disable);
        flip_align_button.setDisable(disable);
        text_field.setDisable(disable);
        combo_box.setDisable(disable);
    }

    /**
     * Creates the text field that is used to set the length of the displayed array. Only allows numerical values.
     *
     * @since v0.1.0
     */
    private void createArrayLengthReader() {
        text_field = new TextField();
        text_field.setFocusTraversable(false);
        text_field.setPromptText("Enter array length");
        text_field.textProperty().addListener((observable, oldValue, newValue) -> {
            init_array_button.setDisable(true);
            if (!newValue.equals("") && !newValue.matches("^[1-9]\\d*$")) {
                text_field.setText(oldValue);
            } else if (!newValue.equals("") && !newValue.equals(String.valueOf(array_pane.getArrayLength()))) {
                init_array_button.setDisable(false);
            }
        });
    }

    /**
     * Creates the button that initializes the array with the length entered to the text field.
     *
     * @since v0.1.0
     */
    private void createInitializeArrayButton() {
        init_array_button = new Button("Initialize");
        init_array_button.setDisable(true);
        init_array_button.setOnAction(
                event -> {
                    array_pane.initArrays(Integer.parseInt(text_field.getText()));

                    combo_box.setDisable(false);
                    combo_box.setPromptText("Select algorithm");
                    init_array_button.setDisable(true);
                    init_array_button.setText("Resize array");
                    shuffle_array_button.setDisable(false);
                    rev_array_button.setDisable(false);
                    flip_align_button.setDisable(false);
                }
        );
    }

    /**
     * Creates the button that shuffles the displayed array.
     *
     * @since v0.1.0
     */
    private void createShuffleButton() {
        shuffle_array_button = new Button("Shuffle");
        shuffle_array_button.setDisable(true);
        shuffle_array_button.setOnAction(
                event -> array_pane.shuffleArray()
        );
    }

    /**
     * Creates the button that reverses the displayed array.
     *
     * @since v0.1.0
     */
    private void createReverseButton() {
        rev_array_button = new Button("Reverse");
        rev_array_button.setDisable(true);
        rev_array_button.setOnAction(
                event -> array_pane.reverseArray()
        );
    }

    /**
     * Creates the button that flips the alignment of the displayed array.
     *
     * @since v0.1.0
     */
    private void createFlipButton() {
        flip_align_button = new Button("Flip");
        flip_align_button.setDisable(true);
        flip_align_button.setOnAction(
                event -> array_pane.flipRectangles()
        );
    }

    /**
     * Called after the algorithm finished or was stopped. Enables all buttons and sets the run button text to "Run".
     *
     * @since v0.1.0
     */
    public void resetAlgo() {
        run_algo_button.setText("Run");
        disableButtons(false);
    }

}
