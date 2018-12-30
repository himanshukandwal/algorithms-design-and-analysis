package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 965. Univalued Binary Tree
 *
 * A binary tree is univalued if every node in the tree has the same value. Return true if and only if the given tree is univalued.
 *
 * Example 1:
 *      Input: [1,1,1,1,1,null,1]
 *      Output: true
 *
 * Example 2:
 *      Input: [2,2,2,5,2]
 *      Output: false
 *
 * @author Hxkandwal
 */
public class UnivaluedBinaryTree extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        boolean ans = true;
        if (root.left != null) {
            if (root.val == root.left.val) ans &= isUnivalTree(root.left);
            else return false;
        }
        if (root.right != null) {
            if (root.val == root.right.val) ans &= isUnivalTree(root.right);
            else return false;
        }
        return ans;
    }
}
