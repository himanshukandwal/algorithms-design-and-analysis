package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 220. Contains Duplicate III
 * 
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at 
 * most t and the absolute difference between i and j is at most k.
 * 
 * @author Hxkandwal
 */
public class ContainsDuplicateIII extends AbstractCustomTestRunner {
	
	private static ContainsDuplicateIII _instance = new ContainsDuplicateIII();
	
	private ContainsDuplicateIII() {}

	/**
	 * https://discuss.leetcode.com/topic/15199/ac-o-n-solution-in-java-using-buckets-with-explanation
	 */
	public boolean _containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (k < 1 || t < 0) return false;
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long) t + 1);
            if (map.containsKey(bucket)
                    || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
                        || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t))
                            return true;
            if (map.entrySet().size() >= k) {
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false;
	}
    
   	// driver method
   	public static void main(String[] args) {
		_instance.runTest(new int [] { 2, 1 }, 1, 1, true);
		_instance.runTest(new int [] { -3, -3 }, 2, 4, false);
   	}

   	public void runTest(final int[] nums, final int k, final int t, final boolean expectedOutput) {
   		List<Object> answers = runAll(getClass(), new Object[] { nums, k, t });

   		for (Object answer : answers)
   				assertThat((Boolean) answer).isEqualTo(expectedOutput);

   		System.out.println("ok!");
   	}	    
   	
}
