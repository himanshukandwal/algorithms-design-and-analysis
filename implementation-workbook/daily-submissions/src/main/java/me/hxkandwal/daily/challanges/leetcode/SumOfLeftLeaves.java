package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 404. Sum of Left Leaves
 * 
 * Find the sum of all left leaves in a given binary tree.
 * 
 * Example:
 *
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 *
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 * 
 * @author Hxkandwal
 */
public class SumOfLeftLeaves extends AbstractCustomTestRunner {
	
	private static SumOfLeftLeaves _instance = new SumOfLeftLeaves();
	
	private SumOfLeftLeaves() {}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public static int _sumOfLeftLeaves(TreeNode root) {
		int ans = 0;
		if (root != null) {
			if (root.left != null ) ans += innerRecursion (root.left, true);
			if (root.right!= null ) ans += innerRecursion (root.right, false);
		}
		return ans;
    }
	
	private static int innerRecursion (TreeNode node, boolean isLeft) {
		int ans = 0;
		if (node != null) {
			if (node.left == null && node.right == null) return (isLeft ? node.val : 0);
			if (node.left != null ) ans += innerRecursion (node.left, true);
			if (node.right!= null ) ans += innerRecursion (node.right, false);
		}
		return ans;
	}

	// driver method
	public static void main(String[] args) {
		TreeNode node = new TreeNode(3);
		node.left = new TreeNode(9);
		
		TreeNode right = new TreeNode(20);
		right.left = new TreeNode(15);
		right.right = new TreeNode(7);
				
		node.right = right;
		
		_instance.runTest(node, 24);
	}

	public void runTest(final TreeNode node, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { node });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
