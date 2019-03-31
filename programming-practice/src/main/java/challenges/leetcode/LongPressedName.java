package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 925. Long Pressed Name
 *
 * Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.
 * You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.
 *
 * Example 1:
 *      Input: name = "alex", typed = "aaleex"
 *      Output: true
 *      Explanation: 'a' and 'e' in 'alex' were long pressed.
 *
 * Example 2:
 *      Input: name = "saeed", typed = "ssaaedd"
 *      Output: false
 *      Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
 *
 * Example 3:
 *      Input: name = "leelee", typed = "lleeelee"
 *      Output: true
 *
 * Example 4:
 *      Input: name = "laiden", typed = "laiden"
 *      Output: true
 *      Explanation: It's not necessary to long press any character.
 *
 * Note:
 *  name.length <= 1000
 *  typed.length <= 1000
 *  The characters of name and typed are lowercase letters.
 *
 * @author Hxkandwal
 */
public class LongPressedName extends AbstractCustomTestRunner {

    private static LongPressedName _instance = new LongPressedName();

    public boolean _isLongPressedName(String name, String typed) {
        int idx1 = 0, idx2 = 0;
        while (idx1 < name.length() && idx2 < typed.length()) {
            if (name.charAt(idx1) == typed.charAt(idx2)) { idx1 ++; idx2 ++; }
            else if (idx1 > 0 && typed.charAt(idx2) == name.charAt(idx1 - 1)) {
                idx2 ++;
            } else return false;
        }
        return idx1 == name.length();
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("alex", "aaleex", true);
    }

    public void runTest(final String name, final String typed, final Boolean expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { name, typed });

        for (Object answer : answers)
            assertThat((Boolean) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
