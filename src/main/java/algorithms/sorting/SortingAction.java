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
package algorithms.sorting;

/**
 * Each element of this enumeration represents an action taken by a sorting algorithm in order to sort an array.
 *
 * @author Tim-Michael Krieg
 * @version 1.0.0
 * @since v1.0.0
 */
public enum SortingAction {

    /**
     * The action of highlighting an element inside the array. Might be removed in the future.
     *
     * @since v1.0.0
     */
    HIGHLIGHT,

    /**
     * The action of comparing two elements inside the array.
     *
     * @since v1.0.0
     */
    COMPARE,

    /**
     * The action of swapping two elements inside the array.
     *
     * @since v1.0.0
     */
    SWAP
}
