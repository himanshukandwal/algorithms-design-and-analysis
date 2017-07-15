package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 425. Word Squares
 * 
 * Given a set of words (without duplicates), find all word squares you can build from them.
 * A sequence of words forms a valid word square if the kth row and column read the exact same 
 * string, where 0 ≤ k < max(numRows, numColumns).
 * 
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each 
 * word reads the same both horizontally and vertically.
 * 
 * 		b a l l
 * 		a r e a
 * 		l e a d
 * 		l a d y
 * 
 * Note:
 * 	-	There are at least 1 and at most 1000 words.
 * 	-	All words will have the exact same length.
 * 	-	Word length is at least 1 and at most 5.
 * 	-	Each word contains only lowercase English alphabet a-z.
 * 
 * @author Hxkandwal
 */
public class WordSquares extends AbstractCustomTestRunner {
	
	private static WordSquares _instance = new WordSquares();
	
	public class Node {
        private char ch;
        private Node [] children = new Node [256];
        private String word;
        
        public Node (char ch) { this.ch = ch; }
        
        public void add (String s) {
            Node t = this;
            for (char ch : s.toCharArray ()) {
                if (t.children [ch] == null) t.children [ch] = new Node (ch);
                t = t.children [ch];
            }
            t.word = s;
        }
        
        public List<String> getWords (String prefix) {
            List<String> ans = new ArrayList<>();
            Node t = this;
            for (char ch : prefix.toCharArray ()) {
                if (t.children [ch] == null) return ans;
                t = t.children [ch];
            }
            dfs (ans, t);
            return ans;
        }
        
        private void dfs (List<String> ans, Node node) {
            if (node.word != null) ans.add (node.word);
            for (Node c : node.children) 
                if (c != null) dfs (ans, c);
        }
    }
    
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> ans = new ArrayList<List<String>> ();
        if (words.length == 0) return ans;
        Node root = new Node (' ');
        for (String w : words) root.add (w);
        for (String w : words) {
            List<String> build = new ArrayList<>();
            build.add (w);
            dfs (root, ans, build);
        }
        return ans;
    }
    
    private void dfs (Node root, List<List<String>> ans, List<String> build) {
        if (build.size () == build.get (0).length ()) ans.add (new ArrayList<>(build));
        else {
            StringBuilder pre = new StringBuilder ();
            for (int jdx = 0; jdx < build.size(); jdx ++) pre.append (build.get (jdx).charAt (build.size ()));
            for (String ps : root.getWords (pre.toString ())) {
                build.add (ps);
                dfs (root, ans, build);
                build.remove (build.size () - 1);
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
