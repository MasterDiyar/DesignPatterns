package main;

import java.util.Arrays;

public class MergeSort extends Sorter {
    private int[] array;

    private int recursionCalls;
    private int maxDepth;
    private int comparisons;

    private static final int CUTOFF = 16;

    @Override
    public void sort(int[] array) {
        if (array == null || array.length < 2) {
            this.array = array;
            return;
        }
        this.array = array;
        recursionCalls = 0;
        maxDepth = 0;
        comparisons = 0;

        int[] buffer = new int[array.length];
        mergeSort(array, 0, array.length - 1, buffer, 1);
    }

    @Override
    public void print() {
        MergeSort sorter = new MergeSort();
        int[] arr = {42, 5, 9, 1, 55, 12, 7, 19, 100, 3};

        sorter.sort(arr);

        if (sorter.array != null) {
            System.out.println("Результат MergeSort: " + Arrays.toString(sorter.array));
            System.out.println("Recursion calls: " + sorter.recursionCalls);
            System.out.println("Max depth: " + sorter.maxDepth);
            System.out.println("Comparisons: " + sorter.comparisons);
        } else {
            System.out.println("Массив ещё не отсортирован.");
        }

        Timer.timeIt("mergesort", () -> sorter.sort(arr));
    }


    @Override
    public void TXTEnter(String s) {

    }
    @Override
    public void CSVEnter(String s) {

    }

    private void mergeSort(int[] arr, int left, int right, int[] buffer, int depth) {
        recursionCalls++;
        if (depth > maxDepth) maxDepth = depth;

        if (left >= right) {          // <-- always stop at 0 or 1 element
            return;
        }

        int size = right - left + 1;
        if (size <= CUTOFF) {         // cutoff to insertion sort
            insertionSort(arr, left, right);
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid, buffer, depth + 1);
        mergeSort(arr, mid + 1, right, buffer, depth + 1);
        merge(arr, left, mid, right, buffer);
    }

    private void merge(int[] arr, int left, int mid, int right, int[] buffer) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            comparisons++;
            if (arr[i] <= arr[j]) buffer[k++] = arr[i++];
            else buffer[k++] = arr[j++];
        }
        while (i <= mid) buffer[k++] = arr[i++];
        while (j <= right) buffer[k++] = arr[j++];

        for (int p = left; p <= right; p++) arr[p] = buffer[p];
    }

    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                comparisons++;
                if (arr[j] <= key) break;
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
