package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 * 
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum 
 * equals to k.
 * 
 * Example 1:
 * 		Input:	nums = [1,1,1], k = 2
 * 	                 [0,1,2,3]
 * 		Output: 2
 * 
 * Note:
 * 	-	The length of the array is in range [1, 20,000].
 * 	-	The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 * 
 * @author Hxkandwal
 */
public class SubarraySumEqualsK extends AbstractCustomTestRunner {

	public int _subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put (0, 1);
        int ans = 0, sum = 0;
        for (int idx = 0; idx < nums.length; idx ++) {
            sum += nums [idx];
            if (!map.containsKey(sum)) map.put(sum, 0);

            ans += map.getOrDefault(sum - k, 0);
            map.put(sum, map.get(sum) + 1);
        }
        return ans;
    }
	
}
