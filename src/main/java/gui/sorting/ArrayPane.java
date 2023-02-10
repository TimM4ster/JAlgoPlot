package gui.sorting;

import algorithms.State;
import algorithms.StateMachine;
import algorithms.sorting.SortingAlgorithm;
import algorithms.sorting.SortingState;
import algorithms.sorting.bubblesort.BubbleSort;
import datastructure.Pair;
import gui.GUI_Utils;
import javafx.animation.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static gui.sorting.ArrayUtils.DEFAULT_COLOR;
import static gui.sorting.ArrayUtils.SELECTED_COLOR;

public class ArrayPane extends Pane {

    private final boolean movable = false;

    private int[] array;

    private Rectangle[] rectangles;

    private TextField[] labels;

    private double[] xCoordinates;

    private int length;

    private int largest;

    private double rectangleWidth;

    private SortingAlgorithm algorithm;

    public final SequentialTransition algoAnimation = new SequentialTransition();

    private double totalStates;

    private double currentState;

    private final DoubleProperty progressProperty = new SimpleDoubleProperty(0);

    private final BooleanProperty isFinishedProperty = new SimpleBooleanProperty(false);

    public ArrayPane() {
        widthProperty().addListener((observable, oldValue, newValue) ->
                paint()
        );

        heightProperty().addListener((observable, oldValue, newValue) ->
                paint()
        );

        setStyle("-fx-border-color: gray; -fx-border-width: 1 0;");
    }

    public int getLength() {
        return length;
    }

    public BooleanProperty isFinishedProperty() {
        return isFinishedProperty;
    }

    public DoubleProperty progressProperty() {
        return progressProperty;
    }

    public void init(int length) {
        this.length = length;
        this.xCoordinates = new double[length];
        this.array = new int[length];
        this.rectangles = new Rectangle[length];
        this.labels = new TextField[length];

        this.rectangleWidth = ((getWidth() - 2 * GUI_Utils.DEFAULT_PADDING) - 2 * (length - 1)) / length;

        initXCoordinates();
        initArray();
        initRectangles();
        initLabels();
    }

    private void initXCoordinates() {
        for (int i = 0; i < length; i++) {
            xCoordinates[i] = GUI_Utils.DEFAULT_PADDING + i * (rectangleWidth + 2);
        }
    }

    private void initArray() {
        for (int i = 0; i < length; i++) {
            array[i] = i + 1;
        }
        largest = length;
    }

    private double draggingOffset = 0;
    private double initialX = 0;
    private void initRectangles() {
        getChildren().clear();

        for (int i = 0; i < length; i++) {
            rectangles[i] = new Rectangle();
            rectangles[i].setFill(DEFAULT_COLOR);
            rectangles[i].setOnMousePressed(
                    event -> {

                        if (event.getButton().equals(MouseButton.SECONDARY)) {
                            ContextMenu contextMenu = new ContextMenu();
                            MenuItem resizeItem = new MenuItem("Resize");
                            contextMenu.getItems().add(resizeItem);
                            contextMenu.show(this, event.getScreenX(), event.getScreenY());
                        }

                    }
            );

            if (movable) {
                int finalI = i;

                rectangles[i].setOnMousePressed(
                        event -> {
                            rectangles[finalI].setFill(SELECTED_COLOR);
                            initialX = rectangles[finalI].getX();
                            draggingOffset = event.getX() - initialX;
                        }
                );
                rectangles[i].setOnMouseDragged(
                        event -> {
                            double newX = event.getX() - draggingOffset;
                            rectangles[finalI].setX(newX);
                            labels[finalI].setLayoutX(event.getX() - draggingOffset);
                        }
                );
                rectangles[i].setOnMouseReleased(
                        event -> {
                            rectangles[finalI].setFill(DEFAULT_COLOR);
                            rectangles[finalI].setX(getClosestX(rectangles[finalI].getX()));
                            System.out.println("Drag released");
                        }
                );
            }
        }

        getChildren().addAll(rectangles);
        paintRectangles();
    }

    public void paintRectangles() {
        for (int i = 0; i < length; i++) {
            double height = (getHeight() - GUI_Utils.DEFAULT_PADDING * 2) / largest * array[i];

            rectangles[i].setHeight(
                    height
            );

            rectangles[i].setWidth(
                    rectangleWidth
            );

            rectangles[i].setX(
                    xCoordinates[i]
            );

            rectangles[i].setY(
                    getHeight() - GUI_Utils.DEFAULT_PADDING - height
            );

            rectangles[i].setTranslateX(
                    0
            );
        }
    }

