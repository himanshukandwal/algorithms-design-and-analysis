package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 66. Plus One
 * 
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * 
 * The digits are stored such that the most significant digit is at the head of the list.
 * 
 * @author Hxkandwal
 *
 */
public class PlusOne extends AbstractCustomTestRunner {

	private static PlusOne _instance = new PlusOne();
	
	private PlusOne() {}
	
    public int[] _plusOne(int[] digits) {
        if (digits == null || digits.length == 0)
            return new int[] { 1 };
        
        int carry = 0;
        for (int idx = digits.length - 1; idx >= 0; idx --) {
            int val = digits [idx] + (idx == digits.length - 1 ? 1 : 0) + carry;
            
            if (val >= 10)  {
                carry = 1;
                val %= 10;
            } else
                carry = 0;
            
            digits [idx] = val;
        }
        
        if (carry > 0) {
            int [] res = new int [digits.length + 1];
            res [0] = carry;
            
            for (int idx = 1; idx < res.length; idx ++)
                res [idx] = digits [idx -1];
                
            digits = res;    
        }
        
        return digits;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] {}, new int[] { 1 });
		_instance.runTest(null, new int[] { 1 });
		_instance.runTest(new int[] { 1 }, new int[] { 2 });
		_instance.runTest(new int[] { 1, 2 }, new int[] { 1, 3 });
		_instance.runTest(new int[] { 9 }, new int[] { 1, 0 });
	}

	public void runTest(final int[] digits, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { digits });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
