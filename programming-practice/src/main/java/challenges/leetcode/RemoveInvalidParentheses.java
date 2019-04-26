package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;

/**
 * 301. Remove Invalid Parentheses
 *
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *      Input: "()())()"
 *      Output: ["()()()", "(())()"]
 *
 * Example 2:
 *      Input: "(a)())()"
 *      Output: ["(a)()()", "(a())()"]
 *
 * Example 3:
 *      Input: ")("
 *      Output: [""]
 *
 * @author Hxkandwal
 */
public class RemoveInvalidParentheses extends AbstractCustomTestRunner {

    private static RemoveInvalidParentheses _instance = new RemoveInvalidParentheses();

    public List<String> _removeInvalidParentheses(String s) {
        Queue<String> queue = new LinkedList<>();
        queue.offer (s);
        boolean found = false;
        Map<String, Boolean> map = new HashMap<>();

        Set<String> ans = new HashSet<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> nextBatch = new HashSet<>();
            while (size -- > 0) {
                String str = queue.poll();
                if (isValid(map, str)) {
                    found = true;
                    ans.add (str);
                }
                else {
                    for (int idx = 0; idx < str.length(); idx ++)
                        if (idx == 0 || str.charAt(idx) != str.charAt(idx - 1))
                            nextBatch.add(str.substring(0, idx) + str.substring(idx + 1));
                }
            }
            if (found) break;
            for (String str : nextBatch) queue.offer(str);
        }

        return new ArrayList<>(ans);
    }

    private boolean isValid(Map<String, Boolean> map, String s) {
        if (map.containsKey(s)) return map.get (s);
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c != '(' && c != ')') continue;
            if (c == '(') count ++;
            else if (count -- == 0) return false;
        }
        map.put (s, count == 0);
        return count == 0;
    }

    public List<String> _removeInvalidParenthesesFastest(String s) {
        Set<String> ans = new HashSet<>();                          // using hash set to avoid duplicates
        int le = 0, re = 0;                                         // extras, indicates how many '(' ans ')' need to be deleted.
        for (char c : s.toCharArray()) {
            if (c == '(') le ++;
            if (c == ')') {
                if (le > 0) le --;
                else re ++;
            }
        }

        dfs (ans, "", le, re, 0, 0, s);
        return new ArrayList<>(ans);
    }

    private void dfs (Set<String> ans, String build, int le, int re, int balance, int index, String s) {
        //situation that get a valid result.
        if (le == 0 && re == 0 && balance == 0 && index == s.length()) {
            ans.add(build);
        }

        if (le < 0 || re < 0 || index >= s.length() || balance < 0) return;

        char c = s.charAt(index);

        if (c == '(') {
            dfs (ans, build, le - 1, re, balance, index + 1, s);                        // delete this
            dfs (ans, build + "(", le, re, balance + 1, index + 1, s);       // add this
        }
        else if (c == ')') {
            dfs (ans, build, le, re  - 1, balance, index + 1, s);                      // delete this
            dfs (ans, build + ")", le, re, balance - 1, index + 1, s);       // add this
        } else {
            //situation of other string
            dfs (ans, build + c, le, re, balance, index + 1, s);
        }
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest("()())()", Arrays.asList("()()()", "(())()"));
    }

    public void runTest(final String input, final List<String> expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input });

        for (Object answer : answers)
            assertThat((List<String>) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
