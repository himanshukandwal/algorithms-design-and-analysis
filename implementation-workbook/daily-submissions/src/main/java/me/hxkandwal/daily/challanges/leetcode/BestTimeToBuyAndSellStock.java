package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	
	private BestTimeToBuyAndSellStock() {}
	
	public static int _maxProfit(int[] prices) {
		int result = 0;
		int minIdx = 0;
		
		for (int idx = 1; idx < prices.length; idx ++)
			if (prices [idx] < prices[minIdx])
				minIdx = idx;
		
		if (minIdx < prices.length)
			for (int idx = minIdx; idx < prices.length; idx ++) 
				result = Math.max(result, prices [idx] - prices [minIdx]);
		
		return result;
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
