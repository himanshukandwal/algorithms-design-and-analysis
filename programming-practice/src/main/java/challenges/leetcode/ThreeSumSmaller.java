package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 259. 3Sum Smaller
 * 
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n 
 * that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 * 
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 * Return 2. Because there are two triplets which sums are less than 2:
 * 
 * 		[-2, 0, 1]
 * 		[-2, 0, 3]
 * 
 * Follow up: Could you solve it in O(n^2) runtime?
 * 
 * @author Hxkandwal
 */
public class ThreeSumSmaller extends AbstractCustomTestRunner {
	
	private static ThreeSumSmaller _instance = new ThreeSumSmaller();

	public int _threeSumSmaller(int[] nums, int target) {
        int count = 0;
        Arrays.sort (nums);
        for (int idx = 0; idx < nums.length - 2; idx ++) {
        	int left = idx + 1, right = nums.length - 1;
        	while (left < right) 
				if (nums [idx] + nums [left] + nums [right] < target) { count += right - left; left++; } 
				else right--;
        }
        return count;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { -2, 0, 1, 3 }, 4, 3);
		_instance.runTest(new int[] { -2, 0, 1, 3 }, 2, 2);
	}

	public void runTest(final int[] nums, int target, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, target });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
