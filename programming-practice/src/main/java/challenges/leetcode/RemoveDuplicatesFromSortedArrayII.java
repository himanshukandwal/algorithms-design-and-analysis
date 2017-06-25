package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 80. Remove Duplicates from Sorted Array II
 * 
 * Follow up for "Remove Duplicates": What if duplicates are allowed at most twice?
 * 
 * For example, Given sorted array nums = [1,1,1,2,2,3],
 * 
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. 
 * It doesn't matter what you leave beyond the new length.
 * 
 * @author Hxkandwal
 */
public class RemoveDuplicatesFromSortedArrayII extends AbstractCustomTestRunner {

	public int removeDuplicates(int[] nums) {
        int uIdx = -1;
        for (int idx = 0; idx < nums.length; idx ++) 
            if (idx < 2 || nums [idx] != nums [uIdx] || nums [idx] != nums [uIdx - 1]) 
                nums [++ uIdx] = nums [idx];
        return uIdx + 1;
    }
	
	public int removeDuplicatesOther (int[] nums) {
	    int idx = 0;
	    for (int n : nums) if (idx < 2 || n > nums[idx-2]) nums [idx ++] = n;
	    return idx;
	}
	
}
