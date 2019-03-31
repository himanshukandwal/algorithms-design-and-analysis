package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

/**
 * 347. Top K Frequent Elements
 * 
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * For example,
 * 		Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * 
 * Note:
 * 		You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * 		Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * 
 * @author Hxkandwal
 */
public class TopKFrequentElements extends AbstractCustomTestRunner {
	
	// bucket sort, more auxillary memory, lesser processing time O(n)
	public List<Integer> _topKFrequentBucket(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        List<Integer>[] buckets = new ArrayList [nums.length + 1];
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (buckets [entry.getValue()] == null) buckets [entry.getValue()] = new ArrayList<>();
            buckets [entry.getValue()].add (entry.getKey());
        }
        
        for (int idx = buckets.length - 1; idx > 0 && ans.size() < k; idx --) 
            if (buckets [idx] != null) ans.addAll (buckets [idx]);
        return ans;
    }
	
	public List<Integer> _topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put (num, map.getOrDefault(num, 0) + 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(b) - map.get (a));
        for (int key : map.keySet()) pq.offer (key);

        List<Integer> ans = new ArrayList<>();
        while (!pq.isEmpty() && k -- > 0) ans.add (pq.poll());
        return ans;
    }

}
