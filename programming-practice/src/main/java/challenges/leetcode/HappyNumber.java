package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 202. Happy Number
 * 
 * Write an algorithm to determine if a number is "happy".
 * 
 * A happy number is a number defined by the following process:
 *  
 * 		Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process 
 * 		until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. 
 * 
 * Those numbers for which this process ends in 1 are happy numbers.
 * 
 * Example: 19 is a happy number
 * 			12 + 92 = 82
 * 			82 + 22 = 68
 * 			62 + 82 = 100
 * 			12 + 02 + 02 = 1
 * 
 * @author Hxkandwal
 *
 */
public class HappyNumber extends AbstractCustomTestRunner {
	
	private static HappyNumber _instance = new HappyNumber();
	
	public boolean _isHappyFloydCycleDetectionAlgorithm(int n) {
		int slow = sqrtsum (n), fast = sqrtsum (slow);
        while (fast != slow) {
            slow = sqrtsum (slow);
            fast = sqrtsum (sqrtsum (fast));
        }
        return slow == 1;
    }

    private int sqrtsum (int num) {
        int sum = 0;
        while (num > 0) {
            sum += (num % 10) * (num % 10);
            num /= 10;
        }
        return sum;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(19, true);
		_instance.runTest(2, false);
		_instance.runTest(7, true);
	}

	public void runTest(final int n, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
}
