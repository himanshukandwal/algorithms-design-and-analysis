package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 1189. Maximum Number of Balloons
 *
 * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
 * You can use each character in text at most once. Return the maximum number of instances that can be formed.
 *
 * Example 1:
 *      Input: text = "nlaebolko"
 *      Output: 1
 *
 * Example 2:
 *      Input: text = "loonbalxballpoon"
 *      Output: 2
 *
 * Example 3:
 *      Input: text = "leetcode"
 *      Output: 0
 *
 * Constraints:
 *  1 <= text.length <= 10^4
 *  text consists of lower case English letters only.
 *
 * @author Hxkandwal
 */
public class MaximumNumberOfBalloons extends AbstractCustomTestRunner {

    public int _maxNumberOfBalloons(String text) {
        if (text == null || text.length() == 0) return 0;
        int ans = Integer.MAX_VALUE;
        int [] map = new int [256];
        for (char c : text.toCharArray()) map [c] ++;

        ans = Math.min(ans, map ['b']);
        ans = Math.min(ans, map ['a']);
        ans = Math.min(ans, map ['l'] / 2);
        ans = Math.min(ans, map ['o'] / 2);
        ans = Math.min(ans, map ['n']);
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

}
