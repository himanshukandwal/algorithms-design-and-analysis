package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 336. Palindrome Pairs
 * 
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation 
 * of the two words, i.e. words[i] + words[j] is a palindrome.
 * 
 * Example 1:	Given words = ["bat", "tab", "cat"]
 * 				Return [[0, 1], [1, 0]]
 * 
 * 				The palindromes are ["battab", "tabbat"]
 * 
 * Example 2:	Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 * 				Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 * 
 * 				The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 * 
 * @author Hxkandwal
 */
public class PalindromePairs extends AbstractCustomTestRunner {
	
	private static PalindromePairs _instance = new PalindromePairs();

	public List<List<Integer>> _palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) map.put(words[i], i);
        
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) { // Note j=[0..len]: s1="",s2=word -> s1=word,s2=""
                String s1 = words[i].substring(0, j), s2 = words[i].substring(j);
                if (isPalindrome(s1)) { // word2 + s1 + s2 is palindrome
                    String t = new StringBuilder(s2).reverse().toString();
                    if (map.getOrDefault(t, i) != i) ret.add(Arrays.asList(map.get(t), i));
                }
                if (isPalindrome(s2) && !s2.isEmpty()) { // s1 + s2 + word2 is palindrome (avoid duplicate)
                    String t = new StringBuilder(s1).reverse().toString();
                    if (map.getOrDefault(t, i) != i) ret.add(Arrays.asList(i, map.get(t)));
                }
            }
        }
        return ret;
    }
    
    private boolean isPalindrome (String str) {
        str = str.trim ();
        int i = 0, j = str.length() - 1;
        while (i < j && str.charAt (i) == str.charAt (j)) { i ++; j --; }
        return (i == j || i > j);
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new String[] { "bat", "tab", "cat" }, Arrays.asList(Arrays.asList(1, 0), Arrays.asList(0, 1)));
	}

	public void runTest(final String[] words, final List<List<Integer>> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { words });

		for (Object answer : answers)
				assertThat((List<List<Integer>>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
		
}
