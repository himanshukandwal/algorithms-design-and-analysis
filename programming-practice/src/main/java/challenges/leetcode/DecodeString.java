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
		StringBuilder ans = new StringBuilder();
		Stack<Object[]> stack = new Stack<>();
		int idx = 0, num = 0;
		while (idx < s.length()) {
			char ch = s.charAt(idx ++);
			if (ch >= '0' && ch <= '9') num = num * 10 + (ch - '0');
			else {
				if (ch == '[') stack.push(new Object[] { num, "" });
				else if (ch == ']') {
					int val = (Integer) stack.peek()[0];
					String str = (String) stack.pop()[1];
					StringBuilder sb = new StringBuilder();
					while (val-- > 0) sb.append(str);
					if (stack.isEmpty()) ans.append(sb.toString());
					else stack.peek()[1] = (String) stack.peek()[1] + sb.toString();
				} else {
					if (stack.isEmpty()) ans.append(String.valueOf(ch));
					else stack.peek()[1] = (String) stack.peek()[1] + String.valueOf(ch);
				}
				num = 0;
			}
		}
		return ans.toString();
	}
	
}
