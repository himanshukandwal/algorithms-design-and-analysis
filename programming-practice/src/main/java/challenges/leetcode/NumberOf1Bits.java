package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 191. Number of 1 Bits
 * 
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
 * 
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
 * 
 * @author Hxkandwal
 */
public class NumberOf1Bits extends AbstractCustomTestRunner {
	
	private static NumberOf1Bits _instance = new NumberOf1Bits();
	
	private NumberOf1Bits() {}
	
    public static int _hammingWeight(int n) {
    	int count = 0;
        for (int idx = 31; idx >= 0; idx --) count += (n >> idx & 1);
        return count;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(11, 3);
	}

	public void runTest(final int n, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
