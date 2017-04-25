package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Rearrange Array
 * 
 * Rearrange a given array so that Arr[i] becomes Arr[Arr[i]] with O(1) extra space.
 * 
 * Example:
 * 	Input  : [1, 0]	
 * 	Return : [0, 1]
 * 
 * @author Hxkandwal
 */
public class RearrangeArray extends AbstractCustomTestRunner {
	
	private static RearrangeArray _instance = new RearrangeArray();

	/**
	 * If you had extra space to do it, the problem will be very easy. Store a copy of Arr in B.
	 * And then for every element, do Arr[i] = B[B[i]]
	 * 
	 * Lets restate what we just said for extra space :
	 * If we could somehow store 2 numbers in every index ( that is, Arr[i] can contani the old value, and the new value somehow ), 
	 * then the problem becomes very easy.
	 * 
	 * 		NewValue of Arr[i] = OldValue of Arr[OldValue of Arr[i]]
	 * 
	 * Now, we will do a slight trick to encode 2 numbers in one index.
	 * This trick assumes that N * N does not overflow.
	 * 
	 * 	1) Increase every Array element Arr[i] by (Arr[Arr[i]] % n)*n.
	 * 	2) Divide every element by N.
	 * 
	 * Given a number as
	 * A = B + C * N 	if  B, C < N 
	 * A % N = B A / N = C 
	 * 
	 * We use this fact to encode 2 numbers into each element of Arr.
	 */
	public List<Integer> _arrangeFaster(List<Integer> a) {
		for (int idx = 0; idx < a.size(); idx ++) {
			int val = a.get(idx);
			a.set(idx, val + (a.get(val) % a.size()) * a.size());
		}
		for (int idx = 0; idx < a.size(); idx ++)  a.set(idx, a.get(idx)/a.size());
		return a;
	}
	
	// O (n) memory
	public List<Integer> arrange(List<Integer> a) {
		for (int idx = 0; idx < a.size(); idx ++)  if (a.get(idx) >= 0) reversal (a, idx, idx);
		for (int idx = 0; idx < a.size(); idx ++)  a.set(idx, a.get(idx) == Integer.MIN_VALUE ? 0 : -a.get(idx));
	    return a;
	}
	
	private void reversal (List<Integer> a, int current, int start) {
	    int next = a.get (current);
	    int replace = a.get (next);
	    if (next != start) reversal (a, next, start);
	    a.set (current, (replace == 0 ? Integer.MIN_VALUE : -replace));
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList(1, 2, 7, 0, 9, 3, 6, 8, 5, 4), Arrays.asList(2, 7, 8, 1, 4, 0, 6, 5, 3, 9));
		_instance.runTest(Arrays.asList(4, 0, 2, 1, 3), Arrays.asList(3, 4, 2, 0, 1));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final List<Integer> a, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a });

		for (Object answer : answers)
			assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
