package gui.scenes;

import algorithms.State;
import algorithms.StateMachine;
import algorithms.sorting.SortingAlgorithm;
import algorithms.sorting.SortingState;
import algorithms.sorting.bubblesort.BubbleSort;
import datastructure.Pair;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static gui.scenes.ArrayUtils.*;

/**
 * A pane that displays an array and the states of a sorting algorithm. The states are displayed as a timeline. The user
 * can control the timeline by using the play, pause and stop buttons. The user can also change the speed of the
 * animation.
 *
 * @author TimK
 * @version v1.0.0
 * @since v1.0.0
 */
public class ArrayPane extends Pane {

    /**
     * The scene that contains this pane.
     *
     * @since v1.0.0
     */
    private final SortingScene scene;

    /**
     * The length of the array that is displayed.
     *
     * @since v1.0.0
     */
    private int array_length;

    /**
     * The array that is displayed. Each element of the array is represented by a rectangle. The height of the rectangle
     * represents the value of the element.
     *
     * @since v1.0.0
     */
    private int[] array;

    /**
     * The rectangles that are displayed. The height of the rectangles represent the values in the array.
     *
     * @since v1.0.0
     */
    private Rectangle[] rectangles;

    /**
     * Represents whether the rectangles are currently flipped.
     *
     * @since v1.0.0
     */
    private boolean flipped = false;

    /**
     * The padding of the pane. Each side of the pane has the same padding.
     *
     * @since v1.0.0
     */
    private final double padding = 10.0;

    /**
     * The algorithm that is executed on the array.
     *
     * @since v1.0.0
     */
    private SortingAlgorithm algorithm;

    /**
     * The transition that is currently playing. The object is global so that it can be stopped, paused or continued.
     * The transition is composed of multiple transitions. Each transition represents a state of the algorithm.
     *
     * @since v1.0.0
     */
    private SequentialTransition sequentialTransition;

    /**
     * Constructor initializing the scene object and the width and height of the pane. Further, listeners are added to
     * the width and height properties of the pane. The listeners are used to update the width and height of the
     * rectangles.
     *
     * @param scene The scene that contains this pane.
     * @param width The width of the pane.
     * @since v1.0.0
     */
    public ArrayPane(SortingScene scene, double width) {
        this.scene = scene;
        setPrefSize(width, 400);

        setPadding(new Insets(padding));

        widthProperty().addListener((observable, oldValue, newValue) ->
                repaintRectangles()
        );

        heightProperty().addListener((observable, oldValue, newValue) ->
                repaintRectangles()
        );
    }


    /**
     * Returns the length of the array that is displayed.
     *
     * @return The length of the array.
     * @since v1.0.0
     */
    public int getArrayLength() {
        return array_length;
    }

    /**
     * Initializes the array and the rectangles. The rectangles are added to the pane. The rectangles are then
     * displayed.
     *
     * @param array_length The length of the array.
     * @since v1.0.0
     */
    public void initArrays(int array_length) {
        this.array_length = array_length;
        this.array = new int[array_length];
        this.rectangles = new Rectangle[array_length];

        initArray();
        initRectangles();
    }

    /**
     * Initializes the array. The array is filled with values corresponding to the index of the value plus one.
     *
     * @since v1.0.0
     */
    private void initArray() {
        for (int i = 0; i < array_length; i++) {
            array[i] = i + 1;
        }
    }

    /**
     * Shuffles the array. The array is shuffled by swapping two random elements of the array. Afterwards, the
     * rectangles are redrawn.
     *
     * @since v1.0.0
     */
    public void shuffleArray() {
        ArrayUtils.shuffleArray(array);
        repaintRectangles();
    }

    /**
     * Reverses the array. The array is reversed by swapping the first and last element, the second and second last
     * element and so on. Afterwards, the rectangles are redrawn.
     *
     * @since v1.0.0
     */
    public void reverseArray() {
        ArrayUtils.reverseArray(array);
        repaintRectangles();
    }

    /**
     * Initializes the rectangles. The rectangles are added to the pane. The rectangles are then displayed.
     *
     * @since v1.0.0
     */
    private void initRectangles() {
        getChildren().clear();

        for (int i = 0; i < array_length; i++) {
            rectangles[i] = new Rectangle();
            rectangles[i].setFill(DEFAULT_COLOR);
        }

        getChildren().addAll(rectangles);
        repaintRectangles();
    }

