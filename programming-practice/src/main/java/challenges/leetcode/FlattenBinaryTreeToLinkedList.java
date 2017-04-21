package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 114. Flatten Binary Tree to Linked List
 * 
 * Given a binary tree, flatten it to a linked list in-place.
 * 
 * For example,
 * Given
 * 
 *          1
 *         / \
 *        2   5
 *       / \   \
 *      3   4   6
 *      
 * The flattened tree should look like:
 * 
 *    1
 *     \
 *      2
 *       \
 *        3
 *         \
 *          4
 *           \
 *            5
 *             \
 *              6
 * 
 * @author Hxkandwal
 */
public class FlattenBinaryTreeToLinkedList extends AbstractCustomTestRunner {

	public class TreeNode {
		int val;
		TreeNode left, right;
		
		TreeNode(int x) { val = x; }
	}
	
	public void flatten(TreeNode root) {
        flattenPostOrder (root);
    }
    
    private TreeNode flattenPostOrder (TreeNode node) {
        if (node == null) return null;
        node.left = flattenPostOrder (node.left);
        node.right = flattenPostOrder (node.right);
        if (node.left == null) return node;
        TreeNode right = node.right;
        TreeNode traverser = node.right = node.left;
        node.left = null;
        while (traverser.right != null) traverser = traverser.right;
        traverser.right = right;
        return node;
    }
    
}
