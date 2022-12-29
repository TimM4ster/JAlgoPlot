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
package algorithms.summary;

import algorithms.Algorithm;

/**
 * An object of this class represents a summary of an algorithm. It contains information about the time the algorithm
 * took until completion, the name and type of the algorithm and further information can be added through extending
 * classes. The summary is used to display information about the algorithm in the GUI. It will be included in future
 * versions of JAlgoPlot.
 *
 * @author Tim-Michael Krieg
 * @version 1.0.0
 * @since v1.0.0
 */
public abstract class AlgoSummary {

    /**
     * The name of the algorithm.
     *
     * @since v1.0.0
     */
    protected final String name;

    /**
     * The type of the algorithm (example: sorting).
     *
     * @since v1.0.0
     */
    protected final String type;

    /**
     * The total time elapsed during the execution of the algorithm.
     *
     * @since v1.0.0
     */
    protected final double totalTime;

    /**
     * The total number of iterations the algorithm took for completion.
     *
     * @since v1.0.0
     */
    protected final int totalIterations;

    /**
     * Constructor extracting the name and the type of the algorithm, the total time passed until completion of the
     * algorithm and the number of iterations the algorithm had to go through from the given algorithm object.
     *
     * @param algorithm The algorithm to extract the information from.
     * @since v1.0.0
     */
    public AlgoSummary(Algorithm algorithm) {
        this.name = algorithm.getName();
        this.type = algorithm.getType();
        this.totalTime = algorithm.getTotalTime();
        this.totalIterations = algorithm.getTotalIterations();
    }

    /**
     * Returns the name of the algorithm.
     *
     * @return  The name.
     * @since v1.0.0
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the algorithm.
     *
     * @return  The type.
     * @since v1.0.0
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the total time elapsed since the start of the algorithm.
     *
     * @return  The total time elapsed.
     * @since v1.0.0
     */
    public double getTotalTime() {
        return totalTime;
    }

    /**
     * Returns the total amount of iterations the algorithm went through.
     *
     * @return  The total amount of iterations.
     * @since v1.0.0
     */
    public int getTotalIterations() {
        return totalIterations;
    }

    /**
     * Prints this summary to the console.
     *
     * @since v1.0.0
     */
    public void print() {
        System.out.println(toString());
    }

    /**
     * Creates and returns a string-representation of this Summary.
     *
     * @return  The string-representation.
     * @since v1.0.0
     */
    public abstract String toString();
}
