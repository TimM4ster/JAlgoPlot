package algorithms.sorting.bubblesort;

import algorithms.sorting.SortingAlgorithm;
import algorithms.sorting.SortingState;
import datastructure.Pair;

/**
 * An object of this class represents the Bubble sort algorithm executed on an int array.
 */
public class BubbleSort extends SortingAlgorithm {

    /**
     * Constructor initializing the array that is to be sorted by the Bubble sort algorithm.
     *
     * @param array The array to be sorted.
     */
    public BubbleSort(int[] array) {
        super(array);
    }

    /**
     * Returns the name of the Bubble sort algorithm.
     *
     * @return The name.
     */
    @Override
    public String getName() {
        return "Bubble Sort";
    }

    /**
     * Returns the type of this algorithm.
     *
     * @return The type.
     */
    @Override
    public String getType() {
        return "Sorting";
    }

    /**
     * Runs the algorithm and builds the state machine. Should always create a new instance of the state machine.
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
