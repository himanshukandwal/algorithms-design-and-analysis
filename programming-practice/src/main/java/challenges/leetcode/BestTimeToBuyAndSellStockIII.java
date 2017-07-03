package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 123. Best Time to Buy and Sell Stock III
 * 
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *  
 * @author Hxkandwal
 */
public class BestTimeToBuyAndSellStockIII extends AbstractCustomTestRunner {
	
	private static BestTimeToBuyAndSellStockIII _instance = new BestTimeToBuyAndSellStockIII();
	
	// using MCM rule.
	public int _maxProfitMCM(int[] prices) {
        if (prices.length == 0) return 0;
        int min = prices [0], ans = 0;
        for (int idx = 1; idx < prices.length; idx ++) 
            if (min > prices [idx]) min = prices [idx];
            else ans = Math.max (ans, prices [idx] - min + max (prices, idx, prices.length - 1));
        return ans;
    }
    
	// simple hook for the MCM engine.
    private int max (int [] prices, int start, int end) {
        int min = prices [start], ans = 0;
        for (int idx = start; idx <= end; idx ++)
            if (min > prices [idx]) min = prices [idx];
            else ans = Math.max (ans, prices [idx] - min);
        return ans;
    }
    
    public int _maxProfit(int[] prices) {
    	if (prices == null || prices.length <= 1) return 0;
    	
    	int [][] dp = new int [3][prices.length];
    	
    	for (int transaction = 1; transaction < 3; transaction ++) {
    		int maxDiff = 0 - prices [0];
    		
			for (int day = 1; day < prices.length; day ++) {
				dp [transaction][day] = Math.max(dp [transaction][day - 1], maxDiff + prices [day]);
				maxDiff = Math.max(maxDiff, dp [transaction - 1][day] - prices [day]);
			}
		}
    	
    	return dp [2][prices.length - 1];
    }	

	// driver method
	public static void main(String[] args) throws FileNotFoundException {
		_instance.runTest(new int [] { 1, 2 }, 1);
	}
	
	public void runTest(final int[] prices, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { prices });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}    

}
