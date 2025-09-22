package test.java.main;
import main.QuickSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class QuickSortTest {
    @Test
    public void testRecursionDepthBound() {
        int n = 1 << 16;
        int[] arr = new java.util.Random().ints(n, 0, 1_000_000).toArray();
        QuickSort sorter = new QuickSort();
        sorter.sort(arr);
        for (int i = 1; i < arr.length; i++) {
            assertTrue(arr[i - 1] <= arr[i], "Array not sorted!");
        }
        int bound = 2 * (int) (Math.log(n) / Math.log(2)) + 10;
        assertTrue(sorter.getMaxDepth() <= bound,
                "Recursion depth exceeded theoretical bound!");
    }
}
