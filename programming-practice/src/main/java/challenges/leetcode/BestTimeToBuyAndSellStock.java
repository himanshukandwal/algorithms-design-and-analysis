package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 121. Best Time to Buy and Sell Stock
 * 
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * 
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * 
 * Example 1:
 * 		Input: [7, 1, 5, 3, 6, 4]
 * 		Output: 5
 * 		
 * 		Max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 * 
 * Example 2:
 * 		Input: [7, 6, 4, 3, 1]
 * 		Output: 0
 * 		
 * 		In this case, no transaction is done, i.e. max profit = 0.
 * 
 * @author Hxkandwal
 *
 */
public class BestTimeToBuyAndSellStock extends AbstractCustomTestRunner {
	
	private static BestTimeToBuyAndSellStock _instance = new BestTimeToBuyAndSellStock();
	
	public static int _maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) return 0;
		int max = 0, min = prices [0];
		for (int p : prices)
			if (p > min) max = Math.max(max, p - min); else min = p;
		return max;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 7, 1, 5, 3, 6, 4 }, 5);
		_instance.runTest(new int[] { 7, 6, 4, 3, 1 }, 0);
		_instance.runTest(new int[] { 2, 4, 1 }, 2);
	}
	
	public void runTest(final int[] prices, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { prices });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
