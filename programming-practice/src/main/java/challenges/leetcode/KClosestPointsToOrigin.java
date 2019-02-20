package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 973. K Closest Points to Origin
 *
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0). (Here, the distance between two points on a plane is the Euclidean distance.)
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 * Example 1:
 *      Input: points = [[1,3],[-2,2]], K = 1
 *      Output: [[-2,2]]
 *      Explanation: The distance between (1, 3) and the origin is sqrt(10).
 *                   The distance between (-2, 2) and the origin is sqrt(8).
 *                   Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 *                   We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 *
 * Example 2:
 *      Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 *      Output: [[3,3],[-2,4]]
 *      (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 * Note:
 *  1 <= K <= points.length <= 10000
 *  -10000 < points[i][0] < 10000
 *  -10000 < points[i][1] < 10000
 *
 * @author Hxkandwal
 */
public class KClosestPointsToOrigin extends AbstractCustomTestRunner {

    private static KClosestPointsToOrigin _instance = new KClosestPointsToOrigin();

    public int[][] kClosest(int[][] points, int K) {
        sort (points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    private void sort(int[][] p, int s, int e, int K) {
        if (s >= e) return;
        int pi = e, pivotValue = value (p [e]);

        int i = s, j = e;
        while (i < j) {
            while (i < j && value (p [i]) < pivotValue) i ++;
            while (i < j && value (p [j]) >= pivotValue) j --;
            if (i < j) swap(p, i, j);
        }
        swap (p, pi, j);

        int len = j - s + 1;
        if (K < len)
            sort (p, s, j - 1, K);
        else if (K > len)
            sort (p, j + 1, e, K - len);
    }

    private int value(int[] p) {
        return p[0] * p [0] + p[1] * p[1];
    }

    private void swap(int[][] p, int s, int e) {
        int t0 = p [s][0], t1 = p [s][1];
        p [s][0] = p [e][0];
        p [s][1] = p [e][1];
        p [e][0] = t0;
        p [e][1] = t1;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int [][] {{ 1,3},{-2,2}}, 1, new int [][] {{-2,2}});
        _instance.runTest(new int [][] {{ 1,3},{-2,2}}, 1, new int [][] {{-2,2}});
    }

    public void runTest(final int[][] points, final int K, final int[][] expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { points, K });

        for (Object answer : answers)
            for (int idx = 0; idx < expectedOutput.length; idx ++)
                assertThat(((int [][])answer) [idx]).isEqualTo(expectedOutput [idx]);

        System.out.println("ok!");
    }
}
