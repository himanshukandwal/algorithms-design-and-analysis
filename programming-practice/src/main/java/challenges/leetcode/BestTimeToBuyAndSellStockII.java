package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 122. Best Time to Buy and Sell Stock II
 * 
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the 
 * stock multiple times). 
 * 
 * However, you may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * 
 * @author Hxkandwal
 */
public class BestTimeToBuyAndSellStockII extends AbstractCustomTestRunner {
	
	private static BestTimeToBuyAndSellStockII _instance = new BestTimeToBuyAndSellStockII();
	
	private BestTimeToBuyAndSellStockII() {}
	
    public static int _maxProfit (int[] prices) {
    	if (prices == null || prices.length == 0)
    		return 0;
    	
    	int [] dp = new int [prices.length];
        
    	for (int idx = 1; idx < prices.length; idx ++)
    		dp [idx] = dp [idx - 1] + (prices [idx] > prices [idx - 1] ? (prices [idx] - prices [idx - 1]) : 0);
    	
    	return dp [dp.length - 1];
    }
	
    public static int _maxProfit2 (int[] prices) {
    	int result = 0;
    	
    	for (int idx = 1; idx < prices.length; idx ++)
    		result = (prices [idx] > prices [idx - 1] ? (result + prices [idx] - prices [idx - 1]) : result);
    	
    	return result; 
    }	
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2, 4 }, 3);
		_instance.runTest(new int[] { 1, 2, 4, 9, 8, 10 }, 10);
	}
	
	public void runTest(final int[] prices, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { prices });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
