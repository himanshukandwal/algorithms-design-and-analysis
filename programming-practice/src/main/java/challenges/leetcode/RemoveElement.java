package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 27. Remove Element
 * 
 * Given an array and a value, remove all instances of that value in place and return the new length. 
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * 
 * Example:
 * 	Given input array nums = [3,2,2,3], val = 3
 * 	Your function should return length = 2, with the first two elements of nums being 2.
 * 
 * @author Hxkandwal
 *
 */
public class RemoveElement extends AbstractCustomTestRunner {

	// method 1 : using sorting
	public int removeElement(int[] nums, int val) {
		int uIdx = -1;
        for (int idx = 0; idx < nums.length; idx ++) if (nums [idx] != val) nums [++ uIdx] = nums [idx];
        return uIdx + 1;
	}
	
}
