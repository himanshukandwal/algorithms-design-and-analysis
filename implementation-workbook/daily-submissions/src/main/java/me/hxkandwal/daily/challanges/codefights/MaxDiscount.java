package me.hxkandwal.daily.challanges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * maxDiscount
 * 
 * You're running a "Buy 2, Get 1 Free" promotion at your store. Your customers can group any 3 consecutive items 
 * in their shopping cart, and the cheapest item from every such group of 3 items will be free.
 * 
 * Given a list of prices, your task is to maximize the discount your customer can get.
 * Example:
 * 		For prices = [10, 20, 17, 7, 16, 19, 16], the output should be maxDiscount(prices) = 26.
 * 
 * The customer can group their items as follows: (10, 20, 17), 7, (16,19,16). 
 * Thus, they can get a $10 discount from the first group and a $16 discount from the second group, for a total discount of $26.
 * 
 * For prices = [1, 2, 7, 8, 10, 2], the output should be maxDiscount(prices) = 7.
 * The customer can group their items as follows: 1, 2, (7, 8, 10), 2. Thus, they can get a $7 discount.
 * 
 * @author Hxkandwal
 */
public class MaxDiscount extends AbstractCustomTestRunner {
	
	private static MaxDiscount _instance = new MaxDiscount();

	public int _maxDiscount(int[] prices) {
		int [] dp = new int [prices.length];
		for (int idx = 0; idx < prices.length - 2; idx ++)
			dp [idx] = Math.min (prices [idx], Math.min (prices [idx + 1], prices [idx + 2]));
		
		int idx = 0, max = 0;
		while (idx < prices.length - 2) {
			int lmax = Math.max (dp [idx], Math.max (dp [idx + 1], dp [idx + 2]));
			if (lmax == dp [idx]) idx += 3;
			else if (lmax == dp [idx + 1]) idx += 4;
			else if (lmax == dp [idx + 2]) idx += 5;
			max += lmax;
		}
		return max;
	}
	
    // driver method
    public static void main(String[] args) {
//    	_instance.runTest(new int [] { 1, 2, 7, 8, 10, 2 }, 7);
//        _instance.runTest(new int [] { 10, 20, 17, 7, 16, 19, 16 }, 26);
        _instance.runTest(new int [] { 85, 23, 22, 94, 27, 23, 86, 25, 85, 90, 73, 67 }, 137);
    }

    public void runTest(final int[] prices, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { prices });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
    
}