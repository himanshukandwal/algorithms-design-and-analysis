package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 662. Maximum Width of Binary Tree
 *
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the
 * same structure as a full binary tree, but some nodes are null.
 *
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes
 * are also counted into the length calculation.
 *
 * Example 1:
 *              Input:
 *
 *                            1
 *                          /   \
 *                         3     2
 *                        / \     \
 *                       5   3     9
 *
 *              Output: 4
 *              Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 *
 * Example 2:
 *              Input:
 *
 *
 *                           1
 *                          /
 *                         3
 *                        / \
 *                       5   3
 *
 *              Output: 2
 *              Explanation: The maximum width existing in the third level with the length 2 (5,3).
 *
 * Example 3:
 *              Input:
 *
 *                           1
 *                          / \
 *                         3   2
 *                        /
 *                       5
 *
 *              Output: 2
 *              Explanation: The maximum width existing in the second level with the length 2 (3,2).
 *
 * Example 4:
 *              Input:
 *
 *                           1
 *                          / \
 *                         3   2
 *                        /     \
 *                       5       9
 *                      /         \
 *                     6           7
 *
 *              Output: 8
 *              Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 *
 * Note: Answer will in the range of 32-bit signed integer.
 *
 * @author Hxkandwal
 */
public class MaximumWidthofBinaryTree extends AbstractCustomTestRunner {

     public class TreeNode {
         int val;
         TreeNode left, right;
         TreeNode(int x) { val = x; }
     }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<MetaNode> queue = new LinkedList<>();
        queue.offer (new MetaNode(0, root));

        int ans = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int base = -1;
            while (size -- > 0) {
                MetaNode n = queue.poll();
                if (n.node.left != null) {
                    int pos = 2 * n.pos;
                    if (base < 0) base = pos;
                    ans = Math.max (ans, pos - base + 1);
                    queue.offer (new MetaNode (pos, n.node.left));
                }

                if (n.node.right != null) {
                    int pos = 2 * n.pos + 1;
                    if (base < 0) base = pos;
                    ans = Math.max (ans, pos - base + 1);
                    queue.offer (new MetaNode (pos, n.node.right));
                }
            }
        }
        return ans;
    }

    public class MetaNode {
        public int pos;
        public TreeNode node;

        public MetaNode(int pos, TreeNode node) {
            this.pos = pos;
            this.node = node;
        }
    }
}
