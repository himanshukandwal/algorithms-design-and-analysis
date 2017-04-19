package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Largest Sum Contiguous Subarray
 * 
 * @author Hxkandwal
 *
 */
public class MaximalSumSubarray extends AbstractCustomTestRunner {
	
	private static MaximalSumSubarray _instance = new MaximalSumSubarray();
	
	// sliding window (MaximalWidthSubarray) works only on positive numbers, to handle totally negative numbers (test case 2), 
	// we used this algorithm 
	public static int _maxSubarraySum(int[] array) {
	    int maxSum = Integer.MIN_VALUE, localSum = 0;
	    
	    for (int idx = 0; idx < array.length; idx ++) {
			localSum = Math.max (array [idx], localSum + array [idx]);
			maxSum = Math.max (maxSum, localSum);
		}
		
	    return maxSum;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { -2, -3, 4, -1, -2, 1, 5, -3 }, 7);
		_instance.runTest(new int[] { -39, -69, -18, -45 }, -18);
		_instance.runTest(new int[] { -27, -36, 41, 11, -47, -32, 47, -56, -38, 57, -63, -41, 23, 41, 29, 78, 
				16, -65, 90, -58, -12, 6, -60, 42, -36, -52, -54, -95, -10, 29, 70, 50, -94, 1 }, 212);
	}

	public void runTest(final int[] array, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
