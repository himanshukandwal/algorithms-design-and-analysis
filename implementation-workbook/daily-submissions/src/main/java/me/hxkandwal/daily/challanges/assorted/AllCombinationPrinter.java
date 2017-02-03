package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Write a program to print all combinations (k) of a given Integer.
 * 
 * @author Hxkandwal
 */
public class AllCombinationPrinter extends AbstractCustomTestRunner {
	
	private static AllCombinationPrinter _instance = new AllCombinationPrinter();
	
	private AllCombinationPrinter() {}
	
	public static int _generateCombinations(int num, int k) {
		if (num == 0)
			return num;
		
		char[] number = String.valueOf(num).toCharArray();
		boolean[] seen = new boolean[number.length];
		
		for (int idx = 0; idx < seen.length; idx ++)
			seen [idx] = true;
		
		generateCombinations(number, seen, 0, k);
		
		return 0;
	}

	private static void generateCombinations(char[] number, boolean[] seen, int idx, int k) {
		for (; idx < seen.length; idx ++) {
			seen [idx] = false;
			generateCombinations(number, seen, idx + 1, k);
			
			seen [idx] = true;
			generateCombinations(number, seen, idx + 1, k);
		}
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(1234, 2, 0);
	}

	public void runTest(final int input, final int k, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input, k });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}		
	
}
