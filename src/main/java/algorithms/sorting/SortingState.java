package algorithms.sorting;

import algorithms.State;
import datastructure.Pair;

import java.util.Arrays;

/**
 * An object of this class extends the State class such that it represents a state of a sorting algorithm. In addition
 * to the attributes provided by the superclass, it also includes the array that is currently sorted and other
 * information about the sorting process. That includes the sorting action that is currently taking place.
 */
public class SortingState extends State {

    /**
     * The current state of the array that is sorted by the algorithm.
     */
    private final int[] array;

    /**
     * The index of the element inside the array that is currently highlighted by the algorithm.
     */
    private final int index;

    /**
     * The element inside the array that is currently highlighted by the algorithm.
     */
    private final int element;

    /**
     * The indices of the elements that are either compared or swapped in this state, depending on the action that takes
     * place.
     */
    private final Pair<Integer, Integer> indices;

    /**
     * The elements that are either compared or swapped in this state, depending on the action that takes place.
     */
    private final Pair<Integer, Integer> elements;

    /**
     * The action that takes place in this state. See the SortingAction enumeration for more details.
     */
    private final SortingAction action;

    /**
     * Constructor initializing a state of a sorting algorithm. Sets indices and elements to null.
     *
     * @param id    The id (typically the number) of the state.
     * @param time  The time elapsed from start of algorithm until this state.
     * @param iteration The iteration of the algorithm in this state.
     * @param array The current state of the array that is sorted by the algorithm.
     * @param index The index of the element inside the array that is currently highlighted by the algorithm.
     * @param element   The element inside the array that is currently highlighted by the algorithm.
     * @param action    The action that takes place in this state. See the SortingAction enumeration for more details.
     */
    public SortingState(int id, double time, int iteration, int[] array, int index, int element, SortingAction action) {
        super(id, time, iteration);
        this.array = array;
        this.index = index;
        this.element = element;
        this.indices = null;
        this.elements = null;
        this.action = action;
    }

    /**
     * Constructor initializing a state of a sorting algorithm. Sets index and element to Integer.MIN_VALUE.
     *
     * @param id    The id (typically the number) of the state.
     * @param time  The time elapsed from start of algorithm until this state.
     * @param iteration The iteration of the algorithm in this state.
     * @param array The current state of the array that is sorted by the algorithm.
     * @param indices   The indices of the elements that are either compared or swapped in this state, depending on the
     *                  action that takes place.
     * @param elements  The elements that are either compared or swapped in this state, depending on the action that
     *                  takes place.
     * @param action    The action that takes place in this state. See the SortingAction enumeration for more details.
     */
    public SortingState(int id, double time, int iteration, int[] array, Pair<Integer, Integer> indices, Pair<Integer, Integer> elements, SortingAction action) {
        super(id, time, iteration);
        this.array = array;
        this.index = Integer.MIN_VALUE;
        this.element = Integer.MIN_VALUE;
        this.indices = indices;
        this.elements = elements;
        this.action = action;
    }

    /**
     * Returns the array in the current state of sorting.
     *
     * @return  The array.
     */
    public int[] getArray() {
        return array;
    }

    /**
     * Returns the index of the element inside the array that is currently highlighted by the sorting algorithm.
     *
     * @return  The index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the element inside the array that is currently highlighted by the sorting algorithm.
     *
     * @return  The element.
     */
    public int getElement() {
        return element;
    }

    /**
     * Returns the indices of the elements that are either compared or swapped in this state, depending on the action
     * that takes place.
     *
     * @return  The indices.
     */
    public Pair<Integer, Integer> getIndices() {
        return indices;
    }

    /**
     * Returns the elements that are either compared or swapped in this state, depending on the action that takes place.
     *
     * @return  The pair of elements.
     */
    public Pair<Integer, Integer> getElements() {
        return elements;
    }

    /**
     * Returns the action that takes place in this state. See the SortingState enumeration for more details.
     *
     * @return  The action.
     */
    public SortingAction getAction() {
        return action;
    }

    /**
     * Creates and returns a string-representation of this state.
     *
     * @return The string-representation.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("ID: %d, ", id));

        switch (action) {
            case HIGHLIGHT -> {
                builder.append(
                        "Performed action: Highlight"
                ).append(
                        String.format(", Highlighted index: %d", index)
                ).append(
                        String.format(", Highlighted element: %d, " + element)
                );
            }
            case COMPARE -> {
                assert indices != null;
                assert elements != null;
                builder.append(
                        "Performed action: Compare"
                ).append(
                        String.format(", Compared indices: %d and %d", indices.first, indices.second)
                ).append(
                        String.format(", Compared elements: %d and %d, ", elements.first, elements.second)
                );
            }
            case SWAP -> {
                assert indices != null;
                assert elements != null;
                builder.append(
                        "Performed action: Swap"
                ).append(
                        String.format(", Swapped indices: %d and %d", indices.first, indices.second)
                ).append(
                        String.format(", Swapped elements: %d and %d, ", elements.first, elements.second)
                );
            }
        }

        builder.append(
                String.format(
                        "Iteration: %d, Array: %s",
                        iteration,
                        Arrays.toString(array)
                )
        );

        return builder.toString();
    }
}
