package me.hxkandwal.daily.challanges.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 272. Closest Binary Search Tree Value II Add to List
 * 
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 * 
 * Note:
 * 		Given target value is a floating point.
 * 		You may assume k is always valid, that is: k ≤ total nodes.
 * 		You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 * 
 * Follow up:
 * 		Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 * 
 * @author Hxkandwal
 */
public class ClosestBinarySearchTreeValueII extends AbstractCustomTestRunner {
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	// this is O (nlogn) as full traversal is there and we are making heap operations at-most n times (min K times, best case)
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
        PriorityQueue <Integer> maxHeap = new PriorityQueue<Integer> ();
        close (root, target, k, maxHeap);
        List<Integer> ans = new ArrayList<>();
        
        for (int idx = 0; idx < k && !maxHeap.isEmpty(); idx ++)  ans.add (maxHeap.poll ());
        return ans;
    }
    
    private void close (TreeNode node, double target, int k, PriorityQueue<Integer> collector) {
        if (node == null) return;
        close (node.left, target, k, collector);
        int a = node.val;
        
        if (collector.size() < k) collector.offer (a);
        else if (Math.abs(a - target) < Math.abs (collector.peek() - target)) {
                collector.poll ();
                collector.offer (a);
        }
        
        close (node.right, target, k, collector);
    }

}
