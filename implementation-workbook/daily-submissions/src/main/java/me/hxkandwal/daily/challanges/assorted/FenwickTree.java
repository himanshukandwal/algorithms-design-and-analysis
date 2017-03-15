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
		this.size = size + 1;
		this.array = new int [size + 1];
	}
	
	public void add (int index, int val) {
		index ++;
		int idx = index;
		while (idx < size) {
			array [idx] += val;
			idx += idx & (~idx + 1);  // -x == ~x + 1
		}
	}
	
	public int sum (int index) {
		index ++;
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int idx = 0; idx < array.length; idx ++) {
			sb.append(array [idx]);
			if (idx < array.length - 1) sb.append(" , ");
		}
		return super.toString();
	}
	
}