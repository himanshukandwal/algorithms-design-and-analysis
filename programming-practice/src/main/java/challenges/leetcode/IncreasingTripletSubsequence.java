package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/**
 * 334. Increasing Triplet Subsequence
 *
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 *
 * Formally the function should:
 *
 *  Return true if there exists i, j, k such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 *
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 *
 * Examples:
 *  Given [1, 2, 3, 4, 5], return true.
 *
 *  Given [5, 4, 3, 2, 1], return false.
 *
 *  @author Hxkandwal
 */
public class IncreasingTripletSubsequence extends AbstractCustomTestRunner {

    private static IncreasingTripletSubsequence _instance = new IncreasingTripletSubsequence();

    public boolean _increasingTriplet(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int res = 0;
            for (Integer key : map.keySet()) if (key < num) res = Math.max (res, map.get (key) + 1);
            if (res >= 2) return true;
            map.put (num, res);
        }
        return false;
    }

    public boolean _increasingTripletFaster(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= first)  first = num;
            else if (num <= second) second = num;
            else return true;
        }
        return false;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int [] { 2, 1, 5, 0, 3 }, false);
    }

    public void runTest(final int[] nums, final boolean expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { nums });

        for (Object answer : answers)
            assertThat((Boolean) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
