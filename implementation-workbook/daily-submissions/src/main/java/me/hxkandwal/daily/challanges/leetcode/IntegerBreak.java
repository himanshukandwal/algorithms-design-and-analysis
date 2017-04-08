package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 343. Integer Break
 * 
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize 
 * the product of those integers. Return the maximum product you can get.
 * 
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 * 
 * Note: You may assume that n is not less than 2 and not larger than 58.
 * 
 * @author Hxkandwal
 */
public class IntegerBreak extends AbstractCustomTestRunner {
	
	private static IntegerBreak _instance = new IntegerBreak();
	
    public int _integerBreak(int n) {
    	if (n <= 1) return 0;
    	int [] dp = new int [n + 1];
    	dp [1] = 1;
        for (int idx = 2; idx <= n; idx ++) integerBreakInner (dp, idx);
        return dp [n];
    }
    
    private int integerBreakInner (int [] dp, int n) {
        int ans = 0;
        for (int idx = 1; idx <= n/2; idx ++)
            ans = Math.max (ans, Math.max(idx, dp [idx]) * Math.max((n - idx), dp [n - idx]));    
        return dp [n] = ans;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(10, 36);
        _instance.runTest(8, 18);
    }

    public void runTest(final int n, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { n });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
