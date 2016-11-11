package me.hxkandwal.daily.challanges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * String Construction
 * 
 * Amanda has a string, s, of m lowercase letters that she wants to copy into a new string, p. 
 * She can perform the following operations any number of times to construct string p:
 * 	1.	Append a character to the end of string p at a cost of 1 dollar.
 * 	2.	Choose any substring of p and append it to the end of p at no charge.
 * 
 * Example:
 * a.		Input:	abcd
 * 			Output:	4
 * 
 * b.		Input:	abab
 * 			Output:	2
 * 
 * Note:
 * 		A substring of a string S is another string S' that occurs "in" S. 
 * 		For example, the substrings of the string "abc" are "a", "b", "c", "ab", "bc", and "abc".
 * 
 * link: https://www.hackerrank.com/challenges/string-construction
 * 
 * @author Hxkandwal
 * 
 */
public class StringConstruction extends AbstractCustomTestRunner {
	
	private static StringConstruction _instance = new StringConstruction();
	
	private StringConstruction() {}
	
	public static int _getConstructionCost(String s) {
		boolean [] alphabets = new boolean ['z' - 'a' + 1];
		int count = 0;
		for (int idx = 0; idx < s.length(); idx ++) 
			if (!alphabets [s.charAt(idx) - 'a']) {
				count ++;
				alphabets [s.charAt(idx) - 'a'] = true;
			}
				
		return count;
	}

    // driver method
    public static void main(String[] args) {
        _instance.runTest("abcd", 4);
        _instance.runTest("abab", 2);
        _instance.runTest("abababc", 3);
        _instance.runTest("abba", 2);
        _instance.runTest("abcba", 3);
    }

    public void runTest(final String s, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { s });

        for (Object answer : answers)
            assertThat((int) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
