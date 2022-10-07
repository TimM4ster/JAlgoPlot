package algorithms.summary;

import algorithms.Algorithm;

/**
 * An object of this class represents a summary of an algorithm. It contains information about the time the algorithm
 * took until completion, the name and type of the algorithm and further information can be added through extending
 * classes.
 */
public abstract class AlgoSummary {

    /**
     * The name of the algorithm.
     */
    protected final String name;

    /**
     * The type of the algorithm (example: sorting).
     */
    protected final String type;

    /**
     * The total time elapsed during the execution of the algorithm.
     */
    protected final double totalTime;

    /**
     * The total number of iterations the algorithm took for completion.
     */
    protected final int totalIterations;

    /**
     * Constructor extracting the name and the type of the algorithm, the total time passed until completion of the
     * algorithm and the number of iterations the algorithm had to go through from the given algorithm object.
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
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the algorithm.
     *
     * @return  The type.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the total time elapsed since the start of the algorithm.
     *
     * @return  The total time elapsed.
     */
    public double getTotalTime() {
        return totalTime;
    }

    /**
     * Returns the total amount of iterations the algorithm went through.
     *
     * @return  The total amount of iterations.
     */
    public int getTotalIterations() {
        return totalIterations;
    }

    /**
     * Prints this summary to the console.
     */
    public void print() {
        System.out.println(toString());
    }

    /**
     * Creates and returns a string-representation of this Summary.
     *
     * @return  The string-representation.
     */
    public abstract String toString();
}
