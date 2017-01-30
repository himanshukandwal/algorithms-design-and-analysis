package me.hxkandwal.daily.challanges.assorted.tree.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The trie data structure, also known as a prefix tree are space and time
 * efficient structures for text storage and search. They let words to share
 * prefixes.
 * 
 * @author Hxkandwal
 */
public class Trie {
	
	private char letter;
	private Trie[] children;
	private boolean isTerminal;
	private List<Integer> positions;

	public Trie (char letter) {
		this.letter = letter;
		this.children = new Trie[256];
		this.positions = new ArrayList<>();
	}

	public List<Integer> getPositions() {
		return positions;
	}
	
	public Trie[] getChildren() {
		return children;
	}
	
	/** 
	 * Insert if not present, retrieve positions if present.
	 * To be used from root node.
	 */
	public List<Integer> getItem(String word) {
		Trie traverser = null;
		
		for (int idx = 0; idx < word.length(); idx++) {
			char ch = word.charAt(idx);
			
			if (children[ch] == null)
				children[ch] = new Trie(ch);
			
			traverser = children[ch];
		}

		traverser.isTerminal = true;
		return traverser.positions;
	}
	
	public boolean contains(String word) {
		Trie traverser = null;
		
		for (int idx = 0; idx < word.length(); idx++) {
			char ch = word.charAt(idx);
			
			if (children[ch] == null)
				return false;
			
			traverser = children[ch];
		}
		
		return traverser.isTerminal;
	}
	
	@Override
	public String toString() {
		return String.valueOf("[" + String.valueOf(letter) + "]");
	}

}