package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 7. Reverse Integer
 * 
 * Reverse digits of an integer.
 * 
 * Example1: x = 123, return 321
 * 
 * Example2: x = -123, return -321
 * 
 * @author Hxkandwal
 *
 */
public class ReverseInteger extends AbstractCustomTestRunner {
	
	private static ReverseInteger _instance = new ReverseInteger();
	
	public int _reverse(int x) {
		long ans = 0;
        boolean isNeg = (x < 0);
        if (isNeg) x = -x;
        
        while (x > 0) {
            ans = 10 * ans + (x % 10);
            x /= 10;
        }
        
        if (ans > Integer.MAX_VALUE) return 0;
        else return ((int) ans * (isNeg ? -1 : 1));
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(123, 321);
		_instance.runTest(-123, -321);
		_instance.runTest(-1, -1);
		_instance.runTest(4, 4);
		_instance.runTest(10, 1);
		_instance.runTest(1534236469, 0);
		_instance.runTest(1000000009, 0);
	}
	
	public void runTest(final int x, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { x });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
