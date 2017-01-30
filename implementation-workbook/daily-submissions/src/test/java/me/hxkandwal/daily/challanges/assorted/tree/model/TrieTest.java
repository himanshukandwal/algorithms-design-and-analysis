package me.hxkandwal.daily.challanges.assorted.tree.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TrieTest {

	private Trie root;
	
	@Before
	public void initialize() {
		root = new Trie(' ');
	}
	
	@Test
	public void testGetItem() {
		assertEquals(0, root.getItem("hello").size());
		assertEquals(0, root.getItem("world").size());
	}

	@Test
	public void testContains() {
		assertEquals(0, root.getItem("hello").size());
		assertTrue(root.contains("hello"));
	}

}
