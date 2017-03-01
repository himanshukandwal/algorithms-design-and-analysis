package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 308. Range Sum Query 2D - Mutable
 * 
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle
 * defined by its upper left corner (row1, col1) and lower right corner (row2,
 * col2).
 * 
 * Example: Given matrix = [ [3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7], [1, 0, 3, 0, 5] ]
 * 
 * sumRegion(2, 1, 4, 3) -> 8 update(3, 2, 2) sumRegion(2, 1, 4, 3) -> 10
 * 
 * @author Hxkandwal
 */
public class RangeSumQuery2DMutable extends AbstractCustomTestRunner {

	public class BIT {
		int[] arr;
		int size = 0;

		public BIT(int size) {
			this.size = size + 1;
			this.arr = new int[this.size];
		}

		public void add(int idx, int val) {
			idx++;
			while (idx < size) {
				arr[idx] += val;
				idx += idx & (~idx + 1);
			}
		}

		public int sum(int idx) {
			idx++;
			int sum = 0;
			while (idx > 0) {
				sum += arr[idx];
				idx -= idx & (~idx + 1);
			}
			return sum;
		}

		public int range(int to, int from) {
			return sum(from) - sum(to);
		}
	}

	private BIT[] ft;

	public RangeSumQuery2DMutable(int[][] matrix) {
		this.ft = new BIT[matrix.length];

		for (int row = 0; row < matrix.length; row++) {
			ft[row] = new BIT(matrix[row].length);
			for (int col = 0; col < matrix[row].length; col++)
				ft[row].add(col, matrix[row][col]);
		}
	}

	public void update(int row, int col, int val) {
		ft[row].add(col, -ft[row].arr[col + 1]);
		ft[row].add(col, val);
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		int sum = 0;
		for (int row = row1; row <= row2; row++)
			sum += ft[row].range(col1 - 1, col2);
		return sum;
	}

	// driver method
	public static void main(String[] args) {
		RangeSumQuery2DMutable mutable = new RangeSumQuery2DMutable(
				new int[][] { new int[] { 3, 0, 1, 4, 2 }, new int[] { 5, 6, 3, 2, 1 }, new int[] { 1, 2, 0, 1, 5 },
						new int[] { 4, 1, 0, 1, 7 }, new int[] { 1, 0, 3, 0, 5 } });

		System.out.println(mutable.sumRegion(2, 1, 4, 3));
		mutable.update(3, 2, 2);
		System.out.println(mutable.sumRegion(2, 1, 4, 3));
	}

	public void runTest(final String s, List<String> d, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, d });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
