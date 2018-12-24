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
		Stack<Object[]> stack = new Stack<>();
		String str = "";
		for (int idx = 0, num = 0; idx < s.length(); idx ++) {
			while (idx < s.length() && Character.isDigit(s.charAt(idx))) num = 10 * num + (s.charAt (idx ++) - '0');
			if (s.charAt(idx) == '[') {
				stack.push(new Object[] { num, str });
				num = 0;
				str = "";
			} else if (s.charAt(idx) == ']') {
				int on = (Integer) stack.peek() [0];
				String os = (String) stack.pop() [1];
				while (on -- > 0 && str.length() > 0) os += str;
				str = os;
			} else
				str += s.charAt(idx);
		}
		return str;
	}

}
