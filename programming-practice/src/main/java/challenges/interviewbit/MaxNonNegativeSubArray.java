package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Max Non Negative SubArray
 * 
 * Find out the maximum sub-array of non negative numbers from an array.
 * The sub-array should be continuous. That is, a sub-array created by choosing the second and fourth element 
 * and skipping the third element is invalid.
 * 
 * Maximum sub-array is defined in terms of the sum of the elements in the sub-array. Sub-array A is greater than 
 * sub-array B if sum(A) > sum(B).
 * 
 * Example:
 * 		A : [1, 2, 5, -7, 2, 3]
 * 		
 * 		The two sub-arrays are [1, 2, 5] [2, 3].
 * 		The answer is [1, 2, 5] as its sum is larger than [2, 3]
 * 
 * link: https://www.interviewbit.com/problems/max-non-negative-subarray
 * 
 * @author Hxkandwal
 */
public class MaxNonNegativeSubArray extends AbstractCustomTestRunner {
	
	private static MaxNonNegativeSubArray _instance = new MaxNonNegativeSubArray();

	public List<Integer> _maxset(List<Integer> a) {
		List<Integer> ans = new ArrayList<>();
	    int start = -1;
	    long prevSum = -1, currSum = -1;
	    
	    for (int idx = 0; idx < a.size(); idx ++) {
	        int ai = a.get (idx);
	        if (ai >= 0) {
	            if (start < 0) { currSum = ai; start = idx; }
	            else currSum += ai;
	        } else if (ai < 0 && start >= 0) {
	            if (currSum > prevSum) {
	                prevSum = currSum;
	                ans.clear();
	                for (int jdx = start; jdx < idx; jdx ++) ans.add (a.get (jdx));
	            }
	            start = -1;
	        }
	    }
	    
	    if (currSum > prevSum) { 
	    	ans.clear();
            for (int jdx = start; jdx < a.size(); jdx ++) ans.add (a.get (jdx));
	    }
	    
	    return ans;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList(1, 2, 5, -7, 2, 3), Arrays.asList(1, 2, 5));
		_instance.runTest(Arrays.asList(756898537, -1973594324, -2038664370, -184803526, 1424268980), Arrays.asList(1424268980));
		_instance.runTest(Arrays.asList(24115, -75629, -46517, 30105, 19451, -82188, 99505, 6752, -36716, 54438, -51501,
				83871, 11137, -53177, 22294, -21609, -59745, 53635, -98142, 27968, -260, 41594, 16395, 19113, 71006,
				-97942, 42082, -30767, 85695, -73671), Arrays.asList(41594, 16395, 19113, 71006));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final List<Integer> a, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a });

		for (Object answer : answers)
			assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
