package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 357. Count Numbers with Unique Digits
 * 
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
 * 
 * Example:
 * 		Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, 
 * 		excluding [11,22,33,44,55,66,77,88,99])
 * 
 * @author Hxkandwal
 */
public class CountNumbersWithUniqueDigits extends AbstractCustomTestRunner {
	
	private static CountNumbersWithUniqueDigits _instance = new CountNumbersWithUniqueDigits();
	
	private CountNumbersWithUniqueDigits() {}
	
    public static int _countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        int[] dp = new int [n + 1];
        dp [1] = 10;
        
        for (int idx = 2; idx < dp.length; idx ++) {
            int count = 9;
            for (int iidx = 0; iidx < idx - 1; iidx ++) count *=  (9 - iidx);
            
            dp [idx] = dp [idx - 1] + count;
        }
        return dp [dp.length - 1];
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(2, 91);
	}

	public void runTest(final int n, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
				assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
