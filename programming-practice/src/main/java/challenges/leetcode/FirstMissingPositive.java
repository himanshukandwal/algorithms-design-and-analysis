package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 41. First Missing Positive
 * 
 * Given an unsorted integer array, find the first missing positive integer.
 * 
 * For example, given [1,2,0] return 3, and given [3,4,-1,1] return 2.
 * 
 * Your algorithm should run in O(n) time and uses constant space.
 * 
 * @author Hxkandwal
 */
public class FirstMissingPositive extends AbstractCustomTestRunner {
	
	private static FirstMissingPositive _instance = new FirstMissingPositive();
	
	public int _firstMissingPositive(int[] nums) {
		for (int num : nums) {
			while (num <= nums.length && num > 0 && nums [num - 1] != num) {
				int val = nums [num - 1];
				nums [num - 1] = num;
				num = val;
			}
		}
		for (int idx = 0; idx < nums.length; idx ++) if (nums [idx] != idx + 1) return idx + 1;
		return nums.length + 1;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1 }, 2);
	}
	
	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
