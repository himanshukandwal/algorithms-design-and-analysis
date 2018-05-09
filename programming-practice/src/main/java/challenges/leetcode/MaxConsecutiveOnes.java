package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 485. Max Consecutive Ones
 * 
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 * 
 * Example 1:
 * 		Input: [ 1, 1, 0, 1, 1, 1 ]
 * 		Output: 3
 * 
 * 		Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 * 
 * @author Hxkandwal
 *
 */
public class MaxConsecutiveOnes extends AbstractCustomTestRunner {
	
	private static MaxConsecutiveOnes _instance = new MaxConsecutiveOnes();

	public int _findMaxConsecutiveOnes(int[] nums) {
		int ans = 0;
		for (int idx = 0, count = 0; idx < nums.length; idx ++) {
			count = (nums [idx] == 1) ? count + 1 : 0;
			ans = Math.max (ans, count);
		}
		return ans;
	}
	
	public static int _findMaxConsecutiveOnes2(int[] nums) {
        int maxHere = 0, max = 0;
        for (int n : nums)
            max = Math.max(max, maxHere = n == 0 ? 0 : maxHere + 1);
        return max; 
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 1, 0, 1, 1, 1 }, 3);
		_instance.runTest(new int [] { 1, 1, 0, 1 }, 2);
		_instance.runTest(new int [] { 0, 0, 0, 0 }, 0);
	}
	
	public void runTest(final int[] input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers) 
			assertThat((int) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
