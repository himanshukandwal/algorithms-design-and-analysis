package challenges.assorted.tree.model;

import static challenges.assorted.tree.model.Trie.createFreshRoot;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TrieTest {

	private Trie root;
	
	@Before
	public void initialize() {
		root = createFreshRoot();
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
	
	@Test
	public void testGetAllPrefixes() throws Exception {
		assertEquals(0, root.getItem("auth").size());
		assertEquals(0, root.getItem("author").size());
		assertEquals(0, root.getItem("authori").size());
		assertEquals(0, root.getItem("authorize").size());
		assertEquals(3, root.getAllPrefixes("authority").size());
		assertEquals(4, root.getAllPrefixes("authorize").size());
	}

	@Test
	public void testGetSuggestions() throws Exception {
		assertEquals(0, root.getItem("auth").size());
		assertEquals(0, root.getItem("author").size());
		assertEquals(0, root.getItem("authori").size());
		assertEquals(0, root.getItem("authorize").size());
		assertEquals(4, root.getSuggestions("auth").size());
		assertEquals(0, root.getSuggestions("b").size());
	}
	
}
