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
package algorithms.sorting.bubblesort;

import algorithms.sorting.SortingAlgorithm;
import algorithms.sorting.SortingState;
import datastructure.Pair;

/**
 * An object of this class represents the Bubble sort algorithm executed on an int array.
 *
 * @author Tim-Michael Krieg
 * @version 1.0.0
 * @since v1.0.0
 */
public class BubbleSort extends SortingAlgorithm {

    /**
     * Constructor initializing the array that is to be sorted by the Bubble sort algorithm.
     *
     * @param array The array to be sorted.
     * @since v1.0.0
     * @author Tim-Michael Krieg
     */
    public BubbleSort(int[] array) {
        super(array);
    }

    /**
     * Returns the name of the Bubble sort algorithm.
     *
     * @return The name.
     * @since v1.0.0
     */
    @Override
    public String getName() {
        return "Bubble Sort";
    }

    /**
     * Returns the type of this algorithm.
     *
     * @return The type.
     * @since v1.0.0
     */
    @Override
    public String getType() {
        return "Sorting";
    }

    /**
     * Runs the algorithm and builds the state machine.
     *
     * @since v1.0.0
     */
    @Override
    protected void run() {
        long startTime = System.nanoTime();
        int n = array.length;
        int id = 1;
        int iteration = 1;
        while(n > 1) {
            for (int i = 0; i < n - 1; i++) {
                stateMachine.append(
                        SortingState.comparison(
                                id++,
                                (double) (System.nanoTime() - startTime) / 1_000_000_000,
                                iteration++,
                                getArrayCopy(),
                                new Pair<>(i, i + 1),
                                new Pair<>(array[i], array[i + 1])
                        )
                );
                if (array[i] > array[i + 1]) {
                    int[] before = getArrayCopy();
                    int tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;
                    stateMachine.append(
                            SortingState.swap(
                                    id++,
                                    (double) (System.nanoTime() - startTime) / 1_000_000_000,
                                    iteration,
                                    before,
                                    getArrayCopy(),
                                    new Pair<>(i, i + 1),
                                    new Pair<>(array[i + 1], array[i])
                            )
                    );
                    totalSwaps++;
                }
                totalComparisons++;
            }
            n--;
        }
        totalTime = (double) (System.nanoTime() - startTime) / 1_000_000_000;
        totalIterations = iteration;
    }

    /**
     * Runs the algorithm. Running the algorithm sorts the array from the smallest to the biggest value. This method is
     * simply to check whether the implementation of the algorithm works.
     *
     * @since v1.0.0
     */
    @Override
    public void sort() {
        // Get number of elements in array.
        int n = array.length;

        // Iterate through all items.
        while(n > 1) {

            // Iterate through array until index n - 1.
            for (int i = 0; i < n - 1; i++) {

                // Compare the i-th element to the subsequent element (i + 1). If it is larger, swap the two elements.
                if (array[i] > array[i + 1]) {

                    // Save element at index i in variable.
                    int tmp = array[i];

                    // Assign subsequent element to index i.
                    array[i] = array[i + 1];

                    // Assign saved element to subsequent index.
                    array[i + 1] = tmp;
                }
            }

            // Decrease the number of elements to iterate through.
            n--;
        }
    }
}
