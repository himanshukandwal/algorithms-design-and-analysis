package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 154. Find Minimum in Rotated Sorted Array II
 * 
 * Follow up for "Find Minimum in Rotated Sorted Array":
 * What if duplicates are allowed?
 * 
 * Would this affect the run-time complexity? How and why?
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element. The array may contain duplicates.
 * 
 * @author Hxkandwal
 */
public class FindMinimumInRotatedSortedArrayII extends AbstractCustomTestRunner {
	
	public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end && nums [start] >= nums [end]) {
            int mid = (start + end) >>> 1;
            if (nums [mid] > nums [end]) start = mid + 1;
            else if (nums [mid] < nums [start]) end = mid;
            else start ++;
        }
        return nums [start];
    }

}
