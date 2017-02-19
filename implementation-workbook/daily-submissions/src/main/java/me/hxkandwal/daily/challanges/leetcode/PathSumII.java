package me.hxkandwal.daily.challanges.leetcode;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
@SuppressWarnings({ "unchecked", "rawtypes" })
public class PathSumII extends AbstractCustomTestRunner {
	
	private static PathSumII _instance = new PathSumII();
	
	public PathSumII() {}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> answer = new ArrayList<>();
        if (root == null) return answer;
        recurser (answer, new ArrayList<>(), root, sum);
        return answer;
    }
    
    private void recurser (List<List<Integer>> answer, List<Integer> build, TreeNode node, int sum) {
        if (node.left == null && node.right == null) {
            if (node.val == sum) {
                build.add (node.val);
                answer.add (new ArrayList(build));
                build.remove(build.size() - 1);
            }
        } else {
            build.add (node.val);
            if (node.left != null) recurser (answer, build, node.left, sum - node.val);    
            if (node.right != null) recurser (answer, build, node.right, sum - node.val);    
            build.remove (build.size() - 1);
        }
    }

}
