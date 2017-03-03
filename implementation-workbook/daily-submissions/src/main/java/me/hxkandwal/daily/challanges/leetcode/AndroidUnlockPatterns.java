package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 351. Android Unlock Patterns
 * 
 * @author Hxkandwal
 */
public class AndroidUnlockPatterns extends AbstractCustomTestRunner {

	public int numberOfPatterns(int m, int n) {
        if (m == 1 && n == 1) return  9;
        int[][] jump = new int [10][10];
        jump [1] [3] = jump [3] [1] = 2;
        jump [4] [6] = jump [6] [4] = 5;
        jump [7] [9] = jump [9] [7] = 8;
        jump [1] [7] = jump [7] [1] = 4;
        jump [2] [8] = jump [8] [2] = 5;
        jump [3] [9] = jump [9] [3] = 6;
        jump [1] [9] = jump [9] [1] = 5;
        jump [3] [7] = jump [7] [3] = 5;
        
        boolean [] visited = new boolean [10];
        int res = 0;
        res += dfs (visited, jump, 1, 1, m, n, 0) * 4;
        res += dfs (visited, jump, 2, 1, m, n, 0) * 4;
        res += dfs (visited, jump, 5, 1, m, n, 0);
        return res;
    }
    
    private int dfs (boolean [] visited, int[][] jump, int start, int rlen, int m, int n, int count) {
        if (rlen >= m) count ++;
        rlen ++;
        if (rlen > n) return count;
        
        visited [start] = true;
        for (int num = 1; num <= 9; num ++) {
            int jumpVal = jump [num][start];
            if (!visited [num] && (jumpVal == 0 || visited [jumpVal]))
                count = dfs (visited, jump, num, rlen, m, n, count);
        }
        visited [start] = false;
        
        return count;
    }
    
}
