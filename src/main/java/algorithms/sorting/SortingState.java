/**
 *  JAlgoPlot - Made to make understanding algorithms easier.
 *     Copyright (C) 2022-2023  Tim-Michael Krieg
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package algorithms.sorting;

import algorithms.State;
import datastructure.Pair;

import java.util.Arrays;

/**
 * An object of this class extends the State class such that it represents a state of a sorting algorithm. In addition
 * to the attributes provided by the superclass, it also includes the array that is currently sorted and other
 * information about the sorting process. That includes the sorting action that is currently taking place.
 *
 * @author Tim-Michael Krieg
 * @version 1.0.0
 * @since v1.0.0
 */
public class SortingState extends State {

    /**
     * The state of the array before the action was performed by the sorting algorithm.
     *
     * @since v1.0.0
     */
    private final int[] before;

    /**
     * The state of the array after the action was performed by the sorting algorithm.
     *
     * @since v1.0.0
     */
    private final int[] after;

    /**
     * The index of the element inside the array that is currently highlighted by the algorithm.
     *
     * @since v1.0.0
     */
    private final int index;

    /**
     * The element inside the array that is currently highlighted by the algorithm.
     *
     * @since v1.0.0
     */
    private final int element;

    /**
     * The indices of the elements that are either compared or swapped in this state, depending on the action that takes
     * place.
     *
     * @since v1.0.0
     */
    private final Pair<Integer, Integer> indices;

    /**
     * The elements that are either compared or swapped in this state, depending on the action that takes place.
     *
     * @since v1.0.0
     */
    private final Pair<Integer, Integer> elements;

    /**
     * The action that takes place in this state. See the SortingAction enumeration for more details.
     *
     * @since v1.0.0
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
     * @since v1.0.0
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
     * @return  A new state of a sorting algorithm.
     * @since v1.0.0
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
     * @since v1.0.0
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
     * @since v1.0.0
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
     * @return  The swap state.
     * @since v1.0.0
     */
    public static SortingState swap(int id, double time, int iteration, int[] before, int[] after, Pair<Integer, Integer> indices, Pair<Integer, Integer> elements) {
        return new SortingState(id, time, iteration, before, after, Integer.MIN_VALUE, Integer.MIN_VALUE, indices, elements, SortingAction.SWAP);
    }

    /**
     * Returns the state of the array before the action by the algorithm was performed.
     *
     * @return  The array.
     * @since v1.0.0
     */
    public int[] getArrayBefore() {
        return before;
    }

    /**
     * Returns the state of the array after the action by the algorithm was performed.
     *
     * @return  The array.
     * @since v1.0.0
     */
    public int[] getArrayAfter() {
        return after;
    }

    /**
     * Returns the index of the element inside the array that is currently highlighted by the sorting algorithm.
     *
     * @return  The index.
     * @since v1.0.0
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the element inside the array that is currently highlighted by the sorting algorithm.
     *
     * @return  The element.
     * @since v1.0.0
     */
    public int getElement() {
        return element;
    }

    /**
     * Returns the indices of the elements that are either compared or swapped in this state, depending on the action
     * that takes place.
     *
     * @return  The indices.
     * @since v1.0.0
     */
    public Pair<Integer, Integer> getIndices() {
        return indices;
    }

    /**
     * Returns the elements that are either compared or swapped in this state, depending on the action that takes place.
     *
     * @return  The pair of elements.
     * @since v1.0.0
     */
    public Pair<Integer, Integer> getElements() {
        return elements;
    }

    /**
     * Returns the action that takes place in this state. See the SortingState enumeration for more details.
     *
     * @return  The action.
     * @since v1.0.0
     */
    public SortingAction getAction() {
        return action;
    }

    /**
     * Creates and returns a string-representation of this state.
     *
     * @return The string-representation.
     * @since v1.0.0
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
