package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 31. Next Permutation
 * 
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place, do not allocate extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 
 * 	1,2,3 → 1,3,2
 * 	3,2,1 → 1,2,3
 * 	1,1,5 → 1,5,1
 * 
 * @author Hxkandwal
 */
public class NextPermutation extends AbstractCustomTestRunner {
	
	private static NextPermutation _instance = new NextPermutation();
	
	private NextPermutation() {}
	
    public int[] _nextPermutation(int[] nums) {
    	int idx = nums.length - 1;
    	for (; idx >= 1; idx --) if (nums [idx] > nums [idx - 1]) break;
    	
    	if (idx == 0) {
    		for (int jdx = 0; jdx < nums.length / 2; jdx ++) {
    			int t = nums [nums.length - 1 - jdx];
    			nums [nums.length - 1 - jdx] = nums [jdx];
    			nums [jdx] = t;
    		}
    	} else {
	    	for (int oIdx = nums.length - 1; oIdx >= idx; oIdx --) 
	    		if (nums [oIdx] > nums [idx - 1]) {
	    			int t = nums [oIdx];
	    			nums [oIdx] = nums [idx - 1];
	    			nums [idx - 1] = t;
	    			break;
	    		}
	    	
	    	for (int oIdx = 0; oIdx <= (nums.length - idx)/2; oIdx ++) {
	    		int t = nums [idx + oIdx];
	    		nums [idx + oIdx] = nums [nums.length - 1 - oIdx];
	    		nums [nums.length - 1 - oIdx] = t;
	    	}
    	}
    	
    	return nums;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2, 3 }, new int[] { 1, 3, 2 });
		_instance.runTest(new int[] { 1, 1, 5 }, new int[] { 1, 5, 1 });
		_instance.runTest(new int[] { 3, 2, 1 }, new int[] { 1, 2, 3 });
	}

	public void runTest(final int[] nums, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
