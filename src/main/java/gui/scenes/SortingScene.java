package gui.scenes;

import algorithms.Algorithm;
import algorithms.sorting.bubblesort.BubbleSort;
import gui.Launcher;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
     * TODO
     */
    private Rectangle[] rectangles;

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
     * The pane in which the array is displayed.
     *
     * @since v0.9.0 (beta)
     */
    private GridPane array_display;

    /**
     * The menu bar at the top of the scene.
     *
     * @since v0.9.0 (beta)
     */
    private MenuBar menu_bar;

    /**
     * The button that starts the display of the algorithm.
     *
     * @since v0.9.0 (beta)
     */
    private Button run_button;

    /**
     * The combo box from which the algorithm can be chosen.
     *
     * @since v0.9.0 (beta)
     */
    private ComboBox<String> combo_box;

    /**
     * The text field from which the array length is read.
     *
     * @since v0.9.0 (beta)
     */
    private TextField text_field;

    /**
     * Button that initializes the array with the length set in the text field.
     *
     * @since v0.9.0 (beta)
     */
    private Button initialize_array;

    /**
     * Button that shuffles the array displayed. Only activate after array was initialized.
     *
     * @since v0.9.0 (beta)
     */
    private Button shuffle_array;

    /**
     * Button that reverses the array displayed. Only active after array was initialized.
     *
     * @since v0.9.0 (beta)
     */
    private Button reverse_array;

    /**
     * Constructor initializing the stage object that the scene is added to. It further initializes the actual scene
     * with its components. The scene can then be accessed by the {@link #getScene() getScene}-method.
     *
     * @param stage The stage this scene is set in.
     * @since v0.9.0 (beta)
     */
    public SortingScene(Stage stage) {
        stage.setTitle("Sorting Pane");
        stage.setResizable(true);
        this.STAGE = stage;
        VBox box = new VBox();
        createMenuBar();
        box.getChildren().addAll(menu_bar, createMainPane());
        SCENE = new Scene(box);
        SCENE.widthProperty().addListener((observable, oldValue, newValue) -> repaintRectangles());
        SCENE.heightProperty().addListener((observable, oldValue, newValue) -> repaintRectangles());
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
        createArrayDisplay();
        main_pane.setCenter(array_display);
        return main_pane;
    }

    /**
     * Creates the menu bar located at the top of the scene. Offers the following items:
     * <ul>
     *    <li>Home
     * </ul>
     *
     * @since v0.9.0 (beta)
     */
    private void createMenuBar() {
        menu_bar = new MenuBar();
        menu_bar.getMenus().addAll(
            createHomeMenu()
        );
        menu_bar.prefWidthProperty().bind(STAGE.widthProperty());
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
                    STAGE.sizeToScene();
                    STAGE.setResizable(false);
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
        createRunButton();
        createComboBox();
        createInitializeArrayButton();
        createShuffleButton();
        createArrayLengthReader();
        createReverseButton();
        HBox h_box = new HBox();
        h_box.setPadding(new Insets(10, 10, 10, 10));
        h_box.setSpacing(10);
        h_box.getChildren().addAll(
                combo_box,
                run_button,
                text_field,
                initialize_array,
                shuffle_array,
                reverse_array
        );
        h_box.setStyle("-fx-background-color: #336699;");
        return h_box;
    }

    /**
     * Creates the combo box that is used to select the algorithm for display. All available algorithms are stored in
     * the ALGORITHMS attribute. It is disabled by default until an array is initialized.
     *
     * @since v0.9.0 (beta)
     */
    private void createComboBox() {
        combo_box = new ComboBox<>(FXCollections.observableArrayList(ALGORITHMS));
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
    }

    /**
     * Creates the button that starts the display of the chosen algorithm. It is disabled by default until an algorithm
     * is chosen.
     *
     * @since v0.9.0 (beta)
     */
    private void createRunButton() {
        run_button = new Button("Run");
        run_button.setDisable(true);
        run_button.setOnAction(event -> System.out.println("Run"));
    }

    /**
     * Creates the text field that is used to set the length of the displayed array. Only allows numerical values.
     *
     * @since v0.9.0 (beta)
     */
    private void createArrayLengthReader() {
        text_field = new TextField();
        text_field.setFocusTraversable(false);
        text_field.setPromptText("Enter array length");
        text_field.textProperty().addListener((observable, oldValue, newValue) -> {
            initialize_array.setDisable(true);
            if (!newValue.equals("") && !newValue.matches("^[1-9]\\d*$")) {
                text_field.setText(oldValue);
            } else if (!newValue.equals("") && !newValue.equals(String.valueOf(array_length))) {
                initialize_array.setDisable(false);
            }
        });
    }

    /**
     * Creates the button that initializes the array with the length entered to the text field.
     *
     * @since v0.9.0 (beta)
     */
    private void createInitializeArrayButton() {
        initialize_array = new Button("Initialize");
        initialize_array.setDisable(true);
        initialize_array.setOnAction(
                event -> {
                    array_display.setHgap(.5);
                    createArrays();
                    repaintRectangles();
                    combo_box.setDisable(false);
                    combo_box.setPromptText("Select algorithm");
                    initialize_array.setDisable(true);
                    initialize_array.setText("Resize array");
                    shuffle_array.setDisable(false);
                    reverse_array.setDisable(false);
                }
        );
    }

    /**
     * Creates the button that shuffles the displayed array.
     *
     * @since v0.9.0 (beta)
     */
    private void createShuffleButton() {
        shuffle_array = new Button("Shuffle");
        shuffle_array.setDisable(true);
        shuffle_array.setOnAction(
                event -> {
                    Random random = ThreadLocalRandom.current();
                    for (int i = array_length - 1; i > 0; i--) {
                        int index = random.nextInt(i + 1);
                        // Simple swap
                        int a = array[index];
                        array[index] = array[i];
                        array[i] = a;
                    }
                    repaintRectangles();
                }
        );
    }

    /**
     * Creates the button that reverses the displayed array.
     *
     * @since v0.9.0 (beta)
     */
    private void createReverseButton() {
        reverse_array = new Button("Reverse");
        reverse_array.setDisable(true);
        reverse_array.setOnAction(
                event -> {
                    for (int i = 0; i < array_length / 2; i++) {
                        int tmp = array[i];
                        array[i] = array[array_length - 1 - i];
                        array[array_length - 1 - i] = tmp;
                    }
                    highlightRectangles(0, 3);
                    repaintRectangles();
                }
        );
    }

    /**
     * Creates the grid pane that is used to display the array and the algorithm.
     *
     * @since v0.9.0 (beta)
     */
    private void createArrayDisplay() {
        array_display = new GridPane();
        array_display.setPadding(new Insets(10, 10, 10, 10));
        //array_display.setStyle("-fx-background-color: red");
    }

    /**
     * Creates the arrays.
     *
     * @since v0.9.0 (beta)
     */
    private void createArrays() {
        if (!array_display.getChildren().isEmpty()) {
            array_display.getChildren().removeAll(rectangles);
        }
        array_length = Integer.parseInt(text_field.getText());
        array = new int[array_length];
        rectangles = new Rectangle[array_length];
        for (int i = 0; i < array_length; i++) {
            array[i] = i + 1;
            rectangles[i] = new Rectangle();
            rectangles[i].setStyle("-fx-fill: green");
            GridPane.setValignment(rectangles[i], VPos.BOTTOM);
            array_display.add(rectangles[i], i, 0);
        }
    }

    private void repaintRectangles() {
        for (int i = 0; i < array_length; i++) {
            double gap_size = (array_length - 1) * array_display.getHgap();
            double h_padding = array_display.getPadding().getLeft() + array_display.getPadding().getRight();
            double h_stage_addon = 8;
            double v_padding = array_display.getPadding().getTop() + array_display.getPadding().getBottom();
            double v_stage_addon = 32;
            double width = (SCENE.getWidth() - h_padding - h_stage_addon - gap_size) / array_length;
            double height = (SCENE.getHeight() - v_padding - v_stage_addon - array_display.getLayoutY()) * array[i] / array_length;
            rectangles[i].setWidth(width);
            rectangles[i].setHeight(height);
        }
    }

    private void highlightRectangles(int... indices) {
        for (int index : indices) {
            rectangles[index].setFill(Paint.valueOf("Yellow"));
        }
    }
}
