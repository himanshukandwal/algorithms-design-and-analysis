package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 38. Count and Say
 * 
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 	
 * 	1, 11, 21, 1211, 111221, ...
 * 
 * 	1 is read off as "one 1" or 11.
 * 	11 is read off as "two 1s" or 21.
 * 	21 is read off as "one 2, then one 1" or 1211.
 * 
 * Given an integer n, generate the nth sequence.
 * 
 * Note: The sequence of integers will be represented as a string.
 * 
 * @author Hxkandwal
 *
 */
public class CountAndSay extends AbstractCustomTestRunner {
	
	private static CountAndSay _instance = new CountAndSay();
	
	private CountAndSay() {}
	
    public String _countAndSay(int n) {
        
    	return null;
    }
	
	public void runTest(final char[][] board, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { board });
		
		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
			
		System.out.println("ok!");
	}
	
}
