package challenges.leetcode;

import challenges.AbstractCustomTestRunner;
import challenges.leetcode.BinaryTreeZigzagLevelOrderTraversal.TreeNode;

/**
 * 450. Delete Node in a BST
 * 
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference 
 * (possibly updated) of the BST.
 * 
 * Basically, the deletion can be divided into two stages:
 * 	1. 	Search for a node to remove.
 * 	2.	If the node is found, delete the node.
 * 
 * Note: Time complexity should be O(height of tree).
 * 
 * Example:
 * 	root = [5,3,6,2,4,null,7] key = 3
 * 	 
 * 		      5
 *		   	 / \
 *   		3   6
 *  	   / \   \
 * 		  2   4   7
 * 
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 * 
 *     		  5
 *    		 / \
 *   		4   6
 *  	   /     \
 * 		  2       7
 * 
 * Another valid answer is [5,2,6,null,4,null,7].
 * 
 *     		  5
 *    		 / \
 *   		2   6
 *    		 \   \
 *     		  4   7
 * 
 * @author Hxkandwal
 */
public class DeleteNodeInABST extends AbstractCustomTestRunner {

	public class TreeNode {
		int val;
		TreeNode left, right;
		
		TreeNode(int x) { val = x; }
	}
	
	public TreeNode _deleteNode(TreeNode root, int key) {
		return postOrderDeletion (root, key);
    }
    
    private TreeNode postOrderDeletion (TreeNode node, int key) {
        if (node == null) return null;
        node.left = postOrderDeletion (node.left, key);
        node.right = postOrderDeletion (node.right, key);
        if (node.val == key) {
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            else {
                TreeNode old = node.left.right;
                node.left.right = node.right;
                TreeNode traverser = node.right;
                while (traverser.left != null) traverser = traverser.left;
                traverser.left = old;
                return node.left;
            }
        }
        return node;
    }

}
