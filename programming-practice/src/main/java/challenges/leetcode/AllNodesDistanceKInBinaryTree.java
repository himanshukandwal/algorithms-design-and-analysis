package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 863. All Nodes Distance K in Binary Tree
 *
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 *
 * Example 1:
 *              Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 *              Output: [7,4,1]
 *              Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
 *                           Note that the inputs "root" and "target" are actually TreeNodes.
 *                           The descriptions of the inputs above are just serializations of these objects.
 *
 * Note:
 *  The given tree is non-empty.
 *  Each node in the tree has unique values 0 <= node.val <= 500.
 *  The target node is a node in the tree.
 *  0 <= K <= 1000.
 *
 * @author Hxkandwal
 */
public class AllNodesDistanceKInBinaryTree extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> _distanceK(TreeNode root, TreeNode target, int K) {
        if (K == 0) return Arrays.asList(target.val);

        List<Integer> ans = new ArrayList<>();
        collect (ans, target, K);

        Stack<TreeNode> stack = new Stack<>();
        path (stack, root, target);

        if (stack.size() >= K + 1) {
            while (stack.size() != K + 1) stack.pop();
            ans.add (stack.pop().val);

            for (int idx = stack.size() - 1, k = 0; idx > 0; idx --, k ++) {
                TreeNode n = stack.get (idx), nc = stack.get (idx - 1);
                collect (ans, nc == n.left ? n.right : n.left, k);
            }
        }
        else {
            for (int idx = stack.size() - 1, k = K - stack.size(); idx > 0; idx --, k ++) {
                TreeNode n = stack.get (idx), nc = stack.get (idx - 1);
                collect (ans, nc == n.left ? n.right : n.left, k);
            }
        }

        return ans;
    }

    private boolean path (Stack<TreeNode> stack, TreeNode n, TreeNode target) {
        if (n == null) return false;
        if (n == target || path (stack, n.left, target) || path (stack, n.right, target)) {
            stack.push (n);
            return true;
        }
        return false;
    }

    private void collect (List<Integer> list, TreeNode n, int K) {
        if (n == null) return;
        if (K == 0) list.add (n.val);
        else {
            collect (list, n.left, K - 1);
            collect (list, n.right, K - 1);
        }
    }
}
