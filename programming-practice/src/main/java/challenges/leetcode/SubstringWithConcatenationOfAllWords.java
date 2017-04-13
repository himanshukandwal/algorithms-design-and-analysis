package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 30. Substring with Concatenation of All Words
 * 
 * You are given a string, s, and a list of words, words, that are all of the same length. 
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once 
 * and without any intervening characters.
 * 
 * For example, given: s: "barfoothefoobarman" words: ["foo", "bar"]
 * 				You should return the indices: [0,9].
 * 				(order does not matter).
 * 
 * @author Hxkandwal
 */
public class SubstringWithConcatenationOfAllWords extends AbstractCustomTestRunner {
	
	private static SubstringWithConcatenationOfAllWords _instance = new SubstringWithConcatenationOfAllWords();
	
	public List<Integer> _findSubstring(String s, String[] words) {
		List<Integer> res = new ArrayList<Integer>();
		if (s == null || words == null || words.length == 0) return res;
		int len = words[0].length(); // length of each word

		Map<String, Integer> map = new HashMap<String, Integer>(); // map for L
		for (String word : words) map.put(word, map.getOrDefault(word, 0) + 1);

		for (int i = 0; i <= s.length() - len * words.length; i ++) {
			Map<String, Integer> copy = new HashMap<String, Integer>(map);
			
			for (int j = 0; j < words.length; j++) { // check if match
				String str = s.substring(i + j * len, i + j * len + len); // next word
				
				if (copy.containsKey(str)) { // is in remaining words
					if (copy.put(str, copy.get(str) - 1) == 1) copy.remove(str);
					if (copy.isEmpty()) { /* matches */  res.add(i); break; }
				} else break; // not in L
			}
		}
		return res;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("barfoothefoobarman", new String [] { "foo", "bar" }, Arrays.asList(0, 9));
		_instance.runTest("barfoofoobarthefoobarman", new String [] {"bar","foo","the"}, Arrays.asList(6, 9, 12));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final String s, final String[] words, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, words });

		for (Object answer : answers)
			assertThat((List<Integer>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 
	
}
