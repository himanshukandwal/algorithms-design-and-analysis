package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 140. Word Break II (Extraction)
 * 
 * DP + DFS
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
	
	/**
	 * regular boolean [] solution (extra work while going down, have to check everything every time)
	 * 
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public static List<String> wordBreak(String s, List<String> wordDict) {
		Set<String> hash = new HashSet<>();
		int [] presentChars = new int [256];
		
		for (String word : wordDict) { 
			hash.add(word);
			for (int idx = 0; idx < word.length(); idx ++)
				presentChars [word.charAt(idx)] ++;
		}
		
		for (int idx = 0; idx < s.length(); idx ++)
			if (presentChars[s.charAt(idx)] == 0)
				return new ArrayList<>();
		
		boolean [] f = new boolean [s.length() + 1];
		f [0] = true;
		
		Map<Integer, List<String>> indexBasedRegister = new HashMap<>();
		indexBasedRegister.put(0, new ArrayList() {{ add (""); }});
		
		for (int idx = 1; idx < f.length; idx ++) {
			boolean found = false;
			for (int innerIdx = idx - 1; innerIdx >= 0; innerIdx --) {
				if (f [innerIdx] && hash.contains(s.substring(innerIdx, idx))) {
					found = (!found) ? true : found;
					
					if (! indexBasedRegister.containsKey(idx))
						indexBasedRegister.put(idx, new ArrayList<>());
					
					for (String innerIdxStrings : indexBasedRegister.get(innerIdx)) 
						indexBasedRegister.get(idx).add(innerIdxStrings + (innerIdxStrings.length() > 0 ? " " : "") + s.substring(innerIdx, idx));
				}
			}
			
			f [idx] = found;
		}
		
        return (indexBasedRegister.size() == 0 || !indexBasedRegister.containsKey(f.length - 1) ? new ArrayList<>() : indexBasedRegister.get(f.length - 1));
    }

	/**
	 * DFS Solution
	 * 
	 * same philosophy of start as that of boolean []. build up ahead and concatenate while coming back. (bottom up, lesser work, build up)
	 */
	public static List<String> _wordBreakDFS (String s, List<String> wordDict) {
		Set<String> set = new HashSet<>();
		int maxDictionaryWordLength = 0;
		for (String word : wordDict) { maxDictionaryWordLength = Math.max(maxDictionaryWordLength, word.length()); set.add(word); }
		
		Map<Integer, List<String>> indexBasedRegister = new HashMap<>();
		return generateWords (indexBasedRegister, s, set, 0, maxDictionaryWordLength);
	}
	
	private static List<String> generateWords (Map<Integer, List<String>> indexBasedRegister, String s, Set<String> set, int start, int maxDictionaryWordLength) {
		List<String> words = new ArrayList<>();
		
		if (start >= s.length()) {
			words.add("");
			return words;
		}
		
		for (int idx = start + 1; idx <= start + maxDictionaryWordLength && idx <= s.length(); idx ++) {
			String currentWord = s.substring(start, idx);
			
			if (set.contains(currentWord)) {
				List<String> processedList;
				if (indexBasedRegister.containsKey(idx)) processedList = indexBasedRegister.get(idx);
				else processedList = generateWords (indexBasedRegister, s, set, idx, maxDictionaryWordLength);
				
				// reverse build up stack.
				for (String processedWord : processedList)
					words.add (currentWord + (processedWord.length() > 0 ? " " + processedWord : ""));
			}
		}
		
		indexBasedRegister.put (start, words); 
		return words;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("catsanddog", new ArrayList() {{ add ("cat"); add ("cats"); add ("and"); add ("sand"); add ("dog"); }}, 
							new ArrayList() {{ add("cat sand dog"); add ("cats and dog"); }});
		
		_instance.runTest("ilikesamsung", new ArrayList() {{ add("i"); add("like"); add("sam"); add("sung"); add("samsung"); add("mobile"); add("ice"); add("go");
		   													 add("cream"); add("icecream"); add("man"); add("mango"); }}, 
						   new ArrayList() {{ add("i like sam sung"); add ("i like samsung"); }});
		
		_instance.runTest("aaaaaaaa", new ArrayList() {{ add("aaaa"); add("aa"); add("a"); }},
				new ArrayList() {{ add("a a a a a a a a"); add ("aa a a a a a a"); add("a aa a a a a a"); add("a a aa a a a a"); add("aa aa a a a a"); add("aaaa a a a a"); 
								   add("a a a aa a a a"); add("aa a aa a a a"); add("a aa aa a a a"); add("a aaaa a a a"); add("a a a a aa a a"); add("aa a a aa a a"); 
								   add("a aa a aa a a"); add("a a aa aa a a"); add("aa aa aa a a"); add("aaaa aa a a"); add("a a aaaa a a"); add("aa aaaa a a"); add("a a a a a aa a"); 
								   add("aa a a a aa a"); add("a aa a a aa a"); add("a a aa a aa a"); add("aa aa a aa a"); add("aaaa a aa a"); add("a a a aa aa a"); add("aa a aa aa a");
								   add("a aa aa aa a"); add("a aaaa aa a"); add("a a a aaaa a"); add("aa a aaaa a"); add("a aa aaaa a"); add("a a a a a a aa"); add("aa a a a a aa"); 
								   add("a aa a a a aa"); add("a a aa a a aa"); add("aa aa a a aa"); add("aaaa a a aa"); add("a a a aa a aa"); add("aa a aa a aa"); add("a aa aa a aa"); 
								   add("a aaaa a aa"); add("a a a a aa aa"); add("aa a a aa aa"); add("a aa a aa aa"); add("a a aa aa aa"); add("aa aa aa aa"); add("aaaa aa aa"); 
								   add("a a aaaa aa"); add("aa aaaa aa"); add("a a a a aaaa"); add("aa a a aaaa"); add("a aa a aaaa"); add("a a aa aaaa"); add("aa aa aaaa"); 
								   add("aaaa aaaa");}});
	}

	public void runTest(final String s, final List<String> words, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, words });

		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
