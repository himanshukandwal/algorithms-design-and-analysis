package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

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

    // try to move away from the plateau
    public int _findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            if (nums [l] == nums [l + 1]) l ++;
            else if (nums [r] == nums [r - 1]) r --;
            else {
                int m = l + (r - l)/2;

                if (nums [m] < nums [r]) r = m;
                else l = m + 1;
            }
        }
        return nums [l];
    }
}
