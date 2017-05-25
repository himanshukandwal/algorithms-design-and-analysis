package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 16. 3Sum Closest
 * 
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * 
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * 
 * @author Hxkandwal
 */
public class ThreeSumClosest extends AbstractCustomTestRunner {
	
	private static ThreeSumClosest _instance = new ThreeSumClosest();

	public int _threeSumClosest(int[] nums, int target) {
		Arrays.sort (nums);
        int closest = nums [0] + nums [1] + nums [2];
        for (int idx = 0; idx < nums.length - 2; idx ++) {
            for (int jdx = idx + 1; jdx < nums.length - 1; jdx ++) {
                int low = jdx + 1, high = nums.length - 1;
                while (low <= high) {
                    int m = (low + high)/2;
                    int sum = nums [idx] + nums [jdx] + nums [m];
                    
                    if (Math.abs (target - closest) > Math.abs (target - sum)) closest = sum;
                    
                    if (sum > target) high = m - 1;
                    else if (sum < target) low = m + 1;
                    else break;
                }
            }
        }
        return closest;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 0, 0, 0 }, 1 , 0);
		_instance.runTest(new int[] { -1, 2, 1, -4 }, 1, 2);
	}

	public void runTest(final int[] nums, int target, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, target });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
