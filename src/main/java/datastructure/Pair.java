/**
 *  JAlgoPlot - Made to make understanding algorithms easier.
 *     Copyright (C) 2022-2023  Tim-Michael Krieg
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package datastructure;

/**
 * An object of this class represents a pair of elements with different types.
 *
 * @param <F>   The type of the first element in the pair.
 * @param <S>   The type of the second element in the pair.
 * @author Tim-Michael Krieg
 * @version 1.0.0
 * @since v1.0.0
 */
public class Pair<F, S> {

    /**
     * The first element of this pair.
     *
     * @since v1.0.0
     */
    public F first;

    /**
     * The second element of this pair.
     *
     * @since v1.0.0
     */
    public S second;

    /**
     * Constructor initializing each element in the pair. Use the {@code of} method to create an instance of this
     * object.
     *
     * @param first The first element of this pair.
     * @param second    The second element of this pair.
     * @since v1.0.0
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
     * @since v1.0.0
     */
    public static <F, S> Pair<F, S> of(F first, S second) {
        return new Pair<>(first, second);
    }
}
