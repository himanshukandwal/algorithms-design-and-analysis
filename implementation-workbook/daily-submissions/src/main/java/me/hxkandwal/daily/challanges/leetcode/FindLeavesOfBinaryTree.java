package me.hxkandwal.daily.challanges.leetcode;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 366. Find Leaves of Binary Tree
 * 
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
 * 
 * Example:
 * 
 *		Given binary tree 
 *		          1
 *		         / \
 *		        2   3
 *		       / \     
 *		      4   5    
 *
 *		Returns [4, 5, 3], [2], [1].
 *		
 * Explanation:
 * 
 *		1. Removing the leaves [4, 5, 3] would result in this tree:
 *		          1
 *		         / 
 *		        2          
 *
 *		2. Now removing the leaf [2] would result in this tree:
 *		          1          
 *
 *		3. Now removing the leaf [1] would result in the empty tree:
 *		          []         
 *
 *		Returns [4, 5, 3], [2], [1].
 * 
 * @author Hxkandwal
 */
public class FindLeavesOfBinaryTree extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	// highly fast one pass O(n) algorithm, no change (breaking) input 
	public List<List<Integer>> findLeavesBetter(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        useHeight (root, ans);
        return ans;
    }
	
    private int useHeight (TreeNode node, List<List<Integer>> ans) {
        if (node == null) return -1;
        int height = 1 + Math.max (useHeight (node.left, ans), useHeight(node.right, ans));
        while (height >= ans.size()) ans.add (new ArrayList<>());
        ans.get (height).add (node.val);
        return height;
    }
	
	// many pass O(n^2) solution.
	public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        while (root.left != null || root.right != null) {
            List<Integer> ans = new ArrayList<>();
            plucker (null, root, ans);
            res.add (ans);
        }
        List<Integer> ans = new ArrayList<>();
        plucker (null, root, ans);
        res.add (ans);
        
        return res;
    }
    
    private void plucker (TreeNode parent, TreeNode node, List<Integer> ans) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            ans.add (node.val);
            if (parent != null) { 
                if (parent.left == node) parent.left = null;  
                else parent.right = null;
            }
        } else {
            plucker (node, node.left, ans);
            plucker (node, node.right, ans);
        }
    }
    
}
