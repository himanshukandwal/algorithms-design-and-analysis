package challenges.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 145. Binary Tree Postorder Traversal
 * 
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * 
 * For example: Given binary tree {1,#,2,3},
 * 
 *	   1
 *	    \
 *	     2
 *	    /
 *	   3
 *
 * return [3,2,1].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 * 
 * @author Hxkandwal
 */
public class BinaryTreePostorderTraversal extends AbstractCustomTestRunner {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
	}
	
	// standard 
	public List<Integer> postorderTraversalStandard(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        getPostOrder (root, answer);
        return answer;
    }
    
    private void getPostOrder (TreeNode node, List<Integer> collector) {
        if (node == null) return;
        getPostOrder (node.left, collector);
        getPostOrder (node.right, collector);
        collector.add (node.val);
    }
    
    // iterative
	public List<Integer> postorderTraversal(TreeNode root) {
		LinkedList<Integer> answer = new LinkedList<>();
        if (root == null) return answer;
        
        Stack<TreeNode> bstk = new Stack<>();
        TreeNode node = root;
        bstk.push (node);
        
        while (!bstk.isEmpty()) {
            node = bstk.pop ();
            answer.addFirst (node.val);
            if (node.left != null) bstk.push (node.left);
            if (node.right != null) bstk.push (node.right);
        }
        
        return answer;
	}
}
