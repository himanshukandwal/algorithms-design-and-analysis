package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 205. Isomorphic Strings
 * 
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * 
 * All occurrences of a character must be replaced with another character while preserving the order 
 * of characters. No two characters may map to the same character but a character may map to itself.
 * 
 * For example,
 * 
 * (a)		Given "egg", "add", return true.
 * (b)		Given "foo", "bar", return false.
 * (c)		Given "paper", "title", return true.
 * 
 * @author Hxkandwal
 *
 */
public class IsomorphicStrings extends AbstractCustomTestRunner {

	private static IsomorphicStrings _instance = new IsomorphicStrings();

	private IsomorphicStrings() {}

	public static boolean _isIsomorphic(String s, String t) {
		if (s.length() == t.length()) {
			Map<Character, Character> replacementMap = new HashMap<>();
			
			for (int idx = 0; idx < s.length(); idx ++) {
				char sch = s.charAt(idx);
				char tch = t.charAt(idx);
				
				if (replacementMap.containsKey(sch) && tch != replacementMap.get(sch))
					return false;
				
				if (!replacementMap.containsKey(sch) && replacementMap.values().contains(tch))
					return false;
				
				replacementMap.put(sch, tch);
			}
			return true;
		}
		return false;
	}
	
	// driver method
    public static void main(String[] args) throws FileNotFoundException {
    	_instance.runTest("egg", "add", true);
    	_instance.runTest("ab", "aa", false);
		_instance.runTest("foo", "bar", false);
		_instance.runTest("paper", "title", true);
		_instance.runTest("abca", "zbxz", true);
		_instance.runTest("abcb", "zbxz", false);
		_instance.runTest("abcb", "zbxb", false);
		
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/leetcode/IsomorphicStrings-1.txt", false);
    }

    private static void testComplex(String filename, final boolean expectedOutput) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(System.getProperty("user.dir") + filename));
        _instance.runTest(sc.nextLine(), sc.nextLine(), expectedOutput);
        sc.close();
    }

	public void runTest(final String s, final String t, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, t });
		
		for (Object answer : answers) 
			assertThat((boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
