package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
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
		StringBuilder org = new StringBuilder ();
	    for (Integer ai : a) org.append(ai.toString());
	    return permutation(a, 0, org.toString());
	}

	private String permutation (List<Integer> a, int start, String best) {
		if (start >= a.size()) {
			StringBuilder itr = new StringBuilder ();
		    for (Integer ai : a) itr.append(ai.toString());
			return (best.compareTo(itr.toString()) < 0 ? itr.toString() : best);
		}
		for (int idx = start; idx < a.size(); idx ++) {
			int startVal = a.get(start);
			a.set(start, a.get(idx));
			a.set(idx, startVal);
			best = permutation(a, start + 1, best);
			startVal = a.get(start);
			a.set(start, a.get(idx));
			a.set(idx, startVal);
		}
		return best;
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
