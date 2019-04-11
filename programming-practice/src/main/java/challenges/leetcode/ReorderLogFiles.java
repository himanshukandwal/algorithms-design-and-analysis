package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 937. Reorder Log Files
 *
 * You have an array of logs.  Each log is a space delimited string of words.
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 *
 *  Each word after the identifier will consist only of lowercase letters, or;
 *  Each word after the identifier will consist only of digits.
 *  We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
 *
 * Reorder the logs so that all of the letter-logs come before any digit-log.
 * The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
 *
 * Return the final order of the logs.
 *
 * Example 1:
 *  Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 *  Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 *
 * Note:
 *  0 <= logs.length <= 100
 *  3 <= logs[i].length <= 100
 *  logs[i] is guaranteed to have an identifier, and a word after the identifier.
 *
 * @author Hxkandwal
 */
public class ReorderLogFiles extends AbstractCustomTestRunner {

    public String[] _reorderLogFiles(String[] logs) {
        List<Integer> digits = new ArrayList<>(), words = new ArrayList<>();
        for (int idx = 0; idx < logs.length; idx ++) {
            String log = logs [idx];

            int sIdx = log.indexOf(" ");

            if (Character.isDigit(log.charAt(sIdx + 1))) digits.add (idx);
            else words.add (idx);
        }

        Collections.sort (words, (a, b) -> {
            int aIdx = logs [a].indexOf(" "), bIdx = logs [b].indexOf(" ");
            int compare = logs [a].substring (aIdx).compareTo(logs [b].substring(bIdx));

            return (compare == 0) ?
                    logs [a].substring (0, aIdx).compareTo(logs [b].substring(0, bIdx)) :
                    compare;
        });

        String [] ans = new String [logs.length];
        int idx = 0;
        for (int w : words) ans [idx ++] = logs [w];
        for (int d : digits) ans [idx ++] = logs [d];
        return ans;
    }
}
