package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/**
 * 740. Delete and Earn
 *
 * Given an array nums of integers, you can perform operations on the array.
 *
 * In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element
 * equal to nums[i] - 1 or nums[i] + 1.
 *
 * You start with 0 points. Return the maximum number of points you can earn by applying such operations.
 *
 * Example 1:
 *      Input: nums = [3, 4, 2]
 *      Output: 6
 *
 *      Explanation: Delete 4 to earn 4 points, consequently 3 is also deleted.
 *                   Then, delete 2 to earn 2 points. 6 total points are earned.
 *
 * Example 2:
 *      Input: nums = [2, 2, 3, 3, 3, 4]
 *      Output: 9
 *
 *      Explanation: Delete 3 to earn 3 points, deleting both 2's and the 4.
 *                   Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
 *                   9 total points are earned.
 *
 * Note:
 * -  The length of nums is at most 20000.
 * -  Each element nums[i] is an integer in the range [1, 10000].
 *
 * @author Hxkandwal
 */
public class DeleteAndEarn extends AbstractCustomTestRunner {

    private static DeleteAndEarn _instance = new DeleteAndEarn();

    public int _deleteAndEarn(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put (num, map.getOrDefault (num, 0) + num);

        int ans = 0;
        while (map.size() > 0) {
            int key = map.keySet().iterator().next();
            int start = key; while (map.containsKey (start)) start --; start ++;
            int end = key; while (map.containsKey (end)) end ++; end --;

            int f = 0, s = 0;
            for (int idx = start; idx <= end; idx ++) {
                int t = Math.max (map.get (idx) + f, s);
                f = s;
                s = t;
            }
            ans += s;

            for (int idx = start; idx <= end; idx ++) map.remove (idx);
        }
        return ans;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int[] { 2, 2, 3, 3, 3, 4 }, 9);
        _instance.runTest(new int[] { 10, 8, 4, 2, 1, 3, 4, 8, 2, 9, 10, 4, 8, 5, 9, 1, 5, 1, 6, 8, 1, 1,
                                       6, 7, 8, 9, 1, 7, 6, 8, 4, 5, 4, 1, 5, 9, 8, 6, 10, 6, 4, 3, 8, 4,
                                      10, 8, 8, 10, 6, 4, 4, 4, 9, 6, 9, 10, 7, 1, 5, 3, 4, 4, 8, 1, 1, 2,
                                       1, 4, 1, 1, 4, 9, 4, 7, 1, 5, 1, 10, 3, 5, 10, 3, 10, 2, 1, 10, 4,
                                       1, 1, 4, 1, 2, 10, 9, 7, 10, 1, 2, 7, 5 }, 338);
    }

    public void runTest(final int [] arr, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { arr });

        for (Object answer : answers)
            assertThat((int) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
