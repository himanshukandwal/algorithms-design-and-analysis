package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import challenges.AbstractCustomTestRunner;

/**
 * 290. Word Pattern
 * 
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * 
 * Examples:
 * 		pattern = "abba", str = "dog cat cat dog" should return true.
 * 		pattern = "abba", str = "dog cat cat fish" should return false.
 * 		pattern = "aaaa", str = "dog cat cat dog" should return false.
 * 		pattern = "abba", str = "dog dog dog dog" should return false.
 * 
 * Notes: 
 * 	You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 * 
 * @author Hxkandwal
 */
public class WordPattern extends AbstractCustomTestRunner {
	
	private static WordPattern _instance = new WordPattern();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean _wordPattern(String pattern, String str) {
		String [] parts = str.split ("\\ ");
        if (pattern.length() != parts.length) return false;
        
        Map map = new HashMap();
        for (int idx = 0; idx < pattern.length(); idx ++)
            if (!Objects.equals (map.put(pattern.charAt(idx), idx), map.put(parts [idx], idx)))
            	return false;
        return true;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("abba", "dog cat cat dog", true);
	}

	public void runTest(final String pattern, final String str, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { pattern, str });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}