package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;
import static me.hxkandwal.daily.challanges.assorted.tree.model.Trie.createFreshRoot;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;
import me.hxkandwal.daily.challanges.assorted.tree.model.Trie;

/**
 * Keyboard Word Prediction
 * 
 * While typing text on our phone, we will be displayed with next possible word.
 * 
 * link: https://discuss.leetcode.com/topic/40443/how-do-you-implement-the-keyboard-word-prediction
 * 
 * @author Hxkandwal
 *
 */
@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class KeyboardWordPrediction extends AbstractCustomTestRunner {
	
	private static KeyboardWordPrediction _instance = new KeyboardWordPrediction();
	
	private KeyboardWordPrediction() {}
	
	public static List<String> _predictSuggestions(String[] bank, String word) {
		Trie trie = createFreshRoot();
		
		for (String bankWord : bank) 
			trie.getItem(bankWord);
		
		return trie.getSuggestions(word);
	}
	
	// driver method
	public static void main(String[] args) throws Exception {
		_instance.runTest(new String [] { "cat", "cats", "catsdogcats", "catxdogcatsrat", "dog", "dogcatsdog", "hippopotamuses", "rat", 
										  "ratcat", "catdog", "catratdogcat" }, "cat", 
						  new ArrayList() {{ add ("cat"); add ("catdog"); add ("catratdogcat"); add ("cats"); add ("catsdogcats"); add ("catxdogcatsrat"); }});
		
		_instance.runTest(new String [] { "cat", "cats", "catsdogcats", "catxdogcatsrat", "dog", "dogcatsdog", "hippopotamuses", "rat", 
				  						  "ratcat", "catdog", "catratdogcat" }, "b", 
						  new ArrayList());
	}
	
	public void runTest(final String[] words, final String word, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { words, word });

		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
