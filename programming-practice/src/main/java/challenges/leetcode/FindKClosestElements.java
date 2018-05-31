package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 658. Find K Closest Elements
 *
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie,
 * the smaller elements are always preferred.
 *
 * Example 1:
 *      Input: [1,2,3,4,5], k = 4, x = 3
 *      Output: [1,2,3,4]
 *
 * Example 2:
 *      Input: [1,2,3,4,5], k = 4, x = -1
 *      Output: [1,2,3,4]
 *
 * Note:
 *  The value k is positive and will always be smaller than the length of the sorted array.
 *  Length of the given array is positive and will not exceed 10^4
 *  Absolute value of elements in the array and x will not exceed 10^4
 *
 * @author hxkandwal
 */
public class FindKClosestElements extends AbstractCustomTestRunner {

    private static FindKClosestElements _instance = new FindKClosestElements();

    public List<Integer> _findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();

        int l = 0, r = arr.length - 1;
        while (l < r) {
            int m = l + (r - l)/2;

            if (arr [m] >= x) r = m;
            else l = m + 1;
        }

        // this means valid ans is something like this:
        //     start <--------- ans -----------> end
        // where start and end are not part of the answer boundary.
        int start = l - 1, end = arr [l] == x ? l + 1 : l;
        while (end - start - 1 < k) {
            if (start < 0) end ++;
            else if (end > arr.length - 1) start --;
            else {
                if (Math.abs (arr [start] - x) > Math.abs (arr [end] - x)) end ++;
                else start --;
            }
        }
        for (int idx = start + 1; idx < end; idx ++) ans.add (arr [idx]);
        return ans;
    }

    /* Intuitively, we can sort the elements in list arr by their absolute difference values to the target x. Then the sublist of the first k elements is the result after sorting the elements by the natural order. */
    public List<Integer> _findClosestElementsSorting(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        for (int val : arr) list.add (val);

        Collections.sort(list, (a, b) -> a == b ? a - b : Math.abs(a - x) - Math.abs(b - x));
        list = list.subList(0, k);
        Collections.sort(list);
        return list;
    }

    public List<Integer> _findClosestElementsStartLocator(int [] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        for (int val : arr) list.add (val);

        int lo = 0, hi = list.size() - k;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (x - list.get(mid) > list.get(mid + k) - x)
                lo = mid + 1;
            else
                hi = mid;
        }
        return list.subList(lo, lo + k);
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int[] { 0, 0, 0, 1, 3, 5, 6, 7, 8, 8 }, 2, 2, Arrays.asList(1, 3));
        _instance.runTest(new int[] { 1, 2, 3, 4, 5 }, 4, 3, Arrays.asList(1, 2, 3, 4));
        _instance.runTest(new int[] { 0, 1, 2, 2, 2, 3, 6, 8, 8, 9 }, 5, 9, Arrays.asList(3, 6, 8, 8, 9));
        _instance.runTest(new int[] { 1, 1, 2, 3, 3, 3, 4, 6, 8, 8 }, 6, 1, Arrays.asList(1, 1, 2, 3, 3, 3));
        _instance.runTest(new int[] { 0, 0, 1, 2, 3, 3, 4, 7, 7, 8 }, 3, 5, Arrays.asList(3, 3, 4));
    }

    public void runTest(final int[] arr, final int k, final int x, final List<Integer> expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { arr, k, x });

        for (Object answer : answers)
            assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
