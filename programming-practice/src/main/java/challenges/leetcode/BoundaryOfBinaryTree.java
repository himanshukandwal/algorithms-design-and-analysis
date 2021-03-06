package challenges.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 545. Boundary of Binary Tree
 *
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves,
 * and right boundary in order without duplicate nodes.
 *
 * Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node.
 * If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies
 * to the input binary tree, and not applies to any subtrees.
 *
 * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the
 * right subtree. Repeat until you reach a leaf node. The right-most node is also defined by the same way with left and right exchanged.
 *
 * Example 1
 *      Input:
 *                  1
 *                   \
 *                   2
 *                  / \
 *                 3   4
 *
 *      Output: [1, 3, 4, 2]
 *
 *      Explanation: The root doesn't have left subtree, so the root itself is left boundary.
 *                   The leaves are node 3 and 4.
 *                   The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
 *
 *                   So order them in anti-clockwise without duplicates and we have [1,3,4,2].
 *
 * Example 2
 *      Input:
 *                          ____1_____
 *                         /          \
 *                        2            3
 *                       / \          /
 *                      4   5        6
 *                         / \      / \
 *                        7   8    9  10
 *
 *      Output: [1,2,4,7,8,9,10,6,3]
 *
 *      Explanation: The left boundary are node 1,2,4. (4 is the left-most node according to definition)
 *                   The leaves are node 4,7,8,9,10.
 *                   The right boundary are node 1,3,6,10. (10 is the right-most node).
 *                   So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 *
 * Created by Hxkandwal
 */
public class BoundaryOfBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        LinkedList<Integer> right = new LinkedList<>();
        dfs (ans, right, root, 0);
        for (Integer val : right) ans.add (val);
        return ans;
    }

    public void dfs (List<Integer> ans, LinkedList<Integer> right, TreeNode node, int flag) {
        if (node == null) return;
        if (flag <= 1 || (node.left == null && node.right == null)) ans.add (node.val);
        else if (flag == 2) right.addFirst (node.val);
        dfs (ans, right, node.left, flag <= 1 ? 1: (flag == 2 && node.right == null ? 2 : 3));
        dfs (ans, right, node.right, flag % 2 == 0 ? 2: (flag == 1 && node.left == null ? 1 : 3));
    }

    //another variation
    public List<Integer> boundaryOfBinaryTreeOneList(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs (ans, root, 0);
        return ans;
    }

    // 2 = right; 0 = root; 3 = internal; 1 = left
    private void dfs (List<Integer> ans, TreeNode node, int flag) {
        if (node == null) return;
        if (flag <= 1 || (flag != 2 && node.left == null && node.right == null)) ans.add (node.val); // pre-order addition (root + left + leaf nodes)
        dfs (ans, node.left, flag <= 1 ? 1 : (flag == 2 && node.right == null ? 2 : 3));
        dfs (ans, node.right, flag % 2 == 0 ? 2: (flag == 1 && node.left == null ? 1 : 3));
        if (flag == 2) ans.add (node.val); // post order addition (root + right nodes)
    }
}
