package gui.scenes;

import algorithms.Algorithm;
import algorithms.sorting.bubblesort.BubbleSort;
import gui.Launcher;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * This class creates a scene
 * TODO
 *
 * @since v0.9.0 (beta)
 */
public class SortingScene {

    /**
     * The array that is displayed in the middle of the scene and that is sorted by the chosen algorithm. It is
     * initially set to null and can be initialized by entering a size into the text field and pressing the initialize
     * button.
     *
     * @since v0.9.0 (beta)
     */
    private int[] array;

    /**
     * The length of the array that is displayed by the scene.
     *
     * @since v0.9.0 (beta)
     */
    private int array_length;

    /**
     * The algorithm that is chosen in the combo box to sort the array of the scene.
     *
     * @since v0.9.0 (beta)
     */
    private Algorithm algorithm;

    /**
     * The stage object that the scene is added to. Can be used to extract information about the size of the stage.
     *
     * @since v0.9.0 (beta)
     */
    private final Stage STAGE;

    /**
     * The scene that this class creates.
     *
     * @since v0.9.0 (beta)
     */
    private final Scene SCENE;

    /**
     * List of all algorithms that can be displayed by the scene. The list is added to the combo box.
     *
     * @since v0.9.0 (beta)
     */
    private final String[] ALGORITHMS = {
            "Bubble Sort"
    };

    /**
     * Constructor initializing the stage object that the scene is added to. It further initializes the actual scene
     * with its components. The scene can then be accessed by the {@link #getScene() getScene}-method.
     *
     * @param stage The stage this scene is set in.
     * @since v0.9.0 (beta)
     */
    public SortingScene(Stage stage) {
        stage.setTitle("Sorting Pane");
        this.STAGE = stage;
        VBox box = new VBox();
        box.getChildren().addAll(createMenuBar(), createMainPane());
        SCENE = new Scene(box);
    }

    /**
     * Returns the scene that is initialized in the constructor of this class.
     *
     * @return  The stage.
     * @since v0.9.0 (beta)
     */
    public Scene getScene() {
        return SCENE;
    }

    /**
     * Creates the main pane of the scene that all components are added to and returns it after creation.
     *
     * @return  The main pane of the scene.
     * @since v0.9.0 (beta)
     */
    private BorderPane createMainPane() {
        BorderPane main_pane = new BorderPane();
        main_pane.setTop(createSettingsBox());
        return main_pane;
    }

    /**
     * Creates and returns the menu bar located at the top of the scene. Offers the following items:
     * <ul>
     *    <li>Home
     * </ul>
     * @return  The menu bar.
     * @since v0.9.0 (beta)
     */
    private MenuBar createMenuBar() {
        MenuBar menu_bar = new MenuBar();
        menu_bar.getMenus().addAll(
            createHomeMenu()
        );
        menu_bar.prefWidthProperty().bind(STAGE.widthProperty());
        return menu_bar;
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
     */
    private Menu createHomeMenu() {
        Menu home_menu = new Menu("Home");

        MenuItem main_menu = new MenuItem("Main Menu");
        main_menu.setOnAction(
                e -> {
                    Parent root;
                    try {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/Launcher.fxml")));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Scene scene = new Scene(root, Launcher.WIDTH, Launcher.HEIGHT);
                    STAGE.setTitle("JAlgoPlot");
                    STAGE.setScene(scene);
                }
        );

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(event -> Platform.exit());

        home_menu.getItems().addAll(
                main_menu,
                exit
        );

        return home_menu;
    }

    /**
     * Creates the upper portion of the scene. That is, it creates a horizontal box containing setting functionalities.
     *
     * @return  The HBox.
     * @since v0.9.0 (beta)
     */
    private HBox createSettingsBox() {
        Button run = createRunButton();
        ComboBox<String> combo_box = createComboBox(run);
        Button initialize = createInitializeArrayButton(combo_box);
        TextField array_length_reader = createArrayLengthReader(initialize);
        HBox h_box = new HBox();
        h_box.setPadding(new Insets(10, 10, 10, 10));
        h_box.setSpacing(10);
        h_box.getChildren().addAll(
                combo_box,
                run,
                array_length_reader,
                initialize
        );
        h_box.setStyle("-fx-background-color: #336699;");
        return h_box;
    }

    /**
     * Creates the combo box that is used to select the algorithm for display. All available algorithms are stored in
     * the ALGORITHMS attribute. It is disabled by default until an array is initialized.
     *
     * @return  The combo box.
     * @since v0.9.0 (beta)
     */
    private ComboBox<String> createComboBox(Button run_button) {
        ComboBox<String> combo_box = new ComboBox<>(FXCollections.observableArrayList(ALGORITHMS));
        combo_box.setDisable(true);
        combo_box.setPromptText("Initialize Array");
        combo_box.setOnAction(
                event -> {
                    run_button.setDisable(false);
                    algorithm = switch (combo_box.getValue()) {
                        case "Bubble Sort" -> new BubbleSort(array);
                        default -> null;
                        //TODO: Add more cases
                    };
                }
        );
        return combo_box;
    }

    /**
     * Creates the button that starts the display of the chosen algorithm. It is disabled by default until an algorithm
     * is chosen.
     *
     * @return  The run button.
     * @since v0.9.0 (beta)
     */
    private Button createRunButton() {
        Button run_button = new Button("Run");
        run_button.setDisable(true);
        run_button.setOnAction(event -> System.out.println("Run"));
        return run_button;
    }

    /**
     * Creates the text field that is used to set the length of the displayed array. Only allows numerical values.
     *
     * @return  The text field.
     * @since v0.9.0 (beta)
     */
    private TextField createArrayLengthReader(Button initialize_button) {
        TextField text_field = new TextField();
        text_field.setFocusTraversable(false);
        text_field.setPromptText("Enter array length");
        text_field.textProperty().addListener((observable, oldValue, newValue) -> {
            initialize_button.setDisable(true);
            if (!newValue.equals("") && !newValue.matches("^[1-9]\\d*$")) {
                text_field.setText(oldValue);
            } else if (!newValue.equals("")) {
                initialize_button.setDisable(false);
                array_length = Integer.parseInt(text_field.getText());
            }
        });
        return text_field;
    }

    /**
     * Creates the button that initializes the array with the length entered to the text field.
     *
     * @return  The button.
     * @since v0.9.0 (beta)
     */
    private Button createInitializeArrayButton(ComboBox<String> combo_box) {
        Button initialize_array = new Button("Initialize");
        initialize_array.setDisable(true);
        initialize_array.setOnAction(
                event -> {
                    array = new int[array_length];
                    combo_box.setDisable(false);
                    combo_box.setPromptText("Select algorithm");
                    initialize_array.setText("Resize array");
                }
        );
        return initialize_array;
    }
}
