package algorithms.sorting;

/**
 * Each element of this enumeration represents an action taken by a sorting algorithm in order to sort an array.
 */
public enum SortingAction {

    /**
     * The action of highlighting an element inside the array.
     */
    HIGHLIGHT,

    /**
     * The action of comparing two elements inside the array.
     */
    COMPARE,

    /**
     * The action of swapping two elements inside the array.
     */
    SWAP
}
