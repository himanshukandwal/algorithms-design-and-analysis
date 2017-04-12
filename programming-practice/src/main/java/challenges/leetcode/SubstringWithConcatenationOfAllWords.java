package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	class Node {
        char ch;
        Node [] children = new Node [256];
        boolean terminal;
        
        Node (char ch) { this.ch = ch; }
        
        public void add (String word) {
            Node t = this;
            for (char ch : word.toCharArray()) {
                if (t.children [ch] == null) t.children [ch] = new Node (ch);
                t = t.children [ch];
            }
            t.terminal = true;
        }
    }
    
    public List<Integer> _findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        Node r = new Node (' '), t = r;
        for (String word : words) r.add (word);
        Set<String> seen = new HashSet<>();
        int start = 0, end = 0, wstart = 0;
        while (end < s.length()) {
            char ch = s.charAt (end ++);
            if (t.children [ch] == null) { t = r; seen.clear(); start = wstart = end; }
            else {
                t = t.children [ch];
                if (t.terminal) {
                    if (!seen.add (s.substring (wstart, end))) { 
                    	String lookupWord = s.substring (wstart, end); 
                    	int nstart = start;
                    	while (!s.substring(start, nstart).equals(lookupWord)) {
                    		seen.remove(s.substring(start, nstart));
                    		t = r; start = nstart;
                    		while (!t.terminal) t = t.children [s.charAt(nstart ++)];
                    	}
                    	start = nstart; 
                    }
                    if (seen.size() == words.length) { 
                    	ans.add (start); 
                    	wstart = start; t = r; 
                    	t = t.children [s.charAt(wstart ++)];
                    	while (!t.terminal) t = t.children [s.charAt(wstart ++)];
                    	seen.remove(s.substring(start, wstart));
                    	start = wstart;
                    }
                    wstart = end; t = r;
                }
            }
        }
        return ans;
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
