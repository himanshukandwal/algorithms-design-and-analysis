package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Given an array, find the subarray whose element count is maximum, making cumulative sum k.  
 * 
 * @author Hxkandwal
 *
 */
public class MaximalWidthSubarray extends AbstractCustomTestRunner {

	private static MaximalWidthSubarray _instance = new MaximalWidthSubarray();
	
	private MaximalWidthSubarray() {}
	
	public static int _maximalWidthSubarray(int[] a, int k) {
		int cumulativeSum = 0;
		int maxWidth = 0, windowStart = 0;

		for (int idx = 0; idx < a.length; idx++) {
			cumulativeSum += a[idx];

			if (cumulativeSum <= k)
				maxWidth = Math.max(maxWidth, idx - windowStart + 1);
			else
				cumulativeSum -= a[windowStart++];
		}
		return maxWidth;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] {1, 2, 3}, 2, 1);
		_instance.runTest(new int[] {1, 2, 3}, 3, 2);
		_instance.runTest(new int[] {1, 2, 3}, 5, 2);
		_instance.runTest(new int[] {1, 2, 3}, 6, 3);
		_instance.runTest(new int[] { 74, 659, 931, 273, 545, 879, 924, 710, 441, 166, 493, 43, 988, 504, 328, 730, 841,
				613, 304, 170, 710, 158, 561, 934, 100, 279, 817, 336, 98, 827, 513, 268, 811, 634, 980, 150, 580, 822,
				968, 673, 394, 337, 486, 746, 229, 92, 195, 358, 2, 154, 709, 945, 669, 491, 125, 197, 531, 904, 723,
				667, 550 }, 22337, 46);
	}

	public void runTest(final int[] array, final int k, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array, k });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
}
