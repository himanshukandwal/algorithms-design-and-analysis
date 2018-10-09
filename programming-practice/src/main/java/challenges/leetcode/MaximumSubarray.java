package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 53. Maximum Subarray
 * 
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * 
 * For example, given the array [ -2, 1, -3, 4, -1, 2, 1, -5 ,4 ], the contiguous sub-array [ 4, -1, 2, 1 ] 
 * 				has the largest sum = 6.
 * 
 * @author Hxkandwal
 */
public class MaximumSubarray extends AbstractCustomTestRunner {
	
	private static MaximumSubarray _instance = new MaximumSubarray();

	public static int _maxSubArray(int[] nums) {
		int max = Integer.MIN_VALUE, lmax = 0;
		for (int num : nums) {
			lmax = Math.max (num, lmax + num);
			max = Math.max (max, lmax);
		}
		return max;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }, 6);
	}
	
	public void runTest(final int[] input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers) 
			assertThat((int) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
