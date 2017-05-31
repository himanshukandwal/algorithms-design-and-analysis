package challenges.leetcode;

import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Path Sum II
 * 
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * 
 * For example:
 * 		Given the below binary tree and sum = 22, 
 * 
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 *         
 * return
 * 			[
 * 				[5,4,11,2],
 * 				[5,8,4,5]
 * 			]
 * 
 * @author Hxkandwal
 */
public class PathSumII extends AbstractCustomTestRunner {
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> ans = new ArrayList<> ();
        searchSum (ans, new ArrayList<>(), root, sum);
        return ans;
    }
    
    private void searchSum (List<List<Integer>> ans, List<Integer> build, TreeNode node, int sum) {
        if (node == null) return;
        build.add (node.val);
        if (node.val == sum && node.left == null && node.right == null) ans.add (new ArrayList<> (build));
        searchSum (ans, build, node.left, sum - node.val);
        searchSum (ans, build, node.right, sum - node.val);
        build.remove (build.size() - 1);
    }

}
