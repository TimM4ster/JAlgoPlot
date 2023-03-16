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
package gui.sorting;

import javafx.scene.paint.Color;

/**
 * Utility class for the display of the array. Contains methods to shuffle or reverse the array. Furthermore, it
 * contains the colors used for the array.
 *
 * @author Tim-Michael Krieg
 * @version {@value Launcher#VERSION}
 * @since 1.0.0
 */
public class ArrayUtils {

    /**
     * The default color of the array elements. Might change in the future or will be replaced by a color picker.
     *
     * @since 1.0.0
     */
    public static final Color DEFAULT_COLOR = Color.rgb(0, 0, 0, 0.5);

    /**
     * The color of the array elements that are currently being compared or swapped. Might change in the future or will
     * be replaced by a color picker.
     *
     * @since 1.0.0
     */
    public static final Color SELECTED_COLOR = Color.rgb(255, 0, 0, 0.5);

    /**
     * Shuffles the given array. The array is shuffled by swapping each element with a random element in the array.
     *
     * @param array The array to shuffle.
     * @since 1.0.0
     */
    public static void shuffleArray(int[] array) {
        int index;
        int temp;
        for (int i = array.length - 1; i > 0; i--) {
            index = (int) (Math.random() * (i + 1));
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    /**
     * Reverses the given array. The array is reversed by swapping each element with the element at the opposite end of
     * the array.
     *
     * @param array The array to reverse.
     * @since 1.0.0
     */
    public static void reverseArray(int[] array) {
        int temp;
        for (int i = 0; i < array.length / 2; i++) {
            temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

    /**
     * Returns the largest element in the given array.
     *
     * @param array The array to search.
     * @return The largest element in the given array.
     * @since 1.1.0
     */
    public static int getLargestValue(int[] array) {
        int largest = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > largest) {
                largest = array[i];
            }
        }
        return largest;
    }

}
