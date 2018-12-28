package challenges.codefights;

import challenges.AbstractCustomTestRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Matrix Elements Sum
 *
 * After they became famous, the CodeBots all decided to move to a new building and live together. The building is represented by a rectangular matrix of rooms.
 * Each cell in the matrix contains an integer that represents the price of the room. Some rooms are free (their cost is 0), but that's probably because they
 * are haunted, so all the bots are afraid of them. That is why any room that is free or is located anywhere below a free room in the same column is not considered
 * suitable for the bots to live in.
 *
 * Help the bots calculate the total price of all the rooms that are suitable for them.
 *
 * Example:
 *  For matrix = [[0, 1, 1, 2],
 *                [0, 5, 0, 0],
 *                [2, 0, 3, 3]]
 *
 *  the output should be matrixElementsSum(matrix) = 9.
 *
 *  Here's the rooms matrix with unsuitable rooms marked with 'x':
 *          [[x, 1, 1, 2],
 *           [x, 5, x, x],
 *           [x, x, x, x]]
 *
 *  Thus, the answer is 1 + 5 + 1 + 2 = 9.
 *
 * link: https://app.codesignal.com/arcade/intro/level-2/xskq4ZxLyqQMCLshr/description
 *
 * @author Hxkandwal
 */
public class MatrixElementsSum extends AbstractCustomTestRunner {

    int matrixElementsSum(int[][] matrix) {
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for (int r = 0; r < matrix.length; r ++)
            for (int c = 0; c < matrix[0].length; c ++)
                if (!set.contains(c)) {
                    if (matrix [r][c] == 0) set.add (c);
                    else ans += matrix [r][c];
                }

        return ans;
    }
}
