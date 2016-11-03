package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Given array with positive and negative values, return the maximum alternating slice size, 
 * two elements are alternating if they have different signs, zero is treating as both negative 
 * and positive.
 * 
 * Example :
 * 
 * (a)	Input 	: [ 1, -5, 23, 0, 1, 1, 0, 2, -5, 3, -1, 1, 2, 3 ]
 * 		Output 	: 7 
 * 
 * 		The sequence [ 1, 0, 2, -5, 3, -1, 1 ] has maximum alternating slice size.
 * 
 * (b)	Input 	: [ -9, -1, 2, 0, 5, -6, 7, -8, 0, -1 ]
 * 		Output 	: 8 
 * 
 * 		The sequence [ -1, 2, 0, 5, -6, 7, -8, 0, -1  ] has maximum alternating slice size.
 *  
 * 		The expected runtime is o(n).
 * 
 * @author Hxkandwal
 *
 */
public class MaximumAlternatingSliceSize extends AbstractCustomTestRunner {

	private static MaximumAlternatingSliceSize _instance = new MaximumAlternatingSliceSize();
	
	private MaximumAlternatingSliceSize() {}
	
	public static int _findMaximumSlice (int[] array) {
		int start = 0, max = 0;
		
		boolean foundNegative = false;
		for (int idx = 0; idx < array.length; idx ++) {
			if (foundNegative) {
				if (array [idx] < 0) 
					start = idx;
				else
					foundNegative = !foundNegative;
			} else {
				if (array [idx] > 0) 
					start = idx;
				else
					foundNegative = !foundNegative;
			}
			max = Math.max(max, idx - start + 1);
		}
		return max;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, -5, 23, 0, 1, 1, 0, 2, -5, 3, -1, 1, 2, 3 }, 7);
		_instance.runTest(new int [] { -9, -1, 2, 0, 5, -6, 7, -8, 0, -1 }, 9);	
	}
	
	public void runTest(final int[] input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers) 
			assertThat((int) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
