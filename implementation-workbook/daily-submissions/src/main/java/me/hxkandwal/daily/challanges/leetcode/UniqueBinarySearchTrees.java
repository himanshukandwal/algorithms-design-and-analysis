package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 96. Unique Binary Search Trees
 * 
 * @author Hxkandwal
 */
public class UniqueBinarySearchTrees extends AbstractCustomTestRunner {
	
    public int numTrees(int n) {
        int [] num = new int [n + 1];
        num [0] = 1;
        num [1] = 1;
        
        for (int idx = 2; idx <= n; idx ++)
            for (int j = 1; j <= idx; j ++)
                num [idx] += num [j - 1] * num [idx - j];
        return num [n];
    }

}
