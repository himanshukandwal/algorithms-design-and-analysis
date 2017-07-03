package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 300. Longest Increasing Subsequence
 * 
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example,
 * 		Given [10, 9, 2, 5, 3, 7, 101, 18],
 * 		
 * 		The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than 
 * one LIS combination, it is only necessary for you to return the length.
 * 
 * Your algorithm should run in O(n^2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 * 
 * @author Hxkandwal
 */
public class LongestIncreasingSubsequence extends AbstractCustomTestRunner {
	
	private static LongestIncreasingSubsequence _instance = new LongestIncreasingSubsequence();

	// O (nlogn) solution.
	public int _lengthOfLISBetter(int[] nums) {            
        int[] dp = new int[nums.length];
        int len = 0;

        for (int x : nums) {
            int i = Arrays.binarySearch (dp, 0, len, x);
			if (i < 0) i = -(i + 1);
            dp [i] = x;
			if (i == len) len ++;
        }
        return len;
    }
	
	public int _lengthOfLIS(int[] nums) {
		if (nums.length == 0) return 0;
        int [] dp = new int [nums.length];
        Arrays.fill (dp, 1);
        int max = 1;
        for (int idx = 0; idx < nums.length; idx ++)
            for (int jdx = idx + 1; jdx < nums.length; jdx ++)
                if (nums [jdx] > nums [idx]) max = Math.max (max, dp [jdx] = Math.max (dp [jdx], dp [idx] + 1));
        return max;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { -2, -1 }, 2);
		_instance.runTest(new int [] { 10, 9, 2, 5, 3, 7, 101, 18 }, 4);
	}

	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
