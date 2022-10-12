package gui;

import gui.controllers.LauncherControls;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scenes/Launcher.fxml")));
        for (Node node :
                root.getChildrenUnmodifiable()) {
            System.out.println(node);
        }

        GridPane testPane = new GridPane();
        Rectangle rec1 = new Rectangle();
        rec1.setHeight(200);
        rec1.setWidth(100);
        Rectangle rec2 = new Rectangle();
        rec2.setHeight(300);
        rec2.setWidth(100);
        GridPane.setValignment(rec1, VPos.BOTTOM);
        testPane.add(rec1, 0, 0);
        testPane.add(rec2, 1, 0);


        Scene scene = new Scene(root, LauncherControls.WIDTH, LauncherControls.HEIGHT);

        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->
                //rec1.setHeight(stage.getHeight());
                System.out.println("Height: " + stage.getHeight() + " Width: " + stage.getWidth());

        stage.setTitle("Launcher");
        stage.setScene(scene);
        //stage.setResizable(false);

        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);

        stage.show();

        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());

    }
}
