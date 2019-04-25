package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 680. Valid Palindrome II
 *
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Example 1:
 *              Input: "aba"
 *              Output: True
 *
 * Example 2:
 *              Input: "abca"
 *              Output: True
 *              Explanation: You could delete the character 'c'.
 *
 * Note:
 *  The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 *
 * @author Hxkandwal
 */
public class ValidPalindromeII extends AbstractCustomTestRunner {

    private static ValidPalindromeII _instance = new ValidPalindromeII();

    public boolean _validPalindrome(String s) {
        int l = 0, r = s.length() - 1, count = 0;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r))
                return isPalindrome(s.substring (l, r)) || isPalindrome(s.substring (l + 1, r + 1));

            l ++;
            r --;
        }
        return true;
    }

    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l ++;
            r --;
        }
        return true;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga", true);
    }

    public void runTest(final String a, final Boolean expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { a });

        for (Object answer : answers)
            assertThat((Boolean) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
