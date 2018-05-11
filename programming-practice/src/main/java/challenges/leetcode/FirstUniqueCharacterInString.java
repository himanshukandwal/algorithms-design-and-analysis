package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 387. First Unique Character in a String
 * 
 * Given a string, find the first non-repeating character in it and return it's
 * index. If it doesn't exist, return -1.
 * 
 * Example : s = "leetcode" return 0. s = "loveleetcode" return 2.
 * 
 * @author Hxkandwal
 *
 */
public class FirstUniqueCharacterInString extends AbstractCustomTestRunner {
	
	private static FirstUniqueCharacterInString _instance = new FirstUniqueCharacterInString();
	
	public int _firstUniqCharCollections(String s) {
		int [] map = new int [256];
	    for (char ch : s.toCharArray()) map [ch] ++;
	    for (int idx = 0; idx < s.length(); idx ++) if (map [s.charAt(idx)] == 1) return idx;
	    return -1;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("leetcode", 0);
		_instance.runTest("loveleetcode", 2);
		_instance.runTest("aaabbcc", -1);
	}
	
	public void runTest(final String s, int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
