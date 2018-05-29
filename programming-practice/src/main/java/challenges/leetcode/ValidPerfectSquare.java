package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 367. Valid Perfect Square
 * 
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * 
 * Note: Do not use any built-in library function such as sqrt.
 * 
 * Example 1:
 * 		Input: 16
 * 		Returns: True
 * 		Example 2:
 * 
 * 		Input: 14
 * 		Returns: False
 * 
 * @author Hxkandwal
 */
public class ValidPerfectSquare extends AbstractCustomTestRunner {
	
	private static ValidPerfectSquare _instance = new ValidPerfectSquare();
	
	private ValidPerfectSquare() {}
	
	/**
	 * Newtons method (converging quadratic function)
	 */
    public static boolean _isPerfectSquarePerfectAnswer(int x) {
    	long r = x;
    	while (r * r > x) r = (r + x/r) / 2;
    	return (r * r == x);
    }

	public boolean _isPerfectSquare(int num) {
		if (num == 1) return true;
		int l = 1, r = num;
		while (l <= r) {
			long m = l + (r - l)/2;
			if (m * m < num) l = (int) m + 1;
			else if (m * m > num) r = (int) m - 1;
			else return true;
		}
		return false;
	}
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(16, true);
		_instance.runTest(14, false);
		_instance.runTest(100, true);
		_instance.runTest(808201, true);
	}

	public void runTest(final int num, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { num });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}    

}
