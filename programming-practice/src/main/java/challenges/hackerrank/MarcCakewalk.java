package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Marc's Cakewalk
 * 
 * Print a long integer denoting the minimum number of miles Marc must walk to maintain his weight.
 * 
 * Sample Input 0
 * 		3
 * 		1 3 2
 * 
 * Sample Output 0
 * 		11
 * 
 * @author Hxkandwal
 */
public class MarcCakewalk extends AbstractCustomTestRunner {
	
	private static MarcCakewalk _instance = new MarcCakewalk();
	
	public String _calculateCalories(int[] calories) {
		 Arrays.sort (calories);
		 long sum = 0;
		 for (int idx = calories.length - 1; idx >= 0; idx --)
			 sum += calories [idx] * Math.pow (2, calories.length - 1 - idx);
		 return String.valueOf(sum);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 3, 2 }, "11");
		_instance.runTest(new int [] { 819, 701, 578, 403, 50, 400, 983, 665, 510, 523, 696, 532, 51, 449,
									   333, 234, 958, 460, 277, 347, 950, 53, 123, 227, 646, 190, 938, 61,
									   409, 110, 61, 178, 659, 989, 625, 237, 944, 550, 954, 439 }, "59715404338867");
	}

	public void runTest(final int[] calories, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { calories });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
