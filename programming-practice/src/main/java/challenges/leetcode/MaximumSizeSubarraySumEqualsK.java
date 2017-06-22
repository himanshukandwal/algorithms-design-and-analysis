package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 325. Maximum Size Subarray Sum Equals k
 * 
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. 
 * If there isn't one, return 0 instead.
 * 
 * Note: The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 * 
 * Example 1:
 * 		Given nums = [1, -1, 5, -2, 3], k = 3, return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * 
 * Example 2:
 * 		Given nums = [-2, -1, 2, 1], k = 1, return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 * 
 * Follow Up: Can you do it in O(n) time?
 * 
 * @author Hxkandwal
 */
public class MaximumSizeSubarraySumEqualsK extends AbstractCustomTestRunner {
	
	private static MaximumSizeSubarraySumEqualsK _instance = new MaximumSizeSubarraySumEqualsK();
	
	private MaximumSizeSubarraySumEqualsK() {}
	
	// O(n) solution : Two Sum philosophy
	public int _maxSubArrayLen(int[] nums, int k) {
		int max = 0, localsum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int idx = 0; idx < nums.length; idx ++) { 
            localsum += nums [idx];
            
            if (localsum == k) 
                max = Math.max (max, idx + 1);
            else if (map.containsKey(-(k - localsum)))
                    max = Math.max (max, idx - map.get(-(k - localsum)));            // <<<<<<<<<<<<<<<<<<<<<<<< this is same as (localsum - k)
                    
            map.putIfAbsent(localsum, idx);
        }
        
        return max;
    }
	
	// bruteforce O(n^2) solution
	public int maxSubArrayLen(int[] nums, int k) {
        int max = 0, localsum = 0;
        for (int idx = 0; idx < nums.length; idx ++) {
            localsum = 0;
            for (int innerIdx = idx; innerIdx < nums.length; innerIdx ++) {
                localsum = localsum + nums [innerIdx];
                if (localsum == k) max = Math.max (max, innerIdx - idx + 1);
            }
        }
        return max;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { -2, -1, 2, 1 }, 1, 2);
		_instance.runTest(new int[] { 1, -1, 5, -2, 3 }, 3, 4);
	}

	public void runTest(final int[] nums, final int k, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, k });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	} 	
}
