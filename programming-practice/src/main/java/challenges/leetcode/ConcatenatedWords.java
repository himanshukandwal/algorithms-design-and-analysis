package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 472. Concatenated Words
 * 
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 * 
 * Example:
 * 		Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * 		Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * 
 * 		Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * 				  	 "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * 					 "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 * 
 *	Note: 	
 *		The number of elements of the given array will not exceed 10,000
 *		The length sum of elements in the given array will not exceed 600,000.
 *		All the input string will only include lower case letters.
 *		The returned elements order does not matter.
 *
 * @author Hxkandwal
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class ConcatenatedWords extends AbstractCustomTestRunner {
	
	private static ConcatenatedWords _instance = new ConcatenatedWords();
	
	private ConcatenatedWords() {}

	// basic trie data structure.
	public static class Trie {
		char ch;
		Trie[] children = new Trie [256];
		boolean isTerminal;
		
		public Trie(char ch) {
			this.ch = ch;
		}
		
		public Trie getItem(String word) {
			Trie node = this;
			for (int idx = 0; idx < word.length(); idx ++) {
				char ch = word.charAt(idx);
				node = node.children [ch] = (node.children [ch] == null) ? new Trie(ch) : node.children [ch];
			}
			node.isTerminal = true;
			return node;
		}
		
		public List<String> getPrefixes(String word) {
			List<String> prefixes = new ArrayList<>();
			Trie node = this;
			StringBuilder builder = new StringBuilder();
			
			for (int idx = 0; idx < word.length(); idx ++) {
				char ch = word.charAt(idx);
				
				if (node.children [ch] == null)  return prefixes;
				
				builder.append(ch);
				node = node.children [ch];
				if (node.isTerminal)
					prefixes.add(builder.toString()); 
			}
			
			return prefixes;
		}
	}
	
	public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        Trie root = new Trie(' ');
        List<String> answer = new ArrayList<>();
        
        // populate data-structure
        for (String word : words) 
        	root.getItem(word);
        
        Queue<String> queue = new LinkedList<>();
        for (String word : words) { 
        	List<String> prefixes = root.getPrefixes(word);
        	
        	for (String prefix : prefixes)
        		if (prefix.length() < word.length())
        			queue.add(word.substring(prefix.length()));
        	
        	while (!queue.isEmpty()) {
        		String suffix = queue.poll();
        		List<String> suffixPrefixes = root.getPrefixes(suffix);
        		
        		for (String suffixPrefix : suffixPrefixes) {
        			if (suffixPrefix.length() == suffix.length()) {
        				answer.add(word);
        				queue.clear();
        				break;
        			} else 
        				queue.add(suffix.substring(suffixPrefix.length()));
        		}
        	}
        }
        
        answer.stream().forEach(System.out::println);
		return answer;
    }
	
	public static List<String> _findAllConcatenatedWordsInADictOptimized(String[] words) {
		List<String> answer = new ArrayList<>();
		
		if (words.length > 1) {
			Set<String> hash = new HashSet<>();
			for (String word : words) hash.add(word);
			
			for (String word : words) {
				if (word.length() > 1) {
					boolean [] f = new boolean [word.length() + 1];
					f [0] = true;
					
					for (int idx = 1; idx < f.length; idx ++)
						for (int innerIdx = idx - 1; innerIdx >= 0; innerIdx --)
							if (f [idx] = (f [innerIdx] && hash.contains(word.substring(innerIdx, idx)) && !word.substring(innerIdx, idx).equals(word)))
								break;
					
					if (f [f.length - 1]) answer.add(word);
				}
			}
		}
		
		return answer;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new String[] { "" }, new ArrayList());
		
		_instance.runTest(new String[] { "a","b","ab","abc" }, new ArrayList() {{ add ("ab"); }});
		
		_instance.runTest(new String[] { "cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat" }, 
						  new ArrayList() {{ add ("catsdogcats"); add ("dogcatsdog"); add ("ratcatdogcat"); }});
	}

	public void runTest(final String[] words, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { words });

		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
