package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

/**
 * 314. Binary Tree Vertical Order Traversal
 *
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column). If two nodes are in the same row and column,
 * the order should be from left to right.
 * Examples 1:
 *      Input: [3,9,20,null,null,15,7]
 *
 *    3
 *   /\
 *  /  \
 *  9  20
 *     /\
 *    /  \
 *   15   7
 *
 * Output:
 *
 * [
 *   [9],
 *   [3,15],
 *   [20],
 *   [7]
 * ]
 * Examples 2:
 *
 * Input: [3,9,8,4,0,1,7]
 *
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *
 * Output:
 *
 * [
 *   [4],
 *   [9],
 *   [3,0,1],
 *   [8],
 *   [7]
 * ]
 * Examples 3:
 *
 * Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
 *
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *     /\
 *    /  \
 *    5   2
 *
 * Output:
 *
 * [
 *   [4],
 *   [9,5],
 *   [3,0,1],
 *   [8,2],
 *   [7]
 * ]
 * @author Hxkandwal
 */
public class BinaryTreeVerticalOrderTraversal extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return Arrays.asList();
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Object[]> queue = new LinkedList<>();
        queue.offer (new Object [] { root, 0 });
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0) {
                Object[] item = queue.poll();
                TreeNode n = (TreeNode) item [0];
                Integer l = (Integer) item [1];

                map.computeIfAbsent(l, k -> new ArrayList<>()).add(n.val);
                if (n.left != null) queue.offer (new Object[] { n.left, l - 1 });
                if (n.right != null) queue.offer (new Object[] { n.right, l + 1 });
            }
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int k : map.keySet()) {
            min = Math.min(min, k);
            max = Math.max(max, k);
        }
        for (int idx = min; idx <= max; idx ++) ans.add(map.get (idx));
        return ans;
    }

    // other way : Identify range first
    int min = 0, max = 0;
    public List<List<Integer>> verticalOrderRangeFirst(TreeNode root) {
        if (root == null) return Arrays.asList();
        List<List<Integer>> ans = new ArrayList<>();
        dfsRange(root, 0);
        for (int idx = min; idx <= max; idx ++) ans.add(new ArrayList<>());
        Queue<Object[]> queue = new LinkedList<>();
        queue.offer (new Object[] {root, 0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0) {
                Object[] items = queue.poll();
                TreeNode n = (TreeNode) items [0];
                Integer l = (Integer) items [1];

                ans.get (Math.abs (min) + l).add(n.val);
                if (n.left != null) queue.offer (new Object[] { n.left, l - 1});
                if (n.right != null) queue.offer (new Object[] { n.right, l + 1});
            }
        }
        return ans;
    }

    private void dfsRange(TreeNode n, int l) {
        if (n == null) return;
        max = Math.max(max, l);
        min = Math.min(min, l);
        dfsRange(n.left, l - 1);
        dfsRange(n.right, l + 1);
    }
}
