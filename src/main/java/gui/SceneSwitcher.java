package gui;

import gui.MainMenuScene;
import gui.settings.ColorSettingsScene;
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

    public static void openColorSettings() {
        Stage settingsStage = new Stage();
        settingsStage.setScene(new ColorSettingsScene());
        settingsStage.setTitle("JAlgoPlot - Color Settings");
        settingsStage.setResizable(false);
        settingsStage.show();
    }

}
