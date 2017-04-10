package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import challenges.AbstractCustomTestRunner;

/**
 * 396. Rotate Function
 * 
 * Given an array of integers A and let n to be its length.
 * 
 * Assume Bk to be an array obtained by rotating the array A k positions clock-wise  we define a "rotation function" F on 
 * A as follow:
 * 
 * 		F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
 * 
 * Calculate the maximum value of F(0)  F(1)  ...  F(n-1).
 * 
 * Note: n is guaranteed to be less than 10^5.
 * 
 * Example:
 * 
 * A = [4  3  2  6]
 * 
 * 		F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * 		F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * 		F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 * 		F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 * 
 * So the maximum value of F(0)  F(1)  F(2)  F(3) is F(3) = 26.
 * 
 * @author Hxkandwal
 *
 */
public class RotateFunction extends AbstractCustomTestRunner {
	
	private static RotateFunction _instance = new RotateFunction();
	
	private RotateFunction() {}

	// slow O(n^2) answer.
	public int _maxRotateFunction(int[] A) {
		Integer maxValue = null;
		
		for (int rotation = 0; rotation < A.length; rotation ++) {
			int value = 0;
			for (int idx = 0; idx < A.length; idx ++)
				value += ((idx + rotation) % A.length) * A [idx];
			
			if (maxValue == null) 
				maxValue = value;
			else
				maxValue = Math.max (maxValue, value);
		}
		
		return (maxValue == null) ? 0 : maxValue;
	}
	
	// better answer O(n) : https://discuss.leetcode.com/topic/58616/java-solution-o-n-with-non-mathametical-explaination
	public int _maxRotateFunction2(int[] A) {
		Integer maxValue = null;
		
		int totalSum = 0;
		for (int idx = 0; idx < A.length; idx ++)
			totalSum += A [idx];
		
		Integer previousEstimation = null;
		for (int idx = 0; idx < A.length; idx ++) {
			if (previousEstimation == null) {
				previousEstimation = 0;
				for (int innerIdx = 0; innerIdx < A.length; innerIdx ++) 
					previousEstimation += innerIdx * A [innerIdx];
			} else
				previousEstimation = previousEstimation - totalSum + A.length * A [idx - 1];
			
			maxValue = (maxValue == null) ? previousEstimation : Math.max(maxValue, previousEstimation);
		}
		
		return (maxValue == null) ? 0 : maxValue;
	}

    
	// driver method
	public static void main(String[] args) throws FileNotFoundException {
		_instance.runTest(new int[] { 4, 3, 2, 6 },  26);
		_instance.runTest(new int[] { }, 0);
		_instance.runTest(new int[] { -2147483648, -2147483648 },  -2147483648);
		
        testComplex ("/src/test/resources/me/hxkandwal/daily/challanges/leetcode/RotateFunction-1.txt", 1491052486);
    }

    private static void testComplex(String filename, final int expectedOutput) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(System.getProperty("user.dir") + filename));

        int[] input = new int [sc.nextInt()];
        for (int idx = 0; idx < input.length; idx ++) 
        	input [idx] = sc.nextInt();

        _instance.runTest(input, expectedOutput);

        sc.close();
    }
	
	public void runTest(final int[] A,  final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { A });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
