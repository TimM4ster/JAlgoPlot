package algorithms;

import algorithms.summary.AlgoSummary;

/**
 * An object of this class represents an algorithm. Each algorithm includes a method to run the actual algorithm on the
 * data structure and further, it includes a method to create the associated state machine that can be used to plot the
 * algorithm.
 */
public abstract class Algorithm {

    /**
     * The state machine of this algorithm. Can be used to plot the algorithm.
     */
    protected final StateMachine stateMachine = new StateMachine();

    /**
     * The total time elapsed from the start of the algorithm until completion.
     */
    protected double totalTime;

    /**
     * The total number of iterations in the algorithm.
     */
    protected int totalIterations = 0;

    /**
     * Returns the name of this algorithm.
     *
     * @return  The name.
     */
    public abstract String getName();

    /**
     * Returns the type of this algorithm (example: sorting).
     *
     * @return  The type.
     */
    public abstract String getType();

    /**
     * Returns the associated summary object of this algorithm.
     *
     * @return  The summary of this algorithm.
     */
    public abstract AlgoSummary getSummary();

    /**
     * Returns the state machine of the algorithm. Should only be used if the run method was called.
     *
     * @return  The state machine.
     */
    public StateMachine getStateMachine() {
        run();
        return stateMachine;
    }

    /**
     * Returns the total time elapsed from the beginning of the algorithm until completion. Only call after method run.
     *
     * @return  The total time elapsed.
     */
    public double getTotalTime() {
        return totalTime;
    }

    /**
     * Returns the total number of iterations the algorithm needed until completion. Only call after method run.
     *
     * @return  The total number of iterations.
     */
    public int getTotalIterations() {
        return totalIterations;
    }

    /**
     * Runs the algorithm and builds the state machine. Should always create a new instance of the state machine.
     */
    protected abstract void run();
}
