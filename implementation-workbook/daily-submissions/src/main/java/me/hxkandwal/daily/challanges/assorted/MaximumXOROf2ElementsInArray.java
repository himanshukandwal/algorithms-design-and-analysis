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
	
	public static class BinaryTrie {
		Integer value;
		BinaryTrie right; 	// 0
		BinaryTrie left; 	// 1
		
		// for root 
		public BinaryTrie() {}
		
		public BinaryTrie(int value) { this.value = value; }
		
		public void insert(String element, int index) {
			if (index >= element.length()) 
				return;
			
			if (element.charAt(index) == '0') {
				if (right == null) 
					right = new BinaryTrie(0);
				
				right.insert(element, index + 1);
			} else {
				if (left == null) 
					left = new BinaryTrie(1);
				
				left.insert(element, index + 1);				
			}
		}
		
		public int getXOR(String element, int index) {
			if (left == null && right == null)
				return 0;
			
			int res = 0;
			if (element.charAt(index) == '0') {
				if (left != null) {
					res = left.getXOR(element, index + 1);
					res += Math.pow (2, element.length() - index - 1);
				} else {
					res = right.getXOR(element, index + 1);
				}
			} else {
				if (right != null) {
					res = right.getXOR(element, index + 1);
					res += Math.pow (2, element.length() - index - 1);
				}
			}
			
			return res;
		}
		
		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}
	
	public static int _printMaxXORof2Elements(int[] array) {
		BinaryTrie root = new BinaryTrie();
		
		int maxXor = -1;
		for (int arrayElement : array) {
			String element = String.format("%8s",Integer.toBinaryString(arrayElement)).replace(" ", "0");
			
			// insert the arrayElement			
			root.insert(element, 0);
			
			// find XOR for the arrayElement
			maxXor = Math.max(maxXor, root.getXOR(element, 0));
		}
		
		return maxXor;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 5 }, 0);
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
