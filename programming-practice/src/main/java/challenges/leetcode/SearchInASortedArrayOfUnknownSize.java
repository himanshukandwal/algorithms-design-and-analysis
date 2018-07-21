package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 702. Search in a Sorted Array of Unknown Size
 *
 * Given an integer array sorted in ascending order, write a function to search target in nums.  If target exists, then return its index, otherwise return -1. However, the array size is unknown to you.
 * You may only access the array using an ArrayReader interface, where ArrayReader.get(k) returns the element of the array at index k (0-indexed).
 *
 * You may assume all integers in the array are less than 10000, and if you access the array out of bounds, ArrayReader.get will return 2147483647.
 *
 * Example 1:
 *      Input: array = [-1,0,3,5,9,12], target = 9
 *      Output: 4
 *      Explanation: 9 exists in nums and its index is 4
 *
 * Example 2:
 *      Input: array = [-1,0,3,5,9,12], target = 2
 *      Output: -1
 *      Explanation: 2 does not exist in nums so return -1
 *
 * Note:
 *  - You may assume that all elements in the array are unique.
 *  - The value of each element in the array will be in the range [-9999, 9999].
 *
 * @author hxkandwal
 */
public class SearchInASortedArrayOfUnknownSize extends AbstractCustomTestRunner {

    private static SearchInASortedArrayOfUnknownSize _instance = new SearchInASortedArrayOfUnknownSize();

    public static class ArrayReader {
        private int[] arr;

        public ArrayReader(int[] arr) {
            this.arr = arr;
        }

        public int get(int idx) {
            return (idx < arr.length) ? arr [idx] : Integer.MAX_VALUE;
        }
    }

    public int _search(ArrayReader reader, int target) {
        int bound = 2147483647;
        int start = 0;
        while (reader.get(start) < bound) {
            if (reader.get(start) == target) return start;

            int p = 1;
            for (; reader.get(start + p) < bound && reader.get(start + p) <= target; p = 2 * p)
                if (reader.get(start + p) == target) return start + p;

            if (p/2 == 0) break;
            start += p/2;
        }
        return -1;
    }

    public int _searchBinary(ArrayReader reader, int target) {
        int end = 1;

        // set the end pointer to the valid point, i.e. anyplace after the target, if possible.
        while (reader.get(end) < target) end = end << 1;

        // start the start pointer from the half of the end pointer.
        int start = end >> 1;
        while (start <= end) {
            int m = start + (end - start) / 2;
            if (reader.get(m) > target) end = m - 1;
            else if (reader.get(m) < target) start = m + 1;
            else return m;
        }
        return -1;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new ArrayReader(new int[] { -1, 0, 3, 5, 9, 12 }), 9, 4);
        _instance.runTest(new ArrayReader(new int[] { -1, 0, 3, 5, 9, 12 }), 2, -1);
        _instance.runTest(new ArrayReader(new int[] { -1, 0, 3, 5, 9, 12 }), -1, 0);
        _instance.runTest(new ArrayReader(new int[] { -1, 0, 3, 5, 9, 12 }), 12, 5);
    }

    public void runTest(final ArrayReader reader, final int target, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { reader, target });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
