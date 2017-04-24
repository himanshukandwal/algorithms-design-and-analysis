package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Prime Sum
 * 
 * Given an even number ( greater than 2 ), return two prime numbers whose sum will be equal to given number.
 * 
 * NOTE: A solution will always exist. read Goldbach’s conjecture.
 * Example:
 * 		Input : 4
 * 		Output: 2 + 2 = 4
 * 				If there are more than one solutions possible, return the lexicographically smaller solution.
 * 
 * If [a, b] is one solution with a <= b, and [c,d] is another solution with c <= d, then [a, b] < [c, d]
 * If a < c OR a==c AND b < d. 
 * 
 * @author Hxkandwal
 */
public class PrimeSum extends AbstractCustomTestRunner {

	private static PrimeSum _instance = new PrimeSum();
	
	public List<Integer> _primesum(int a) {
        boolean [] pr = new boolean [a];
        Arrays.fill(pr, true);
        for (int idx = 2; idx < a; idx ++) {
            if (pr [idx])
                for (int iidx = idx + idx; iidx < a; iidx += idx) pr [iidx] = false;
        }
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int idx = 2; idx < a; idx ++) {
            if (pr [idx] && pr [a - idx]) {
                ans.add (idx);
                ans.add (a - idx);
                break;
            }
        }
        return ans;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(4, Arrays.asList(2, 2));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final int a, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a });

		for (Object answer : answers)
			assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
