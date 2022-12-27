package gui.scenes;

import algorithms.State;
import algorithms.sorting.SortingAction;
import algorithms.sorting.SortingAlgorithm;
import algorithms.sorting.SortingState;
import algorithms.sorting.bubblesort.BubbleSort;
import datastructure.Pair;
import gui.GUI_Utils;
import gui.Launcher;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents the scene of the sorting algorithms. It offers all methods that change the scene accordingly.
 * Further, action events triggered by the buttons of the sorting scene are processed here.
 *
 * @since v0.9.0 (beta)
 */
public class SortingScene {

    /*
    --- General attributes ---
     */

    /**
     * The length of the array that is displayed by the scene.
     *
     * @since v0.9.0 (beta)
     */
    private int array_length;

    /**
     * The array that is displayed in the middle of the scene and that is sorted by the chosen algorithm. It is
     * initially set to null and can be initialized by entering a size into the text field and pressing the initialize
     * button.
     *
     * @since v0.9.0 (beta)
     */
    private int[] array;

    /**
     * The algorithm that is selected in the combo box to sort the array of the scene.
     *
     * @since v0.9.0 (beta)
     */
    private SortingAlgorithm algorithm;

    /**
     * List of all algorithms that can be displayed by the scene. The list is added to the combo box.
     *
     * @since v0.9.0 (beta)
     */
    private final String[] ALGORITHMS = {
            "Bubble Sort",
            "Soon more..."
    };

    private final double PADDING = 10.0;


    /*
    --- JavaFX attributes ---
     */

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

    private Pane array_display;

    /**
     * TODO
     */
    private Rectangle[] rectangles;

    /**
     * The menu bar at the top of the scene.
     *
     * @since v0.9.0 (beta)
     */
    private MenuBar menu_bar;

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
     * The button that starts the display of the algorithm.
     *
     * @since v0.9.0 (beta)
     */
    private Button run_algo_button;

    /**
     * Button that initializes the array with the length set in the text field.
     *
     * @since v0.9.0 (beta)
     */
    private Button init_array_button;

    /**
     * Button that shuffles the array displayed. Only active after array was initialized.
     *
     * @since v0.9.0 (beta)
     */
    private Button shuffle_array_button;

    /**
     * Button that reverses the array displayed. Only active after array was initialized.
     *
     * @since v0.9.0 (beta)
     */
    private Button rev_array_button;

    /**
     * Button that flips the alignment of the rectangles displayed in the scene. Only active after array was initialized.
     *
     * @since v0.9.0 (beta)
     */
    private Button flip_align_button;

    /**
     * Constructor initializing the stage object that the scene is added to. It further initializes the actual scene
     * with its components. The scene can then be accessed by the {@link #getScene() getScene}-method.
     *
     * @param stage The stage this scene is set in.
     * @since v0.9.0 (beta)
     */
    public SortingScene(Stage stage) {
        stage.setTitle("Sorting Pane");  // Set title of the stage
        stage.setResizable(true);  // Allow resizing of the stage
        this.STAGE = stage;  // Set the stage object of this class to the stage object passed to the constructor

        VBox box = new VBox();  // Create a new VBox to add all components to

        createMenuBar();  // Create the menu bar
        box.getChildren().addAll(menu_bar, createMainPane());  // Add the menu bar and the main pane to the VBox

        SCENE = new Scene(box);  // Create the scene with the VBox as root node
        SCENE.widthProperty().addListener(
                (observable, oldValue, newValue) -> repaintRectangles()
        );  // Add a listener to the width of the scene to repaint the rectangles when the width changes
        SCENE.heightProperty().addListener(
                (observable, oldValue, newValue) -> repaintRectangles()
        );  // Add a listener to the height of the scene to repaint the rectangles when the height changes
    }

    /**
     * Returns the scene that is initialized in the constructor of this class.
     *
     * @return  The stage.
     * @since v0.9.0 (beta)
     */
    public Scene getScene() {
        return SCENE;  // Return the scene
    }

