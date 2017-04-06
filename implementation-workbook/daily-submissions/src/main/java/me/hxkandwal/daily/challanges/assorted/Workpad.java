package me.hxkandwal.daily.challanges.assorted;

import java.util.Arrays;
import java.util.List;

public class Workpad {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 3, 1, 10, 5, 6, 2, 2, 3, 4, 5, 8);
		
		System.out.println(Arrays.binarySearch(list.toArray(new Integer [0]), 3, (a, b) -> Integer.compare(a, b)));
	
	}
}
