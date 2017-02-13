package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 421. Maximum XOR of Two Numbers in an Array
 * 
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231. Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 * 
 * Could you do this in O(n) runtime?
 * 
 * Example:
 * 		Input: [3, 10, 5, 25, 2, 8]
 * 		Output: 28
 * 		Explanation: The maximum result is 5 ^ 25 = 28.
 * 
 * More info : https://threads-iiith.quora.com/Tutorial-on-Trie-and-example-problems
 *  
 * @author Hxkandwal
 */
public class MaximumXOROf2ElementsInArray extends AbstractCustomTestRunner {
	
	private static MaximumXOROf2ElementsInArray _instance = new MaximumXOROf2ElementsInArray();
	
	private MaximumXOROf2ElementsInArray() {}

	public static int _getMaxXORof2Elements(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		
		// trie root.
		Object[] root = { null, null };
		
		// trie population.
		for (int number : nums) {
			Object [] currentNode = root;
			
			for (int pos = 31; pos >= 0; pos --) {
				int currentBit = (number >>> pos) & 1;
				currentNode = (Object[]) (currentNode [currentBit] = (currentNode [currentBit] == null) ? new Object[] { null, null } : currentNode [currentBit]);
			}
		}
        
		int max = Integer.MIN_VALUE;
		for (int number : nums) {
			int negElementReconstructed = 0;
			Object [] currentNode = root;
			
			for (int pos = 31; pos >= 0; pos --) {
				int currentBit = (number >>> pos) & 1;
				
				if (currentNode [currentBit ^ 1] != null) {
					negElementReconstructed += (1 << pos);
					currentNode = (Object[]) currentNode [currentBit ^ 1];
				} else
					currentNode = (Object[]) currentNode [currentBit];
			}
			
			max = Math.max (max, negElementReconstructed);
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
