package me.hxkandwal.daily.challanges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Sherlock and Array
 *
 * Watson gives Sherlock an array A of length N.
 * Then he asks him to determine if there exists an element in the array such that the sum of the elements on its left
 * is equal to the sum of the elements on its right. If there are no elements to the left/right, then the sum is considered
 * to be zero.
 *
 * Formally, find an i, such that, A1 + A2 + ... + Ai-1 = Ai+1 + Ai+2 + .... + AN.
 *
 * Exmaple :
 *          1 2 3
 * Output : NO
 *
 *          1 2 3 3
 * Output : YES
 *
 * Created by Hxkandwal
 *
 */
public class SherlockAndTheArray extends AbstractCustomTestRunner {

    private static SherlockAndTheArray _instance = new SherlockAndTheArray();

    private SherlockAndTheArray() {}

	public static String _isElementPresent(int[] input) {
		long totalSum = 0, runningSum = 0;
		for (int idx = 0; idx < input.length; idx++)
			totalSum += input[idx];

		for (int idx = 0; idx < input.length; idx++) {
			if (runningSum == totalSum - runningSum - input[idx])
				return "YES";

			runningSum += input[idx];
		}

		return "NO";
	}

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int[] { 1, 2, 3 } , "NO");
        _instance.runTest(new int[] { 1, 2, 3, 3 } , "YES");
    }

    public void runTest(final int[] input, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
