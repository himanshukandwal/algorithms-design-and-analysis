package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 189. Rotate Array
 * 
 * Rotate an array of n elements to the right by k steps.
 * 
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * 
 * Note:
 * 		Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * 
 * @author Hxkandwal
 */
public class RotateArray extends AbstractCustomTestRunner {
	
	private static RotateArray _instance = new RotateArray();

	public int[] _rotate(int[] nums, int k) {
        if ((k %= nums.length) == 0 || nums.length == 0) return nums;
        reverse (nums, 0, nums.length - 1);
        reverse (nums, 0, k - 1);
        reverse (nums, k, nums.length - 1);
        return nums;
    }
	    
    private void reverse (int [] nums, int from, int to) {
        for (int idx = 0; idx <= (to - from) >>> 1; idx ++) {
            int temp = nums [from + idx];
            nums [from + idx] = nums [to - idx];
            nums [to - idx] = temp;
        }
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2, 3, 4, 5, 6 }, 2, new int[] { 5, 6, 1, 2, 3, 4 });	
		_instance.runTest(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 3, new int[] { 5, 6, 7, 1, 2, 3, 4 });	
		_instance.runTest(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 2, new int[] { 6, 7, 1, 2, 3, 4, 5 });	
	}

	public void runTest(final int[] nums, final int k, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, k });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}