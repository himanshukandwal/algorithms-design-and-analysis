package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 1008. Construct Binary Search Tree from Preorder Traversal
 *
 * Return the root node of a binary search tree that matches the given pre-order traversal.
 *
 * (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right
 * has a value > node.val.  Also recall that a pre-order traversal displays the value of the node first, then traverses node.left, then traverses node.right.)
 *
 * @author Hxkandwal
 */
public class ConstructBinarySearchTreefromPreorderTraversal extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder (preorder, 0, preorder.length - 1);
    }

    private TreeNode bstFromPreorder(int[] preorder, int start, int end) {
        if (preorder == null || preorder.length == 0 || start > end) return null;
        int val = preorder [start];
        TreeNode node = new TreeNode (val);
        int idx = start + 1;
        while (idx <= end) if (preorder [idx] > val) break; else idx ++;
        node.left = bstFromPreorder(preorder, start + 1, idx - 1);
        node.right = bstFromPreorder(preorder, idx, end);
        return node;
    }

}
