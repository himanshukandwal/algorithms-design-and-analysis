package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * You are given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to set all bits between i and j 
 * in N equal to M (e.g., M becomes a substring of N located at i and starting at j).
 * 
 * Example :		
 * 
 * 		Input: N = 10000000000, M = 10101, i = 2, j = 6 
 * 		Output: N = 10001010100
 * 
 * @author Hxkandwal
 */
public class UpdateBits extends AbstractCustomTestRunner {
	
	private static UpdateBits _instance = new UpdateBits();
	
	public UpdateBits() {}
	
	public int _update (int n, int m, int start, int end) {
		int res = 0;
		for (int idx = 0; idx < start; idx ++)  res |= ((n >>> idx) & 1) << idx;
		for (int idx = start; idx <= end; idx ++)  res |= ((m >>> (idx - start)) & 1) << idx;
		for (int idx = end + 1; idx < 32; idx ++)  res |= ((n >>> idx) & 1) << idx;
		return res;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(Integer.valueOf("10000000000", 2), Integer.valueOf("10101", 2), 2, 6, Integer.valueOf("10001010100", 2));
	}

	public void runTest(final int n, final int m, final int start, final int end, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n, m, start, end });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	} 	

}
