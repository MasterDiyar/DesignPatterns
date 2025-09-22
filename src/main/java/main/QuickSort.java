package main;

import java.util.Arrays;
import java.util.Random;
public class QuickSort extends Sorter {
    private int[] array;
    private static final Random rand = new Random();
    @Override
    public void sort(int[] array) {
        this.array = array;
        quickSort(array, 0, array.length - 1);
    }
    private void quickSort(int[] arr, int low, int high) {
        while (low < high) {
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
                quickSort(arr, low, i - 1);
                low = i + 1;
            } else {
                quickSort(arr, i + 1, high);
                high = i - 1;
            }
        }
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    @Override
    public void print() {
        QuickSort sorter = new QuickSort();
        int[] arr = {42, 5, 9, 1, 55, 12, 7, 19, 100, 3};
        sorter.sort(arr);
        if (sorter.array != null) {
            System.out.println("Результат сортировки: " + Arrays.toString(sorter.array));
        } else {
            System.out.println("Массив ещё не отсортирован.");
        }
        Timer.timeIt("quicksort" ,()->sorter.sort(arr));
    }
    @Override
    public void TXTEnter(String s) {
    }
    @Override
    public void CSVEnter(String s) {
    }

}
