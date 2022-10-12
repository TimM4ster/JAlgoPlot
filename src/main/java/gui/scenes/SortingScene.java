package gui.scenes;

import algorithms.sorting.bubblesort.BubbleSort;
import gui.controllers.AlgoScene;
import gui.panes.SortingPane;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SortingScene extends AlgoScene {

    /**
     * The array that is currently displayed by this scene;
     *
     * @since v0.9.0 (beta)
     */
    private int[] array = {2, 1, 5, 3, 4};

    /**
     * List of all algorithms that can be displayed by the scene.
     *
     * @since v0.9.0 (beta)
     */
    private static final String[] ALGORITHMS = {
            "Bubble Sort"
    };

    /**
     * Constructor that initializes the stage this scene is displayed in.
     *
     * @param stage The stage this scene is displayed in.
     * @since v0.9.0 (beta)
     */
    public SortingScene(Stage stage) {
        super(stage);
        createPane();
        stage.setMinWidth(MINIMUM_WIDTH);
        stage.setMinHeight(MINIMUM_HEIGHT);
    }

    protected void createPane() {
        SortingPane pane = new SortingPane();

        pane.setArray(array);

        PANE = pane;

        PANE.prefWidthProperty().bind(STAGE.widthProperty());
        PANE.prefHeightProperty().bind(STAGE.heightProperty());
    }

    /**
     * Creates the ComboBox that is used to select the algorithm for display. Each implementing class should add all
     * algorithms of that category. Further, each implementing class should add an event handler that assigns the
     * algorithm attribute of this class to the corresponding selected algorithm.
     *
     * @since v0.9.0 (beta)
     */
    @Override
    protected void createComboBox() {
        choose_algorithm = new ComboBox<>(FXCollections.observableArrayList(ALGORITHMS));
        choose_algorithm.setOnAction(
                event -> {
                    algorithm = switch (choose_algorithm.getValue()) {
                        case "Bubble Sort" -> new BubbleSort(array);
                        default -> null;
                        //TODO: Add more cases
                    };
                    run_button.setDisable(false);
                }
        );
        choose_algorithm.setPromptText("Select algorithm");
        choose_algorithm.setLayoutX(OFFSET);
        choose_algorithm.setLayoutY(menu_bar_height + OFFSET);
    }

    /**
     * Starts the graphical display of the selected algorithm. Updates the pane containing the data structure according
     * to the states inside the StateMachine of the algorithm.
     *
     * @since v0.9.0 (beta)
     */
    @Override
    protected void run() {

    }

    /**
     * Creates the scene that contains all components and returns it. Each implementing class should add all necessary
     * components.
     *
     * @return The scene.
     * @since v0.9.0 (beta)
     */
    @Override
    public Scene getScene() {
        AnchorPane pane = new AnchorPane();
        pane.getChildren().addAll(menu_bar, choose_algorithm, run_button, PANE);
        return new Scene(pane, MINIMUM_WIDTH, MINIMUM_HEIGHT);
    }
}
