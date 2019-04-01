package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 669. Trim a Binary Search Tree
 *
 * Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L).
 * You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.
 *
 * Example 1:
 *              Input:
 *                             1
 *                            / \
 *                           0   2
 *
 *                           L = 1
 *                           R = 2
 *
 *                         Output:
 *                             1
 *                               \
 *                                2
 * Example 2:
 *              Input:
 *                             3
 *                            / \
 *                           0   4
 *                            \
 *                             2
 *                            /
 *                           1
 *
 *                           L = 1
 *                           R = 3
 *
 *                         Output:
 *                               3
 *                              /
 *                            2
 *                           /
 *                          1
 *
 * @author Hxkandwal
 */
public class TrimABinarySearchTree extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode _trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        if (root.val < L) return _trimBST (root.right, L, R);
        if (root.val > R) return _trimBST (root.left, L, R);

        root.left = _trimBST (root.left, L, R);
        root.right = _trimBST (root.right, L, R);
        return root;
    }
}
