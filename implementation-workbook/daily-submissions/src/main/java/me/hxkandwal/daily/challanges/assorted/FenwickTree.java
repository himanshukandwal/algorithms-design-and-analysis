package me.hxkandwal.daily.challanges.assorted;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * https://www.youtube.com/watch?v=kPaJfAUwViY
 * 
 * @author Hxkandwal
 *
 */
public class FenwickTree extends AbstractCustomTestRunner {

	private int size;
	private int [] array;
	
	public FenwickTree(int size) {
		this.size = size;
		this.array = new int [size];
	}
	
	public void add (int index, int val) {
		int idx = index;
		while (idx < size) {
			array [idx] += val;
			idx += idx & (~idx + 1);  // -x == ~x + 1
		}
	}
	
	public int sum (int index) {
		int sum = 0;
		while (index > 0) {
			sum += array [index];
			index -= index & (~index + 1);  // -x == ~x + 1
		}
		return sum;
	}
	
	public int rangeSum (int idx1, int idx2) {
		return sum (idx2) - sum (idx1);
	}
}
