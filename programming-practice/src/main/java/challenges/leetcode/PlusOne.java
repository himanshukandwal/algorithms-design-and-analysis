package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 66. Plus One
 * 
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * 
 * The digits are stored such that the most significant digit is at the head of the list.
 * 
 * @author Hxkandwal
 *
 */
public class PlusOne extends AbstractCustomTestRunner {

	private static PlusOne _instance = new PlusOne();
	
	private PlusOne() {}
	
	public int[] plusOne(int[] digits) {
		int n = digits.length;
		for (int idx = n - 1; idx >= 0; idx --) {
			if (digits [idx] < 9) {
				digits [idx] ++;
				return digits;
			}
			digits [idx] = 0;
		}
		int [] ans = new int [n + 1];
		ans [0] = 1;
		return ans;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] {}, new int[] { 1 });
		_instance.runTest(null, new int[] { 1 });
		_instance.runTest(new int[] { 1 }, new int[] { 2 });
		_instance.runTest(new int[] { 1, 2 }, new int[] { 1, 3 });
		_instance.runTest(new int[] { 9 }, new int[] { 1, 0 });
	}

	public void runTest(final int[] digits, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { digits });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
