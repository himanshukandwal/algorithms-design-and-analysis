package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * Word Search II
 * 
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally 
 * or vertically neighboring. The same letter cell may not be used more than once in a word.
 * 
 * For example,
 * 		Given words = ["oath","pea","eat","rain"] and board =
 * 				[
 * 					['o','a','a','n'],
 * 					['e','t','a','e'],
 * 					['i','h','k','r'],
 * 					['i','f','l','v']
 * 				]
 * 		
 * 		Return ["eat","oath"].
 * 
 * @author Hxkandwal
 */
public class WordSearchII extends AbstractCustomTestRunner {
	
	private static WordSearchII _instance = new WordSearchII();
	
	class Trie {
        char ch;
        Trie [] children = new Trie [256];
        boolean terminal;
        
        Trie (char ch) { this.ch = ch; }
        
        void add (String word) {
            Trie t = this;
            for (char c : word.toCharArray()) {
                if (t.children [c] == null) t.children [c] = new Trie (c);
                t = t.children [c];
            }
            t.terminal = true;
        }
    }
    
    public List<String> _findWords(char[][] board, String[] words) {
        List<String> answer = new ArrayList<>();
        Set<String> ans = new HashSet<>();
        if (board.length == 0 || words.length == 0) return answer;
        Trie root = new Trie (' ');
        for (String word : words) root.add (word);
        
        for (int row = 0; row < board.length; row ++) {
            for (int col = 0; col < board [0].length; col ++) {
                char ch = board [row][col];
                if (root.children [ch] != null) 
                    dfs (board, root.children [ch], ans, String.valueOf(ch), row, col);
            }
        }
        answer.addAll(ans);
        return answer;
    }
    
    int [] rdir = new int [] { 0, 1, 0, -1 };
    int [] cdir = new int [] { 1, 0, -1, 0 };
    
    private boolean dfs (char [][] board, Trie node, Set<String> ans, String build, int row, int col) {
        if (node.terminal) ans.add (build);
        char old = board [row][col];
        board [row][col] = '*';
        
        boolean found = false;
        for (int idx = 0; idx < 4 && !found; idx ++) {
            int nrow = row + rdir [idx], ncol = col + cdir [idx];
            if (nrow < 0 || nrow >= board.length || ncol < 0 || ncol >= board [0].length) continue;
            char ch = board [nrow][ncol];
            if (node.children [ch] != null) found = dfs (board, node.children [ch], ans, build + ch, nrow, ncol);
        }
        
        board [row][col] = old;
        return found;
    }
    
    // driver method
 	public static void main(String[] args) {
 		_instance.runTest(new char[][] { "ab".toCharArray(), 
			 							 "aa".toCharArray() },
 						 new String[] { "aba", "baa", "bab", "aaab", "aaa", "aaaa", "aaba" }, 
 						 Arrays.asList("aaa", "aba", "aaba", "baa", "aaab"));
 		_instance.runTest(new char[][] { "aa".toCharArray() }, new String[] { "a" }, Arrays.asList("a"));
		_instance.runTest(new char[][] { "oaan".toCharArray(), 
										 "etae".toCharArray(),
										 "ihkr".toCharArray(),
										 "iflv".toCharArray() },
				new String[] { "oath", "pea", "eat", "rain" }, Arrays.asList("oath", "eat"));
 	}

 	@SuppressWarnings("unchecked")
	public void runTest(char[][] board, String[] words, final List<String> expectedOutput) {
 		List<Object> answers = runAll(getClass(), new Object[] { board, words });

 		for (Object answer : answers)
 			assertThat((List<String>) answer).isEqualTo(expectedOutput);

 		System.out.println("ok!");
 	} 
 	
}
