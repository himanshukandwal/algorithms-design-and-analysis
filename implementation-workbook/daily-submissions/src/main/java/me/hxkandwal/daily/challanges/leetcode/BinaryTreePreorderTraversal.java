package me.hxkandwal.daily.challanges.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 144. Binary Tree Preorder Traversal
 * 
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * 
 * For example:
 * Given binary tree {1,#,2,3},
 * 
 * 	   1
 * 	    \
 * 	     2
 * 	    /
 * 	   3
 * 
 * 	return [1,2,3].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 * 
 * @author Hxkandwal
 */
public class BinaryTreePreorderTraversal extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> bstk = new Stack<>();
            
            TreeNode t = root;
            bstk.push (root);
            while (!bstk.isEmpty()) {
                t = bstk.pop();
                ans.add (t.val);
                if (t.right != null) bstk.push (t.right);
                if (t.left != null) bstk.push (t.left);
            }
        }
        return ans;
    }
	
}
