package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Repeat and Missing Number Array
 * 
 * Please Note:
 * 	There are certain problems which are asked in the interview to also check how you take care of overflows in your problem.
 * 	This is one of those problems.
 * 	Please take extra care to make sure that you are type-casting your ints to long properly and at all places. 
 * 	Try to verify if your solution works if number of elements is as large as 105
 * 
 * Food for thought :
 * 	> Even though it might not be required in this problem, in some cases, you might be required to order the operations cleverly 
 * 		so that the numbers do not overflow.
 * 	> For example, if you need to calculate n! / k! where n! is factorial(n), one approach is to calculate factorial(n), factorial(k) 
 * 		and then divide them.
 * 	> Another approach is to only multiple numbers from k + 1 ... n to calculate the result.
 * 	> Obviously approach 1 is more susceptible to overflows.
 * 
 * You are given a read only array of n integers from 1 to n. Each integer appears exactly once except A which appears twice and B which 
 * is missing. Return A and B.
 *  
 * Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * 		 Note that in your output A should precede B.
 * 
 * Example:
 * 		Input:	[3 1 2 5 3]
 * 		Output:	[3, 4]
 * 			
 * 				A = 3, B = 4
 * 
 * link: https://www.interviewbit.com/problems/repeat-and-missing-number-array/
 * 
 * @author Hxkandwal
 */
public class RepeatAndMissingNumberArray extends AbstractCustomTestRunner {
	
	private static RepeatAndMissingNumberArray _instance = new RepeatAndMissingNumberArray();

	public List<Integer> _repeatedNumber(final List<Integer> a) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
	    int actualsum = (a.size() * (a.size() + 1))/2;
	    int sum = 0;
	    for (int idx = 0; idx < a.size (); idx ++) sum += a.get (idx);
	    int aminusb = actualsum - sum;
	    
	    long actualsqsum = (a.size() * (a.size() + 1) * (2 * a.size() + 1))/6;
	    long sqsum = 0;
	    for (int idx = 0; idx < a.size (); idx ++) sqsum += (a.get (idx) * a.get (idx));
	    long asqminusbsq = actualsqsum - sqsum;
	    
	    long aplusb = asqminusbsq / aminusb;
	    ans.add ((int) (aplusb - aminusb) / 2);
	    ans.add ((int) (aminusb + aplusb) / 2);
	    return ans;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList(3, 1, 2, 5, 3), Arrays.asList(3, 4));
	}
	
	@SuppressWarnings("unchecked")
	public void runTest(final List<Integer> a, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a });
	
		for (Object answer : answers) 
			assertThat((List<Integer>) answer).isEqualTo(expectedOutput);
	
		System.out.println("ok!");
	}
	
}
