package me.hxkandwal.daily.challanges.assorted;

import org.junit.Test;

public class FenwickTreeTest {

	@Test
	public void test() {
		FenwickTree ft = new FenwickTree(3);
		ft.add(0, 1);
		ft.add(1, 2);
		ft.add(2, 3);
		System.out.println(ft.sum(2));
	}

}
