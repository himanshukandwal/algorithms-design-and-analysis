package challenges.codefights;

import challenges.AbstractCustomTestRunner;

/**
 * Avoid Obstacles
 *
 * You are given an array of integers representing coordinates of obstacles situated on a straight line.
 * Assume that you are jumping from the point with coordinate 0 to the right. You are allowed only to make jumps of the same length represented by some integer.
 * Find the minimal length of the jump enough to avoid all the obstacles.
 *
 * Example:
 *  For inputArray = [5, 3, 6, 7, 9], the output should be avoidObstacles(inputArray) = 4.
 *
 * link: https://app.codesignal.com/arcade/intro/level-5/XC9Q2DhRRKQrfLhb5/description
 *
 * @author Hxkandwal
 */
public class AvoidObstacles extends AbstractCustomTestRunner {

    int avoidObstacles(int[] arr) {
        int i = 0;
        outer: while (++ i < Integer.MAX_VALUE) {
            inner: for (int a : arr) if (a % i == 0) continue outer;
            break outer;
        }
        return i;
    }

}
