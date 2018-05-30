package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 34. Search for a Range
 * 
 * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * For example,
 * 		Given [5, 7, 7, 8, 8, 10] and target value 8,
 * 		return [3, 4].
 * 
 * @author Hxkandwal
 */
public class SearchForARange extends AbstractCustomTestRunner {

	/**
	 * https://www.topcoder.com/community/data-science/data-science-tutorials/binary-search/
	 */
	public int[] _searchRange(int[] nums, int target) {
		int [] ans = { -1, -1 };
		if (nums.length == 0) return ans;

		int l = 0, r = nums.length - 1;
		while (l < r) {
			int m = l + (r - l)/2;

			if (nums [m] >= target) r = m;
			else l = m + 1;
		}
		if (nums [l] == target) ans [0] = l;

		l = 0; r = nums.length - 1;
		while (l < r) {
			int m = l + (r - l + 1)/2;

			if (nums [m] <= target) l = m;
			else r = m - 1;
		}
		if (nums [l] == target) ans [1] = l;

		return ans;
    }
}
