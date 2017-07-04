package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 212. Word Search II
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
	
	public class Node {
		private char ch;
        private Node [] children = new Node [256];
        private String word;
        public Node (char ch) { this.ch = ch; }
        
        public void add (String word) {
            Node t = this;
            for (char ch : word.toCharArray ()) {
                if (t.children [ch] == null) t.children [ch] = new Node (ch);
                t = t.children [ch];
            }
            t.word = word;
        }
    
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        Node root = new Node (' ');
        for (String word : words) root.add (word);
        for (int row = 0; row < board.length; row ++)
            for (int col = 0; col < board [0].length; col ++)
                dfs (board, root, ans, row, col);
        return ans;   
    }
    
    private int [] rdir = { 1, 0, -1, 0 };
    private int [] cdir = { 0, 1, 0, -1 };
    
    private void dfs (char [][] board, Node node, List<String> ans, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board [0].length || board [row][col] == '*' || node.children [board [row][col]] == null) return;
        
        if (node.children [board [row][col]].word != null) {
            ans.add (node.children [board [row][col]].word);
            node.children [board [row][col]].word = null;
        }
        
        char ch = board [row][col];
        board [row][col] = '*';
        for (int idx = 0; idx < 4; idx ++)
            dfs (board, node.children [ch], ans, row + rdir [idx], col + cdir [idx]); 
        board [row][col] = ch;
    } 
    
    // driver method
 	public static void main(String[] args) {
 		_instance.runTest(new char[][] { "ab".toCharArray(), 
			 							 "aa".toCharArray() },
 						 new String[] { "aba", "baa", "bab", "aaab", "aaa", "aaaa", "aaba" }, 
 						 Arrays.asList("aba", "aaa", "aaab", "baa", "aaba"));
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
