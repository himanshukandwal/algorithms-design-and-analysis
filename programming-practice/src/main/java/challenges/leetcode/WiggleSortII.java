package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 324. Wiggle Sort II
 * 
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * 
 * Example:
 * 		(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * 
 *  	(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 *  
 * Note: You may assume all input has valid answer.
 * 
 * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 * 
 * @author Hxkandwal
 */
public class WiggleSortII extends AbstractCustomTestRunner {
	
	private static WiggleSortII _instance = new WiggleSortII();

	public int[] _wiggleSort(int[] nums) {
		for (int idx = 0; idx < nums.length - 1; idx ++) {
			if (nums [idx] == nums [idx + 1]) {
    			int next = idx + 2;
				while (next < nums.length && nums [next] == nums [idx]) next ++;
    			if (next >= nums.length) return nums;
    			else swap (nums, idx + 1, next);
    		}
			
			if ((idx % 2 == 1) == (nums[idx] < nums[idx + 1])) swap (nums, idx, idx + 1);
    	}
        return nums;
    }
    
    private void swap (int [] nums, int to, int from) {
        int val = nums [from];
        nums [from] = nums [to];
        nums [to] = val;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 3, 2, 2, 3, 1 }, new int[] { 1, 3, 2, 3, 1, 2 });
		_instance.runTest(new int[] { 4, 5, 5, 6 }, new int[] { 5, 6, 4, 5 });	
		_instance.runTest(new int[] { 1, 2, 2, 1, 2, 1, 1, 1, 1, 2, 2, 2 }, new int[] { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 });
	}

	public void runTest(final int[] nums, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((int []) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
