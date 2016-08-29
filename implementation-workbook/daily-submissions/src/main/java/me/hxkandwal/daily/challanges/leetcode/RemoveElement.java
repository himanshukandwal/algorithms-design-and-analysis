package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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

	private static RemoveElement _instance = new RemoveElement();

	private RemoveElement() {}

	// method 1 : using sorting
	public int removeElement(int[] nums, int val) {
		if (nums == null || nums.length == 0)
			return 0;
		
		Arrays.sort(nums);
		
		int len = 0;
		for (int idx = 0, followingIdx = -1; idx < nums.length; idx ++) {
			if (nums [idx] == val)
				followingIdx = (followingIdx < 0) ? idx : followingIdx;
			else {
				if (followingIdx >= 0)
					nums [followingIdx ++] = nums [idx];
				
				len ++;
			}
		}
		
		return len;
	}

	//	method 2 : This problem can be solve by using two indices.
	public int _removeElementNoSorting(int[] A, int elem) {
	    int i=0;
	    int j=0;
	 
	    while (A != null && j < A.length) {
	        if (A[j] != elem) {
	            A[i] = A[j];
	            i++; 
	        }
	 
	        j++;
	    }
	 
	    return i;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 3, 2, 2, 3 }, 3, 2);
		_instance.runTest(new int[] {}, 3, 0);
		_instance.runTest(null, 3, 0);
		_instance.runTest(new int[] { 1, 2, 3, 4 }, 1, 3);
		_instance.runTest(new int[] { 2, 2, 3 }, 2, 1);
	}

	public void runTest(final int[] nums, final int val, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, val });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		StringBuilder sb = new StringBuilder();
		
		for (int idx = 0; nums != null && idx < nums.length; idx ++) 
			sb.append(nums [idx]).append(" ");
		
		System.out.println(sb.toString() + " : ok!");		
	}
	
}
