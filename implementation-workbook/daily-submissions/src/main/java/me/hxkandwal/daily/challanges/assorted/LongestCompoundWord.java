package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;
import static me.hxkandwal.daily.challanges.assorted.tree.model.Trie.createFreshRoot;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;
import me.hxkandwal.daily.challanges.assorted.tree.model.Trie;

/**
 * Longest Compound Word
 * 
 * Given a sorted list of words, find the longest compound word in the list that is constructed by concatenating the words in the list. 
 * 
 * For example, 
 * 		if the input list is: [‘cat’, ‘cats’, ‘catsdogcats’, ‘catxdogcatsrat’, ‘dog’, ‘dogcatsdog’, ‘hippopotamuses’, ‘rat’, ‘ratcat’, ‘ratcatdog’, ‘ratcatdogcat’]. 
 * 
 * Then the longest compound word is ‘ratcatdogcat’ with 12 letters. 
 * 
 * Note that the longest individual words are ‘catxdogcatsrat’ and ‘hippopotamuses’ with 14 letters, but they’re not fully constructed by other words. 
 * Former one has an extra ‘x’ letter, and latter is an individual word by itself not a compound word.
 * 
 * link: http://www.ardendertat.com/2012/06/15/programming-interview-questions-28-longest-compound-word/
 * 
 * @author Hxkandwal
 *
 */
public class LongestCompoundWord extends AbstractCustomTestRunner {
	
	private static LongestCompoundWord _instance = new LongestCompoundWord();
	
	private LongestCompoundWord() {}
	
	public static class Tuple {
		
		private String prefix;
		private String suffix;
		
		public Tuple(String prefix, String suffix) {
			this.prefix = prefix;
			this.suffix = suffix;
		}
		
		public String getPrefix() {
			return prefix;
		}
		
		public String getSuffix() {
			return suffix;
		}
		
		@Override
		public String toString() {
			return super.toString();
		}
	}
	
	public static String _getLongestCompoundWord(String[] words) {
		Trie root = createFreshRoot();
		
		Queue<Tuple> queue = new LinkedList<>();
		for (String word : words) {
			root.getItem(word);
			
			List<String> prefixes = root.getAllPrefixes(word);
			
			for (String prefix : prefixes) 
				queue.add(new Tuple(prefix, word.substring(word.indexOf(prefix) + prefix.length())));
		}
		
		String maxCompoundWord = null;
		while (!queue.isEmpty()) {
			
		}
		
		return maxCompoundWord;
	}
	
	// driver method
	public static void main(String[] args) throws Exception {
		_instance.runTest("(AB) C((D E) F)/ SSS", "ABC(DEF)");
	}
	
	public void runTest(final String line, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { line });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
