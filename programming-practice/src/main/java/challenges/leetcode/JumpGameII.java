package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Jump Game II
 * 
 * Given an array of non-negative integers, you are initially positioned at the first index of the array. Each element in 
 * the array represents your maximum jump length at that position. Your goal is to reach the last index in the minimum number 
 * of jumps.
 * 
 * For example:	Given array A = [2,3,1,1,4]
 * 
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 * 
 * Note: You can assume that you can always reach the last index.
 * 
 * @author Hxkandwal
 */
public class JumpGameII extends AbstractCustomTestRunner {

	private static JumpGameII _instance = new JumpGameII();
	
	public int _jump(int[] nums) {
		if (nums.length == 1) return 0;
        int farIndex = 0, count = 0, best = nums [0];
        for (int idx = 0; idx < nums.length && farIndex < nums.length - 1; idx ++) {
            best = Math.max (best, idx + nums [idx]);
            if (farIndex == idx) { farIndex = best; count ++; best = 0; }
        }
        return count;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 2, 3, 1, 1, 4 }, 2);
	}
	
	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
