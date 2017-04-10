package challenges.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

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
	
	/**
	 * O (n) solution
	 * 
	 * Consider implement these two helper functions:
	 * 		getPredecessor(N), which returns the next smaller node to N.	
	 * 		getSuccessor(N), which returns the next larger node to N.
	 * 
	 * Try to assume that each node has a parent pointer, it makes the problem much easier.
	 * Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
	 * You would need two stacks to track the path in finding predecessor and successor node separately.
	 */
	public List<Integer> _closestKValues(TreeNode root, double target, int k) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        Stack<TreeNode> p = new Stack<>();
        
        TreeNode t = root;
        // crawling down (logn)
        while (t != null) {
            if (t.val >= target) { s.push (t); t = t.left; }
            else { p.push (t); t = t.right; }
        }
        
        while (k -- > 0) {
            if (p.isEmpty() && s.isEmpty()) break;
            else if (p.isEmpty()) ans.add (getSuccessor(s));
            else if (s.isEmpty()) ans.add (getPredecessor(p));
            else {
                if (Math.abs (p.peek().val - target) > Math.abs (s.peek().val - target))
                    ans.add (getSuccessor(s));
                else ans.add (getPredecessor(p));
            }
        }
        return ans;
    }
    
    private Integer getSuccessor (Stack<TreeNode> stk) {
        TreeNode ret = stk.pop(), t = ret;
        if (t.right != null) {
            t = t.right;
            stk.push (t);
            while (t.left != null) { t = t.left; stk.push (t); }
        }
        return ret.val;														// <<<<<<<<<<<<<<<<<<<<< next nearest would be ret.val
    }
    
    private Integer getPredecessor (Stack<TreeNode> stk) {
        TreeNode ret = stk.pop(), t = ret;
        if (t.left != null) {
            t = t.left;
            stk.push (t);
            while (t.right != null) { t = t.right; stk.push (t); }
        }
        return ret.val;        												// <<<<<<<<<<<<<<<<<<<<< next nearest would be ret.val
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
