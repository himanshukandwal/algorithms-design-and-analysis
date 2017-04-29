package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 26. Remove Duplicates from Sorted Array
 * 
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * 
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * 
 * For example, Given input array nums = [1,1,2],
 * 
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what 
 * you leave beyond the new length.
 * 
 * @author Hxkandwal
 *
 */
public class RemoveDuplicatesFromSortedArray extends AbstractCustomTestRunner {
	
    public int _removeDuplicates(int[] nums) {
    	int uIdx = 0;
        for (int idx = 1; idx < nums.length; idx ++)
            if (nums [idx] > nums [uIdx]) nums [++ uIdx] = nums [idx];
        return uIdx + 1;
    }
   
}