    /**
     * Repaints the rectangles. The rectangles are resized and moved to the correct position.
     *
     * @since v1.0.0
     */
    public void repaintRectangles() {
        for (int i = 0; i < array_length; i++) {
            double height = (getHeight() - padding * 2) / array_length * array[i];

            rectangles[i].setHeight(
                    height
            );

            rectangles[i].setWidth(
                    ((getWidth() - 2 * padding) - 2 * (array_length - 1)) / array_length
            );

            rectangles[i].setX(
                    getRectangleX(i, rectangles[i].getWidth())
            );

            rectangles[i].setY(
                    getHeight() - padding - height
            );

            rectangles[i].setTranslateX(
                    0
            );
        }
    }

    /**
     * Returns the x-coordinate of the rectangle at the specified index.
     *
     * @param index The index of the rectangle.
     * @param width The width of the rectangle.
     * @return The x-coordinate of the rectangle.
     * @since v1.0.0
     */
    private double getRectangleX(int index, double width) {
        return padding + index * (width + 2);
    }

    /**
     * Highlights the specified rectangle. The rectangle is highlighted by changing its color.
     *
     * @param indices   The indices of the rectangles that are highlighted.
     * @since v1.0.0
     */
    private void highlightRectangles(int... indices) {
        for (int index : indices) {
            rectangles[index].setFill(SELECTED_COLOR);
        }
    }

    /**
     * Highlight the specified rectangles. The rectangles are highlighted by changing its color.
     *
     * @param indices   The pair indices of the rectangles that are highlighted.
     * @since v1.0.0
     */
    private void highlightRectangles(Pair<Integer, Integer> indices) {
        highlightRectangles(indices.first, indices.second);
    }

    /**
     * Resets the color of all the rectangles. The color of the rectangles is reset to the default color.
     *
     * @since v1.0.0
     */
    private void resetRectangleColors() {
        for (Rectangle rectangle : rectangles) {
            rectangle.setFill(DEFAULT_COLOR);
        }
    }

    /**
     * Resets the color of the specified rectangles. The color of the rectangles is reset to the default color.
     *
     * @param indices  The indices of the rectangles that are reset.
     * @since v1.0.0
     */
    private void resetRectangleColors(int... indices) {
        for (int index : indices) {
            rectangles[index].setFill(DEFAULT_COLOR);
        }
    }

    /**
     * Resets the color of the specified rectangles. The color of the rectangles is reset to the default color.
     *
     * @param indices  The pair indices of the rectangles that are reset.
     * @since v1.0.0
     */
    private void resetRectangleColors(Pair<Integer, Integer> indices) {
        resetRectangleColors(indices.first, indices.second);
    }

    /**
     * Returns a transition that swaps the specified rectangles. The rectangles are swapped by moving them to the
     * correct position.
     *
     * @param index1    The index of the first rectangle.
     * @param index2    The index of the second rectangle.
     * @return The transition that swaps the rectangles.
     * @since v1.0.0
     */
    private ParallelTransition getSwappingTransition(int index1, int index2) {
        //double translation = rectangles[index2].getX() - rectangles[index1].getX();

        TranslateTransition tt1 = new TranslateTransition(Duration.millis(500), rectangles[index1]);
        //tt1.setByX(translation);
        tt1.byXProperty().bind(rectangles[index2].xProperty().subtract(rectangles[index1].xProperty()));

        TranslateTransition tt2 = new TranslateTransition(Duration.millis(500), rectangles[index2]);
        //tt2.setByX(-translation);
        tt2.byXProperty().bind(rectangles[index1].xProperty().subtract(rectangles[index2].xProperty()));

        ParallelTransition swappingTransition = new ParallelTransition(tt1, tt2);

        swappingTransition.setOnFinished(
                event -> {
                    rectangles[index1].setTranslateX(0);
                    rectangles[index2].setTranslateX(0);

                    int tmp = array[index1];
                    array[index1] = array[index2];
                    array[index2] = tmp;
                    repaintRectangles();
                }
        );

        return swappingTransition;
    }

    /**
     * Returns a transition that swaps the specified rectangles. The rectangles are swapped by moving them to the
     * correct position.
     *
     * @param indices   The pair indices of the rectangles that are swapped.
     * @return The transition that swaps the rectangles.
     * @since v1.0.0
     */
    private ParallelTransition getSwappingTransition(Pair<Integer, Integer> indices) {
        return getSwappingTransition(indices.first, indices.second);
    }

