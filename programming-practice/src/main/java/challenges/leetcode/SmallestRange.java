package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 632. Smallest Range
 * 
 * You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from 
 * each of the k lists. We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
 * 
 * Example 1:
 * 		Input	:	[[4, 10, 15, 24, 26], [0, 9, 12, 20], [5, 18, 22, 30]]
 * 		Output	: 	[20, 24]
 * 		Explanation:	
 * 					List 1: [4, 10, 15, 24, 26], 24 is in range [20, 24].
 * 					List 2: [0, 9, 12, 20], 20 is in range [20, 24].
 * 					List 3: [5, 18, 22, 30], 22 is in range [20, 24].
 * 
 * Note:
 * 	-	The given list may contain duplicates, so ascending order means >= here.
 * 	-	1 <= k <= 3500
 * 	-	-10^5 <= value of elements <= 10^5.
 * 	-	For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code 
 * 		template, you'll see this point.
 *  
 * @author Hxkandwal
 */
public class SmallestRange extends AbstractCustomTestRunner {
	
	private static SmallestRange _instance = new SmallestRange();

	public int[] _smallestRange(List<List<Integer>> nums) {
        int [] ans = { Integer.MIN_VALUE, Integer.MAX_VALUE };
        for (int val : nums.get (0)) {
            int start = val, end = val;
            
            for (int idx = 1; idx < nums.size(); idx ++) {
                int pos = Collections.binarySearch (nums.get (idx), val);
                if (pos >= 0) continue; else pos = -pos - 1;
                
                if (pos == 0) end = Math.max (end, nums.get (idx).get (pos));
                else if (pos == nums.get (idx).size ()) start = Math.min (start, nums.get (idx).get (pos - 1));
                else {
                	
                	if (nums.get (idx).get (pos) - val > val - nums.get (idx).get (pos - 1))
                		start = Math.min (start, nums.get (idx).get (pos - 1));
                	else end = Math.max (end, nums.get (idx).get (pos));
                }
            }
            
            if (Long.valueOf (end) - Long.valueOf (start) < Long.valueOf (ans [1]) - Long.valueOf (ans [0])) { 
                ans [0] = start;
                ans [1] = end;
            }
        }
        return ans;
    }

	// driver method
	public static void main(String[] args) {
//		_instance.runTest(Arrays.asList(Arrays.asList (4, 10, 15, 24, 26), 
//										Arrays.asList (0, 9, 12, 20), 
//										Arrays.asList (5, 18, 22, 30)), new int [] { 20, 24 });
		
		_instance.runTest(Arrays.asList(Arrays.asList (11, 38, 83, 84, 84, 85, 88, 89, 89, 92),
										Arrays.asList (28, 61, 89),
										Arrays.asList (52, 77, 79, 80, 81),
										Arrays.asList (21, 25, 26, 26, 26, 27), 
										Arrays.asList (9, 83, 85, 90), 
										Arrays.asList (84, 85, 87),
										Arrays.asList (26, 68, 70, 71), 
										Arrays.asList (36, 40, 41, 42, 45), 
										Arrays.asList (-34, 21),
										Arrays.asList (-28, -28, -23, 1, 13, 21, 28, 37, 37, 38), 
										Arrays.asList (-74, 1, 2, 22, 33, 35, 43, 45),
										Arrays.asList (54, 96, 98, 98, 99), 
										Arrays.asList (43, 54, 60, 65, 71, 75), 
										Arrays.asList (43, 46),
										Arrays.asList (50, 50, 58, 67, 69), 
										Arrays.asList (7, 14, 15), 
										Arrays.asList (78, 80, 89, 89, 90),
										Arrays.asList (35, 47, 63, 69, 77, 92, 94)), new int [] { 15, 84 });
	}
	
	public void runTest(final List<List<Integer>> nums, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });
		
		for (Object answer : answers) 
			assertThat((int []) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
