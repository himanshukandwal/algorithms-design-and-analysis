package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	 * More amazing approach.
	 * 
	 * def searchRange(self, nums, target):
	 * 		def search(n):
	 * 			lo, hi = 0, len(nums)
	 * 			while lo < hi:
	 * 				mid = (lo + hi) / 2
	 * 				if nums[mid] >= n:
	 * 					hi = mid
	 * 				else:
	 * 					lo = mid + 1
	 * 			return lo
	 * 
	 * 	lo = search(target)
	 * 	return [lo, search(target+1)-1] if target in nums[lo:lo+1] else [-1, -1]
	 * 
	 * https://discuss.leetcode.com/topic/16486/9-11-lines-o-log-n
	 */
	public int[] _searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int [] { -1, -1 };
        int low = 0, high = nums.length - 1;
        while (low + 1 < high) {
            int mid = (low + high) >>> 1;
            if (nums [mid] >= target) high = mid;
            else low = mid;
        }
        int first = (nums [low] == target) ? low : (nums [high] == target ? high : -1);
        low = 0; high = nums.length - 1;
        while (low + 1 < high) {
            int mid = (low + high) >>> 1;
            if (nums [mid] > target) high = mid;
            else low = mid;
        }
        int last = (nums [high] == target) ? high : (nums [low] == target ? low : -1);
        return new int [] { first, last };
    }
	
}
