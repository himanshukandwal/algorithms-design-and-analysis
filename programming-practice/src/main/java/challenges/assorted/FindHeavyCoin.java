package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Given an array of coins of same weight, find the single heavier coin.
 * 
 * @author Hxkandwal
 */
public class FindHeavyCoin extends AbstractCustomTestRunner {
	
	private static FindHeavyCoin _instance = new FindHeavyCoin();

	public int _find(int[] nums) {
		int [] dp = new int [2];
		int len = 0;
		for (int num : nums) {
			int idx = Arrays.binarySearch(dp, 0, len, num);
			if (idx < 0) idx = - (idx + 1);
			dp [idx] = num;
			if (idx == len) len ++;
		}
		return dp [1];
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 5, 8 }, 8);
		_instance.runTest(new int[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 8 }, 8);
	}

	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
