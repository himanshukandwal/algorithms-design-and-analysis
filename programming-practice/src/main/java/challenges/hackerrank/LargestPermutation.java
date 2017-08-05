package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/**
 * Largest Permutation
 *
 * lexicographically largest permutation you can make with at most K swaps.
 *
 * link: https://www.hackerrank.com/challenges/largest-permutation
 *
 * Created by Hxkandwal
 */
public class LargestPermutation extends AbstractCustomTestRunner {

    private static LargestPermutation _instance = new LargestPermutation();

    public int [] _permute (int [] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int idx = 0; idx < nums.length; idx ++) map.put (nums [idx], idx);
        for (int idx = 0; idx < nums.length && k > 0; idx ++) {
            int expected = nums.length - idx;
            if (nums [idx] != expected) {
                k --;
                int pos = map.get (expected), val = nums [pos];

                nums [pos] = nums [idx];
                nums [idx] = val;

                map.put (val, idx);
                map.put (nums [pos], pos);
            }
        }
        return nums;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int[] { 4, 2, 3, 5, 1 }, 1, new int [] { 5, 2, 3, 4, 1 });
    }

    public void runTest(final int [] nums, final int k, final int [] expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { nums, k });

        for (Object answer : answers)
            assertThat((int []) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
