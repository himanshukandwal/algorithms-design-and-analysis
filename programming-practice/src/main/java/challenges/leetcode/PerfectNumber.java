package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 507. Perfect Number
 * 
 * We define the Perfect Number is a positive integer that is equal to the sum of all its 
 * positive divisors except itself.
 * 
 * Now, given an integer n, write a function that returns true when it is a perfect number 
 * and false when it is not.
 * 
 * Example:
 * 		Input: 28
 * 		Output: True
 * 		
 * 		Explanation: 28 = 1 + 2 + 4 + 7 + 14
 * 
 * Note: The input number n will not exceed 100,000,000. (1e8)
 * 
 * @author Hxkandwal
 */
public class PerfectNumber extends AbstractCustomTestRunner {
	
	private static PerfectNumber _instance = new PerfectNumber();
	
	public boolean _checkPerfectNumber(int num) {
        if (num <= 1) return false;
        int sum = 1;
        for (int idx = 2; idx * idx <= num; idx ++) if (num % idx == 0) sum += idx + num / idx;
        return num == sum;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(28, true);
		_instance.runTest(29, false);
	}

	public void runTest(final int num, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { num });

		for (Object answer : answers)
			assertThat((boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
