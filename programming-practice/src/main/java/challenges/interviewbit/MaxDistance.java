package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Max Distance
 * 
 * Given an array A of integers, find the maximum of j - i subjected to the constraint of A[i] <= A[j].
 * If there is no solution possible, return -1.
 * 
 * Example :
 * 	A : [3 5 4 2]
 * 	Output : 2 for the pair (3, 4)
 * 
 * link: https://www.interviewbit.com/problems/max-distance/ 
 * 
 * @author Hxkandwal
 */
public class MaxDistance extends AbstractCustomTestRunner {
	
	private static MaxDistance _instance = new MaxDistance();

	public int _maximumGap(final List<Integer> a) {
		int max = Integer.MIN_VALUE;
	    List<Integer> incrList = new ArrayList<>();
	    for (int idx = a.size() - 1; idx >= 0; idx --) {
	    	if (incrList.isEmpty() || a.get (incrList.get(incrList.size() - 1)) < a.get(idx)) incrList.add (idx); 
	    	else {
	    	    int low = 0, high = incrList.size() - 1;
	    	    while (low < high) {
	    	        int mid = (low + high) >>> 1;
	    	        if (a.get (incrList.get (mid)) > a.get (idx)) high = mid - 1;
	    	        else low = mid + 1;
	    	    }
	    	    if (a.get (incrList.get (low)) < a.get(idx)) low ++;
	    	    max = Math.max (max, incrList.get (low) - idx);
	    	}
	    }
	    return (max == Integer.MIN_VALUE ? 0 : max);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList(-1, -1, 2), 2);
		_instance.runTest(Arrays.asList(3, 5, 4, 2), 2);
	}
	
	public void runTest(final List<Integer> a, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
		
}
