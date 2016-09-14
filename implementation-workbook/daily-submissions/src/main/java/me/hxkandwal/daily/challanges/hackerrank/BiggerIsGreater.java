package me.hxkandwal.daily.challanges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Bigger is Greater
 * 
 * Given a word w, rearrange the letters of w to construct another word s in such a way that s is 
 * lexicographically greater than w. 
 * 
 * In case of multiple possible answers, find the lexicographically smallest one among them.
 * 
 * Example :
 * 		ab
 * 		bb
 * 		hefg
 * 		dhck
 * 		dkhc
 * 
 * Result :
 * 		ba
 * 		no answer
 * 		hegf
 * 		dhkc
 * 		hcdk
 * 
 * Explanation :
 * 		
 * 	Test case 1: There exists only one string greater than ab which can be built by rearranging ab. That is ba.
 * 	Test case 2: Not possible to rearrange bb and get a lexicographically greater string.
 * 	Test case 3: hegf is the next string lexicographically greater than hefg.
 * 	Test case 4: dhkc is the next string lexicographically greater than dhck.
 * 	Test case 5: hcdk is the next string lexicographically greater than dkhc.
 * 
 * 
 * Points :
 * 	a) Knuth L algorithm
 * 	b) details :
 * 		lexicographically permutations algorithm : https://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order
 * 
 * @author Hxkandwal
 *
 */
public class BiggerIsGreater extends AbstractCustomTestRunner {

	private static BiggerIsGreater _instance = new BiggerIsGreater();
	
	private BiggerIsGreater() {}
	
	// method : run only 1 iteration of Knuth's L algorithm.
	public static String _getLexicographicallyGreaterString(String input) {
		char[] inputArr = input.toCharArray();
		
		int j = inputArr.length - 2;
		
		// find position where a[j] < a[j + 1]
		for (; j >= 0; j--) {
			if (inputArr[j] < inputArr[j + 1])
				break;
		}

		// base condition : j == 0, processing done. Return the array.
		if (j < 0)
			return "no answer";

		// partition the array : inputArr [0 .. j] and inputArr [j+1 .. n]
		for (int partition2reverseIndex = inputArr.length - 1; partition2reverseIndex >= (j
				+ 1); partition2reverseIndex--) {
			if (inputArr[partition2reverseIndex] > inputArr[j]) {
				swap(inputArr, j, partition2reverseIndex);
				break;
			}
		}

		// reverse the contents of second array : inputArr [j+1 .. n]
		for (int position = 0; position < (inputArr.length - j - 1) / 2; position++)
			swap(inputArr, j + 1 + position, inputArr.length - position - 1);

		return String.valueOf(inputArr);
	}

	/**
	 * a helper function to swap the contents of position1 and position2 within inputArr.
	 * 
	 * @param inputArr
	 * @param position1
	 * @param position2
	 */
	private static void swap (char[] inputArr, int position1, int position2) {
		char interimPlaceholder = inputArr [position1];
		inputArr [position1] = inputArr [position2];
		inputArr [position2] = interimPlaceholder;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("ab", "ba");
		_instance.runTest("bb", "no answer");
		_instance.runTest("hefg", "hegf");
		_instance.runTest("dhck", "dhkc");
		_instance.runTest("dkhc", "hcdk");
	}

	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
