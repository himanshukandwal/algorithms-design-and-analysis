package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 646. Maximum Length of Pair Chain
 * 
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed 
 * in this fashion.
 * 
 * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. 
 * You can select pairs in any order.
 * 
 * Example 1:
 * 		Input: [[1,2], [2,3], [3,4]]
 * 		Output: 2
 * 		Explanation: The longest chain is [1,2] -> [3,4]
 * 
 * Note:
 * 	-	The number of given pairs will be in the range [1, 1000].
 * 
 * @author Hxkandwal
 */
public class MaximumLengthOfPairChain extends AbstractCustomTestRunner {
	
	private static MaximumLengthOfPairChain _instance = new MaximumLengthOfPairChain();
	
	// using exclusion principle
	public int findLongestChain(int[][] pairs) {
        Arrays.sort (pairs, (a, b) -> a [1] - b [1]);
        int sum = 0, idx = -1;
        while ( ++ idx < pairs.length) {
            sum ++;
            int end = pairs [idx][1];
            while (idx + 1 < pairs.length && pairs [idx + 1][0] <= end) idx ++;
        }
        return sum;
    }

	public int _findLongestChain(int[][] pairs) {
        Arrays.sort (pairs, (a, b) -> a [0] - b [0]);
        Integer [] dp = new Integer [pairs.length];
        int ans = 0;
        for (int idx = 0; idx < dp.length; idx ++) if (dp [idx] == null) ans = Math.max (ans, dfs (dp, pairs, idx));
        return ans;
    }
    
    private int dfs (Integer [] dp, int [][] pairs, int start) {
        if (dp [start] != null) return dp [start];
        int max = 1;
        for (int idx = start + 1; idx < pairs.length; idx ++)
            if (pairs [start][1] < pairs [idx][0])
                max = Math.max (max, 1 + dfs (dp, pairs, idx));
        return dp [start] = max;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [][] { { 1, 2 }, { 2, 3 }, { 3, 4 } }, 2);
	}

	public void runTest(final int[][] pairs, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { pairs });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
