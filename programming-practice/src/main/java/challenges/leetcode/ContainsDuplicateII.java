package challenges.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 219. Contains Duplicate II
 * 
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the 
 * array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 * 
 * @author Hxkandwal
 */
public class ContainsDuplicateII extends AbstractCustomTestRunner {
	
	private static ContainsDuplicateII _instance = new ContainsDuplicateII();

    public boolean _containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int idx = 0; idx < nums.length; idx ++) {
            if (map.containsKey (nums [idx]) && idx - map.get (nums [idx]) <= k) return true;
            map.put (nums [idx], idx);
        }
        return false;
    }

	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Set<Integer> set = new HashSet<>();
        for (int idx = 0; idx < nums.length; idx ++) {
            if (idx > k) set.remove (nums [idx - k - 1]);
            if (!set.add (nums[idx])) return true;
        } 
        return false;
    }

}
