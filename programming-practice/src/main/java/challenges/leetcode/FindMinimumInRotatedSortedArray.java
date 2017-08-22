package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 153. Find Minimum in Rotated Sorted Array Add to List
 * 
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element. You may assume no duplicate exists in the array.
 * 
 * @author Hxkandwal
 */
public class FindMinimumInRotatedSortedArray extends AbstractCustomTestRunner {
	
	private static FindMinimumInRotatedSortedArray _instance = new FindMinimumInRotatedSortedArray();

	// always look toward the unsorted array.
	// solve for one hand side, bring other equal to mid.
	public int _findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (nums [mid] > nums [end]) start = mid + 1;
            else end = mid;
        }
        return nums [start];
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 2 }, 1);
		_instance.runTest(new int [] { 4, 5, 6, 7, 0, 1, 2 }, 0);
	}
	
	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });
		
		for (Object answer : answers) 
			assertThat((int) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
