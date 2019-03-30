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
 *          Input:  "word"
 *          Output: ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 *
 * @author Hxkandwal
 */
public class GeneralizedAbbreviation extends AbstractCustomTestRunner {

    public List<String> _generateAbbreviations(String word) {
        List<String> ans = new ArrayList<>();
        dfs(ans, new StringBuilder(), word, 0, 0);
        return ans;
    }

    private void dfs (List<String> ans, StringBuilder build, String w, int index, int k) {
        int len = build.length();
        if (index == w.length()) {
            if (k != 0) build.append (k);
            ans.add (build.toString());
        } else {
            dfs (ans, build, w, index + 1, k + 1);

            if (k != 0) build.append (k);
            dfs (ans, build.append (w.charAt(index)), w, index + 1, 0);
        }
        build.setLength(len);
    }
}
