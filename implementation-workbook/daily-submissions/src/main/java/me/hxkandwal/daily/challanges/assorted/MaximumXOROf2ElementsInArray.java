package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Given an array of integers ,You have to find two elements whose XOR is maximum.
 * 
 * @author Hxkandwal
 *
 */
public class MaximumXOROf2ElementsInArray extends AbstractCustomTestRunner {
	
	private static MaximumXOROf2ElementsInArray _instance = new MaximumXOROf2ElementsInArray();
	
	private MaximumXOROf2ElementsInArray() {}
	
	public static int _getMaxXORof2Elements(int[] array) {
		if(array == null || array.length == 0) return 0;
		
        // Init Trie.
        Object[] root = { null, null };
        
        for(int num: array) {
            Object[] curNode = root;
            
            for (int i = 31; i >= 0; i --) {
                int curBit = (num >>> i) & 1;
                
                if (curNode [curBit] == null) 
                    curNode [curBit] = new Object[] { null, null };
                
                curNode = (Object[]) curNode[curBit];
            }
        }
        
        int max = Integer.MIN_VALUE;
        for (int num: array) {
            Object[] curNode = root;
            int curSum = 0;
            
            for (int i = 31; i >= 0; i --) {
                int curBit = (num >>> i) & 1;
                
                if (curNode [curBit ^ 1] != null) {
                    curSum += (1 << i);
                    curNode = (Object[]) curNode [curBit ^ 1];
                } else
                    curNode = (Object[]) curNode [curBit];
            }
            
            max = Math.max(curSum, max);
        }
        
        return max;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 5 }, 0);
		_instance.runTest(new int[] { 4, 6, 7 }, 3);
		_instance.runTest(new int[] { 5, 1, 4, 3, 0, 2 }, 7);
		_instance.runTest(new int[] { 2, 6, 1, 3, 5, 4, 8 }, 14);
	}

	public void runTest(final int[] input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
