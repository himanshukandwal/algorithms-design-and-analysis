package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
    
    public static boolean _isPerfectSquare(int num) {
    	for (int n = num, idx = 2; idx <= Math.sqrt(num); idx ++) {
			while (n % idx == 0) n /= idx;
			if (n == 1)  return true;
			else n = num; 
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
