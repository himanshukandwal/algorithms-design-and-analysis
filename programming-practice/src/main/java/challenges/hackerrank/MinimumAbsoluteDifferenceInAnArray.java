package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import challenges.AbstractCustomTestRunner;

/**
 * Minimum Absolute Difference in an Array
 * 
 * Given an array of  integers, find and print the minimum absolute difference between any two elements in the array.
 * 
 * Sample Input 0 :
 * 	3
 * 	3 -7 0
 * 
 * Sample Output 0 : 3
 * 
 * @author Hxkandwal
 */
public class MinimumAbsoluteDifferenceInAnArray extends AbstractCustomTestRunner {
	
	private static MinimumAbsoluteDifferenceInAnArray _instance = new MinimumAbsoluteDifferenceInAnArray();
	
	public int _findMin (int[] a) {
		Arrays.sort (a);
        int min = Integer.MAX_VALUE;
        for (int idx = 0; idx < a.length - 1; idx ++) min = Math.min (min, Math.abs(a [idx] - a [idx + 1]));
        return min;
	}

	// driver method
	public static void main(String[] args) throws FileNotFoundException {
		_instance.runTest(new int[] { 3, -7, 0 }, 3);
		
		testComplex("/src/test/resources/challenges/hackerrank/MinimumAbsoluteDifferenceInAnArray-1.txt");
    }

    private static void testComplex(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(System.getProperty("user.dir") + filename));

        int[] input = new int[sc.nextInt()];
        for (int idx = 0; idx < input.length; idx ++)
            input [idx] = sc.nextInt();

        _instance.runTest(input, sc.nextInt());

        sc.close();
    }

	public void runTest(final int[] a, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
		
}