    /**
     * Creates the main pane of the scene that all components are added to and returns it after creation.
     *
     * @return  The main pane of the scene.
     * @since v0.9.0 (beta)
     */
    private BorderPane createMainPane() {
        BorderPane main_pane = new BorderPane();  // Create a new BorderPane to add all components to

        HBox settings_box = createSettingsBox();  // Create the settings box
        main_pane.setTop(settings_box);  // Add the settings box to the top of the main pane

        array_display = new Pane();  // Create a new pane to display the array
        array_display.setPadding(new Insets(10, 10, 10, 10));  // Set padding of the pane
        array_display.setPrefSize(settings_box.getWidth(), 400);// Create the array display
        main_pane.setCenter(array_display);  // Add the array display to the center of the main pane

        return main_pane;  // Return the main pane
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
     * @since v0.9.0 (beta)
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
                    Scene scene = new Scene(root, Launcher.WIDTH, Launcher.HEIGHT);
                    STAGE.setTitle("JAlgoPlot");
                    STAGE.setScene(scene);
                    STAGE.sizeToScene();
                    STAGE.setResizable(false);
                    STAGE.show();
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
     * @since v0.9.0 (beta)
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
                flip_align_button,
                screenshot
        );  // Add all components to the HBox
        h_box.setStyle("-fx-background-color: #336699;");  // Set the background color of the HBox

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
                    algorithm = switch (combo_box.getValue()) {
                        case "Bubble Sort" -> new BubbleSort(array);
                        default -> null;
                        //TODO: Add more cases
                    };
                    run_algo_button.setDisable(algorithm == null);
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
                event -> run_algorithm()
        );
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
            init_array_button.setDisable(true);
            if (!newValue.equals("") && !newValue.matches("^[1-9]\\d*$")) {
                text_field.setText(oldValue);
            } else if (!newValue.equals("") && !newValue.equals(String.valueOf(array_length))) {
                init_array_button.setDisable(false);
            }
        });
    }

    /**
     * Creates the button that initializes the array with the length entered to the text field.
     *
     * @since v0.9.0 (beta)
     */
    private void createInitializeArrayButton() {
        init_array_button = new Button("Initialize");
        init_array_button.setDisable(true);
        init_array_button.setOnAction(
                event -> {
                    initArrays();
                    repaintRectangles();
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
     * @since v0.9.0 (beta)
     */
    private void createShuffleButton() {
        shuffle_array_button = new Button("Shuffle");
        shuffle_array_button.setDisable(true);
        shuffle_array_button.setOnAction(
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
        rev_array_button = new Button("Reverse");
        rev_array_button.setDisable(true);
        rev_array_button.setOnAction(
                event -> {
                    for (int i = 0; i < array_length / 2; i++) {
                        int tmp = array[i];
                        array[i] = array[array_length - 1 - i];
                        array[array_length - 1 - i] = tmp;
                    }
                    repaintRectangles();
                }
        );
    }

    /**
     * Creates the button that flips the alignment of the displayed array.
     *
     * @since v0.9.0 (beta)
     */
    private void createFlipButton() {
        flip_align_button = new Button("Flip");
        flip_align_button.setDisable(true);
        flip_align_button.setOnAction(
                event -> {
                    for (int i = 0; i < array_length; i++) {
                        rectangles[i].setY(
                                rectangles[i].getY() == PADDING ?
                                        array_display.getHeight() - PADDING - rectangles[i].getHeight() :
                                        PADDING
                        );
                    }
                }
        );
    }

    /**
     * Creates the arrays. The array_length attribute is used to determine the length of the array. The array attribute
     * is used to store the values of the array. The rectangles attribute is used to store the rectangles that are
     * displayed.
     *
     * @since v0.9.0 (beta)
     */
    private void initArrays() {
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
            array_display.getChildren().add(rectangles[i]);
        }
    }

    /**
     * Repaints the rectangles that are displayed. The rectangles are painted green if they are not highlighted, red if
     * they are highlighted and blue if they are being compared.
     *
     * @since v0.9.0 (beta)
     */
    private void repaintRectangles() {
        for (int i = 0; i < array_length; i++) {
            double v_stage_addon = 32;
            double width = ((array_display.getWidth() - 2 * PADDING) - (array_length - 1)) / array_length;
            double height = (SCENE.getHeight() - 2 * PADDING - v_stage_addon - array_display.getLayoutY()) * array[i] / array_length;

            double x = PADDING + i * (width + 1);

            rectangles[i].setWidth(width);
            rectangles[i].setHeight(height);
            rectangles[i].setX(x);
            rectangles[i].setY(PADDING);
        }
    }

    /**
     * Highlights the rectangles that are displayed. The rectangles are painted green if they are not highlighted, red if
     * they are highlighted and blue if they are being compared.
     *
     * @since v0.9.0 (beta)
     */
    private void highlightRectangles(int... indices) {
        for (int index : indices) {
            rectangles[index].setFill(Paint.valueOf("Yellow"));
        }
    }

    private void highlightRectangles(Pair<Integer, Integer> indices) {
        highlightRectangles(indices.first, indices.second);
    }

    private void resetRectangleColors(int... indices) {
        for (int index : indices) {
            rectangles[index].setFill(Paint.valueOf("Green"));
        }
    }

    private void resetRectangleColors(Pair<Integer, Integer> indices) {
        resetRectangleColors(indices.first, indices.second);
    }

    /**
     * Runs the selected algorithm.
     *
     * @since v0.9.0 (beta)
     */
    private void run_algorithm() {
        Iterator<State> iterator = algorithm.getStateMachine().iterator();
        new Thread(() -> {
            while (iterator.hasNext()) {
                SortingState state = (SortingState) iterator.next();
                Platform.runLater(() -> {
                    //highlightRectangles(state.getIndices());
                    if (state.getAction() == SortingAction.SWAP) {
                        swapRectangles(state.getIndices());
                    }
                    //resetRectangleColors(state.getIndices());
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void swapRectangles(int index1, int index2) {
        highlightRectangles(index1, index2);

        double translation = rectangles[index2].getX() - rectangles[index1].getX();

        TranslateTransition tt1 = new TranslateTransition(Duration.millis(500), rectangles[index1]);
        tt1.setByX(translation);

        TranslateTransition tt2 = new TranslateTransition(Duration.millis(500), rectangles[index2]);
        tt2.setByX(-translation);

        ParallelTransition pt = new ParallelTransition(tt1, tt2);
        pt.setOnFinished(
                event -> {
                    rectangles[index1].setX(rectangles[index1].getX() + rectangles[index1].getTranslateX());
                    rectangles[index1].setTranslateX(0);
                    rectangles[index2].setX(rectangles[index2].getX() + rectangles[index2].getTranslateX());
                    rectangles[index2].setTranslateX(0);

                    Rectangle temp = rectangles[index1];
                    rectangles[index1] = rectangles[index2];
                    rectangles[index2] = temp;

                    int tmp = array[index1];
                    array[index1] = array[index2];
                    array[index2] = tmp;

                    resetRectangleColors(index1, index2);
                }
        );
        pt.play();
    }


    private void swapRectangles(Pair<Integer, Integer> indices) {
        swapRectangles(indices.first, indices.second);
    }
}
