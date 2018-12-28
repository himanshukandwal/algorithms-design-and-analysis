package challenges.codefights;

import challenges.AbstractCustomTestRunner;

/**
 * All Longest Strings
 *
 * Given an array of strings, return another array containing all of its longest strings.
 * Example:
 *  For inputArray = ["aba", "aa", "ad", "vcd", "aba"], the output should be allLongestStrings(inputArray) = ["aba", "vcd", "aba"].
 *
 * link: https://app.codesignal.com/arcade/intro/level-3/fzsCQGYbxaEcTr2bL/description
 *
 * @author Hxkandwal
 */
public class AllLongestStrings extends AbstractCustomTestRunner {

    String[] allLongestStrings(String[] inputArray) {
        int maxLength = 0, count = 0;
        for (String s : inputArray) {
            if (maxLength < s.length()) {  maxLength = s.length(); count = 1; }
            else if (maxLength == s.length()) count ++;
        }
        String[] ans = new String [count];
        int idx = 0;
        for (String s : inputArray) if (s.length() == maxLength) ans [idx ++] = s;
        return ans;
    }

}
