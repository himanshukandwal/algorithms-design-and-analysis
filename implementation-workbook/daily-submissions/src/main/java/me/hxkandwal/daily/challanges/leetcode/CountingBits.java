package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 338. Counting Bits
 * 
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation 
 * and return them as an array.
 * 
 * Example:
 * 		For num = 5 you should return [0,1,1,2,1,2].
 * 		Follow up:
 * 			It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
 * 			Space complexity should be O(n).
 * 			Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 * 
 * @author Hxkandwal
 */
public class CountingBits extends AbstractCustomTestRunner {
	
	private static CountingBits _instance  = new CountingBits();
	
	private CountingBits() {}
	
	public static int[] _countBits(int num) {
        int [] ans = new int [num + 1];
        ans [0] = 0;
        
        for (int n = 1; n <= num; n ++) {
        	int count = 0;
        	for (int pos = 31; pos >= 0; pos --)
        		count += (n >>> pos & 1) == 1 ? 1 : 0;
        	
        	ans [n] = count; 
		}
        
        return ans;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(5, new int[] { 0, 1, 1, 2, 1, 2 });
	}

	public void runTest(final int n, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
