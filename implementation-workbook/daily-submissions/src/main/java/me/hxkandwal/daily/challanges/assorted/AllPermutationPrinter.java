package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Write a program to print all permutations of a given Integer.
 * 
 * @author Hxkandwal
 */
public class AllPermutationPrinter extends AbstractCustomTestRunner {
	
	private static AllPermutationPrinter _instance = new AllPermutationPrinter();
	
	private AllPermutationPrinter() {}
	
	public static int _generatePuermuatations(int num) {
		char[] number = String.valueOf(num).toCharArray();
		int count = 1;
		
		System.out.println(String.valueOf(number));
		while (knuthLAlgorithmIteration(number)) {
			System.out.println(String.valueOf(number));
			count ++;
		}
		
		return count;
	}
	
	private static boolean knuthLAlgorithmIteration(char[] number) {
		int idx = number.length - 1;
		for (; idx >= 1; idx --) 
			if (number [idx] > number [idx - 1])
				break;
		
		if (idx == 0) return false;
			
		for (int revIdx = number.length - 1; revIdx >= idx; revIdx --) {
			if (number [revIdx] > number [idx - 1]) {
				char ch = number [revIdx];
				number [revIdx] = number [idx - 1];
				number [idx - 1] = ch;
				break;
			}
		}
		
		for (int revIdx = 0; revIdx < (number.length - idx)/2; revIdx ++) {
			char ch = number [idx + revIdx];
			number [idx + revIdx] = number [number.length - revIdx - 1];
			number [number.length - revIdx - 1] = ch;
		}
		
		return true;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(1234, 24);
		_instance.runTest(1223, 12);
	}

	public void runTest(final int input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}		

}
