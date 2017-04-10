package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

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
	
	private static RemoveDuplicatesFromSortedArray _instance = new RemoveDuplicatesFromSortedArray();
	
	private RemoveDuplicatesFromSortedArray() {}
	
	// 2 cursors to manager unique and traversing motion.
    public int _removeDuplicates(int[] nums) {
    	if (nums == null || nums.length == 0) 
    		return 0;
    	
    	int uniqueElementsIndex = 0;
    	for (int idx = 0; idx < nums.length; idx ++) {
        	if (nums [idx] != nums [uniqueElementsIndex])
				nums [++ uniqueElementsIndex] = nums [idx];
        }
    	
    	return uniqueElementsIndex + 1;	
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] {1, 1, 2}, 2);
		_instance.runTest(new int[] {1, 2, 2}, 2);
		_instance.runTest(new int[] {1, 2, 3}, 3);
		_instance.runTest(new int[] {}, 0);
		_instance.runTest(null, 0);
	}
	
	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		StringBuilder sb = new StringBuilder();
		
		for (int idx = 0; nums != null && idx < nums.length; idx ++) 
			sb.append(nums [idx]).append(" ");
		
		System.out.println(sb.toString() + " : ok!");
	}
	
}
