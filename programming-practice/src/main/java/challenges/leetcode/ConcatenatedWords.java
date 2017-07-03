package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
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
public class ConcatenatedWords extends AbstractCustomTestRunner {
	
	private static ConcatenatedWords _instance = new ConcatenatedWords();

	// basic trie data structure.
	public class Node {
        private char ch;
        private boolean terminal;
        private Node[] children = new Node [256];
        
        public Node (char ch) { this.ch = ch; }
        
        public void add (String str) {
            Node t = this;
            for (char ch : str.toCharArray()) {
                if (t.children [ch] == null) t.children [ch] = new Node (ch);
                t = t.children [ch];
            }
            t.terminal = true;
        }
        
        public List<String> findPrefixes (String word) {
            Node t = this;
            List<String> ans = new ArrayList<>();
            for (int idx = 0; idx < word.length() - 1; idx ++) {
                char ch = word.charAt (idx);
                if (t.children [ch] == null) return ans; else t = t.children [ch];
                if (t.terminal) ans.add (word.substring (0, idx + 1));
            }
            return ans;
        }
        
        public boolean find (String word) {
            Node t = this;
            for (char ch : word.toCharArray()) if (t.children [ch] == null) return false; else t = t.children [ch];
            return t.terminal;
        }
        
    }
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> ans = new HashSet<>();
        Node root = new Node (' ');
        for (String word : words) root.add (word);
        
        Queue<String[]> queue = new LinkedList<>();
        for (String word : words) {
            for (String prefix : root.findPrefixes (word)) 
                queue.offer (new String [] { prefix, word.substring (prefix.length ()) });
            
            while (!queue.isEmpty()) {
                String processed = queue.peek () [0], suffix = queue.poll ()[1];
                if (root.find (suffix)) ans.add (processed + suffix);
                else for (String prefix : root.findPrefixes (suffix)) 
                    	queue.offer (new String [] { processed + prefix, suffix.substring (prefix.length ()) });
            }
        }
        
        return new ArrayList<String>(ans);
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
		_instance.runTest(new String[] { "a","b","ab","abc" }, Arrays.asList ("ab"));
		_instance.runTest(new String[] { "cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat" }, 
				 Arrays.asList ("catsdogcats", "dogcatsdog", "ratcatdogcat"));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final String[] words, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { words });

		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
