package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 190. Reverse Bits
 * 
 * Reverse bits of a given 32 bits unsigned integer.
 * 
 * For example, 
 * 		given input 43261596 (represented in binary as 00000010100101000001111010011100), 
 * 		return 964176192 (represented in binary as 00111001011110000010100101000000).
 * 
 * Follow up: If this function is called many times, how would you optimize it?
 * 
 * @author Hxkandwal
 */
public class ReverseBits extends AbstractCustomTestRunner {
	
	private static ReverseBits _instance = new ReverseBits();
	
	private ReverseBits() {}
	
	public static int _reverseBits(int n) {
		int result = 0;
		
		for (int pos = 31; pos >= 0; pos --)
			result += (n >>> pos & 1) << (31 - pos);
		
		return result;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(43261596, 964176192);
	}

	public void runTest(final int n, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
