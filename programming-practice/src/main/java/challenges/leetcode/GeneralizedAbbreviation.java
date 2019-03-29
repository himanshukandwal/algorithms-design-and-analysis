package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 320. Generalized Abbreviation
 *
 * Write a function to generate the generalized abbreviations of a word.
 *
 * Note: The order of the output does not matter.
 *
 * Example:
 *          Input: "word"
 *          Output: ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 *
 * @author Hxkandwal
 */
public class GeneralizedAbbreviation extends AbstractCustomTestRunner {

    public List<String> _generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        dfs(res, new StringBuilder(), word.toCharArray(), 0, 0);
        return res;
    }

    public void dfs(List<String> res, StringBuilder sb, char[] c, int i, int num) {
        int len = sb.length();
        if (i == c.length) {
            if (num != 0) sb.append(num);
            res.add(sb.toString());
        } else {
            dfs (res, sb, c, i + 1, num + 1);               // abbr c[i], choose

            if (num != 0) sb.append(num);                           // not abbr c[i], not choose
            dfs (res, sb.append(c[i]), c, i + 1, 0);
        }
        sb.setLength(len);                                          // remove operation
    }
}
