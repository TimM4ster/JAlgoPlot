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

import algorithms.Algorithm;

import java.util.Arrays;

/**
 * Extends the abstract algorithm class and adds additional attributes and methods needed by sorting algorithms. Each
 * sorting algorithm should implement this class for key functionalities.
 *
 * @author Tim-Michael Krieg
 * @version 1.0.0
 * @since v1.0.0
 */
public abstract class SortingAlgorithm extends Algorithm {

    /**
     * The array that is to be sorted by the sorting algorithm.
     *
     * @since v1.0.0
     */
    protected int[] array;

    /**
     * The array in the state before sorting.
     *
     * @since v1.0.0
     */
    protected int[] before;

    /**
     * The total number of comparisons performed by the sorting algorithm.
     *
     * @since v1.0.0
     */
    protected int totalComparisons = 0;

    /**
     * The total number of swaps performed by the sorting algorithm.
     *
     * @since v1.0.0
     */
    protected int totalSwaps = 0;

    /**
     * Constructor initializing the array that is to be sorted by the algorithm.
     *
     * @param array The array to be sorted.
     * @since v1.0.0
     */
    protected SortingAlgorithm(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
        before = getArrayCopy();
    }

    /**
     * Returns the array that is to be sorted by the sorting algorithm.
     *
     * @return  The array.
     * @since v1.0.0
     */
    public int[] getArray() {
        return array;
    }

    /**
     * Sets the array that is to be sorted by the sorting algorithm.
     *
     * @param array The array to be sorted.
     * @since v1.0.0
     */
    public void setArray(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
        this.before = getArrayCopy();
        reset();
    }

    /**
     * Returns the total number of comparisons performed by the sorting algorithm. Only call after method run.
     *
     * @return  The total number of comparisons.
     * @since v1.0.0
     */
    public int getTotalComparisons() {
        return totalComparisons;
    }

    /**
     * Returns the total number of swaps performed by the sorting algorithm. Only call after method run.
     *
     * @return  The total number of swaps.
     * @since v1.0.0
     */
    public int getTotalSwaps() {
        return totalSwaps;
    }

    /**
     * Returns a sorting summary of the sorting algorithm.
     *
     * @return  The sorting summary.
     * @since v1.0.0
     */
    public SortingSummary getSummary() {
        return new SortingSummary(this);
    }

    /**
     * Returns a copy of the current state of the array.
     *
     * @return  The copy.
     * @since v1.0.0
     */
    public int[] getArrayCopy() {
        int[] copy = new int[this.array.length];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }

    /**
     * Returns a string representation of the array.
     *
     * @return  The string representation of the array.
     * @since v1.0.0
     */
    public String arrayToString() {
        return Arrays.toString(array);
    }

    /**
     * Prints the contained array on the console.
     *
     * @since v1.0.0
     */
    public void printArray() {
        System.out.println(arrayToString());
    }

    /**
     * Computes the level of unsortedness of an array. Since all arrays are constructed in a simple manner, a check for
     * equality of index and element is enough. However, this method will be replaced by a more sophisticated method in
     * the future.
     *
     * @param array The array.
     * @return  The unsortedness.
     * @since v1.0.0
     */
    public static double getUnsortednessOfArray(int[] array) {
        int n = array.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (array[i] == i + 1) {
                sum++;
            }
        }
        return (double) sum / n;
    }

    /**
     * Runs the algorithm. Running the algorithm sorts the array from the smallest to the biggest value. This method is
     * simply to check whether the implementation of the algorithm works.
     *
     * @since v1.0.0
     */
    public abstract void sort();

    /**
     * Resets the algorithm. The reset is done by resetting the attributes of the algorithm. Further, the state machine
     * is cleared.
     *
     * @since v1.0.0
     */
    private void reset() {
        totalComparisons = 0;
        totalSwaps = 0;
        totalIterations = 0;
        totalTime = 0;
        stateMachine.clear();
    }

}
