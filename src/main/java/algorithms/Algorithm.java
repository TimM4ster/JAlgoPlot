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

import algorithms.summary.AlgoSummary;

/**
 * An object of this class represents an algorithm. Each algorithm includes a method to run the actual algorithm on the
 * data structure and further, it includes a method to create the associated state machine that can be used to plot the
 * algorithm.
 *
 * @author Tim-Michael Krieg
 * @version {@value Launcher#VERSION}
 * @since v0.1.0
 */
public abstract class Algorithm {

    /**
     * The state machine of this algorithm. Can be used to plot the algorithm.
     *
     * @since v0.1.0
     */
    protected final StateMachine stateMachine = new StateMachine();

    /**
     * The total time elapsed from the start of the algorithm until completion.
     *
     * @since v0.1.0
     */
    protected double totalTime;

    /**
     * The total number of iterations in the algorithm.
     *
     * @since v0.1.0
     */
    protected int totalIterations = 0;

    /**
     * Returns the name of this algorithm.
     *
     * @return  The name.
     * @since v0.1.0
     */
    public abstract String getName();

    /**
     * Returns the type of this algorithm (example: sorting). The type is stored as a string.
     *
     * @return  The type.
     * @since v0.1.0
     */
    public abstract String getType();

    /**
     * Returns the associated summary object of this algorithm. Will be used in future versions to display a summary of
     * the algorithm.
     *
     * @return  The summary of this algorithm.
     * @since v0.1.0
     */
    public abstract AlgoSummary getSummary();

    /**
     * Returns the state machine of the algorithm. Should only be used if the run method was called.
     *
     * @return  The state machine.
     * @since v0.1.0
     */
    public StateMachine getStateMachine() {
        run();
        return stateMachine;
    }

    /**
     * Returns the total time elapsed from the beginning of the algorithm until completion. Only call after method run.
     *
     * @return  The total time elapsed.
     * @since v0.1.0
     */
    public double getTotalTime() {
        return totalTime;
    }

    /**
     * Returns the total number of iterations the algorithm needed until completion. Only call after method run.
     *
     * @return  The total number of iterations.
     * @since v0.1.0
     */
    public int getTotalIterations() {
        return totalIterations;
    }

    /**
     * Runs the algorithm and builds the state machine.
     *
     * @since v0.1.0
     */
    protected abstract void run();
}
