package me.hxkandwal.daily.challanges.leetcode;

import java.util.HashMap;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 508. Most Frequent Subtree Sum
 * 
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node 
 * is defined as the sum of all the node values formed by the subtree rooted at that node (including the node 
 * itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the 
 * highest frequency in any order.
 * 
 * Examples 1
 * 	Input:
 * 		
 * 		  5
 * 		 /  \
 * 		2   -3
 * 		
 * return [2, -3, 4], since all the values happen only once, return all of them in any order.
 * 
 * Examples 2
 * 	Input:
 * 
 * 		  5
 * 		 /  \
 * 		2   -5
 * 	
 * 	return [2], since 2 happens twice, however -5 only occur once.
 * 
 * Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 * 
 * @author Hxkandwal
 */
public class MostFrequentSubtreeSum extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	private class Stats {
        int maxFreqCount;
    }
    
    public int[] findFrequentTreeSum(TreeNode root) {
        Stats stats = new Stats ();
        Map<Integer, Integer> map = new HashMap<>();
        compute (root, map, stats);
        int size = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) 
            if (entry.getValue() == stats.maxFreqCount) size ++;
            
        int [] res = new int [size]; size = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) 
            if (entry.getValue() == stats.maxFreqCount) res [size ++] = entry.getKey();
        return res;
    }
    
    private int compute (TreeNode node, Map<Integer, Integer> map, Stats stats) {
        if (node == null) return 0;
        int val = node.val + compute (node.left, map, stats) + compute (node.right, map, stats);
        map.put (val, map.getOrDefault(val, 0) + 1);
        if (stats.maxFreqCount < map.get(val)) stats.maxFreqCount = map.get(val);
        return val;
    }
    
}
