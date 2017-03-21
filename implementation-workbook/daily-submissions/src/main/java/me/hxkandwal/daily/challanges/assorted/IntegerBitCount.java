package me.hxkandwal.daily.challanges.assorted;

import static org.junit.Assert.assertEquals;

public class IntegerBitCount {
	
	public static int count (int n) {
		int count = 0;
		while (n > 0) {
			count += (n % 2);
			n >>= 1;
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		for (int idx = 0; idx < 100; idx ++) 
			assertEquals(Integer.bitCount(idx), count (idx));
		
		System.out.println(" ok !");
	}
	
}
