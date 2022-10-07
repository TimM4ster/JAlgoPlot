package algorithms.sorting;

import algorithms.Algorithm;

import java.util.Arrays;

/**
 * Extends the abstract algorithm class and adds additional attributes and methods needed by sorting algorithms. Each
 * sorting algorithm should implement this class for key functionalities.
 */
public abstract class SortingAlgorithm extends Algorithm {

    /**
     * The array that is to be sorted by the sorting algorithm.
     */
    protected final int[] array;

    /**
     * The array in the state before sorting.
     */
    protected final int[] before;

    /**
     * The total number of comparisons performed by the sorting algorithm.
     */
    protected int totalComparisons = 0;

    /**
     * The total number of swaps performed by the sorting algorithm.
     */
    protected int totalSwaps = 0;

    /**
     * Constructor initializing the array that is to be sorted by the algorithm.
     *
     * @param array The array to be sorted.
     */
    protected SortingAlgorithm(int[] array) {
        this.array = array;
        before = getArrayCopy();
    }

    /**
     * Returns the array that is to be sorted by the sorting algorithm.
     *
     * @return  The array.
     */
    public int[] getArray() {
        return array;
    }

    /**
     * Returns the total number of comparisons performed by the sorting algorithm. Only call after method run.
     *
     * @return  The total number of comparisons.
     */
    public int getTotalComparisons() {
        return totalComparisons;
    }

    /**
     * Returns the total number of swaps performed by the sorting algorithm. Only call after method run.
     *
     * @return  The total number of swaps.
     */
    public int getTotalSwaps() {
        return totalSwaps;
    }

    /**
     * Returns a sorting summary of the sorting algorithm.
     *
     * @return  The sorting summary.
     */
    public SortingSummary getSummary() {
        return new SortingSummary(this);
    }

    /**
     * Returns a copy of the current state of the array.
     *
     * @return  The copy.
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
     */
    public String arrayToString() {
        return Arrays.toString(array);
    }

    /**
     * Prints the contained array on the console.
     */
    public void printArray() {
        System.out.println(arrayToString());
    }

    /**
     * Computes the level of unsortedness of an array. Since all arrays are constructed in a simple manner, a check for
     * equality of index and element is enough.
     *
     * @param array The array.
     * @return  The unsortedness.
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
     */
    public abstract void sort();

}
