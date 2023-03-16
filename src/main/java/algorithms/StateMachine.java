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
package algorithms;

import java.util.Iterator;

/**
 * This class works as a wrapper for each state of an algorithm. Each state of the algorithm contains information about
 * the current step that was executed by the algorithm. For example, in sorting a step would be to swap two elements.
 * Additional information about the algorithm is stored in a separate AlgoSummary object. Furthermore, this class
 * implements the Iterable interface to allow for iteration over the steps of the algorithm using an iterator.
 *
 * @author Tim-Michael Krieg
 * @version {@value Launcher#VERSION}
 * @since v0.1.0
 */
public class StateMachine implements Iterable<State> {

    /*
    TODO:
        - Progress for each state
     */

    /**
     * The first and initial state of the algorithm.
     *
     * @since v0.1.0
     */
    public State first;

    /**
     * The last state of the algorithm.
     *
     * @since v0.1.0
     */
    public State last;

    /**
     * The total number of states the algorithm has.
     *
     * @since v0.1.0
     */
    public int numberOfStates = 0;

    /**
     * Appends a state to the list of states. If no state has been added yet, the state is set as the first state. Else,
     * the state is appended to the list of states.
     *
     * @param state  The state to be appended.
     * @since v0.1.0
     */
    public void append(State state) {
        if (first == null) {
            first = last = state;
            numberOfStates++;
            return;
        }
        state.previous = last;
        last.next = state;
        last = last.next;
        numberOfStates++;
    }

    /**
     * Prints each state of this state machine to the console by calling the overwritten toString method.
     *
     * @since v0.1.0
     */
    public void print() {
        for (State state = first; state != null; state = state.next) {
            System.out.println(state);
        }
    }

    /**
     * Returns an iterator over elements of type {@code State}.
     *
     * @return an Iterator.
     * @since v0.1.0
     */
    @Override
    public Iterator<State> iterator() {
        return new Iterator<>() {
            State current = null;

            @Override
            public boolean hasNext() {
                return current == null ? first != null : current.next != null;
            }

            @Override
            public State next() {
                if (current == null) {
                    current = first;
                    return current;
                }
                current = current.next;
                return current;
            }
        };
    }

    /**
     * Clears the state machine by setting the first and last state to null and the number of states to 0.
     *
     * @since v0.1.0
     */
    public void clear() {
        first = last = null;
        numberOfStates = 0;
    }
}
