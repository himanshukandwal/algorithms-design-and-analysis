package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 283. Move Zeroes
 * 
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 * 
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums
 * should be [1, 3, 12, 0, 0].
 * 
 * Note: a) You must do this in-place without making a copy of the array. b)
 * Minimize the total number of operations.
 * 
 * @author Hxkandwal
 *
 */
public class MoveZeroes extends AbstractCustomTestRunner {

	private static MoveZeroes _instance = new MoveZeroes();

	public void _moveZeroes(int[] nums) {
		int uIdx = -1;
        for (int idx = 0; idx < nums.length; idx ++) if (nums [idx] != 0) nums [ ++ uIdx] = nums [idx];
        for (int idx = uIdx + 1; idx < nums.length; idx ++) nums [idx] = 0;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] {}, new int[] {});
		_instance.runTest(new int[] {1, 0, 0, 3, 12}, new int[] {1, 3, 12, 0, 0});
		_instance.runTest(new int[] {0, 1, 0, 3, 12}, new int[] {1, 3, 12, 0, 0});
	}

	public void runTest(final int[] nums, final int[] expectedOutput) {
		runAll(getClass(), new Object[] { nums });
		assertThat(nums).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
