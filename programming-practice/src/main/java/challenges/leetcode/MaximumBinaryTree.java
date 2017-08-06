package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;

/**
 * 654. Maximum Binary Tree
 *
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 *
 *  1. The root is the maximum number in the array.
 *  2. The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 *  3. The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 *
 * Construct the maximum tree by the given array and output the root node of this tree.
 *
 * Example 1:
 *      Input: [3,2,1,6,0,5]
 *      Output: return the tree root node representing the following tree:
 *
 *                  6
 *                /   \
 *               3     5
 *               \    /
 *                2  0
 *                \
 *                 1
 *
 * Note: The size of the given array will be in the range [1,1000].
 *
 * Created by Hxkandwal
 */
public class MaximumBinaryTree extends AbstractCustomTestRunner {

    public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0) return null;
        int maxIdx = 0;
        for (int idx = 0; idx < nums.length; idx ++) if (nums [idx] > nums [maxIdx]) maxIdx = idx;
        TreeNode n = new TreeNode (nums [maxIdx]);
        n.left = constructMaximumBinaryTree (Arrays.copyOfRange(nums, 0, maxIdx));
        n.right = constructMaximumBinaryTree (Arrays.copyOfRange(nums, maxIdx + 1, nums.length));
        return n;
    }

}
