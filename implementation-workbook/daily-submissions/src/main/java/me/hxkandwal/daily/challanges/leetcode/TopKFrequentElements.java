package me.hxkandwal.daily.challanges.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	public List<Integer> topKFrequentBucket(int[] nums, int k) {
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
	
	public List<Integer> topKFrequent(int[] nums, int k) {
		List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        
        PriorityQueue<Map.Entry<Integer, Integer>> fmaxheap = new PriorityQueue<Map.Entry<Integer, Integer>>(new Comparator<Map.Entry<Integer, Integer>> () {
                public int compare (Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                    return o2.getValue() - o1.getValue();
                }
            });
            
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) fmaxheap.offer (entry);
        while (!fmaxheap.isEmpty() && k -- > 0) ans.add (fmaxheap.poll().getKey());
        return ans;
    }

}
