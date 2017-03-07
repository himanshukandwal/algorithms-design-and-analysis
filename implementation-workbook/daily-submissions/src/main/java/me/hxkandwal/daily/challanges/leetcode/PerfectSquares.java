package me.hxkandwal.daily.challanges.leetcode;

import java.util.Arrays;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 279. Perfect Squares
 * 
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * 
 * For example, 
 * 		given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 * 
 * @author Hxkandwal
 */
public class PerfectSquares extends AbstractCustomTestRunner {

    public static int _numSquares(int n) {
        if (n <= 0) return 0;
        int [] dp = new int [n + 1];
        Arrays.fill (dp, Integer.MAX_VALUE);
        
        dp [0] = 0;
        for (int idx = 1; idx <= n; idx ++)
            for (int iidx = 1; iidx * iidx <= idx; iidx ++)
                dp [idx] = Math.min (dp [idx], dp [idx - iidx * iidx] + 1);
                
        return dp [n];
    }
    
}
