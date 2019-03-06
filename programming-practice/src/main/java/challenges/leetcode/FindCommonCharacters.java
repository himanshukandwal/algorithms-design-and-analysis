package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 1002. Find Common Characters
 *
 * Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).
 * For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.
 *
 * You may return the answer in any order.
 *
 * Example 1:
 *      Input: ["bella","label","roller"]
 *      Output: ["e","l","l"]
 *
 * Example 2:
 *      Input: ["cool","lock","cook"]
 *      Output: ["c","o"]
 *
 * Note:
 *  1 <= A.length <= 100
 *  1 <= A[i].length <= 100
 *  A[i][j] is a lowercase letter
 *
 * @author Hxkandwal
 */
public class FindCommonCharacters extends AbstractCustomTestRunner {

    public List<String> commonChars(String[] A) {
        int [][] chrs = new int [A.length][256];
        for (int idx = 0; idx < A.length; idx ++)
            for (char c : A [idx].toCharArray()) chrs[idx][c] ++;

        List<String> ans = new ArrayList<>();
        for (int c = 0; c < 256; c ++) {
            int min = Integer.MAX_VALUE;
            for (int r = 0; r < A.length; r ++) min = Math.min (min, chrs [r][c]);
            while (min -- > 0) ans.add (String.valueOf(((char) c)));
        }
        return ans;
    }
}
