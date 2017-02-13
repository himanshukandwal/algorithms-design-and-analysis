package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 140. Word Break II (Extraction)
 * 
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where 
 * each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.
 * 
 * Return all such possible sentences.
 * 
 * For example, 
 * 		given s = "catsanddog",
 * 		dict = ["cat", "cats", "and", "sand", "dog"].
 * 
 * 		A solution is ["cats and dog", "cat sand dog"].
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class WordBreakII extends AbstractCustomTestRunner {
	
	private static WordBreakII _instance = new WordBreakII();
	
	private WordBreakII() {}
	
	public static List<String> _wordBreak(String s, List<String> wordDict) {
		
        return null;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("catsanddog", new ArrayList() {{ add ("cat"); add ("cats"); add ("and"); add ("sand"); add ("dog"); }}, 
							new ArrayList() {{ add ("cats and dog"); add("cat sand dog"); }});
	}

	public void runTest(final String s, final List<String> words, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, words });

		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
