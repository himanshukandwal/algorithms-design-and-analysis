package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 208. Implement Trie (Prefix Tree)
 * 
 * Implement a trie with insert, search, and startsWith methods.
 * 
 * Note: You may assume that all inputs are consist of lowercase letters a-z.
 * 
 * @author Hxkandwal
 */
public class ImplementTrie extends AbstractCustomTestRunner {
	
	private class TrieNode {
        char ch;
        TrieNode[] children = new TrieNode [26];
        boolean isTerminal;
        
        public TrieNode (char ch) {
            this.ch = ch;
        }
    }
	
	TrieNode _root = new TrieNode(' ');
    
    /** Initialize your data structure here. */
    public ImplementTrie() {
        
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word.length() == 0) return;
        TrieNode t = _root;
        for (char c : word.toCharArray()) {
            if (t.children [c] == null) t.children [c] = new TrieNode(c);
            t = t.children [c];
        }
        t.isTerminal = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word.length() == 0) return false;
        TrieNode t = _root;
        for (char c : word.toCharArray()) {
            if (t.children [c] == null) return false;
            t = t.children [c];
        }
        return t.isTerminal;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix.length() == 0) return false;
        TrieNode t = _root;
        for (char c : prefix.toCharArray()) {
            if (t.children [c] == null) return false;
            t = t.children [c];
        }
        return true;
    }

}
