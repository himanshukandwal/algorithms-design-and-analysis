package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 296. Best Meeting Point
 * 
 * @author Hxkandwal
 */
public class BestMeetingPoint extends AbstractCustomTestRunner {

	/**
	 * Before solving the 2D problem we first consider a 1D case. The solution is quite simple. Just find the median of all the x coordinates 
	 * and calculate the distance to the median.
	 * 
	 * Alternatively, we can also use two pointers to solve the 1D problem. left and right are how many people one left/right side of coordinates i/j. 
	 * If we have more people on the left we let j decrease otherwise increase i. The time complexity is O(n) and space is O(1).
	 * 
	 * To be more clear, a better view is we can think i and j as two meet points. All the people in [0, i] go to meet at i and all the people in [j, n - 1] 
	 * meet at j. We let left = sum(vec[:i+1]), right = sum(vec[j:]), which are the number of people at each meet point, and d is the total distance for the 
	 * left people meet at i and right people meet at j.
	 * 
	 * Our job is to let i == j with minimum d.
	 * If we increase i by 1, the distance will increase by left since there are 'left' people at i and they just move 1 step. The same applies to j, when 
	 * decrease j by 1, the distance will increase by right. To make sure the total distance d is minimized we certainly want to move the point with less people. 
	 * And to make sure we do not skip any possible meet point options we need to move one by one.
	 * 
	 * For the 2D cases we first need to sum the columns and rows into two vectors and call the 1D algorithm.
	 * The answer is the sum of the two. The time is then O(mn) and extra space is O(m+n)
	 * 
	 * Moreover, the solution is still O(mn)
	 */
	public int minTotalDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] row_sum = new int[n], col_sum = new int[m];

        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j) {
                row_sum[j] += grid[i][j];
                col_sum[i] += grid[i][j];
            }

        return minDistance1D(row_sum) + minDistance1D(col_sum);
    }

    public int minDistance1D(int[] vector) {
        int i = -1, j = vector.length;
        int d = 0, left = 0, right = 0;

        while (i != j) {
            if (left < right) {
                d += left;
                left += vector[++i];
            }
            else {
                d += right;
                right += vector[--j];
            }
        }
        return d;
    }
    
}
