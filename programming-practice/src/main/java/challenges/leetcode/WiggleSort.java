package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 280. Wiggle Sort
 * 
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * 
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 * 
 * @author Hxkandwal
 */
public class WiggleSort extends AbstractCustomTestRunner {
	
	private static WiggleSort _instance = new WiggleSort();

	/**
	 *  if (i % 2 == 1) {
	 *  	if (nums [i - 1] > nums [i]) swap (nums, i);
	 *  } 
	 *  else if (i != 0 && nums [i - 1] < nums [i]) swap (nums, i);
	 */
	public int[] _wiggleSort(int[] nums) {
		for (int i = 1; i < nums.length; i ++) {
	        int a = nums [i-1];
			if ((i % 2 == 1) == (a > nums [i])) { 		// xor
				nums [i - 1] = nums [i];
				nums [i] = a;
	        }
	    }
		return nums;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2, 3 }, new int[] { 1, 3, 2 });
		_instance.runTest(new int[] { 3, 5, 2, 1, 6, 4 }, new int[] { 3, 5, 1, 6, 2, 4 });
	}

	public void runTest(final int[] nums, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((int []) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
