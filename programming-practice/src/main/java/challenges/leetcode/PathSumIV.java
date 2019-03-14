package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 666. Path Sum IV
 *
 * If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.
 * For each integer in this list:
 *  1. The hundreds digit represents the depth D of this node, 1 <= D <= 4.
 *  2. The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
 *  3. The units digit represents the value V of this node, 0 <= V <= 9.
 *
 * Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root
 * towards the leaves.
 *
 * Example 1:
 *              Input: [113, 215, 221]
 *              Output: 12
 *              Explanation:
 *                          The tree that the list represents is:
 *
 *                                      3
 *                                    / \
 *                                   5   1
 *
 *                          The path sum is (3 + 5) + (3 + 1) = 12.
 *
 * Example 2:
 *              Input: [113, 221]
 *              Output: 4
 *              Explanation:
 *                          The tree that the list represents is:
 *
 *                                      3
 *                                       \
 *                                        1
 *
 *                          The path sum is (3 + 1) = 4.
 *
 * @author Hxkandwal
 */
public class PathSumIV extends AbstractCustomTestRunner {

    // bottom up sum strategy did not work on unbalanced trees such as : [111,217,221,315,415]
    // so, using top down sum strategy.
    public int _pathSum(int[] nums) {
        Integer [] tree = new Integer [32];
        for (int num : nums) {
            int depth = num / 100 - 1, pos = (num % 100) / 10 - 1, val = num % 10;
            tree [(int) Math.pow (2, depth) + pos] = val;
        }

        int ans = 0;
        // either we can use local queue technique (iterative), or dfs (pass and return total sum value)
        // or could use global variable update technique.
        Queue<Integer> queue = new LinkedList<>();
        queue.offer (1);
        while (!queue.isEmpty()) {
            int idx = queue.poll ();
            int left = 2 * idx, right = 2 * idx + 1;
            if (left >= tree.length || (tree [left] == null && tree [right] == null))
                ans += tree [idx];
            else {
                if (tree [left] != null) {
                    tree [left] += tree [idx];
                    queue.offer (left);
                }
                if (tree [right] != null) {
                    tree [right] += tree [idx];
                    queue.offer (right);
                }
            }
        }
        return ans;
    }
}
