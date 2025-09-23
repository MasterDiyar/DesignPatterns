package test.java;

import main.MergeSort;
import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void sortsRandomArrayCorrectly() {
        Random random = new Random();
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10000);
        }
        int[] expected = arr.clone();
        java.util.Arrays.sort(expected);

        MergeSort sorter = new MergeSort();
        sorter.sort(arr);

        assertArrayEquals(expected, arr,
                "MergeSort should correctly sort a random array");
    }

    @Test
    void sortsAdversarialArrayCorrectly() {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr.length - i;
        }
        int[] expected = arr.clone();
        java.util.Arrays.sort(expected);

        MergeSort sorter = new MergeSort();
        sorter.sort(arr);

        assertArrayEquals(expected, arr, "MergeSort should correctly sort a descending array");
    }
}