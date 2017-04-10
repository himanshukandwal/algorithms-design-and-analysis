package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Have you ever heard of an IQ-address? For the given integer n, it is calculated as follows:
 * 
 * 1. Let result = "".
 * 2. If n = 1, prepend "1" to the beginning of result and return it as an answer.
 * 3. Prepend n % 10.5 to the beginning of result.
 * 4. Divide n by 2 with rounding up to the nearest integer.
 * 5. Go to step 2.
 * 
 * Given an integer n, your task is to return IQ-address generated from it.
 * 
 * Example:
 * 
 * For n = 21, the output should be iqAddress(n) = "12.03.06.00.50.0".
 * 
 * Here's why:
 * 		21% 10.5 = 0.0
 * 		11% 10.5 = 0.5
 * 		6 % 10.5 = 6.0
 * 		3 % 10.5 = 3.0
 * 		2 % 10.5 = 2.0
 * 
 * Thus, the answer is "1"+"2.0"+"3.0"+"6.0"+"0.5"+"0.0" = "12.03.06.00.50.0".
 * 
 * @author Hxkandwal
 *
 */
public class IQAddress extends AbstractCustomTestRunner {
	
	private static IQAddress _instance = new IQAddress();
	
	private IQAddress() {}
	
	public static String _iqAddress(int n) {
		return (n > 1) ? _iqAddress((n % 2 == 0 ? n/2 : (n + 1)/ 2)) + n % 10.5 : "1";
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(21, "12.03.06.00.50.0");
	}

	public void runTest(final int number, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { number });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
