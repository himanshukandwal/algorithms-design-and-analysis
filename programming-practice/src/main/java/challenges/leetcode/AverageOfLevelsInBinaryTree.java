package challenges.leetcode;

import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 637. Average of Levels in Binary Tree
 * 
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 * 
 * Example 1:
 * 
 * 	Input:
 * 				3
 * 			   / \
 * 			  9  20
 * 				/  \
 * 			   15   7
 *    
 *	Output: [3, 14.5, 11]
 *
 * Explanation: The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. 
 * 				Hence return [3, 14.5, 11].
 * 
 * Note:
 * 	-	The range of node's value is in the range of 32-bit signed integer.
 * 
 * @author Hxkandwal
 */
public class AverageOfLevelsInBinaryTree extends AbstractCustomTestRunner {
	
	public static class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode(int x) { val = x; }
	}
	
	public List<Double> averageOfLevels(TreeNode root) {
        List<long []> list = new ArrayList<>();
        helper (root, list, 1);
        List<Double> ans = new ArrayList<>();
        for (long [] info : list) ans.add (info [0] * 1d/info [1]);
        return ans;
    }
    
    private void helper (TreeNode node, List<long[]> list, int level) {
        if (node == null) return;
        if (list.size () < level) list.add (new long [] { 0, 0 });
        long [] info = list.get (level - 1);
        info [0] += node.val;
        info [1] ++;
        helper (node.left, list, level + 1);
        helper (node.right, list, level + 1);
    }

}
