package main;

import java.util.*;

public class DeterministicSelect extends Sorter {
    @Override
    public void sort(int[] array) {

        int k = array.length / 2;
        int median = select(array, 0, array.length - 1, k + 1); // k is 1-based
        System.out.println("Median is: " + median);
    }

    @Override
    public void print() {
        int[] arr = {25, 30, 3, 7, 26};
        int k = 2;
        int result = select(arr, 0, arr.length - 1, k);
        System.out.println(k + "-th smallest element is: " + result);
    }

    @Override
    public void TXTEnter(String s) {}

    @Override
    public void CSVEnter(String s) {}

    // ----------- Algorithm below ------------

    public static int select(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];

        int pivot = medianOfMedians(arr, left, right);
        int pivotIndex = partition(arr, left, right, pivot);

        int length = pivotIndex - left + 1;
        if (k == length) return arr[pivotIndex];
        else if (k < length) return select(arr, left, pivotIndex - 1, k);
        else return select(arr, pivotIndex + 1, right, k - length);
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }

        int numMedians = (int) Math.ceil((double) n / 5);
        int[] medians = new int[numMedians];

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }

        return medianOfMedians(medians, 0, medians.length - 1);
    }

    private static int partition(int[] arr, int left, int right, int pivotValue) {
        int pivotIndex = left;
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }
        swap(arr, pivotIndex, right);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
