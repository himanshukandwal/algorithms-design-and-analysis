package challenges.leetcode;

import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 394. Decode String
 * 
 * Given an encoded string, return it's decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * 
 * Note that k is guaranteed to be a positive integer.
 * 
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. 
 * 
 * For example, there won't be input like 3a or 2[4].
 * 
 * Examples:
 *
 *	s = "3[a]2[bc]", return "aaabcbc".
 *	s = "3[a2[c]]", return "accaccacc".
 *	s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 *
 * @author Hxkandwal
 */
public class DecodeString extends AbstractCustomTestRunner {

	 public String decodeString(String s) {
        if (s.length () == 0) return "";
        Stack<Object[]> stk = new Stack<>();
        
        StringBuilder ans = new StringBuilder();
        Integer cnum = null; StringBuilder cbuild = new StringBuilder();
        for (int idx = 0; idx < s.length(); idx ++) {
            char ch = s.charAt(idx);
            
            if (Character.isDigit(ch))
                cnum = (ch - '0') + ((cnum == null) ? 0 : 10 * cnum); 
            else if (ch == '[') {
                stk.push (new Object[] { cnum, cbuild = new StringBuilder() });
                cnum = null;
            } else if (ch == ']') {
                int rep = (Integer) stk.pop () [0];
                String str = cbuild.toString();
                for (int loop = 0; loop < rep - 1; loop ++) cbuild.append (str);
                if (!stk.isEmpty())
                    cbuild = ((StringBuilder) stk.peek()[1]).append (cbuild.toString());
                else ans.append(cbuild.toString());
            } else { if (!stk.isEmpty()) cbuild.append (ch); else ans.append (ch); }
        }
        return ans.toString();
    }
	
}
