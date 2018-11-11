package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

import java.util.Stack;

/**
 * Largest Rectangle
 *
 * link: https://www.hackerrank.com/challenges/largest-rectangle/problem
 */
public class LargestRectangle extends AbstractCustomTestRunner {

    public long largestRectangle(int[] h) {
        long ans = 0;
        Stack<int[]> stack = new Stack<>();
        for (int idx = 0; idx < h.length; idx ++) {
            int i = idx;
            while (!stack.isEmpty() && stack.peek()[1] >= h [idx]) {
                int [] v = stack.pop();
                ans = Math.max (ans, v [1] * (idx - v [0]) * 1l);
                i = v [0];
            }
            stack.push(new int [] { i, h [idx] });
        }
        for (int[] v : stack) ans = Math.max(ans, v [1] * (h.length - v [0]) * 1l);
        return ans;
    }
}
