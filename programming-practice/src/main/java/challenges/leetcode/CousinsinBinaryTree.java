package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * 993. Cousins in Binary Tree
 *
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 *
 * Example 1:
 *              Input: root = [1,2,3,4], x = 4, y = 3
 *              Output: false
 *
 * Example 2:
 *              Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
 *              Output: true
 *
 * Example 3:
 *              Input: root = [1,2,3,null,4], x = 2, y = 3
 *              Output: false
 *
 * Note:
 *  The number of nodes in the tree will be between 2 and 100.
 *  Each node has a unique integer value from 1 to 100.
 *
 * @author Hxkandwal
 */
public class CousinsinBinaryTree extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        Set<Integer> xstack = new HashSet<>(), ystack = new HashSet<>();
        path (xstack, root, x);
        path (ystack, root, y);

        // retainAll (intersection) : https://www.geeksforgeeks.org/set-retainall-method-in-java-with-example/
        return xstack.size() == ystack.size() && xstack.retainAll(ystack) && xstack.size() < ystack.size() - 1;
    }

    private boolean path (Set<Integer> stack, TreeNode n, int val) {
        if (n == null) return false;
        if (n.val == val || path (stack, n.left, val) || path (stack, n.right, val)) {
            stack.add (n.val); return true;
        }
        return false;
    }
}
