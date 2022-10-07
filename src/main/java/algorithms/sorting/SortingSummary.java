package algorithms.sorting;

import algorithms.summary.AlgoSummary;

/**
 * An object of this class represents a summary of a sorting algorithm. It contains additional information to the
 * AlgoSummary class, such as total comparison and total number of swaps.
 */
public class SortingSummary extends AlgoSummary {

    /**
     * The length of the array that was sorted.
     */
    private final int arrayLength;

    /**
     * The total number of comparisons performed by the sorting algorithm.
     */
    private final int totalComparisons;

    /**
     * The total number of swaps performed by the sorting algorithm.
     */
    private final int totalSwaps;

    /**
     * Constructor extracting the name and the type of the algorithm, the total time passed until completion of the
     * algorithm and the number of iterations the algorithm had to go through from the given sorting algorithm object.
     *
     * @param algorithm The sorting algorithm.
     */
    public SortingSummary(SortingAlgorithm algorithm) {
        super(algorithm);
        arrayLength = algorithm.array.length;
        totalComparisons = algorithm.getTotalComparisons(); //TODO
        totalSwaps = algorithm.getTotalSwaps(); //TODO
    }

    /**
     * Returns the length of the array that was sorted.
     *
     * @return  The length of the array.
     */
    public int getArrayLength() {
        return arrayLength;
    }

    /**
     * Returns the total number of comparisons performed by the sorting algorithm.
     *
     * @return  The total number of comparisons.
     */
    public int getTotalComparisons() {
        return totalComparisons;
    }

    /**
     * Returns the total number of swaps performed by the sorting algorithm.
     *
     * @return  The total number of swaps.
     */
    public int getTotalSwaps() {
        return totalSwaps;
    }

    /**
     * Creates and returns a string-representation of this Summary.
     *
     * @return The string-representation.
     */
    @Override
    public String toString() {
        return String.format(
                "Type of algorithm: %s, Name of algorithm: %s, Length of array: %d, Total time elapsed: %f, Total number of iterations: %d, Total number of comparisons: %d, Total number of swaps: %d",
                type,
                name,
                arrayLength,
                totalTime,
                totalIterations,
                totalComparisons,
                totalSwaps
        );
    }
}
