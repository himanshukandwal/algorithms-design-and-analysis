package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Return the last non-zero digit of n!.
 * 
 * Example :
 * 		For n = 5, the output should be lastNotZero(n) = 2 as 5! = 120
 * 
 * 		For n = 10, the output should be lastNotZero(n) = 8 as 10! = 3628800
 * 
 * @author Hxkandwal
 * 
 */
public class LastNotZero extends AbstractCustomTestRunner {

	private static LastNotZero _instance = new LastNotZero();
	
	private LastNotZero() {}
	
	// method : keep atleast 1e9 digits while multiplication.
	public int _lastNotZero(int n) {
		double digit = 1;
		for (int idx = 2; idx <= n; idx ++) {
			digit = (digit * idx) % 1e9;

			while (digit % 10 == 0)
				digit /= 10;
		}

		return (int) (digit % 10);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(5, 2);
		_instance.runTest(10, 8);
		_instance.runTest(12, 6);
		_instance.runTest(15, 8);
		_instance.runTest(7, 4);
		_instance.runTest(70, 8);
	}

	public void runTest(final int n, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
