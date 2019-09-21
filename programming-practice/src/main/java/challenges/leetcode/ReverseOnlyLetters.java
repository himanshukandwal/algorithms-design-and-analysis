package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 917. Reverse Only Letters
 *
 * Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.
 *
 * Example 1:
 *          Input: "ab-cd"
 *          Output: "dc-ba"
 *
 * Example 2:
 *          Input: "a-bC-dEf-ghIj"
 *          Output: "j-Ih-gfE-dCba"
 *
 * Example 3:
 *          Input: "Test1ng-Leet=code-Q!"
 *          Output: "Qedo1ct-eeLg=ntse-T!"
 *
 * Note:
 *  S.length <= 100
 *  33 <= S[i].ASCIIcode <= 122
 *  S doesn't contain \ or "
 *
 * @author Hxkandwal
 */
public class ReverseOnlyLetters extends AbstractCustomTestRunner {

    public String _reverseOnlyLetters(String S) {
        char[] ans = S.toCharArray();
        int l = 0, r = ans.length - 1;
        while (l < r) {
            while (l < r && !Character.isLetter(ans [l])) l ++;
            while (l < r && !Character.isLetter(ans [r])) r --;
            char c = ans [l];
            ans [l] = ans [r];
            ans [r] = c;
            l ++;
            r --;
        }
        return String.valueOf(ans);
    }

}
