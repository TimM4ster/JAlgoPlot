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
     * Constructor initializing the array that is to be sorted by the algorithm.
     *
     * @param array The array to be sorted.
     */
    protected SortingAlgorithm(int[] array) {
        this.array = array;
    }

    /**
     * Returns the array that is to be sorted by the Bubble sort algorithm.
     *
     * @return  The array.
     */
    public int[] getArray() {
        return array;
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
     * Runs the algorithm. Running the algorithm sorts the array from the smallest to the biggest value. This method is
     * simply to check whether the implementation of the algorithm works.
     */
    public abstract void sort();

}
