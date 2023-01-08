package gui;

import gui.MainMenuScene;
import gui.sorting.SortingScene;
import javafx.stage.Stage;

public class SceneSwitcher {

    public static void switchToMainMenu(Stage stage) {
        stage.setScene(new MainMenuScene(stage));
        stage.setTitle("JAlgoPlot");
    }

    public static void switchToSortingDisplay(Stage stage) {
        stage.setScene(new SortingScene(stage));
        stage.setTitle("JAlgoPlot - Sorting");
    }

}
