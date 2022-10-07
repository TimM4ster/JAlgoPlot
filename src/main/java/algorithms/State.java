package algorithms;

/**
 * Each object of this class represents a specific state of an algorithm. Each step of an algorithm is documented using
 * an object of this class. Information about the current step is stored and can be accessed in order to retrieve
 * information about the execution of the algorithm. Further information is to be defined in subclasses.
 */
public abstract class State {

    /**
     * The state following this state.
     */
    public State next;

    /**
     * The state prior to this state.
     */
    public State previous;

    /**
     * The id (typically the number) of this state.
     */
    protected final int id;

    /**
     * Time elapsed from start of algorithm until this state.
     */
    protected final double time;

    /**
     * The iteration of the algorithm in this state.
     */
    protected final int iteration;

    /**
     * Constructor initializing the id (typically the number) of this state, the time elapsed since the algorithm
     * started and the current iteration of the algorithm.
     *
     * @param id    The id (typically the number) of this state.
     * @param time  The time elapsed.
     * @param iteration The iteration.
     */
    protected State(int id, double time, int iteration) {
        this.id = id;
        this.time = time;
        this.iteration = iteration;
    }

    /**
     * Returns the id (typically the number) of this state.
     *
     * @return  The id.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the total time elapsed from the start of the algorithm until this state.
     *
     * @return  The time elapsed.
     */
    public double getTime() {
        return time;
    }

    /**
     * Returns the current iteration of the algorithm in this state.
     *
     * @return  The iteration.
     */
    public int getIteration() {
        return iteration;
    }

    /**
     * Creates and returns a string-representation of this state.
     *
     * @return  The string-representation.
     */
    public abstract String toString();
}
