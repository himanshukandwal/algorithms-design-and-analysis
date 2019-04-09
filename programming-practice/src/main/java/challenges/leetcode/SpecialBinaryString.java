package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;

/**
 * 761. Special Binary String
 *
 * Special binary strings are binary strings with the following two properties:
 *      The number of 0's is equal to the number of 1's.
 *      Every prefix of the binary string has at least as many 1's as 0's.
 *
 * Given a special string S, a move consists of choosing two consecutive, non-empty, special substrings of S, and swapping them.
 * (Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.)
 *
 * At the end of any number of moves, what is the lexicographically largest resulting string possible?
 *
 * Example 1:
 *      Input: S = "11011000"
 *      Output: "11100100"
 *      Explanation: The strings "10" [occuring at S[1]] and "1100" [at S[3]] are swapped.
 *                   This is the lexicographically largest string possible after some number of swaps.
 *
 * Note:
 *  S has length at most 50.
 *  S is guaranteed to be a special binary string as defined above.
 *
 * @author Hxkandwal
 */
public class SpecialBinaryString extends AbstractCustomTestRunner {

    private static SpecialBinaryString _instance = new SpecialBinaryString();

    /**
     * The 2nd definition is essentially saying that at any point of the string, you cannot have more 0's than 1's.
     *
     * If we map '1' to '(', '0's to ')', a Special-String is essentially Valid-Parentheses, therefore share all the properties of a Valid-Parentheses
     * A VP (Valid-Parentheses) have 2 form:
     *
     * single nested VP like "(())", or "1100";
     * a number of consecutive sub-VPs like "()(())", or "101100", which contains "()" + "(())" or "10" + "1100"
     *
     * And this problem is essentially ask you to reorder the sub-VPs in a VP to make it bigger. If we look at this example : "()(())" or "101100",
     * how would you make it bigger?
     *
     * Answer is, by moving the 2nd sub-string to the front. Because deeply nested VP contains more consecutive '('s or '1's in the front.
     * That will make reordered string bigger.
     *
     * The above example is straitforward, and no recursion is needed. But, what if the groups of sub-VPs are not in the root level?
     * Like if we put another "()(())" inside "()(())", like "()(( ()(()) ))", in this case we will need to recursively reorder the children,
     * make them MVP(Max-Valid-Parentheses), then reorder in root.
     *
     * To summarize, we just need to reorder all groups of VPs or SS's at each level to make them MVP, then reorder higher level VPs;
     */
    public String _makeLargestSpecial(String S) {
        if (S.length() == 0) return S;
        List<String> innerPairs = new ArrayList<>();
        int bal = 0;
        for (int idx = 0, start = 0; idx < S.length(); idx ++) {
            bal += S.charAt(idx) == '0' ? -1 : 1;
            if (bal == 0) {
                innerPairs.add ("1" + _makeLargestSpecial(S.substring(start + 1, idx)) + "0");
                start = idx + 1;
            }
        }

        Collections.sort (innerPairs, (a, b) -> b.compareTo(a));
        StringBuilder ans = new StringBuilder();
        for (String pair : innerPairs) ans.append (pair);
        return ans.toString();
    }

    // Single pass implementation (using recursion as stack, and understanding '0' as way to exit stack, '1' to enter)
    public String _makeLargestSpecialFaster(String S) {
        if (S == null || S.length() == 0) return S;
        return helper(S, 0);
    }

    private String helper(String S, int start){
        if (S.charAt(start) == '0') return "";

        Queue<String> tokens = new PriorityQueue<>();
        while (start < S.length() && S.charAt(start) == '1'){
            String token = "1" + helper(S, start + 1) + "0";
            start += token.length();
            tokens.offer(token);
        }

        StringBuilder sb = new StringBuilder();
        while (!tokens.isEmpty())
            sb.insert(0, tokens.poll());

        return sb.toString();
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("110110100100", "111010010100");
    }

    public void runTest(final String s, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { s });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
