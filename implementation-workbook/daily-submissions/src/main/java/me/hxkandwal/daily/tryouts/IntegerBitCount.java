package me.hxkandwal.daily.tryouts;

import static org.junit.Assert.assertEquals;

public class IntegerBitCount {

	public static void main(String[] args) {
		
		for (int idx = 0; idx < 100; idx ++) {
			assertEquals(Integer.bitCount(idx), method1 (idx));
			assertEquals(Integer.bitCount(idx), method2 (idx));
			assertEquals(Integer.bitCount(idx), method3 (idx));
		}
		
		System.out.println(" OK !");
	}
	
	public static int method1 (int n) {
		int count = 0;
		while (n > 0) {
			count += n & 1;
			n >>= 1;
		}
		
		return count;
	}
	
	public static int method2 (int n) {
		int count = 0;
		while (n > 0) {
			count += (n % 2);
			n >>= 1;
		}
		
		return count;
	}
	
	public static int method3 (int i) {
		
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        
        return i & 0x3f;
	}
}
