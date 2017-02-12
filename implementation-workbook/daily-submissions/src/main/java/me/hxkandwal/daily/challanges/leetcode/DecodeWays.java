package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 91. Decode Ways
 * 
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 
 * 		'A' -> 1
 * 		'B' -> 2
 * 		...
 * 		'Z' -> 26
 * 
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * For example, 
 * 	Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * 	The number of ways decoding "12" is 2.
 * 
 * @author Hxkandwal
 *
 */
public class DecodeWays extends AbstractCustomTestRunner {
	
	private static DecodeWays _instance = new DecodeWays();
	
	private DecodeWays() {}
			
    public static int _numDecodings(String s) {
    	int n = s.length();
        if (n == 0) return 0;
        
        int[] memo = new int [n+1];
        memo [n]  = 1;
        memo [n-1] = s.charAt (n-1) != '0' ? 1 : 0;
        
        for (int i = n - 2; i >= 0; i--)
            if (s.charAt(i) == '0') continue;
            else memo [i] = (Integer.parseInt(s.substring (i, i + 2)) <= 26) ? memo [i+1] + memo [i+2] : memo [i+1];
        
        return memo [0];
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("", 0);
		_instance.runTest("0", 0);
		_instance.runTest("1", 1);
		_instance.runTest("00000000", 0);
		_instance.runTest("12", 2);
		_instance.runTest("10", 1);
		_instance.runTest("100", 0);
		_instance.runTest("101", 1);
		_instance.runTest("01", 0);
		_instance.runTest("27", 1);
		_instance.runTest("110", 1);
	}
	
	public void runTest(final String s, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
