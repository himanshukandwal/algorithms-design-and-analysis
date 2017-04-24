package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * Flip
 * 
 * You are given a binary string(i.e. with characters 0 and 1) S consisting of characters S1, S2, …, SN. In a single operation, 
 * you can choose two indices L and R such that 1 ≤ L ≤ R ≤ N and flip the characters SL, SL+1, …, SR. By flipping, we mean change 
 * character 0 to 1 and vice-versa.
 * 
 * Your aim is to perform ATMOST one operation such that in final string number of 1s is maximised. If you don’t want to perform the 
 * operation, return an empty array. Else, return an array consisting of two elements denoting L and R. If there are multiple solutions, 
 * return the lexicographically smallest pair of L and R.
 * 
 * Notes:
 * 	- Pair (a, b) is lexicographically smaller than pair (c, d) if a < c or, if a == c and b < d.
 * 
 * For example,
 * 		S = 010
 * 
 * 		Pair of [L, R] | Final string
 * 		_______________|_____________
 * 		[1 1]          | 110
 * 		[1 2]          | 100
 * 		[1 3]          | 101
 * 		[2 2]          | 000
 * 		[2 3]          | 001
 * 
 * We see that two pairs [1, 1] and [1, 3] give same number of 1s in final string. So, we return [1, 1].
 * 
 * Another example,
 * 		If S = 111
 * 
 * 		No operation can give us more than three 1s in final string. So, we return empty array [].
 * 
 * @author Hxkandwal
 */
public class Flip extends AbstractCustomTestRunner {
	
	private static Flip _instance = new Flip();

	public List<Integer> _flip(String A) {
		Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        map.put (0, new ArrayList<>());
        
        int maxCount = 0, start = -1, count = 0;
        for (int idx = 0; idx < A.length(); idx ++) {
            char ch = A.charAt (idx);
            if (ch == '0') {
                if (start == -1) start = idx;
                count ++;
            } else if (-- count < 0) {  count = 0; start = -1;  }
            
            maxCount = Math.max (maxCount, count);
            
            if (count > 0 && !map.containsKey(count)) {
                ArrayList<Integer> ans = new ArrayList<Integer>();
                ans.add (start + 1);
                ans.add (idx + 1);
                map.put (count, ans);
            }
        }
        return map.get (maxCount);
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("010", Arrays.asList(1, 1));
		_instance.runTest("111", Arrays.asList());
		_instance.runTest("1101", Arrays.asList(3, 3));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final String A, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { A });

		for (Object answer : answers)
			assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
