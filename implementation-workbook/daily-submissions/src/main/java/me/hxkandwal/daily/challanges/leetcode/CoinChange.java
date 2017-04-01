package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 322. Coin Change
 * 
 * You are given coins of different denominations and a total amount of money amount. 
 * Write a function to compute the fewest number of coins that you need to make up that amount. 
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * 
 * Example 1:
 * 		coins = [1, 2, 5], amount = 11
 * 		return 3 (11 = 5 + 5 + 1)
 * 
 * Example 2:
 * 		coins = [2], amount = 3
 * 		return -1.
 * 
 * Note: You may assume that you have an infinite number of each kind of coin.
 * 
 * @author Hxkandwal
 */
public class CoinChange extends AbstractCustomTestRunner {
	
	private static CoinChange _instance = new CoinChange();
	
	public int _coinChange(int[] coins, int amount) {
		int [] dp = new int [amount + 1];
        for (int idx = 1; idx < dp.length; idx ++) dp [idx] = amount + 1;
        Arrays.sort (coins);
        
        for (int sum = 1; sum <= amount; sum ++)
            for (int idx = 0; idx < coins.length && sum >= coins [idx]; idx ++)
                dp [sum] = Math.min (dp [sum], dp [sum - coins [idx]] + 1);
        
        return dp[amount] > amount ? -1 : dp [amount];
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 2, 5 }, 11, 3);
		_instance.runTest(new int [] { 2 }, 3, -1);
	}

	public void runTest(final int[] coins, final int amount, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { coins, amount });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
