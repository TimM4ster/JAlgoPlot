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
     * The state of the array before the action was performed by the sorting algorithm.
     */
    private final int[] before;

    /**
     * The state of the array after the action was performed by the sorting algorithm.
     */
    private final int[] after;

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
     * Constructor initializing a state of a sorting algorithm. Use methods for initialization.
     *
     * @param id    The id (typically the number) of the state.
     * @param time  The time elapsed from start of algorithm until this state.
     * @param iteration The iteration of the algorithm in this state.
     * @param before    The state of the array before the action by the sorting algorithm was performed.
     * @param after The state of the array after the action by the sorting algorithm was performed.
     * @param index The index of the element inside the array that is currently highlighted by the algorithm.
     * @param element   The element inside the array that is currently highlighted by the algorithm.
     * @param indices   The indices of the elements that are either compared or swapped in this state.
     * @param elements  The elements of the elements that are either compared or swapped in this state.
     * @param action    The action that takes place in this state. See the SortingAction enumeration for more details.
     */
    private SortingState(int id, double time, int iteration, int[] before, int[] after, int index, int element, Pair<Integer, Integer> indices, Pair<Integer, Integer> elements, SortingAction action) {
        super(id, time, iteration);
        this.before = before;
        this.after = after;
        this.index = index;
        this.element = element;
        this.indices = indices;
        this.elements = elements;
        this.action = action;
    }

    /**
     * Constructs a state of a sorting algorithm.
     *
     * @param id    The id (typically the number) of the state.
     * @param time  The time elapsed from start of algorithm until this state.
     * @param iteration The iteration of the algorithm in this state.
     * @param before    The state of the array before the action by the sorting algorithm was performed.
     * @param after The state of the array after the action by the sorting algorithm was performed.
     * @param index The index of the element inside the array that is currently highlighted by the algorithm.
     * @param element   The element inside the array that is currently highlighted by the algorithm.
     * @param indices   The indices of the elements that are either compared or swapped in this state.
     * @param elements  The elements of the elements that are either compared or swapped in this state.
     * @param action    The action that takes place in this state. See the SortingAction enumeration for more details.
     */
    public static SortingState state(int id, double time, int iteration, int[] before, int[] after, int index, int element, Pair<Integer, Integer> indices, Pair<Integer, Integer> elements, SortingAction action) {
        return new SortingState(id, time, iteration, before, after, index, element, indices, elements, action);
    }

    /**
     * Constructs a highlight state of a sorting algorithm.
     *
     * @param id    The id (typically the number) of the state.
     * @param time  The time elapsed from start of algorithm until this state.
     * @param iteration The iteration of the algorithm in this state.
     * @param before    The state of the array.
     * @param index The index of the element inside the array that is currently highlighted by the algorithm.
     * @param element   The element inside the array that is currently highlighted by the algorithm.
     * @return  The highlight state.
     */
    public static SortingState highlight(int id, double time, int iteration, int[] before, int index, int element) {
        return new SortingState(id, time, iteration, before, null, index, element, null, null, SortingAction.HIGHLIGHT);
    }

    /**
     * Constructs a compare state of a sorting algorithm.
     *
     * @param id    The id (typically the number) of the state.
     * @param time  The time elapsed from start of algorithm until this state.
     * @param iteration The iteration of the algorithm in this state.
     * @param before    The state of the array.
     * @param indices   The indices of the elements that are compared in this state.
     * @param elements  The elements of the elements that are compared in this state.
     * @return  The compare state.
     */
    public static SortingState comparison(int id, double time, int iteration, int[] before, Pair<Integer, Integer> indices, Pair<Integer, Integer> elements) {
        return new SortingState(id, time, iteration, before, null, Integer.MIN_VALUE, Integer.MIN_VALUE, indices, elements, SortingAction.COMPARE);
    }

    /**
     * Constructs a state of a sorting algorithm.
     *
     * @param id    The id (typically the number) of the state.
     * @param time  The time elapsed from start of algorithm until this state.
     * @param iteration The iteration of the algorithm in this state.
     * @param before    The state of the array before the swap.
     * @param after The state of the array after the swap.
     * @param indices   The indices of the elements that are swapped in this state.
     * @param elements  The elements of the elements that are swapped in this state.
     */
    public static SortingState swap(int id, double time, int iteration, int[] before, int[] after, Pair<Integer, Integer> indices, Pair<Integer, Integer> elements) {
        return new SortingState(id, time, iteration, before, after, Integer.MIN_VALUE, Integer.MIN_VALUE, indices, elements, SortingAction.SWAP);
    }

    /**
     * Returns the state of the array before the action by the algorithm was performed.
     *
     * @return  The array.
     */
    public int[] getArrayBefore() {
        return before;
    }

    /**
     * Returns the state of the array after the action by the algorithm was performed.
     *
     * @return  The array.
     */
    public int[] getArrayAfter() {
        return after;
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

        builder.append(String.format("ID: %d, Iteration: %d, ", id, iteration));

        switch (action) {
            case HIGHLIGHT -> builder.append(
                    "Performed action: Highlight"
            ).append(
                    String.format(", Highlighted index: %d", index)
            ).append(
                    String.format(", Highlighted element: %d", element)
            ).append(
                    String.format(", Array: %s", Arrays.toString(before))
            );
            case COMPARE -> {
                assert indices != null;
                assert elements != null;
                builder.append(
                        "Performed action: Compare"
                ).append(
                        String.format(", Compared indices: %d and %d", indices.first, indices.second)
                ).append(
                        String.format(", Compared elements: %d and %d, ", elements.first, elements.second)
                ).append(
                        String.format(", Array: %s", Arrays.toString(before))
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
                        String.format(", Swapped elements: %d and %d", elements.first, elements.second)
                ).append(
                        String.format(", Array before swap: %s, Array after swap: %s", Arrays.toString(before), Arrays.toString(after))
                );
            }
        }

        return builder.toString();
    }
}
