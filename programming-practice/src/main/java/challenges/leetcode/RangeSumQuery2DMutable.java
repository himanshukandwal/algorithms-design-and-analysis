package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 308. Range Sum Query 2D - Mutable
 * 
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and 
 * lower right corner (row2, col2).
 * 
 * Example: Given matrix = [ 
 * 								[3, 0, 1, 4, 2], 
 * 								[5, 6, 3, 2, 1], 
 * 								[1, 2, 0, 1, 5],
 * 								[4, 1, 0, 1, 7], 
 * 								[1, 0, 3, 0, 5] 
 * 						   ]
 * 
 * sumRegion(2, 1, 4, 3) -> 8 
 * update(3, 2, 2) 
 * sumRegion(2, 1, 4, 3) -> 10
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
	private int[][] matrix;

	public RangeSumQuery2DMutable(int[][] matrix) {
		this.ft = new BIT[matrix.length];
		this.matrix = matrix;
		
		for (int row = 0; row < matrix.length; row++) {
			ft[row] = new BIT(matrix[row].length);
			for (int col = 0; col < matrix[row].length; col++)
				ft[row].add(col, matrix[row][col]);
		}
	}

	public void update(int row, int col, int val) {
		ft [row].add(col, -matrix[row][col]);
		matrix[row][col] = val; 
		ft [row].add(col, val);
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		int sum = 0;
		for (int row = row1; row <= row2; row++)
			sum += ft[row].range(col1 - 1, col2);
		return sum;
	}

	// driver method
	public static void main(String[] args) {
		// first test
		RangeSumQuery2DMutable mutable1 = new RangeSumQuery2DMutable(new int[][] { new int[] { 3, 0, 1, 4, 2 }, 
																				   new int[] { 5, 6, 3, 2, 1 }, 
																				   new int[] { 1, 2, 0, 1, 5 },
																				   new int[] { 4, 1, 0, 1, 7 }, 
																				   new int[] { 1, 0, 3, 0, 5 } });

		System.out.println(mutable1.sumRegion(2, 1, 4, 3));
		mutable1.update(3, 2, 2);
		System.out.println(mutable1.sumRegion(2, 1, 4, 3));
		
		// second test
		RangeSumQuery2DMutable mutable2 = new RangeSumQuery2DMutable(new int[][] { new int[] { 1, 2 }});
		
		System.out.println(mutable2.sumRegion(0, 0, 0, 0));
		System.out.println(mutable2.sumRegion(0, 1, 0, 1));
		System.out.println(mutable2.sumRegion(0, 0, 0, 1));
		mutable2.update(0, 0, 3);
		mutable2.update(0, 1, 5);
		System.out.println(mutable2.sumRegion(0, 0, 0, 1));
		
		// third test
		RangeSumQuery2DMutable mutable3 = new RangeSumQuery2DMutable(new int[][] { new int[] { 2, 4 },
																				   new int[] { -3, 5 }});
		mutable3.update(0, 1, 3);
		mutable3.update(1, 1, -3);
		mutable3.update(0, 1, 1);
		System.out.println(mutable3.sumRegion(0, 0, 1, 1));
	}

	public void runTest(final String s, List<String> d, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, d });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
