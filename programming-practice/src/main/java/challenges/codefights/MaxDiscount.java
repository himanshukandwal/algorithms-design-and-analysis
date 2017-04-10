package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

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
		Arrays.fill(dp, -1);
		return maxDiscountInner(prices, dp, 0);
	}
	
	private static int maxDiscountInner (int [] prices, int [] dp, int index) {
		if (index + 2 >= prices.length) return 0;
		if (dp [index] != -1) return dp [index];
		int min = Math.min (prices [index], Math.min (prices [index + 1], prices [index + 2]));
		int max =  Math.max (min + maxDiscountInner (prices, dp, index + 3), maxDiscountInner (prices, dp, index + 1));
		return dp [index] = max;
	}
	
    // driver method
    public static void main(String[] args) {
    	_instance.runTest(new int [] { 1, 2, 7, 8, 10, 2 }, 7);
        _instance.runTest(new int [] { 10, 20, 17, 7, 16, 19, 16 }, 26);
        _instance.runTest(new int [] { 85, 23, 22, 94, 27, 23, 86, 25, 85, 90, 73, 67 }, 137);
    }

    public void runTest(final int[] prices, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { prices });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
    
}