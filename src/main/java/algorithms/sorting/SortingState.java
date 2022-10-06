package algorithms.sorting;

import algorithms.State;
import datastructure.Pair;

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
     * The elements that are either compared or swapped in this state, depending on the action that takes place.
     */
    private final Pair<Integer, Integer> elements;

    /**
     * The action that takes place in this state. See the SortingAction enumeration for more details.
     */
    private final SortingAction action;

    /**
     * Constructor initializing a state of a sorting algorithm.
     *
     * @param id    The id (typically the number) of the state.
     * @param time  The time elapsed from start of algorithm until this state.
     * @param iteration The iteration of the algorithm in this state.
     * @param array The current state of the array that is sorted by the algorithm.
     * @param index The index of the element inside the array that is currently highlighted by the algorithm.
     * @param element   The element inside the array that is currently highlighted by the algorithm.
     * @param elements  The elements that are either compared or swapped in this state, depending on the action that
     *                  takes place.
     * @param action    The action that takes place in this state. See the SortingAction enumeration for more details.
     */
    public SortingState(int id, double time, int iteration, int[] array, int index, int element, Pair<Integer, Integer> elements, SortingAction action) {
        super(id, time, iteration);
        this.array = array;
        this.index = index;
        this.element = element;
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
}
