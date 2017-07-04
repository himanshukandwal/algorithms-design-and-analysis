package challenges.leetcode;

import java.util.HashMap;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 594. Longest Harmonious Subsequence
 * 
 * We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.
 * Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.
 * 
 * Example 1:	
 * 		Input: [1,3,2,2,5,2,3,7]
 * 		Output: 5
 * 		Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 * 
 * Note: The length of the input array will not exceed 20,000.
 * 
 * @author Hxkandwal
 */
public class LongestHarmoniousSubsequence extends AbstractCustomTestRunner {

	public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) map.put (num, map.getOrDefault (num, 0) + 1);
        for (int num : nums) 
            if (map.containsKey (num + 1)) ans = Math.max (ans, map.get (num) + map.get (num + 1));
        return ans;
    }
	
}
