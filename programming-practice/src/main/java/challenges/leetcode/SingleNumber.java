package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 136. Single Number
 * 
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * 
 * @author Hxkandwal
 *
 */
public class SingleNumber extends AbstractCustomTestRunner {
	
	private static SingleNumber _instance = new SingleNumber();

	public static int _singleNumber(int[] nums) {
		int res = nums [0];
        for (int idx = 1; idx < nums.length; idx ++) res ^= nums [idx];
        return res;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1 }, 1);
		_instance.runTest(new int[] { 1, 1, 3, 3, 4 }, 4);
		_instance.runTest(new int[] { 1, 1, 3, 3, 4, 4, 5 }, 5);
	}
	
	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
