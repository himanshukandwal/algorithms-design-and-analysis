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
		TreeNode left, right;
		public TreeNode(int x) { val = x; }
	}
	
	// Morris Traversal : http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
	public List<Integer> inorderTraversalBetter (TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        if (root == null) return ans;
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) { ans.add (current.val); current = current.right; }
            else {
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) predecessor = predecessor.right;
                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    predecessor.right = null; 
                    ans.add (current.val); 
                    current = current.right; 
                }
            }
        }
        return ans;
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
