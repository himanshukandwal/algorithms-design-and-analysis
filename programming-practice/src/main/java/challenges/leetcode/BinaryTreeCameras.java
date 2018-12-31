package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 968. Binary Tree Cameras
 *
 * Given a binary tree, we install cameras on the nodes of the tree. Each camera at a node can monitor its parent, itself, and its immediate children.
 * Calculate the minimum number of cameras needed to monitor all nodes of the tree.
 *
 * Example 1:
 *      Input: [0,0,null,0,0]
 *      Output: 1
 *      Explanation: One camera is enough to monitor all nodes if placed as shown.
 *
 * Example 2:
 *      Input: [0,0,null,0,null,0,null,null,0]
 *      Output: 2
 *      Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
 *
 * Note:
 *  1. The number of nodes in the given tree will be in the range [1, 1000].
 *  2. Every node has value 0.
 *
 * @author Hxkandwal
 */
public class BinaryTreeCameras extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    /**
     * INV -   V = Behave V
     *   V -   V = Min V.code -> Behave V
     * INV - INV = V.code = (p == null) ? 0 : -1
     */
    public int minCameraCover(TreeNode root) {
        return postOrder(null, root, 1).cover;
    }

    private Meta postOrder (TreeNode p, TreeNode n, int code) {
        if (n == null) return new Meta();
        Meta left = postOrder (n, n.left, code - 1), right = postOrder (n, n.right, code - 1);

        int pcode = 0, pcover = 0;
        if (!isValid(left.code) || !isValid(right.code)) {
            if (!isValid(left.code) && !isValid(right.code)) {
                if (p == null) {
                    pcode = 1;
                    pcover = left.cover + right.cover + 1;
                }
                else {
                    pcode = 0;
                    pcover = left.cover + right.cover;
                }
            }
            else if (!isValid(right.code)) {
                pcode = left.code + 1;
                pcover = left.cover + right.cover + (left.code == 0 ? 1 : 0);
            } else {
                pcode = right.code + 1;
                pcover = left.cover + right.cover + (right.code == 0 ? 1 : 0);
            }
        } else {
            pcode = Math.min(left.code, right.code) + 1;
            pcover = left.cover + right.cover + (Math.min(left.code, right.code) == 0 ? 1 : 0);
        }
        return new Meta (pcode, pcover);
    }

    private boolean isValid(int code) {
        return Math.abs(code) < 2;
    }

    class Meta {
        int code = -2;
        int cover;
        public Meta() {}
        public Meta(int code, int cover) { this.code = code; this.cover = cover; }
    }

    // Official Solution
    public int minCameraCoverDP(TreeNode root) {
        int[] ans = solve(root);
        return Math.min(ans[1], ans[2]);
    }

    // 0: Strict ST; All nodes below this are covered, but not this one
    // 1: Normal ST; All nodes below and incl this are covered - no camera
    // 2: Placed camera; All nodes below this are covered, plus camera here
    public int[] solve(TreeNode node) {
        if (node == null)
            return new int[]{0, 0, 99999};

        int[] L = solve(node.left);
        int[] R = solve(node.right);
        int mL12 = Math.min(L[1], L[2]);
        int mR12 = Math.min(R[1], R[2]);

        int d0 = L[1] + R[1];
        int d1 = Math.min(L[2] + mR12, R[2] + mL12);
        int d2 = 1 + Math.min(L[0], mL12) + Math.min(R[0], mR12);
        return new int[]{d0, d1, d2};
    }
}
