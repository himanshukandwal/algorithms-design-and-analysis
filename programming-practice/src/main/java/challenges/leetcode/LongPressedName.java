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
        if (typed.length() < name.length()) return false;
        int nIdx = 0, tIdx = 0;
        while (nIdx < name.length()) {
            char c = name.charAt(nIdx);
            int ncount = 1;
            while (nIdx + 1 < name.length() && name.charAt(nIdx + 1) == c) { nIdx ++; ncount ++; }

            if (tIdx >= typed.length() || typed.charAt(tIdx) != c) return false;
            int tcount = 1;
            while (tIdx + 1 < typed.length() && typed.charAt(tIdx + 1) == c) { tIdx ++; tcount ++; }

            if (ncount > tcount) return false;
            nIdx ++;
            tIdx ++;
        }
        return true;
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
