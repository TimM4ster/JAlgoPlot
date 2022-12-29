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

import algorithms.summary.AlgoSummary;

/**
 * An object of this class represents a summary of a sorting algorithm. It contains additional information to the
 * AlgoSummary class, such as total comparison and total number of swaps. It is used to display the information in the
 * sorting scene. Will be extended and used in the future.
 *
 * @author Tim-Michael Krieg
 * @version 1.0.0
 * @since v1.0.0
 */
public class SortingSummary extends AlgoSummary {

    /**
     * The length of the array that was sorted.
     *
     * @since v1.0.0
     */
    private final int arrayLength;

    /**
     * The array before it was sorted.
     *
     * @since v1.0.0
     */
    private final int[] before;

    /**
     * The total number of comparisons performed by the sorting algorithm.
     *
     * @since v1.0.0
     */
    private final int totalComparisons;

    /**
     * The total number of swaps performed by the sorting algorithm.
     *
     * @since v1.0.0
     */
    private final int totalSwaps;

    /**
     * Constructor extracting the name and the type of the algorithm, the total time passed until completion of the
     * algorithm and the number of iterations the algorithm had to go through from the given sorting algorithm object.
     *
     * @param algorithm The sorting algorithm.
     * @since v1.0.0
     */
    public SortingSummary(SortingAlgorithm algorithm) {
        super(algorithm);
        arrayLength = algorithm.array.length;
        before = algorithm.before;
        totalComparisons = algorithm.getTotalComparisons();
        totalSwaps = algorithm.getTotalSwaps();
    }

    /**
     * Returns the length of the array that was sorted.
     *
     * @return  The length of the array.
     * @since v1.0.0
     */
    public int getArrayLength() {
        return arrayLength;
    }

    /**
     * Returns the total number of comparisons performed by the sorting algorithm.
     *
     * @return  The total number of comparisons.
     * @since v1.0.0
     */
    public int getTotalComparisons() {
        return totalComparisons;
    }

    /**
     * Returns the total number of swaps performed by the sorting algorithm.
     *
     * @return  The total number of swaps.
     * @since v1.0.0
     */
    public int getTotalSwaps() {
        return totalSwaps;
    }

    /**
     * Creates and returns a string-representation of this Summary.
     *
     * @return The string-representation.
     * @since v1.0.0
     */
    @Override
    public String toString() {
        return String.format(
                "Type of algorithm: %s, Name of algorithm: %s, Length of array: %d, Level of sorting before: %f, Total time elapsed: %f, Total number of iterations: %d, Total number of comparisons: %d, Total number of swaps: %d",
                type,
                name,
                arrayLength,
                SortingAlgorithm.getUnsortednessOfArray(before),
                totalTime,
                totalIterations,
                totalComparisons,
                totalSwaps
        );
    }
}
