package main;
import java.util.Arrays;
import java.util.Random;
public class QuickSort extends Sorter {
    private int[] array;
    private static final Random rand = new Random();
    private int maxDepth = 0;
    @Override
    public void sort(int[] array) {
        this.array = array;
        maxDepth = 0;
        quickSort(array, 0, array.length - 1, 1);
    }
    private void quickSort(int[] arr, int low, int high, int depth) {
        if (low < high) {
            maxDepth = Math.max(maxDepth, depth);
            int pivotIndex = low + rand.nextInt(high - low + 1);
            int pivot = arr[pivotIndex];
            swap(arr, pivotIndex, high);
            int i = low - 1;
            for (int j = low; j < high; j++) {
                if (arr[j] <= pivot) {
                    i++;
                    swap(arr, i, j);
                }
            }
            i++;
            swap(arr, i, high);
            int leftSize = i - low;
            int rightSize = high - i;
            if (leftSize < rightSize) {
                quickSort(arr, low, i - 1, depth + 1);
                quickSort(arr, i + 1, high, depth + 1);
            } else {
                quickSort(arr, i + 1, high, depth + 1);
                quickSort(arr, low, i - 1, depth + 1);
            }
        }
    }
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    public int getMaxDepth() {
        return maxDepth;
    }
    @Override
    public void print() {
        QuickSort sorter = new QuickSort();
        int[] arr = {42, 5, 9, 1, 55, 12, 7, 19, 100, 3};
        this.sort(arr);
        System.out.println("Sorted: " + java.util.Arrays.toString(arr));
        System.out.println("Max recursion depth: " + getMaxDepth());
        Timer.timeIt("quicksort" ,()->sorter.sort(arr));
    }
    @Override
    public void TXTEnter(String s) {}
    @Override
    public void CSVEnter(String s) {}
}
