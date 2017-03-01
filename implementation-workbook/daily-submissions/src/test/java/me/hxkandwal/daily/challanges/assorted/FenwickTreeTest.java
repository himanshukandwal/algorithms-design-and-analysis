package me.hxkandwal.daily.challanges.assorted;

import org.junit.Test;

public class FenwickTreeTest {

	@Test
	public void test() {
		FenwickTree ft = new FenwickTree(5);
		ft.add(0, 3);
		ft.add(1, 0);
		ft.add(2, 1);
		ft.add(3, 4);
		ft.add(4, 2);
		System.out.println(ft.sum(0));
		System.out.println(ft.sum(1));
		System.out.println(ft.sum(2));
		System.out.println(ft.sum(3));
		System.out.println(ft.sum(4));
	}

	
}
