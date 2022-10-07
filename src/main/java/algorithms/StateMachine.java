package algorithms;

/**
 * This class works as a wrapper for each state of an algorithm. Each state of the algorithm contains information about
 * the current step that was executed by the algorithm. For example, in sorting a step would be to swap two elements.
 * Additional information about the algorithm is stored in a separate AlgoSummary object.
 */
public class StateMachine {

    /*
    TODO:
        - Progress for each state
     */

    /**
     * The first and initial state of the algorithm.
     */
    public State first;

    /**
     * The last state of the algorithm.
     */
    public State last;

    /**
     * The total number of states the algorithm has.
     */
    private int numberOfStates = 0;

    /**
     * Appends a state to the list of states.
     *
     * @param state  The state to be appended.
     */
    public void append(State state) {
        if (first == null) {
            first = last = state;
            return;
        }
        state.previous = last;
        last.next = state;
        last = last.next;
        numberOfStates++;
    }

    /**
     * Prints each state of this state machine to the console by calling the overwritten toString method.
     */
    public void print() {
        for (State state = first; state != null; state = state.next) {
            System.out.println(state);
        }
    }
}
