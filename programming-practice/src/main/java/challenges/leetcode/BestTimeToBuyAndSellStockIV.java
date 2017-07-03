package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import challenges.AbstractCustomTestRunner;

/**
 * 188. Best Time to Buy and Sell Stock IV
 * 
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * 
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 * 
 * @author Hxkandwal
 */
public class BestTimeToBuyAndSellStockIV extends AbstractCustomTestRunner {
	
	private static BestTimeToBuyAndSellStockIV _instance = new BestTimeToBuyAndSellStockIV();
	
    public int _maxProfit(int k, int[] prices) {
    	if (prices.length == 0) return 0;
  
    	// case : if k >= n/2, then you can make maximum number of transactions.
    	if (k >= prices.length / 2) {
    		int maxProfit = 0;
    		
    		for (int idx = 1; idx < prices.length; idx ++) 
    			if (prices [idx] > prices [idx - 1]) maxProfit += prices[idx] - prices[idx-1];
    		
    		return maxProfit;
    	}
    	
    	// else build dp
        int [][] dp = new int [k + 1][prices.length];
        for (int transaction = 1; transaction <= k; transaction ++) {
        	int maxDiff = - prices [0];
        	
        	for (int day = 1; day < prices.length; day ++) {
        		dp [transaction][day] = Math.max(dp [transaction][day - 1], maxDiff + prices [day]);
        		maxDiff = Math.max(maxDiff, dp [transaction - 1][day] - prices [day]);
        	}
        }
        
    	return dp [k][prices.length - 1];
    }

	// driver method
	public static void main(String[] args) throws FileNotFoundException {
		_instance.runTest(2, new int [] { }, 0);
		_instance.runTest(1, new int [] { 1, 2 }, 1);
		_instance.runTest(2, new int[] { 6, 1, 3, 2, 4, 7 }, 7);
		
		testComplex("/src/test/resources/challenges/leetcode/Best-Time-To-Buy-And-Sell-Stock-IV-1.txt", 1648961);
	}
	
	private static void testComplex(final String filename, final int expectedOutput) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(System.getProperty("user.dir") +filename));
        int k = sc.nextInt();
        String[] pricesStr = sc.next().split(",");
        
        int [] prices = new int [pricesStr.length];
        for (int idx = 0; idx < pricesStr.length; idx ++)
			prices [idx] = Integer.valueOf(pricesStr [idx]).intValue();
        
        sc.close();
        _instance.runTest(k, prices, expectedOutput);
    }

	public void runTest(final int k, final int[] prices, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { k, prices });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