    private void initLabels() {
        for (int i = 0; i < length; i++) {
            labels[i] = new TextField(String.valueOf(array[i]));
            labels[i].setAlignment(Pos.BOTTOM_CENTER);

            labels[i].layoutXProperty().bind(rectangles[i].xProperty());
            labels[i].layoutYProperty().bind(
                    this.heightProperty().subtract(labels[i].heightProperty().add(GUI_Utils.DEFAULT_PADDING))
            );

            labels[i].prefWidthProperty().bind(rectangles[i].widthProperty());

            labels[i].setStyle("-fx-background-color: transparent;");

            int finalI = i;

            labels[i].textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    labels[finalI].setText(oldValue);
                }
            });
            labels[i].setOnKeyPressed(
                    event -> {
                        if (event.getCode() == KeyCode.ENTER) {
                            int newValue = Integer.parseInt(labels[finalI].getText());
                            array[finalI] = newValue;
                            largest = ArrayUtils.getLargestValue(array);
                            paintRectangles();
                            requestFocus();
                        }
                    }
            );
        }
        getChildren().addAll(labels);
        paintLabels();
    }

    private void paintLabels() {
        for (int i = 0; i < length; i++) {
            if (rectangles[i].getWidth() > 31) {
                labels[i].setVisible(true);
                labels[i].setText(String.valueOf(array[i]));
            } else {
                labels[i].setVisible(false);
            }
        }
    }

    private void updateRectangleWidth() {
        this.rectangleWidth = ((getWidth() - 2 * GUI_Utils.DEFAULT_PADDING) - 2 * (length - 1)) / length;
    }

    private void paint() {
        updateRectangleWidth();
        initXCoordinates();
        paintRectangles();
        paintLabels();
    }

    private double getClosestX(double x) {
        double closestX = xCoordinates[0];
        for (double xCoordinate : xCoordinates) {
            if (Math.abs(xCoordinate - x) < Math.abs(closestX - x)) {
                closestX = xCoordinate;
            }
        }
        return closestX;
    }

    private void resetRectangleColors(int... indices) {
        for (int index : indices) {
            rectangles[index].setFill(DEFAULT_COLOR);
        }
    }

    public void shuffle() {
        ArrayUtils.shuffleArray(array);
        paint();
    }

    public void reverse() {
        ArrayUtils.reverseArray(array);
        paint();
    }

    public void flip() {
        //TODO
    }

    public void setAlgorithm(String name) {
        this.algorithm = switch (name) {
            case "Bubble Sort" -> new BubbleSort(array);
            default -> null;
            //TODO: Add more cases
        };
    }

    public SortingAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void startAlgorithm() {
        isFinishedProperty.set(false);
        algorithm.setArray(array);

        StateMachine stateMachine = algorithm.getStateMachine();

        totalStates = stateMachine.numberOfStates;
        currentState = 0;

        Iterator<State> iterator = stateMachine.iterator();

        List<Animation> transitions = new ArrayList<>();

        while (iterator.hasNext()) {
            SortingState state = (SortingState) iterator.next();
            switch (state.getAction()) {
                case SWAP -> {
                    transitions.add(getSwappingTransition(state.getIndices()));
                }
                case COMPARE -> {
                    transitions.add(getComparisonTransition(state.getIndices()));
                }
                case HIGHLIGHT -> {
                    
                }
            }
        }

        Animation[] animations = transitions.toArray(new Animation[0]);

        algoAnimation.getChildren().addAll(animations);

        algoAnimation.setOnFinished(event -> {
            isFinishedProperty.set(true);
            algoAnimation.getChildren().clear();
        });

        algoAnimation.play();
    }

    public void pauseAlgorithm() {
        algoAnimation.pause();
    }

    public void resumeAlgorithm() {
        algoAnimation.play();
    }

    public void stopAlgorithm() {
        algoAnimation.stop();
        algoAnimation.getChildren().clear();
        paint();
        for (int i = 0; i < length; i++) {
            rectangles[i].setFill(DEFAULT_COLOR);
        }
    }

    private ParallelTransition getSwappingTransition(Pair<Integer, Integer> indices) {
        return getSwappingTransition(indices.first, indices.second);
    }

    private ParallelTransition getSwappingTransition(int index1, int index2) {
        TranslateTransition tt1 = new TranslateTransition(
                GUI_Utils.DEFAULT_ANIMATION_DURATION,
                rectangles[index1]
        );

        tt1.byXProperty().bind(rectangles[index2].xProperty().subtract(rectangles[index1].xProperty()));

        TranslateTransition tt2 = new TranslateTransition(
                GUI_Utils.DEFAULT_ANIMATION_DURATION,
                rectangles[index2]
        );

        tt2.byXProperty().bind(rectangles[index1].xProperty().subtract(rectangles[index2].xProperty()));

        TranslateTransition tt3 = new TranslateTransition(
                GUI_Utils.DEFAULT_ANIMATION_DURATION,
                labels[index1]
        );

        tt3.byXProperty().bind(rectangles[index2].xProperty().subtract(rectangles[index1].xProperty()));

        TranslateTransition tt4 = new TranslateTransition(
                GUI_Utils.DEFAULT_ANIMATION_DURATION,
                labels[index2]
        );

        tt4.byXProperty().bind(rectangles[index1].xProperty().subtract(rectangles[index2].xProperty()));

        ParallelTransition swappingTransition = new ParallelTransition(tt1, tt2, tt3, tt4);

        swappingTransition.setOnFinished(
                event -> {
                    currentState++;
                    progressProperty.set(currentState / totalStates);

                    rectangles[index1].setTranslateX(0);
                    rectangles[index2].setTranslateX(0);

                    labels[index1].setTranslateX(0);
                    labels[index2].setTranslateX(0);

                    int tmp = array[index1];
                    array[index1] = array[index2];
                    array[index2] = tmp;
                    paint();
                }
        );

        return swappingTransition;
    }

    private ParallelTransition getComparisonTransition(Pair<Integer, Integer> indices) {
        return getComparisonTransition(indices.first, indices.second);
    }

    private ParallelTransition getComparisonTransition(int index1, int index2) {
        FillTransition ft1 = new FillTransition(
                GUI_Utils.DEFAULT_ANIMATION_DURATION,
                rectangles[index1],
                DEFAULT_COLOR,
                SELECTED_COLOR
        );

        FillTransition ft2 = new FillTransition(
                GUI_Utils.DEFAULT_ANIMATION_DURATION,
                rectangles[index2],
                DEFAULT_COLOR,
                SELECTED_COLOR
        );

        ParallelTransition comparisonTransition = new ParallelTransition(ft1, ft2);

        comparisonTransition.setOnFinished(
                event -> {
                    currentState++;
                    progressProperty.set(currentState / totalStates);

                    resetRectangleColors(index1, index2);
                }
        );

        return comparisonTransition;
    }

}
