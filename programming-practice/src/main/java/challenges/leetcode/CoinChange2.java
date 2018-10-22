package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 518. Coin Change 2
 * 
 * You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 *
 * Note: You can assume that
 * 	1. 0 <= amount <= 5000
 * 	2. 1 <= coin <= 5000
 * 	3. the number of coins is less than 500
 * 	4. the answer is guaranteed to fit into signed 32-bit integer
 *
 * 	Example 1:
 * 		Input: amount = 5, coins = [1, 2, 5]
 * 		Output: 4
 * 		Explanation: there are four ways to make up the amount:
 * 			5=5
 * 			5=2+2+1
 * 			5=2+1+1+1
 * 			5=1+1+1+1+1
 *
 * Example 2:
 * 		Input: amount = 3, coins = [2]
 * 		Output: 0
 * 		Explanation: the amount of 3 cannot be made up just with coins of 2.
 *
 * Example 3:
 * 		Input: amount = 10, coins = [10]
 * 		Output: 1
 * 
 * @author Hxkandwal
 */
public class CoinChange2 extends AbstractCustomTestRunner {
	
	private static CoinChange2 _instance = new CoinChange2();

	public int change(int amount, int[] coins) {
		int [][] dp = new int [coins.length + 1][amount + 1];
		dp [0][0] = 1;
		for (int r = 0; r < coins.length; r ++) {
			dp [r + 1][0] = 1;
			for (int c = 0; c < amount; c ++) {
				int coin = coins [r], sum = c + 1;
				dp [r + 1][c + 1] = dp [r][c + 1] + (sum >= coin ? dp[r + 1][sum - coin] : 0);
			}
		}
		return dp [coins.length][amount];
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 2, 5 }, 5, 4);
	}

	public void runTest(final int[] coins, final int amount, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[]{coins, amount});

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
}
