package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 378. Kth Smallest Element in a Sorted Matrix
 * 
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest 
 * element in the matrix.
 * 
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * 
 * Example:
 * 		matrix = [
 * 					[ 1,  5,  9],
 * 					[10, 11, 13],
 * 					[12, 13, 15]
 * 				 ],
 * 		k = 8,
 * 
 * return 13.
 * 
 * Note: You may assume k is always valid, 1 ≤ k ≤ n2.
 * 
 * @author Hxkandwal
 */
public class KthSmallestElementInASortedMatrix extends AbstractCustomTestRunner {
	
	private static KthSmallestElementInASortedMatrix _instance = new KthSmallestElementInASortedMatrix();
	
	private KthSmallestElementInASortedMatrix() {}

	public int _kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi]
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0,  j = matrix[0].length - 1;
			for (int i = 0; i < matrix.length; i++) {
				while (j >= 0 && matrix[i][j] > mid) j--;
				count += (j + 1);
			}
            if (count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;	
    }
	
   	// driver method
   	public static void main(String[] args) {
		_instance.runTest(new int [][] { new int[] { 1,  5,  9 },
										 new int[] { 10, 11, 13 },
										 new int[] { 12, 13, 15 }}, 8, 13);
   	}

   	public void runTest(final int[][] matrix, final int k, final int expectedOutput) {
   		List<Object> answers = runAll(getClass(), new Object[] { matrix, k });

   		for (Object answer : answers)
   				assertThat((Integer) answer).isEqualTo(expectedOutput);

   		System.out.println("ok!");
   	}
}
