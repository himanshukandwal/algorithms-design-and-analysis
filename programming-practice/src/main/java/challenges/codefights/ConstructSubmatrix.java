package challenges.codefights;

import challenges.AbstractCustomTestRunner;

/**
 * Construct Submatrix
 *
 * Given a matrix (i.e. an array of arrays), find its submatrix obtained by deleting the specified rows and columns.
 * Example:
 *  For matrix = [[1, 0, 0, 2],
 *                [0, 5, 0, 1],
 *                [0, 0, 3, 5]]
 *      rowsToDelete = [1], and columnsToDelete = [0, 2], the output should be
 *      constructSubmatrix(matrix, rowsToDelete, columnsToDelete) = [[0, 2],
 *                                                                   [0, 5]]
 *
 * link: https://app.codesignal.com/skill-test/spBaKtwKWJpubMWTE
 *
 * @author Hxkandwal
 */
public class ConstructSubmatrix extends AbstractCustomTestRunner {

    int[][] constructSubmatrix(int[][] matrix, int[] rowsToDelete, int[] columnsToDelete) {
        int[][] ans = new int [matrix.length - rowsToDelete.length][matrix[0].length - columnsToDelete.length];
        int idx = 0, nr = 0, nc = 0;
        for (int r = 0, rd = 0; r < matrix.length; r ++) {
            if (rd < rowsToDelete.length && rowsToDelete [rd] == r) rd ++;
            else {
                for (int c = 0, cd = 0; c < matrix[0].length; c ++) {
                    if (cd < columnsToDelete.length && columnsToDelete [cd] == c) cd ++;
                    else {
                        ans [nr][nc ++] = matrix [r][c];
                        if (nc == ans [0].length) { nc = 0; nr ++; }
                    }
                }
            }
        }
        return ans;
    }

}