    /**
     * Returns a transition that highlights the specified rectangles. The rectangles are highlighted by applying a
     * FillTransition.
     *
     * @param index1    The index of the first rectangle.
     * @param index2    The index of the second rectangle.
     * @return The transition that highlights the rectangles.
     * @since v1.0.0
     */
    private ParallelTransition getComparisonTransition(int index1, int index2) {
        FillTransition ft1 = new FillTransition(Duration.millis(500), rectangles[index1], DEFAULT_COLOR, SELECTED_COLOR);
        FillTransition ft2 = new FillTransition(Duration.millis(500), rectangles[index2], DEFAULT_COLOR, SELECTED_COLOR);

        ParallelTransition comparisonTransition = new ParallelTransition(ft1, ft2);

        comparisonTransition.setOnFinished(
                event -> {
                    resetRectangleColors(index1, index2);
                }
        );

        return comparisonTransition;
    }

    /**
     * Returns a transition that highlights the specified rectangles. The rectangles are highlighted by applying a
     * FillTransition.
     *
     * @param indices   The pair indices of the rectangles that are highlighted.
     * @return The transition that highlights the rectangles.
     * @since v1.0.0
     */
    private ParallelTransition getComparisonTransition(Pair<Integer, Integer> indices) {
        return getComparisonTransition(indices.first, indices.second);
    }

    /**
     * Plays a swapping transition that swaps the specified rectangles. The rectangles are swapped by moving them to the
     * correct position.
     *
     * @param index1    The index of the first rectangle.
     * @param index2    The index of the second rectangle.
     * @since v1.0.0
     */
    private void swapRectangles(int index1, int index2) {
        getSwappingTransition(index1, index2).play();
    }

    /**
     * Plays a swapping transition that swaps the specified rectangles. The rectangles are swapped by moving them to the
     * correct position.
     *
     * @param indices   The pair indices of the rectangles that are swapped.
     * @since v1.0.0
     */
    private void swapRectangles(Pair<Integer, Integer> indices) {
        swapRectangles(indices.first, indices.second);
    }

    /**
     * TODO: Fix this method.
     */
    public void flipRectangles() {
        for (int i = 0; i < array_length; i++) {
            if (flipped) {
                rectangles[i].setY(
                        getHeight() - padding - rectangles[i].getHeight()
                );
            } else {
                rectangles[i].setY(
                        padding
                );
            }
        }
        flipped = !flipped;
    }

    /**
     * Returns the algorithm that is currently being used.
     *
     * @return  The algorithm that is currently being used.
     * @since v1.0.0
     */
    public SortingAlgorithm getAlgorithm() {
        return algorithm;
    }

    /**
     * Sets the algorithm that is used to sort the array.
     *
     * @param algorithmName The name of the algorithm.
     * @since v1.0.0
     */
    public void setAlgorithm(String algorithmName) {
        this.algorithm = switch (algorithmName) {
            case "Bubble Sort" -> new BubbleSort(array);
            default -> null;
            //TODO: Add more cases
        };
    }

    /**
     * Updates the algorithm with the current array.
     *
     * @since v1.0.0
     */
    private void updateAlgorithm() {
        algorithm.setArray(array);
    }

    /**
     * Starts the display of the sorting algorithm. The sorting algorithm is applied to the current state of the array.
     * Each swapping and comparison is displayed by a transition. All transitions are played in sequence.
     *
     * @since v1.0.0
     */
    public void startAlgorithm() {
        updateAlgorithm();

        StateMachine machine = algorithm.getStateMachine();

        Iterator<State> iterator = machine.iterator();

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
            }
        }

        Animation[] animations = transitions.toArray(new Animation[0]);

        sequentialTransition = new SequentialTransition(animations);

        sequentialTransition.setOnFinished(
                event -> {
                    scene.resetAlgo();
                }
        );

        sequentialTransition.setRate(1);

        sequentialTransition.play();
    }

    /**
     * Stops the current algorithm and resets the colors of the rectangles. Further, if rectangles are not at the correct
     * position, they are moved to the correct position.
     *
     * @since v1.0.0
     */
    public void stopAlgorithm() {
        if (sequentialTransition != null) {
            sequentialTransition.stop();
            resetRectangleColors();
            repaintRectangles();
        }
    }

    /**
     * Pauses the algorithm.
     *
     * @since v1.0.0
     */
    public void pauseAlgorithm() {
        if (sequentialTransition != null) {
            sequentialTransition.pause();
        }
    }

    /**
     * Resumes the algorithm.
     *
     * @since v1.0.0
     */
    public void resumeAlgorithm() {
        if (sequentialTransition != null) {
            sequentialTransition.play();
        }
    }

}
