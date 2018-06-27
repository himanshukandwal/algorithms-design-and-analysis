package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 728. Self Dividing Numbers
 *
 * A self-dividing number is a number that is divisible by every digit it contains.
 *
 * For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.
 *
 * Also, a self-dividing number is not allowed to contain the digit zero. Given a lower and upper number bound, output a list of every possible
 * self dividing number, including the bounds if possible.
 *
 * Example 1:
 *      Input: left = 1, right = 22
 *      Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 *
 * Note: The boundaries of each input argument are 1 <= left <= right <= 10000.
 *
 * @author hxkandwal
 */
public class SelfDividingNumbers extends AbstractCustomTestRunner {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        outer: for (int idx = left; idx <= right; idx ++) {
            inner: for (int n = idx; n > 0; n /= 10) if (n % 10 == 0 || idx % (n % 10) != 0) continue outer;
            ans.add (idx);
        }
        return ans;
    }
}
