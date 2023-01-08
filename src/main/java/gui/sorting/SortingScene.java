package gui.sorting;

import gui.GUI_Utils;
import gui.SceneSwitcher;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class SortingScene extends Scene {

    private final VBox root;

    private final double width = 1300.0;

    private final double height = 800.0;

    private final Stage stage;

    /**
     * The menu bar at the top of the sorting display. Adds the ability to return to the main menu, exit the application
     * and access the settings.
     *
     * @since v1.1.0
     */
    private final MenuBar menuBar = new MenuBar();

    /**
     * <p>The pane in which the rectangles representing the array are drawn. Further, the pane is responsible for adding
     * animations to the rectangles. Those animations include the highlighting of the rectangles and the swapping of
     * their positions.</p>
     *
     * The position of the pane is set to be in between the controls of the algorithm and the panel containing
     * information about the algorithm. When the stage is resized, the pane will be resized as well.
     *
     * @since v1.1.0
     */
    private final ArrayPane arrayPane = new ArrayPane();

    /**
     * The panel containing the controls of the algorithm. The controls include the buttons to start, pause and stop the
     * algorithm, a drop-down menu to select the algorithm and the ability to control the speed of the display. Further,
     * in order to initialize the displayed array, the panel contains a text field to enter the size of the array.
     *
     * @since v1.1.0
     */
    private final HBox controls = new HBox();

    /**
     * The panel containing information about the algorithm. The information includes the name of the algorithm, the
     * complexity of the algorithm and information about the current state of execution. That includes a progress bar,
     * indicating the progress of the algorithm, and a label, indicating the current step of the algorithm.
     *
     * @since v1.1.0
     */
    private final BorderPane algoInfo = new BorderPane();

    private final ComboBox<String> algoSelection = new ComboBox<>(getAlgorithms());;

    private final Button runButton = new Button("Start");

    private final Button stopButton = new Button("Stop");;

    private final TextField arraySize = new TextField();

    private final Button initButton = new Button("Initialize");

    private final Button shuffleButton = new Button("Shuffle");

    private final Button reverseButton = new Button("Reverse");

    private final Button flipButton = new Button("Flip");

    private final TextField speedField = new TextField("1.0");

    private final Button increaseSpeed = new Button("+");

    private final Button decreaseSpeed = new Button("-");

    private final Button screenshotButton = new Button("Screenshot");

    private final ProgressBar progressBar = new ProgressBar();

    public SortingScene(Stage stage) {
        super(new VBox());
        root = (VBox) getRoot();
        root.setPrefSize(width, height);
        this.stage = stage;

        initMenuBar();

        initControls();

        initArrayDisplay();

        initAlgoInfo();
    }

    /**
     * Initializes the menu bar at the top of the sorting display. Adds the ability to return to the main menu, exit the
     * application and access the settings.
     *
     * @since v1.1.0
     */
    private void initMenuBar() {
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        Menu homeMenu = new Menu("Home");

        MenuItem mainMenu = new MenuItem("Main Menu");
        mainMenu.setOnAction(event -> SceneSwitcher.switchToMainMenu(stage));

        MenuItem exitMenu = new MenuItem("Exit");
        exitMenu.setOnAction(event -> Platform.exit());

        homeMenu.getItems().addAll(
                mainMenu,
                exitMenu
        );


        Menu settingsMenu = new Menu("Settings");

        MenuItem colorSettings = new MenuItem("Color Settings");

        settingsMenu.getItems().addAll(
                colorSettings
        );

        menuBar.getMenus().addAll(homeMenu, settingsMenu);
        root.getChildren().add(menuBar);
    }

    /**
     * Initializes the panel containing the controls of the algorithm. The controls include the buttons to start, pause
     * and stop the algorithm, a drop-down menu to select the algorithm and the ability to control the speed of the
     * display. Further, in order to initialize the displayed array, the panel contains a text field to enter the size
     * of the array.
     *
     * @since v1.1.0
     */
    private void initControls() {
        controls.setPadding(GUI_Utils.DEFAULT_INSETS);
        controls.setSpacing(GUI_Utils.DEFAULT_SPACING);
        controls.prefWidthProperty().bind(stage.widthProperty());

        algoSelection.setDisable(true);
        algoSelection.setPromptText("Initialize Array");
        algoSelection.setOnAction(event -> {
            arrayPane.setAlgorithm(algoSelection.getValue());
            boolean disable = arrayPane.getAlgorithm() == null;
            runButton.setDisable(disable);
            speedField.setDisable(disable);
            increaseSpeed.setDisable(disable);
            decreaseSpeed.setDisable(disable);
        });

        runButton.setDisable(true);
        runButton.setOnAction(event -> {
            if (runButton.getText().equals("Start")) {
                stopButton.setDisable(false);

                initButton.setDisable(true);

                shuffleButton.setDisable(true);
                reverseButton.setDisable(true);
                flipButton.setDisable(true);

                runButton.setText("Pause");

                arrayPane.startAlgorithm();
            } else if (runButton.getText().equals("Pause")) {
                arrayPane.pauseAlgorithm();
                runButton.setText("Play");
            } else {
                arrayPane.resumeAlgorithm();
                runButton.setText("Pause");
            }
        });

        stopButton.setDisable(true);
        stopButton.setOnAction(
                event -> {
                    arrayPane.stopAlgorithm();

                    resetAfterAlgoFinished();
                }
        );

        arraySize.setPromptText("Enter array length");
        arraySize.setFocusTraversable(false);
        arraySize.textProperty().addListener((observable, oldValue, newValue) -> {
            initButton.setDisable(true);
            if (!newValue.equals("") && !newValue.matches("^[1-9]\\d*$")) {
                arraySize.setText(oldValue);
            } else if (!newValue.equals("") && !newValue.equals(String.valueOf(arrayPane.getLength()))) {
                initButton.setDisable(false);
            }
        });

        initButton.setDisable(true);
        initButton.setOnAction(
                event -> {
                    int size = Integer.parseInt(arraySize.getText());
                    arrayPane.init(size);
                    algoSelection.setDisable(false);
                    algoSelection.setPromptText("Select Algorithm");
                    shuffleButton.setDisable(false);
                    reverseButton.setDisable(false);
                    flipButton.setDisable(false);
                    initButton.setText("Resize");
                }
        );

        shuffleButton.setDisable(true);
        shuffleButton.setOnAction(
                event -> arrayPane.shuffle()
        );

        reverseButton.setDisable(true);
        reverseButton.setOnAction(
                event -> arrayPane.reverse()
        );

        flipButton.setDisable(true);
        flipButton.setOnAction(
                event -> arrayPane.flip()
        );

        speedField.setDisable(true);
        speedField.setFocusTraversable(false);
        speedField.setPrefWidth(50);
        arrayPane.algoAnimation.rateProperty().bind(
                Bindings.createDoubleBinding(
                        () -> Double.parseDouble(speedField.getText()),
                        speedField.textProperty()
                )
        );

        increaseSpeed.setDisable(true);
        increaseSpeed.setOnAction(
                e -> {
                    int speed = Double.valueOf(speedField.getText()).intValue();
                    speed++;
                    speedField.setText(String.valueOf(speed));
                }
        );

        decreaseSpeed.setDisable(true);
        decreaseSpeed.setOnAction(
                e -> {
                    int speed = Double.valueOf(speedField.getText()).intValue();
                    speed--;
                    if (speed < 0) {
                        speed = 0;
                    }
                    speedField.setText(String.valueOf(speed));
                }
        );

        HBox speedControls = new HBox(speedField, increaseSpeed, decreaseSpeed);

        screenshotButton.setOnAction(
                event -> GUI_Utils.takeScreenshot(stage.getScene())
        );

        controls.getChildren().addAll(
                algoSelection,
                runButton,
                stopButton,
                arraySize,
                initButton,
                shuffleButton,
                reverseButton,
                flipButton,
                speedControls,
                screenshotButton
        );

        root.getChildren().add(controls);
    }

    private void initAlgoInfo() {
        progressBar.progressProperty().bind(arrayPane.progressProperty());

        progressBar.prefWidthProperty().bind(stage.widthProperty().subtract(2 * GUI_Utils.DEFAULT_SPACING));

        algoInfo.setPadding(GUI_Utils.DEFAULT_INSETS);

        algoInfo.setTop(progressBar);

        root.getChildren().add(algoInfo);
    }

    /**
     * Initializes the panel containing the array display. The array display is an {@link ArrayPane} object. The
     * size of the panel is determined by the size of the other components in the sorting display. The width is kept
     * consistent with the width of the stage, while the height is determined by the height of the stage minus the
     * height of the menu bar, the controls and the algorithm information.
     *
     * @since v1.1.0
     */
    private void initArrayDisplay() {
        arrayPane.prefWidthProperty().bind(stage.widthProperty());

        arrayPane.prefHeightProperty().bind(
                stage.heightProperty().subtract(
                        menuBar.heightProperty().add(
                                controls.heightProperty().add(
                                        algoInfo.heightProperty()
                                )
                        )
                )
        );

        arrayPane.isFinishedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue) {
                        resetAfterAlgoFinished();
                    }
                }
        );

        root.getChildren().add(arrayPane);
    }

    /**
     * Resets the controls after the algorithm has finished. That includes, enabling the buttons to shuffle, reverse and
     * flip the array, disabling the stop button and changing the run button to "Start".
     *
     * @since v1.1.0
     */
    private void resetAfterAlgoFinished() {
        stopButton.setDisable(true);

        initButton.setDisable(false);

        shuffleButton.setDisable(false);
        reverseButton.setDisable(false);
        flipButton.setDisable(false);

        runButton.setText("Start");
    }

    /**
     * Reads all available sorting algorithms from the file "sorting_algorithms.txt" and returns them as a
     * {@link ObservableList}, so that it can be used in a {@link ComboBox}.
     *
     * @return The list of sorting algorithms.
     * @since v1.1.0
     */
    private ObservableList<String> getAlgorithms() {
        List<String> algorithms = new ArrayList<>();

        try (Scanner scanner = new Scanner(
                Objects.requireNonNull(getClass().getResourceAsStream("/algorithms/sorting_algorithms.txt")))
        ) {
            while (scanner.hasNextLine()) {
                algorithms.add(scanner.nextLine());
            }
        }

        return FXCollections.observableArrayList(algorithms);
    }

}
