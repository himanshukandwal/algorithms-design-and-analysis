package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

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

	public String _addBoldTag(String s, String[] dict) {
        boolean [] map = new boolean [s.length ()];
        for (String str : dict)
            for (int idx = 0; idx <= s.length () - str.length (); idx ++) 
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
