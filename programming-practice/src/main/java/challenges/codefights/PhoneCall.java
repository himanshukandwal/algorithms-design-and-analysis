package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Some phone usage rate may be described as follows:
 * 
 * first minute of a talk costs min1 cents, each minute from the 2nd up to 10th
 * (inclusive) costs min2_10 cents each minute after 10th costs min11 cents.
 * 
 * You have s cents on your account before the call. What is the duration of the
 * longest call (in minutes rounded down to the nearest integer) you can have?
 * 
 * Example:
 * 
 * 		For min1 = 3, min2_10 = 1, min11 = 2 and s = 20, 
 * 		the output should be phoneCall(min1, min2_10, min11, s) = 14.
 * 
 * Here's why: 
 * 			the first minute costs 3 cents, which leaves you with 20 - 3 = 17 cents; 
 * 			the total cost of minutes 2 through 10 is 1 * 9 = 9, so you can talk 9 more minutes and still have 17 - 9 = 8 cents; 
 * 			each next minute costs 2 cents, which means that you can talk 8 / 2 = 4 more minutes.
 * 
 * 			Thus, the longest call you can make is 1 + 9 + 4 = 14 minutes long.
 * 
 * @author Hxkandwal
 *
 */
public class PhoneCall extends AbstractCustomTestRunner {

	private static PhoneCall _instance = new PhoneCall();

	private PhoneCall() {}

	public static int _phoneCall(int min1, int min2_10, int min11, int s) {
		int count = 0;
		while (s > 0) {
			s -= (count == 0) ? min1 : ((count >= 1 && count <= 9) ? min2_10 : min11);
			count ++;
		}
		
		return count - (s < 0 ? 1 : 0);
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(3, 1, 2, 20, 14);
		_instance.runTest(10, 1, 2, 22, 11);
	}

	public void runTest(int min1, int min2_10, int min11, int s, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { min1, min2_10, min11, s });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
