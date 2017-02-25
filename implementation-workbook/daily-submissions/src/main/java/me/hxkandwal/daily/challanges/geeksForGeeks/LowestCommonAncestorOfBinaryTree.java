package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 236. Lowest Common Ancestor of a Binary Tree
 * 
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in 
 * T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * 
 * 
 *         _______3______
 *        /              \
 *     ___5__          ___1__
 *    /      \        /      \
 *    6      _2       0       8
 *          /  \
 *          7   4
 *          
 * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of 
 * itself according to the LCA definition.
 * 
 * @author Hxkandwal
 */
public class LowestCommonAncestorOfBinaryTree extends AbstractCustomTestRunner {
	
	private static LowestCommonAncestorOfBinaryTree _instance = new LowestCommonAncestorOfBinaryTree();

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int x) { val = x; }
		public String toString() { return "(" + val + ")"; }
	}
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	    if (root == null || root == p || root == q) return root;
	    TreeNode left = lowestCommonAncestor(root.left, p, q);
	    TreeNode right = lowestCommonAncestor(root.right, p, q);
	    return left == null ? right : right == null ? left : root;
	}
	
	public TreeNode _lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> pstk = new Stack<>();
        Stack<TreeNode> qstk = new Stack<>();
        path (root, p, pstk);
        path (root, q, qstk);
        
        TreeNode lastSame = null;
        while (!pstk.isEmpty() && !qstk.isEmpty()) {
            TreeNode pI = pstk.pop ();
            TreeNode sI = qstk.pop ();
            if (pI == sI) lastSame = pI;
            else break;
        }
        return lastSame;
    }
    
    private TreeNode path (TreeNode root, TreeNode s, Stack <TreeNode> path) {
        if (root == null) return null;
        if (root == s) { path.push (s); return s; }
        
        if (path (root.left, s, path) != null || path (root.right, s, path) != null) {
            path.push (root);
            return root;
        }
        return null;
    }

	// driver method
	public static void main(String[] args) {
		TreeNode node = new TreeNode(1);
		node.left = new TreeNode(2);
		node.left.right = new TreeNode(4);
		
		TreeNode right = new TreeNode(20);
		right.left = new TreeNode(15);
		right.right = new TreeNode(7);
		node.right = right;
		
		_instance.runTest(node, node.left.right, right, node);
	}

	public void runTest(final TreeNode root, final TreeNode p, final TreeNode q, final TreeNode expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { root, p, q });

		for (Object answer : answers)
			assertThat((TreeNode) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
    
}
