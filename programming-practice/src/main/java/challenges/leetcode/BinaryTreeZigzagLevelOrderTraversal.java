package challenges.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * 
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for 
 * the next level and alternate between).
 * 
 * For example:
 * 	Given binary tree [3,9,20,null,null,15,7],
 * 
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *    
 * 	return its zigzag level order traversal as:
 * 	[
 * 		[3],
 * 		[20,9],
 * 		[15,7]
 * 	]
 * 
 * @author Hxkandwal
 */
public class BinaryTreeZigzagLevelOrderTraversal extends AbstractCustomTestRunner {

	public class TreeNode {
		int val;
		TreeNode left, right;
		
		TreeNode(int x) { val = x; }
	}
	
	public List<List<Integer>> _zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<> ();
		dfs (ans, root, 0);
		return ans;
    }

	private void dfs (List<List<Integer>> ans, TreeNode node, int level) {
		if (node == null) return;
		if (ans.size() <= level) ans.add (level % 2 == 0 ? new ArrayList<>() : new LinkedList<>());
		List<Integer> current = ans.get(level);
	    if (level % 2 != 0)	((LinkedList<Integer>) current).addFirst (node.val);
	    else current.add (node.val);
	    dfs (ans, node.left, level + 1);
	    dfs (ans, node.right, level + 1);
	}
	
}
