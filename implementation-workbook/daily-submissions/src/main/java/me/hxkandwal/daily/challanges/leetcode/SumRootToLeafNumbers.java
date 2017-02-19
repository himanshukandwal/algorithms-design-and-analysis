package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 129. Sum Root to Leaf Numbers
 * 
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * 
 * For example,
 *
 *	    1
 *	   / \
 *	  2   3
 *
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * 
 * Return the sum = 12 + 13 = 25.
 * 
 * @author Hxkandwal
 */
public class SumRootToLeafNumbers extends AbstractCustomTestRunner {
	
	private static SumRootToLeafNumbers _instance = new SumRootToLeafNumbers();
	
	public SumRootToLeafNumbers() {}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public int _sumNumbers(TreeNode root) {
		 return count(root, new StringBuilder());
    }
    
    private int count(TreeNode node, StringBuilder sb) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) {
            sb.append (node.val);
            int value = Integer.valueOf(sb.toString());
            sb.deleteCharAt (sb.length() - 1);
            return value;
        }
        
        int res = 0;
        sb.append (node.val);
        res += count (node.left, sb);
        res += count (node.right, sb);
        sb.deleteCharAt (sb.length() - 1);
        return res;
    }

}
