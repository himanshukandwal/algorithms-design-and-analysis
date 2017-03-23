package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 413. Arithmetic Slices
 * 
 * A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any 
 * two consecutive elements is the same.
 * 
 * For example, these are arithmetic sequence:
 * 		1, 3, 5, 7, 9
 * 		7, 7, 7, 7
 * 		
 * 		3, -1, -5, -9
 * 
 * The following sequence is not arithmetic.
 * 		1, 1, 2, 5, 7
 * 
 * @author Hxkandwal
 */
public class ArithmeticSlices extends AbstractCustomTestRunner {
	
	private static ArithmeticSlices _instance = new ArithmeticSlices();

	public int _numberOfArithmeticSlices(int[] A) {
		int curr = 0, sum = 0;
		for (int idx = 2; idx < A.length; idx++) {
			if (A[idx] - A[idx - 1] == A[idx - 1] - A[idx - 2]) {
				curr++;
				sum += curr;
			} else curr = 0;
		}
		return sum;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 2, 3, 4 }, 3);
	}

	public void runTest(final int[] A, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { A });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
