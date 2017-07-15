package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import challenges.AbstractCustomTestRunner;

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

	public class Node {
        private char ch;
        private Node [] children = new Node [256];
        private boolean terminal;
        
        public Node (char ch) { this.ch = ch; }
        
        public void add (String word) {
            Node t = this;
            for (char ch : word.toCharArray()) {
                if (t.children [ch] == null) t.children [ch] = new Node (ch);
                t = t.children [ch];
            }
            t.terminal = true;
        }
        
        public int maxLen (String s, int start) {
            int len = 0;
            Node t = this;
            for (int idx = start; idx < s.length (); idx ++) {
                char c = s.charAt (idx);
                if (t.children [c] == null) return len;
                t = t.children [c];
                if (t.terminal) len = Math.max (len, idx - start + 1);
            }
            return len;
        }
        
    }
    
    public String _addBoldTag(String s, String[] dict) {
    	Node root = new Node (' ');
        for (String str : dict) root.add (str);
        int idx = 0;
        List<Integer> markers = new ArrayList<>();
        while (idx < s.length ()) {
            int len = root.maxLen (s, idx);
            int end = idx + len;
            idx ++;
            if (len > 0) {
                if (markers.size() == 0 || markers.get (markers.size () - 1) != idx - 1) markers.add (idx - 1);
                else markers.remove (markers.size () - 1);
                while (idx < end) end = Math.max (end, idx + root.maxLen (s, idx ++));
                markers.add (idx);
            }
        }
        StringBuilder ans = new StringBuilder ();
        
        if (markers.size() > 0) ans.append (s.substring (0, markers.get (0)));
        else ans.append (s);
        for (int i = 0; i < markers.size() - 1; i ++)
            ans.append ((i % 2 == 0 ? "<b>" : "</b>") + s.substring (markers.get (i), markers.get (i + 1)));
        if (markers.size() > 0) ans.append ("</b>" + s.substring (markers.get (markers.size () - 1)));
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
