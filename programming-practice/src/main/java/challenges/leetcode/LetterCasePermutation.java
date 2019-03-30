package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 784. Letter Case Permutation
 *
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create.
 *
 * Examples:
 *          Input: S = "a1b2"
 *          Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 *          Input: S = "3z4"
 *          Output: ["3z4", "3Z4"]
 *
 *          Input: S = "12345"
 *          Output: ["12345"]
 *
 * Note:
 *  S will be a string with length between 1 and 12.
 *  S will consist only of letters or digits.
 *
 * @author Hxkandwal
 */
public class LetterCasePermutation extends AbstractCustomTestRunner {

    public List<String> _letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        return dfs (S, 0);
    }

    private List<String> dfs (String s, int index) {
        if (index == s.length()) return Arrays.asList("");
        List<String> ans = new ArrayList<>();
        char ch = s.charAt(index);

        for (String next : dfs (s, index + 1)) {
            if (Character.isDigit(ch)) ans.add (ch + next);
            else {
                ans.add (Character.toUpperCase (ch) + next);
                ans.add (Character.toLowerCase (ch) + next);
            }
        }
        return ans;
    }
}
