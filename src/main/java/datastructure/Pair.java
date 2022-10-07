package datastructure;

/**
 * An object of this class represents a pair of elements with different types.
 *
 * @param <F>   The type of the first element in the pair.
 * @param <S>   The type of the second element in the pair.
 */
public class Pair<F, S> {

    /**
     * The first element of this pair.
     */
    public F first;

    /**
     * The second element of this pair.
     */
    public S second;

    /**
     * Constructor initializing each element in the pair. Use the {@code of} method to create an instance of this
     * object.
     *
     * @param first The first element of this pair.
     * @param second    The second element of this pair.
     */
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Method that constructs a pair object containing the given elements.
     *
     * @param first The first element of the pair.
     * @param second    The second element of the pair.
     * @return  The pair-object containing the two elements.
     * @param <F>   The type of the first element.
     * @param <S>   The type of the second element.
     */
    public static <F, S> Pair<F, S> of(F first, S second) {
        return new Pair<>(first, second);
    }
}
