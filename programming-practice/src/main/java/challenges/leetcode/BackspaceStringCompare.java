package challenges.leetcode;

import challenges.AbstractCustomTestRunner;
import java.util.Stack;

/**
 * 844. Backspace String Compare
 *
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 *
 * Example 1:
 *    Input: S = "ab#c", T = "ad#c"
 *    Output: true
 *    Explanation: Both S and T become "ac".
 *
 * Example 2:
 *    Input: S = "ab##", T = "c#d#"
 *    Output: true
 *    Explanation: Both S and T become "".
 *
 * Example 3:
 *    Input: S = "a##c", T = "#a#c"
 *    Output: true
 *    Explanation: Both S and T become "c".
 *
 * Example 4:
 *    Input: S = "a#c", T = "b"
 *    Output: false
 *    Explanation: S becomes "c" while T becomes "b".
 *
 * Note:
 *  1. 1 <= S.length <= 200
 *  2. 1 <= T.length <= 200
 *  3. S and T only contain lowercase letters and '#' characters.
 *
 * @author Hxkandwal
 */
public class BackspaceStringCompare extends AbstractCustomTestRunner {

  public boolean backspaceCompare(String S, String T) {
    Stack<Character> a = new Stack<>(), b = new Stack<>();
    for (char c : S.toCharArray()) {
      if (c != '#') a.push(c);  else if (!a.isEmpty()) a.pop();
    }

    for (char c : T.toCharArray()) {
      if (c != '#') b.push(c); else if (!b.isEmpty()) b.pop();
    }

    return a.toString().equals(b.toString());
  }

}
