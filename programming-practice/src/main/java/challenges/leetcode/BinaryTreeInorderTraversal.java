package challenges.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 94. Binary Tree Inorder Traversal
 * 
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * 
 * For example:
 * 		Given binary tree [1,null,2,3],
 *   1
 *    \
 *     2
 *    /
 *   3
 *   
 * return [1,3,2].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 * 
 * @author Hxkandwal
 */
public class BinaryTreeInorderTraversal extends AbstractCustomTestRunner {
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        Stack<TreeNode> bstk = new Stack<>();
        
        TreeNode node = root;
        while (node != null) {
        	
        	// maintain left 
            while (node != null) { bstk.push (node); node = node.left; }
            answer.add ((node = bstk.pop()).val);
            
            // maintain mid + right
            while (node.right == null && !bstk.isEmpty()) { node = bstk.pop(); answer.add (node.val); }
            node = node.right;
        }
        
        return answer;
    }

}
