package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 342. Power of Four
 * 
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * 
 * Example: Given num = 16, return true. 
 * 			Given num = 5, return false.
 * 
 * Follow up: Could you solve it without loops/recursion?
 * 
 * @author Hxkandwal
 *
 */
public class PowerOfFour extends AbstractCustomTestRunner {
	
	private static PowerOfFour _instance = new PowerOfFour();
	
	public static boolean _isPowerOfFour(int num) {
		int onesCount = 0, zerosCount = 0;
		
		while (num > 0) {
			if (num % 2 == 1) onesCount ++;
			else zerosCount ++;
			num /= 2;
		}
		
		return (onesCount == 1 && zerosCount % 2 == 0);
	}

	public static boolean _isPowerOfFour2(int num) {
		//0x55555555 is to get rid of those power of 2 but not power of 4
        //so that the single 1 bit always appears at the odd position
		return num > 0 && (num & (num-1)) == 0 && (num & 0x55555555) != 0;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(16, true);
		_instance.runTest(4, true);
		_instance.runTest(5, false);
	}

	public void runTest(final int x, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { x });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
