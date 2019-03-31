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
        char [] arr = S.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            while (i < j && !Character.isLetter(arr [i])) i ++;
            while (i < j && !Character.isLetter(arr [j])) j --;
            if (i < j) {
                char c = arr [i];
                arr [i] = arr [j];
                arr [j] = c;
                i ++;
                j --;
            }
        }
        return String.valueOf(arr);
    }

}
