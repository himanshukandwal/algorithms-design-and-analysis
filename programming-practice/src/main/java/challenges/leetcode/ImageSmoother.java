package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 661. Image Smoother
 *
 * Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale
 * (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.
 *
 * Example 1:
 *      Input:
 *              [[1,1,1],
 *               [1,0,1],
 *               [1,1,1]]
 *      Output:
 *              [[0, 0, 0],
 *               [0, 0, 0],
 *               [0, 0, 0]]
 *
 *      Explanation:
 *              For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
 *              For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
 *              For the point (1,1): floor(8/9) = floor(0.88888889) = 0
 *
 * Note:
 *  The value in the given matrix is in the range of [0, 255].
 *  The length and width of the given matrix are in the range of [1, 150].
 *
 * @author Hxkandwak
 */
public class ImageSmoother extends AbstractCustomTestRunner {

    public int[][] _imageSmoother(int[][] M) {
        int[][] ans = new int [M.length][M [0].length];
        for (int r = 0; r < M.length; r ++) {
            for (int c = 0; c < M [0].length; c ++) {
                int count = 0, total = 0;
                for (int i = -1; i < 2; i ++) {
                    for (int j = -1; j < 2; j ++) {
                        if (r + i < 0 || r + i >= M.length || c + j < 0 || c + j >= M[0].length) continue;
                        total ++;
                        count += M [r + i][c + j];
                    }
                }
                ans [r][c] = count/total;
            }
        }
        return ans;
    }
}
