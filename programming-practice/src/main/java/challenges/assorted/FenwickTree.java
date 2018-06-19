package challenges.assorted;

import challenges.AbstractCustomTestRunner;

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

	// driver method
	public static void main(String[] args) {
		FenwickTree ft = new FenwickTree(5);
		ft.add(0, 3);
		ft.add(1, 0);
		ft.add(2, 100);
		ft.add(3, 4);
		ft.add(4, 25);
		System.out.println(ft.sum(0));
		System.out.println(ft.sum(1));
		System.out.println(ft.sum(2));
		System.out.println(ft.sum(3));
		System.out.println(ft.sum(4));
	}
	
}