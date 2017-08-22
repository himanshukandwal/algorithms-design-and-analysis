package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 153. Find Minimum in Rotated Sorted Array
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

	public int findMin(int[] nums) {
		int l = 0, r = nums.length - 1;
		while (l <= r) {
			int m = (l + r) >>> 1;
			if ((m == 0 || nums [ m - 1] > nums [m]) && (m + 1 == nums.length || nums [m + 1] > nums [m])) return nums [m];
			else {
				if (nums [m] > nums [l]) {
					if (nums [l] > nums [r]) l = m + 1;
					else r = m - 1;
				} else {
					if (nums [r] > nums [m]) r = m - 1;
					else l = m + 1;
				}
			}
		}
		return -1;
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
