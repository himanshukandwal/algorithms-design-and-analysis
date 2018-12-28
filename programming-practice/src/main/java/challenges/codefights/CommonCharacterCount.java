package challenges.codefights;

import challenges.AbstractCustomTestRunner;

/**
 * Common Character Count
 *
 * Given two strings, find the number of common characters between them.
 *
 * Example:
 *  For s1 = "aabcc" and s2 = "adcaa", the output should be commonCharacterCount(s1, s2) = 3.
 *  Strings have 3 common characters - 2 "a"s and 1 "c".
 *
 * link: https://app.codesignal.com/arcade/intro/level-3/JKKuHJknZNj4YGL32/description
 *
 * @author Hxkandwal
 */
public class CommonCharacterCount extends AbstractCustomTestRunner {

    int commonCharacterCount(String s1, String s2) {
        int ans = 0;
        int[] arr1 = new int [256], arr2 = new int [256];

        for (char c : s1.toCharArray()) arr1 [c] ++;
        for (char c : s2.toCharArray()) arr2 [c] ++;
        for (int idx = 0; idx < 256; idx ++) ans += Math.min (arr1 [idx], arr2 [idx]);
        return ans;
    }

}
