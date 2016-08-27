package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

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
	
	// Method 1 : O(n) algorithm using O(1) space
	public void _moveZeroes(int[] nums) {
		if (nums == null || nums.length <= 1 )
			return;
		
		for (int nzeroIdx = -1, idx = 0; idx < nums.length; idx ++) {
			if (nums [idx] != 0) {
				if (idx - nzeroIdx == 1)
					nzeroIdx ++;
				else
					swap(nums, idx, ++ nzeroIdx);
			}			
		}
	}
	
	public void swap (int[] nums, int pos1, int pos2) {
		if (pos1 >= 0 && pos2 < nums.length) {
			int swapTemp = nums [pos1];
			nums [pos1] = nums [pos2];
			nums [pos2] = swapTemp;
		}
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(null, null);
		_instance.runTest(new int[] {}, new int[] {});
		_instance.runTest(new int[] {1, 0, 0, 3, 12}, new int[] {1, 3, 12, 0, 0});
		_instance.runTest(new int[] {0, 1, 0, 3, 12}, new int[] {1, 3, 12, 0, 0});
		
		System.out.println("ok!");
	}

	public void runTest(final int[] nums, final int[] expectedOutput) {
		runAll(getClass(), new Object[] { nums });
		assertThat(nums).isEqualTo(expectedOutput);
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		try {
			method.invoke(this, externalVariables);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}
}
