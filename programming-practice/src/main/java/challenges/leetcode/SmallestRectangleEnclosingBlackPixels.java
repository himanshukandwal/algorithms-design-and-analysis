package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 302. Smallest Rectangle Enclosing Black Pixels
 *
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region.
 * Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle
 * that encloses all black pixels.
 *
 * Example:
 *      Input:
 *              [
 *                   "0010",
 *                   "0110",
 *                   "0100"
 *              ]
 *              and x = 0, y = 2
 *      Output: 6
 *
 * @author Hxkandwal
 */
public class SmallestRectangleEnclosingBlackPixels extends AbstractCustomTestRunner {

    private final static SmallestRectangleEnclosingBlackPixels _instance = new SmallestRectangleEnclosingBlackPixels();

    public int _minArea(char[][] image, int x, int y) {
        int[] xy = { x, y, x, y };
        dfs (image, xy, x, y);
        return (xy [2] - xy [0] + 1) * (xy [1] - xy [3] + 1);
    }

    private void dfs (char[][] im, int[] xy, int r, int c) {
        if (r < 0 || r >= im.length || c < 0 || c >= im [0].length || im [r][c] != '1') return;
        im [r][c] = '#';
        xy [0] = Math.min (xy [0], r);
        xy [1] = Math.max (xy [1], c);
        xy [2] = Math.max (xy [2], r);
        xy [3] = Math.min (xy [3], c);

        dfs (im, xy, r - 1, c); dfs (im, xy, r, c - 1);
        dfs (im, xy, r + 1, c); dfs (im, xy, r, c + 1);
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new char [][] {
                "0000001111111100011111100000000000000000000000".toCharArray(),
                "0000001111111100111111100000000000000000000000".toCharArray(),
                "0000001111111110111111110000000000000000000000".toCharArray(),
                "0000001111111110111111110000000000000000000000".toCharArray(),
                "0000000111110111111111110000000000000000000000".toCharArray(),
                "0000000111000111111000010000000000000000000000".toCharArray()
        }, 5, 7, 108);
    }
    
    public void runTest(final char[][] image, final int x, final int y, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { image, x, y });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
