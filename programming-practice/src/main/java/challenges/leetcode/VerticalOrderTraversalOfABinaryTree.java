package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

/**
 * 987. Vertical Order Traversal of a Binary Tree
 *
 * Given a binary tree, return the vertical order traversal of its nodes values. For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
 * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 *
 * Example 1:
 *      Input: [3,9,20,null,null,15,7]
 *      Output: [[9],[3,15],[20],[7]]
 *      Explanation: Without loss of generality, we can assume the root node is at position (0, 0):
 *                   Then, the node with value 9 occurs at position (-1, -1);
 *                   The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
 *                   The node with value 20 occurs at position (1, -1);
 *                   The node with value 7 occurs at position (2, -2).
 *
 * Example 2:
 *      Input: [1,2,3,4,5,6,7]
 *      Output: [[4],[2],[1,5,6],[3],[7]]
 *      Explanation: The node with value 5 and the node with value 6 have the same position according to the given scheme.
 *                   However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 *
 * Note:
 *  The tree will have between 1 and 1000 nodes.
 *  Each node's value will be between 0 and 1000.
 *
 * @author Hxkandwal
 */
public class VerticalOrderTraversalOfABinaryTree extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> _verticalTraversal(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();

        Queue<Object[]> queue = new LinkedList<>();
        queue.offer (new Object[] { root, 0 });

        while (!queue.isEmpty()) {
            int size = queue.size();
            Map<Integer, List<Integer>> levelMap = new HashMap<>();
            while (size -- > 0) {
                Object[] objs = queue.poll();
                TreeNode n = (TreeNode) objs [0];
                int base = (Integer) objs [1];
                levelMap.computeIfAbsent(base, k -> new ArrayList<>()).add (n.val);

                if (n.left != null) queue.offer (new Object[] { n.left, base - 1 });
                if (n.right != null) queue.offer (new Object[] { n.right, base + 1 });
            }

            for (Integer base : levelMap.keySet()) {
                List<Integer> list = levelMap.get (base);
                Collections.sort (list);
                map.computeIfAbsent(base, k -> new ArrayList<>()).addAll (list);
            }
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int k : map.keySet()) { min = Math.min (min, k); max = Math.max (max, k); }
        for (int idx = min; idx <= max; idx ++) ans.add (map.get (idx));
        return ans;
    }

    // better solution
    class Location implements Comparable<Location>{
        int x, y, val;
        Location(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Location that) {
            if (this.x != that.x)
                return Integer.compare(this.x, that.x);
            else if (this.y != that.y)
                return Integer.compare(this.y, that.y);
            else
                return Integer.compare(this.val, that.val);
        }
    }

    public List<List<Integer>> _verticalTraversalBetter(TreeNode root) {
        // Each location is a node's x position, y position, and value
        List<Location> locations = new ArrayList<>();

        dfs(locations, root, 0, 0);
        Collections.sort(locations);

        List<List<Integer>> ans = new ArrayList();
        ans.add(new ArrayList<Integer>());

        int prev = locations.get(0).x;

        for (Location loc: locations) {
            // If the x value changed, it's part of a new report.
            if (loc.x != prev) {
                prev = loc.x;
                ans.add(new ArrayList<Integer>());
            }

            // We always add the node's value to the latest report.
            ans.get(ans.size() - 1).add(loc.val);
        }

        return ans;
    }

    public void dfs(List<Location> locations, TreeNode node, int x, int y) {
        if (node != null) {
            locations.add(new Location(x, y, node.val));
            dfs(locations, node.left, x-1, y+1);
            dfs(locations, node.right, x+1, y+1);
        }
    }

}