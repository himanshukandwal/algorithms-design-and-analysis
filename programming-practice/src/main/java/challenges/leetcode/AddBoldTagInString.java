package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;
import java.util.Stack;

import static com.google.common.truth.Truth.assertThat;

/**
 * 616. Add Bold Tag in String
 * 
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that 
 * exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. 
 * 
 * Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
 * 
 * Example 1:
 * 		Input:	s = "abcxyz123"
 * 				dict = ["abc","123"]
 * 		Output:	"<b>abc</b>xyz<b>123</b>"
 * 
 * Example 2:
 * 		Input:	s = "aaabbcc"
 * 				dict = ["aaa","aab","bc"]
 * 		Output:	"<b>aaabbc</b>c"
 * 
 * Note:
 * 	-	The given dict won't contain duplicates, and its length won't exceed 100.
 * 	-	All the strings in input have length in range [1, 1000].
 * 
 * @author Hxkandwal
 */
public class AddBoldTagInString extends AbstractCustomTestRunner {
	
	private static AddBoldTagInString _instance = new AddBoldTagInString();

	public String _addBoldTag(String s, String[] dict) {
        boolean [] map = new boolean [s.length ()];
        for (String str : dict)
            for (int idx = 0; idx <= s.length () - str.length (); idx ++)               // <<<<<<<<<<<<<<<<<<  we could have used KMP search here too.
                if (s.substring (idx, idx + str.length ()).equals (str))
                    for (int jdx = idx; jdx < idx + str.length (); jdx ++) map [jdx] = true;
        
        StringBuilder ans = new StringBuilder ();
        boolean found = false;
        for (int idx = 0; idx < s.length(); idx ++) {
            if (map [idx] && !found) { ans.append ("<b>"); found = true; }
            if (!map [idx] && found) { ans.append ("</b>"); found = false; }
            ans.append (s.charAt (idx));
        }
        if (found) ans.append ("</b>");
        return ans.toString();
    }

    // other approach using trie
    class Node {
        public char c;
        public Node[] children = new Node [256];
        public boolean terminal;

        public Node (char c) { this.c = c; }

        public void add (String s) {
            Node n = this;
            for (char c : s.toCharArray()) {
                if (n.children [c] == null) n.children [c] = new Node(c);
                n = n.children [c];
            }
            n.terminal = true;
        }

        public int search(String s, int sidx) {
            int ans = -1;
            Node n = this;
            for (int idx = sidx; idx < s.length(); idx ++) {
                char c = s.charAt(idx);
                if (n.children [c] == null) break;
                n = n.children [c];
                if (n.terminal) ans = idx;
            }
            return ans;
        }
    }

    public String _addBoldTagTrie(String s, String[] dict) {
        if (dict.length == 0) return s;
        Node root = new Node(' ');

        Stack<int[]> stk = new Stack<>();

        for (String d : dict) root.add (d);
        for (int idx = 0; idx < s.length(); idx ++) {
            int end = root.search(s, idx);
            if (end >= 0) {
                int start = idx;
                while (!stk.isEmpty() && stk.peek()[1] + 1 >= idx) {
                    start = stk.peek()[0];
                    end = Math.max (end, stk.pop()[1]);
                }
                stk.push(new int [] { start, end });
            }
        }

        // work with stack
        StringBuilder ans = new StringBuilder(s);
        while (!stk.isEmpty()) {
            int [] p = stk.pop();
            ans.insert(p[1] + 1, "</b>");
            ans.insert(p[0], "<b>");
        }
        return ans.toString();
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("aaabbcc", new String[] { "aaa", "aab", "bc"}, "<b>aaabbc</b>c");
	}

	public void runTest(final String s, final String[] dict, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, dict });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}    
}
