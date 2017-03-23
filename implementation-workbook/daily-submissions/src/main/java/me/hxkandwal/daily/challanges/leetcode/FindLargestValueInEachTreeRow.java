package me.hxkandwal.daily.challanges.leetcode;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 515. Find Largest Value in Each Tree Row
 * 
 * You need to find the largest value in each row of a binary tree.
 * 
 * Example:
 * 	Input: 
 * 	
 * 	          1
 * 	         / \
 * 	        3   2
 * 	       / \   \  
 * 	      5   3   9 
 * 
 * Output: [1, 3, 9]
 * 
 * @author Hxkandwal
 */
public class FindLargestValueInEachTreeRow extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public List<Integer> largestValues(TreeNode root) {
        List<Integer> maxList = new ArrayList<>();
        dfs (maxList, root, 0);
        return maxList;
    }
    
    public void dfs (List<Integer> maxList, TreeNode node, int level) {
        if (node == null) return;    
        if (maxList.size() <= level) maxList.add (node.val);
        else if (maxList.get (level) < node.val) maxList.set (level, node.val);
        dfs (maxList, node.left, level + 1);
        dfs (maxList, node.right, level + 1);
    }

}