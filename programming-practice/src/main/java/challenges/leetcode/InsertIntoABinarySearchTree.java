package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 701. Insert into a Binary Search Tree
 *
 * Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion.
 * It is guaranteed that the new value does not exist in the original BST.
 *
 * Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
 *
 * For example,
 *  Given the tree:
 *
 *              4
 *             / \
 *            2   7
 *           / \
 *         1   3
 *
 *  And the value to insert: 5
 *
 *  You can return this binary search tree:
 *
 *              4
 *            /   \
 *           2     7
 *          / \   /
 *         1   3 5
 *
 * This tree is also valid:
 *
 *              5
 *            /   \
 *           2     7
 *          / \
 *         1   3
 *              \
 *              4
 *
 * @author hxkandwal
 */
public class InsertIntoABinarySearchTree extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    // logic to put the new node at the leaf level.
    public TreeNode insertIntoBSTAtTheEnd(TreeNode root, int val) {
        TreeNode n = root, v = new TreeNode(val), p = null;
        if (root == null) return v;

        while (n != null) {
            p = n;
            if (n.val > val) n = n.left;
            else n = n.right;
        }
        if (p.val > val) p.left = v;
        else p.right = v;

        return root;
    }

    // logic that involves mutating the tree and placing the incoming value at the best possible place from the root and recursively finding
    // the next best place of the value which got evicted. (cuckoo hashing idea)
    public TreeNode insertIntoBSTBestPlaceFromRoot(TreeNode r, int n) {
        if (r == null) return new TreeNode(n);

        if (canReplace(r, n)) {
            int evictedVal = r.val;
            r.val = n;
            n = evictedVal;
        }

        // post order processing.
        if (n > r.val) r.right = insertIntoBSTBestPlaceFromRoot (r.right, n);
        else r.left = insertIntoBSTBestPlaceFromRoot (r.left, n);
        return r;
    }

    private boolean canReplace(TreeNode r, int n) {
        TreeNode p = findPredecessor(r), s = findSuccessor(r);
        return r == null || ((p == null || p.val < n) && (s == null || s.val > n));
    }

    private TreeNode findPredecessor(TreeNode n) {
        if (n == null || n.left == null) return null;
        n = n.left;
        while (n.right != null) n = n.right;
        return n;
    }

    private TreeNode findSuccessor(TreeNode n) {
        if (n == null || n.right == null) return null;
        n = n.right;
        while (n.left != null) n = n.left;
        return n;
    }
}
