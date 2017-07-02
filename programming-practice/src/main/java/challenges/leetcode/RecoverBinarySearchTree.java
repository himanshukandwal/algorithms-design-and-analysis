package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;

import challenges.AbstractCustomTestRunner;
import challenges.leetcode.BinaryTreeInorderTraversal.TreeNode;

/**
 * 99. Recover Binary Search Tree
 * 
 * Two elements of a binary search tree (BST) are swapped by mistake. Recover the tree without changing its structure.
 * 
 * Note: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 * 
 * @author Hxkandwal
 */
public class RecoverBinarySearchTree extends AbstractCustomTestRunner {
	
	// explanation : https://discuss.leetcode.com/topic/3988/no-fancy-algorithm-just-simple-and-powerful-in-order-traversal
	public void recoverTree(TreeNode root) {
        TreeNode prev = new TreeNode (Integer.MIN_VALUE), current = root, first = null, second = null;
        while (current != null) {
            if (first == null && current.val <= prev.val) first = prev;
            if (first != null && current.val <= prev.val) second = current; // keep updating the second to the very recent fault location;
            
            if (current.left == null) {
                prev = current; current = current.right;
            } else {
                TreeNode pred = current.left;
                while (pred.right != null && pred.right != current) pred = pred.right;
                
                if (pred.right == null) {
                    pred.right = current;
                    current = current.left;
                } else {
                    pred.right = null;
                    prev = current;
                    current = current.right;
                }
            }
        }
        
        // swap first and second
        int val = first.val;
        first.val = second.val;
        second.val = val;
    }

	// driver method
	public static void main(String[] args) {
		RecoverBinarySearchTree recoverBinarySearchTree = new RecoverBinarySearchTree();
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(3);
		root.right = new TreeNode(1);
		recoverBinarySearchTree.recoverTree(root);
		
		assertThat(new BinaryTreeInorderTraversal().inorderTraversal(root)).isEqualTo(Arrays.asList(1, 2, 3));
		System.out.println("ok!");
	}
	   
}
