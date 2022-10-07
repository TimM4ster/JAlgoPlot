import algorithms.StateMachine;
import algorithms.sorting.SortingAlgorithm;
import algorithms.sorting.bubblesort.BubbleSort;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int length = 10;
        int[] array = new int[length];
        for (int i = length; i > 0; i--) {
            array[length - i] = i;
        }
        testSortingAlgorithm(new BubbleSort(array));
    }

    static void testSortingAlgorithm(SortingAlgorithm algorithm) {
        StateMachine machine = algorithm.getStateMachine();
        machine.print();
        algorithm.getSummary().print();
    }
}