package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 140. Word Break II
 * 
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence 
 * where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.
 * 
 * Return all such possible sentences.
 * 
 * For example,  given s = "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"]. A solution is ["cats and dog", "cat sand dog"].
 * 
 * @author Hxkandwal
 */
public class WordBreakII extends AbstractCustomTestRunner {
	
	private static WordBreakII _instance = new WordBreakII();
	
	public List<String> _wordBreak(String s, List<String> wordDict) {
        return dfs (new HashMap<>(), s, wordDict, 0);
    }
    
    private List<String> dfs (Map<Integer, List<String>> map, String s, List<String> dict, int index) {
        if (index >= s.length ()) return Arrays.asList("");
        if (map.containsKey (index)) return map.get (index);
        List<String> ans = new ArrayList<>();
        
        for (String word : dict) 
            if (s.substring (index).startsWith (word))
                for (String res : dfs (map, s, dict, index + word.length())) ans.add (word + (res.length () > 0 ? " " : "") + res);
        
        map.put (index, ans);
        return ans;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("catsanddog", Arrays.asList ("cat", "cats", "and", "sand", "dog"), Arrays.asList ("cat sand dog", "cats and dog"));
		_instance.runTest("ilikesamsung", Arrays.asList ("i", "like", "sam", "sung", "samsung", "mobile", "ice", "go"),
										  Arrays.asList ("i like sam sung", "i like samsung"));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final String s, final List<String> wordDict, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, wordDict });

		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo (expectedOutput);
		
		System.out.println("ok!");
	}	

}
