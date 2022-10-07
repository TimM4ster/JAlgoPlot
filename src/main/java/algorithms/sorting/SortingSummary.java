package algorithms.sorting;

import algorithms.Algorithm;
import algorithms.StateMachine;
import algorithms.summary.AlgoSummary;

/**
 * An object of this class represents a summary of a sorting algorithm. It contains additional information to the
 * AlgoSummary class, such as total comparison and total number of swaps.
 */
public class SortingSummary extends AlgoSummary {

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
     * algorithm and the number of iterations the algorithm had to go through from the given state machine.
     *
     * @param algorithm The sorting algorithm.
     * @param machine   The state machine of the algorithm.
     *
     *                  TODO: Change Algorithm to SortingAlgorithm
     */
    public SortingSummary(Algorithm algorithm, StateMachine machine) {
        super(algorithm, machine);
        totalComparisons = 0; //TODO
        totalSwaps = 0; //TODO
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
}
