package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Largest Number
 * 
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * 
 * For example:
 * 		Given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * 
 * @author Hxkandwal
 */
public class LargestNumber extends AbstractCustomTestRunner {
	
	private static LargestNumber _instance = new LargestNumber();

	public String _largestNumber(final List<Integer> a) {
		List<String> strs = new ArrayList<>();
		for (Integer ai : a) strs.add (ai.toString());
		Collections.sort(strs, (x, y) -> (x + y).compareTo(y + x) >= 0 ? -1 : 1);
		
		StringBuilder ans = new StringBuilder ();
	    for (String str : strs) ans.append(str);
	    return ans.toString();
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList(3, 30, 34, 5, 9), "9534330");
		_instance.runTest(Arrays.asList(472, 663, 964, 722, 485, 852, 635, 4, 368, 676, 319, 412), "9648527226766636354854724412368319");
	}

	public void runTest(final List<Integer> a, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
