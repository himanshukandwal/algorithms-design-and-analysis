package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 900. RLE Iterator
 *
 * Write an iterator that iterates through a run-length encoded sequence.
 *
 * The iterator is initialized by RLEIterator(int[] A), where A is a run-length encoding of some sequence.  More specifically, for all even i, A[i] tells us the
 * number of times that the non-negative integer value A[i+1] is repeated in the sequence.
 *
 * The iterator supports one function: next(int n), which exhausts the next n elements (n >= 1) and returns the last element exhausted in this way.
 * If there is no element left to exhaust, next returns -1 instead.
 *
 * For example, we start with A = [3,8,0,9,2,5], which is a run-length encoding of the sequence [8,8,8,5,5].  This is because the sequence can be read as "three eights,
 * zero nines, two fives".
 *
 * Example 1:
 *
 *  Input: ["RLEIterator","next","next","next","next"], [[[3,8,0,9,2,5]],[2],[1],[1],[2]]
 *  Output: [null,8,8,5,-1]
 *
 * Explanation:
 *     RLEIterator is initialized with RLEIterator([3,8,0,9,2,5]).
 *     This maps to the sequence [8,8,8,5,5].
 *     RLEIterator.next is then called 4 times:
 *
 *     .next(2) exhausts 2 terms of the sequence, returning 8.  The remaining sequence is now [8, 5, 5].
 *     .next(1) exhausts 1 term of the sequence, returning 8.  The remaining sequence is now [5, 5].
 *     .next(1) exhausts 1 term of the sequence, returning 5.  The remaining sequence is now [5].
 *     .next(2) exhausts 2 terms, returning -1.  This is because the first term exhausted was 5,
 *
 *    but the second term did not exist.  Since the last term exhausted does not exist, we return -1.
 *
 * Note:
 *
 *  0 <= A.length <= 1000
 *  A.length is an even integer.
 *  0 <= A[i] <= 10^9
 *  There are at most 1000 calls to RLEIterator.next(int n) per test case.
 *  Each call to RLEIterator.next(int n) will have 1 <= n <= 10^9.
 *
 * @author Hxkandwal
 */
public class RLEIterator extends AbstractCustomTestRunner {

    private List<Long> idxs = new ArrayList<>();
    private List<Integer> values = new ArrayList<>();
    private long low;
    private long size;

    public RLEIterator(int[] A) {
        for (int idx = 0; idx < A.length; idx += 2) {
            if (A [idx] == 0) continue;
            size += A [idx];
            idxs.add (size);
            values.add (A [idx + 1]);
        }
    }

    public int next(int n) {
        // once we encounter -1, we should always stay at it.
        if (low + n >= size) {
            low = size;
            return -1;
        }

        int idx = Collections.binarySearch(idxs, low + (n - 1));
        idx = idx < 0 ? -(idx + 1) : ++ idx;
        low += n;
        return values.get (idx);
    }

    // driver method
    public static void main(String[] args) {
        RLEIterator rleIterator = new RLEIterator(new int [] { 784, 303, 477, 583, 909, 505 });
        System.out.println(rleIterator.next(130));
        System.out.println(rleIterator.next(333));
        System.out.println(rleIterator.next(238));
        System.out.println(rleIterator.next(87));
        System.out.println(rleIterator.next(301));
        System.out.println(rleIterator.next(276));
    }


    class RLEIteratorOther {

        private int[] A;
        private int idx;
        private int qty;

        public RLEIteratorOther(int[] A) {
            this.A = A;
        }

        public int next(int n) {
            while (idx < A.length) {
                if (n > A [idx] - qty) {
                    n -= (A [idx] - qty);
                    qty = 0;
                    idx += 2;
                } else {
                    qty += n;
                    return A [idx + 1];
                }
            }
            return -1;
        }
    }
}

