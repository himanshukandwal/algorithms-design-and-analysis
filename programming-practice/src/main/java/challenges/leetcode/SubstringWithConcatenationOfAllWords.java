package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;

/**
 * 30. Substring with Concatenation of All Words
 * 
 * You are given a string, s, and a list of words, words, that are all of the same length. 
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once 
 * and without any intervening characters.
 * 
 * For example, given: s: "barfoothefoobarman" words: ["foo", "bar"]
 * 				You should return the indices: [0,9].
 * 				(order does not matter).
 * 
 * @author Hxkandwal
 */
public class SubstringWithConcatenationOfAllWords extends AbstractCustomTestRunner {
	
	private static SubstringWithConcatenationOfAllWords _instance = new SubstringWithConcatenationOfAllWords();

	public List<Integer> _findSubstringFaster(String s, String[] words) {
		if (words == null || words.length == 0) return Arrays.asList();
		int len = words [0].length(), totalLen = words.length * len;

		Map<String, Integer> map = new HashMap<>();
		for (String w : words) map.put(w, map.getOrDefault(w, 0) + 1);

		List<Integer> ans = new ArrayList<>();
		outer: for (int idx = 0; idx <= s.length() - totalLen; idx ++) {
			Map<String, Integer> local = new HashMap<>(map);

			for (int j = idx; j < idx + totalLen; j += len) {
				String str = s.substring (j, j + len);
				if (!local.containsKey(str)) continue outer;
				if (local.put (str, local.get (str) - 1) == 1) local.remove (str);
			}

			if (local.size() == 0) ans.add (idx);
		}
		return ans;
	}

	class Node {
		char c ;
		boolean terminal;
		Node [] children = new Node [256];

		public Node (char c) { this.c = c; }
	}

	public List<Integer> _findSubstringBruteForce(String s, String[] words) {
		if (words == null || words.length == 0) return Arrays.asList();

		Node root = new Node (' ');
		Map<String, Integer> o = new HashMap<>();
		for (String w : words) {
			Node t = root;
			for (char c : w.toCharArray()) {
				if (t.children [c] == null) t.children [c] = new Node (c);
				t = t.children [c];
			}
			t.terminal = true;
			o.put(w, o.getOrDefault(w, 0) + 1);
		}

		List<Integer> ans = new ArrayList<>();

		for (int idx = 0; idx < s.length(); idx ++) {
			int end = -1, j = idx;
			Map<String, Integer> local = new HashMap<>(o);

			while (local.size() > 0 && (end = contains(root, s, j)) >= 0) {
				String str = s.substring(j, end + 1);

				if (!local.containsKey(str)) break;
				if (local.put (str, local.get(str) - 1) == 1) local.remove(str);
				j = end + 1;
			}

			if (local.size() == 0) ans.add (idx);
		}

		return ans;
	}

	public int contains(Node n, String s, int idx) {
		while (idx < s.length()) {
			char c = s.charAt(idx);
			if (n.children [c] == null) break;
			if (n.children [c].terminal) return idx;
			n = n.children [c];
			idx ++;
		}
		return -1;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("barfoothefoobarman", new String [] { "foo", "bar" }, Arrays.asList(0, 9));
		_instance.runTest("barfoofoobarthefoobarman", new String [] {"bar","foo","the"}, Arrays.asList(6, 9, 12));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final String s, final String[] words, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, words });

		for (Object answer : answers)
			assertThat((List<Integer>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 
	
}
