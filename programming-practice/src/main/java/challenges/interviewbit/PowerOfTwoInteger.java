package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Power Of Two Integers
 * 
 * Given a positive integer which fits in a 32 bit signed integer, find if it can be expressed as A^P 
 * where P > 1 and A > 0. A and P both should be integers.
 * 
 * Example
 * 	Input : 4
 * 	Output : True, as 2^2 = 4. 
 * 
 * @author Hxkandwal
 */
public class PowerOfTwoInteger extends AbstractCustomTestRunner {
	
	private static PowerOfTwoInteger _instance = new PowerOfTwoInteger();
	
	public boolean _isPower(int a) {
        if (a == 1) return true;
        for (int idx = 2; idx * idx <= a; idx ++) {
            double val = Math.log (a)/Math.log (idx);
            if ((val - (int) val) < 0.00000001) return true;
        }
        return false;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(4, true);
	}

	public void runTest(final int a, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
