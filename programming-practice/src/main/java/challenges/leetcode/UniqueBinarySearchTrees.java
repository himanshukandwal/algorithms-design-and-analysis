package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 96. Unique Binary Search Trees
 *
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 *
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 *
 *      1         3     3      2      1
 *       \       /     /      / \      \
 *        3     2     1      1   3      2
 *       /     /       \                 \
 *      2     1         2                 3
 * 
 * @author Hxkandwal
 */
public class UniqueBinarySearchTrees extends AbstractCustomTestRunner {

    // bottom-up approach
    public int numTrees(int n) {
        int [] num = new int [n + 1];
        num [0] = 1;
        num [1] = 1;
        
        for (int idx = 2; idx <= n; idx ++)
            for (int j = 0; j < idx; j ++)
                num [idx] += num [j] * num [idx - j - 1];
        return num [n];
    }

    // top-down approach
    public int _numTrees(int n) {
        if (n <= 1) return n;
        return numTreesInner (new Integer [n][n], 0, n - 1);
    }

    private int numTreesInner (Integer [][] dp, int start, int end) {
        if (start >= end) return 1;
        if (dp [start][end] != null) return dp [start][end];

        int val = 0;
        for (int k = start; k <= end; k ++) val += numTreesInner (dp, start, k - 1) * numTreesInner (dp, k + 1, end);
        return dp [start][end] = val;
    }

}
