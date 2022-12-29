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

/**
 * Each object of this class represents a specific state of an algorithm. Each step of an algorithm is documented using
 * an object of this class. Information about the current step is stored and can be accessed in order to retrieve
 * information about the execution of the algorithm. Further information is to be defined in subclasses.
 *
 * @author Tim-Michael Krieg
 * @version 1.0.0
 * @since v1.0.0
 */
public abstract class State {

    /**
     * The state following this state.
     *
     * @since v1.0.0
     */
    public State next;

    /**
     * The state prior to this state.
     *
     * @since v1.0.0
     */
    public State previous;

    /**
     * The id (typically the number) of this state.
     *
     * @since v1.0.0
     */
    protected final int id;

    /**
     * Time elapsed from start of algorithm until this state.
     *
     * @since v1.0.0
     */
    protected final double time;

    /**
     * The iteration of the algorithm in this state.
     *
     * @since v1.0.0
     */
    protected final int iteration;

    /**
     * Constructor initializing the id (typically the number) of this state, the time elapsed since the algorithm
     * started and the current iteration of the algorithm.
     *
     * @param id    The id (typically the number) of this state.
     * @param time  The time elapsed.
     * @param iteration The iteration.
     * @since v1.0.0
     */
    protected State(int id, double time, int iteration) {
        this.id = id;
        this.time = time;
        this.iteration = iteration;
    }

    /**
     * Returns the id (typically the number) of this state.
     *
     * @return  The id.
     * @since v1.0.0
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the total time elapsed from the start of the algorithm until this state.
     *
     * @return  The time elapsed.
     * @since v1.0.0
     */
    public double getTime() {
        return time;
    }

    /**
     * Returns the current iteration of the algorithm in this state.
     *
     * @return  The iteration.
     * @since v1.0.0
     */
    public int getIteration() {
        return iteration;
    }

    /**
     * Creates and returns a string-representation of this state.
     *
     * @return  The string-representation.
     * @since v1.0.0
     */
    public abstract String toString();
}
