package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

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
}
