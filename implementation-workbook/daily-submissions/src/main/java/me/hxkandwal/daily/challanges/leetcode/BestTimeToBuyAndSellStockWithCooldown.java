package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 309. Best Time to Buy and Sell Stock with Cooldown
 * 
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock 
 * multiple times) with the following restrictions:
 * 
 * 	You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * 	After you sell your stock, you cannot buy stock on next day. (i.e., cooldown 1 day)
 * 
 * Example:
 * 
 * 		prices = [1, 2, 3, 0, 2]
 * 		maxProfit = 3
 * 		transactions = [buy, sell, cooldown, buy, sell]
 * 
 * @author Hxkandwal
 *
 */
public class BestTimeToBuyAndSellStockWithCooldown extends AbstractCustomTestRunner {
	
	private static BestTimeToBuyAndSellStockWithCooldown _instance = new BestTimeToBuyAndSellStockWithCooldown();
	
	private BestTimeToBuyAndSellStockWithCooldown() {}
	
	public static int _maxProfit(int[] prices) {
		int [] dp = new int [prices.length]; 
		
		Boolean dipped = null;
		for (int idx = 1; idx < prices.length; idx ++) {
			if (prices [idx] > prices [idx - 1]) {
				dipped = (dipped == null ? false : dipped);
				
				int diff = prices [idx] - prices [idx - 1];
				
				if (idx - 3 >= 0 && dipped) {
					if (prices [idx - 3] > prices [idx])
						dp [idx] =  Math.max (dp [idx - 2], dp [idx - 3] + diff);
					else
						dp [idx] =  Math.max (Math.max (dp [idx - 2], dp [idx - 3] + diff), dp [idx - 3] + prices [idx] - prices [idx - 3]);
				} else
					dp [idx] = dp [idx - 1] + diff;
				
				dipped = false;
			} else {
				dipped = true;
				dp [idx] = dp [idx - 1];
			}
		}
		
        return (prices == null || prices.length == 0 ? 0 : dp [dp.length - 1]);
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2, 3, 0, 2 }, 3);
		_instance.runTest(new int[] { 1, 2, 4 }, 3);
		_instance.runTest(new int[] { 6, 1, 3, 2, 4, 7 }, 6);
		_instance.runTest(new int[] { 8, 3, 6, 2, 8, 8, 8, 4, 2, 0, 7, 2, 9, 4, 9 }, 18);
	}
	
	public void runTest(final int[] prices, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { prices });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
