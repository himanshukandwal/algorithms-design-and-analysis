package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 162. Find Peak Element
 * 
 * A peak element is an element that is greater than its neighbors.
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * 
 * You may imagine that num[-1] = num[n] = -∞.
 * 
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * 
 * @author Hxkandwal
 */
public class FindPeakElement extends AbstractCustomTestRunner {

    public int _findPeakElement(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int m = lo + (hi - lo) / 2;
            if (m > 0 && nums[m - 1] > nums[m]) hi = m - 1;
            else if (m < nums.length - 1 && nums[m + 1] > nums[m]) lo = m + 1;
            else return m;
        }
        return lo;
    }
}
