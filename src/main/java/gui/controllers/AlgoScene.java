package gui.controllers;

import algorithms.Algorithm;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This class represents a scene that displays an algorithm. The algorithm that is to be display can be chosen from the
 * ComboBox of this object. Implementing classes add different algorithms to the ComboBox and implement ways to display
 * the workings of the algorithm. Further, each implementing class should also specify the pane that represents the
 * data structure needed for the algorithm (for example an array).
 * The upper portion of the scene is used to display modifying functionalities that influence the algorithm or the
 * presented data structure. In the middle the scene contains a pane that displays the data structure associated with
 * the type of algorithm represented by the scene. The lower portion of the scene contains general information about the
 * data structure.
 *
 * @since v0.9.0 (beta)
 */
public abstract class AlgoScene {

    /**
     * The algorithm that can be chosen by the ComboBox. The run()-method displays the algorithm in the scene.
     *
     * @since v0.9.0 (beta)
     */
    protected Algorithm algorithm;

    /**
     * The stage that this scene is added to. It should always be given in the constructor.
     *
     * @since v0.9.0 (beta)
     */
    protected final Stage STAGE;

    /**
     * The pane that displays the data structure and offers functionalities concerning the data structure.
     *
     * @since v0.9.0 (beta)
     */
    protected Pane PANE;

    /**
     * The minimum width that is set to the stage. This property avoids graphical bugs that could occur upon resizing
     * the stage this scene is added to.
     *
     * @since v0.9.0 (beta)
     */
    protected final int MINIMUM_WIDTH = 500; //TODO

    /**
     * The minimum height that is set to the stage. This property avoids graphical bugs that could occur upon resizing
     * the stage this scene is added to.
     *
     * @since v0.9.0 (beta)
     */
    protected final int MINIMUM_HEIGHT = 500; //TODO

    /**
     * The offset between the border of the scene (or the stage) and the components.
     *
     * @since v0.9.0 (beta)
     */
    protected final double OFFSET = 10.0;

    /**
     * The height of the menu bar.
     *
     * @since v0.9.0 (beta)
     */
    protected double menu_bar_height = 27;

    /**
     * The minimum height of the upper portion of the scene containing functionalities concerning the algorithm and data
     * structure displayed.
     *
     * @since v0.9.0 (beta)
     */
    protected final int SETTINGS_HEIGHT = 1; //TODO

    /**
     * The minimum height of the lower portion of the scene containing information about the data structure.
     *
     * @since v0.9.0 (beta)
     */
    protected final int INFO_HEIGHT = 1; //TODO

    /**
     * The menu bar that is displayed at the top of the scene.
     *
     * @since v0.9.0 (beta)
     */
    protected MenuBar menu_bar;

    /**
     * The combo box that is used to select the algorithm that is supposed to be displayed by the scene.
     *
     * @since v0.9.0 (beta)
     */
    protected ComboBox<String> choose_algorithm;

    /**
     * The button that starts the display of the selected algorithm when pressed. It is initially disabled and can only
     * be pressed after an algorithm has been chosen in the combo box.
     *
     * @since v0.9.0 (beta)
     */
    protected Button run_button;

    /**
     * Constructor that initializes the stage this scene is displayed in.
     *
     * @param stage The stage this scene is displayed in.
     *
     * @since v0.9.0 (beta)
     */
    protected AlgoScene(Stage stage) {
        this.STAGE = stage;
        createMenuBar();
        createComboBox();
        createRunButton();
    }

    /**
     * Creates the button that starts the display of the selected algorithm when pressed. It is initially deactivated
     * and can only be pressed after an algorithm has been chosen in the ComboBox.
     *
     * @since v0.9.0 (beta)
     */
    protected void createRunButton() {
        run_button = new Button("Run");
        run_button.setOnAction(event -> run());
        //run.prefWidth(0.0); //TODO
        //run.prefHeight(0.0); //TODO
        run_button.setLayoutX(2 * OFFSET + 150); //TODO
        run_button.setLayoutY(menu_bar_height + OFFSET); //TODO
        run_button.setDisable(true);
    }

    /**
     * Creates the first menu "Menu" in the menu bar. Adds all MenuItems.
     *
     * @return  The menu.
     *
     * @since v0.9.0 (beta)
     */
    private Menu createMenuMenu() {
        Menu menu = new Menu("Menu");

        /*
        Create MenuItems
         */
        MenuItem launcher = new MenuItem("Main Menu");
        launcher.setOnAction(event -> System.out.println("TODO")); //TODO
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(event -> Platform.exit());

        /*
        Add MenuItems
         */
        menu.getItems().addAll(launcher, exit);

        return menu;
    }

    /**
     * Creates the menu bar that is displayed at the top of the scene.
     *
     * @since v0.9.0 (beta)
     */
    protected void createMenuBar() {
        menu_bar = new MenuBar();
        menu_bar.getMenus().addAll(createMenuMenu());
        menu_bar.prefWidthProperty().bind(STAGE.widthProperty());
    }

    /**
     * Creates the ComboBox that is used to select the algorithm for display. Each implementing class should add all
     * algorithms of that category. Further, each implementing class should add an event handler that assigns the
     * algorithm attribute of this class to the corresponding selected algorithm.
     *
     * @since v0.9.0 (beta)
     */
    protected abstract void createComboBox();

    /**
     * Starts the graphical display of the selected algorithm. Updates the pane containing the data structure according
     * to the states inside the StateMachine of the algorithm.
     *
     * @since v0.9.0 (beta)
     */
    protected abstract void run();

    /**
     * Creates the scene that contains all components and returns it. Each implementing class should add all necessary
     * components.
     *
     * @return  The scene.
     *
     * @since v0.9.0 (beta)
     */
    public abstract Scene getScene();

}
