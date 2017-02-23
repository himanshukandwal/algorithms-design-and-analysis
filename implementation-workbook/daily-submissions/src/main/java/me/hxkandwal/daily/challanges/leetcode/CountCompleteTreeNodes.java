package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 222. Count Complete Tree Nodes
 * 
 * Given a complete binary tree, count the number of nodes.
 * 
 * Definition of a complete binary tree from Wikipedia:
 * 
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far 
 * left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * 
 * @author Hxkandwal
 */
public class CountCompleteTreeNodes extends AbstractCustomTestRunner {
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	// takes log(n) time
    public int height (TreeNode node) {
        return (node == null ? -1 : 1 + height (node.left));    
    }
    
    // log (n) work as we are splitting tree in every go.
    public int countNodes(TreeNode root) {
        int h = height (root);
        return h < 0 ? 0 : (height (root.right) == h - 1 ? (1 << h) + countNodes (root.right)
                                                         : (1 << h - 1) + countNodes (root.left));
    }

}
