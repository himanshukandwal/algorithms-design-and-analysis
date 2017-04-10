package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 211. Add and Search Word - Data structure design
 * 
 * Design a data structure that supports the following two operations:
 * 
 * 	void addWord(word)
 * 	bool search(word)
 * 	search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 * 
 * For example:
 * 		addWord("bad")
 * 		addWord("dad")
 * 		addWord("mad")
 * 		search("pad") -> false
 * 		search("bad") -> true
 * 		search(".ad") -> true
 * 		search("b..") -> true
 * 
 * @author Hxkandwal
 */
public class AddAndSearchWord extends AbstractCustomTestRunner {

	private class TrieNode {
        private char ch;
        TrieNode [] children = new TrieNode [26];
        boolean isTerminal;
        
        public TrieNode (char ch) { this.ch = ch; }
    }
    
    private TrieNode root = new TrieNode(' ');
    
    /** Initialize your data structure here. */
    public AddAndSearchWord() {
        
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode t = root;
        for (char ch : word.toCharArray()) {
            if (t.children [ch - 'a'] == null) t.children [ch - 'a'] = new TrieNode (ch);
            t = t.children [ch - 'a'];
        }
        t.isTerminal = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search (word, root);
    }
    
    private boolean search (String word, TrieNode node) {
        if (node == null || (word.length() == 0 && !node.isTerminal)) return false;
        if (word.length() == 0 && node.isTerminal) return true;
        
        for (int idx = 0; idx < word.length(); idx ++) {
            char ch = word.charAt(idx);
            
            if (ch != '.') {
                if (node.children [ch - 'a'] == null) return false;
                node = node.children [ch - 'a'];
            } else {
                for (int cIdx = 0; cIdx < node.children.length; cIdx ++) {
                    if (search (word.substring(idx + 1), node.children [cIdx])) 
                        return true;
                }
                return false;
            }
        }
        return node.isTerminal;
    }
    
}
