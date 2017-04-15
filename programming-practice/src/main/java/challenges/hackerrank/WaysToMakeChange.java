package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Coin Change
 * 
 * Print a single integer denoting the number of ways we can make change for n dollars using an infinite supply 
 * of our m types of coins.
 * 
 * Sample Input 0:
 * 		4 3
 * 		1 2 3
 * 
 * Sample Output 0
 * 		4
 * Explanation 0
 * 		For n = 4 and C = { 1, 2, 3 } there are four solutions:
 * 			{ 1, 1, 1, 1 }
 * 			{ 1, 1, 2 }
 * 			{ 2, 2 }
 * 			{ 1, 3 }
 * 
 * link: https://www.hackerrank.com/challenges/ctci-coin-change?h_r=next-challenge&h_v=zen
 * 
 * @author Hxkandwal
 */
public class WaysToMakeChange extends AbstractCustomTestRunner {
	
	private static WaysToMakeChange _instance = new WaysToMakeChange();

	// coins first case. 
	public static long _makeChange(int[] coins, int money) {
        long [] dp = new long [money + 1];
        Arrays.sort (coins);
        dp [0] = 1;
        for (int coin : coins) {
        	for (int m = 1; m <= money; m ++)
            	if (coin <= m) dp [m] += dp [m - coin];
        }
        return dp [money];
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 2, 3 }, 4, 4);
	}

	public void runTest(final int[] coins, final int money, final long expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { coins, money });

		for (Object answer : answers)
			assertThat((Long) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 
		
}
