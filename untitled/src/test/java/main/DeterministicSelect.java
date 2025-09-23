import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class DeterministicSelectTest {

    @Test
    void testSelectAgainstArraysSort() {
        Random random = new Random();
        int trials = 100;

        for (int t = 0; t < trials; t++) {
            int n = random.nextInt(50) + 1;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = random.nextInt(1000);
            }


            int k = random.nextInt(n) + 1;

            // копии массива
            int[] arrCopy1 = Arrays.copyOf(arr, arr.length);
            int[] arrCopy2 = Arrays.copyOf(arr, arr.length);

            // результат алгоритма
            int selected = DeterministicSelect.select(arrCopy1, 0, arrCopy1.length - 1, k);

            // эталон через Arrays.sort
            Arrays.sort(arrCopy2);
            int expected = arrCopy2[k - 1];

            assertEquals(expected, selected,
                    String.format("Ошибка на trial=%d, k=%d, expected=%d, got=%d, массив=%s",
                            t, k, expected, selected, Arrays.toString(arr)));
        }
    }
}
