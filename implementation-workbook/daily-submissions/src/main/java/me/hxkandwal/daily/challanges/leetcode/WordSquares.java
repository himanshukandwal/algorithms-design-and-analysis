package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Word Squares
 * 
 * @author Hxkandwal
 */
public class WordSquares extends AbstractCustomTestRunner {
	
	private static WordSquares _instance = new WordSquares();
	
    public class Trie  {
        char ch;
        Trie [] children = new Trie [256];
        boolean isTerminal;
        
        public Trie (char ch) {
            this.ch = ch;
        }
        
        public void add (String word) {
            Trie node = this;
            for (char c : word.toCharArray()) {
                if (node.children [c] == null) node.children [c] = new Trie (c);
                node = node.children [c];
            }
            node.isTerminal = true;
        }
        
        public List<String> getPrefixes (String str) {
            List<String> ans = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            
            Trie node = this;
            for (char c : str.toCharArray()) {
                if (node.children [c] == null) return ans;
                node = node.children [c];
                sb.append (c);
            }
            dfs (node, sb, ans);
            return ans;
        }
        
        private void dfs (Trie node, StringBuilder sb, List<String> ans) {
            if (node.isTerminal) ans.add (sb.toString());
            
            for (Trie child : node.children) {
                if (child != null) {
                    sb.append (child.ch);
                    dfs (child, sb, ans);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }    
        }
    }
    
    public List<List<String>> _wordSquares(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0) return ans;
        
        Trie root = new Trie (' ');
        for (String word : words) root.add (word);
        
        List<String> res = new ArrayList<>();
        for (String word : words) {
            res.add (word);
            search (ans, res, root);
            res.remove (res.size() - 1);
        }
        return ans;
    }
    
    private void search (List<List<String>> ans, List<String> res, Trie node) {
        if (res.size() == res.get(0).length()) ans.add (new ArrayList<>(res));
        else {
            StringBuilder sb = new StringBuilder();
            int index = res.size();
            for (String str : res) sb.append (str.charAt(index));
            for (String matchedWord : node.getPrefixes (sb.toString())) {
                res.add (matchedWord);
                search (ans, res, node);
                res.remove (res.size() - 1);
            }
        }
    }	
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new String[] { "ball", "area", "lead", "lady" }, Arrays.asList(Arrays.asList("ball", "area", "lead", "lady")));
		_instance.runTest(new String[] { "area", "lead", "wall", "lady", "ball"}, Arrays.asList(Arrays.asList("wall", "area", "lead", "lady"), 
																								Arrays.asList("ball", "area", "lead", "lady")));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final String[] words, final List<List<String>> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { words });
		
		for (Object answer : answers)
			assertThat((List<List<String>>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}    

}
