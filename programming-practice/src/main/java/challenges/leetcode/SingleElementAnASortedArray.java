package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 540. Single Element in a Sorted Array
 *
 * Given a sorted array consisting of only integers where every element appears twice except for one element which appears once.
 * Find this single element that appears only once.
 *
 * Example 1:
 *      Input: [1,1,2,3,3,4,4,8,8]
 *      Output: 2
 *
 * Example 2:
 *      Input: [3,3,7,7,10,11,11]
 *      Output: 10
 *
 * Note: Your solution should run in O(log n) time and O(1) space.
 *
 * @author Hxkandwal
 */
public class SingleElementAnASortedArray extends AbstractCustomTestRunner {

    private static SingleElementAnASortedArray _instance = new SingleElementAnASortedArray();

    public int _singleNonDuplicate(int[] nums) {
        int ans = 0;
        for (int num : nums) ans ^= num;
        return ans;
    }

    public int _singleNonDuplicateLogN(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l)/2;
            if (2*(m/2) + 1 < nums.length && nums [2*(m/2)] == nums [2*(m/2) + 1]) l = m + 1;
            else r = m;
        }
        return nums [l];
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int[] { 1, 1, 2, 3, 3, 4, 4, 8, 8 }, 2);
    }

    public void runTest(final int[] nums, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { nums });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
