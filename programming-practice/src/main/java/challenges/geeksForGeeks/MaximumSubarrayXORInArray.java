package challenges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Find the maximum subarray XOR in a given array
 * 
 * Given an array of integers. find the maximum XOR subarray value in given array. Expected time complexity O(n).
 *
 * Examples:
 * (a)		Input: arr[] = {1, 2, 3, 4}
 * 			Output: 7
 * 
 * 			The subarray {3, 4} has maximum XOR value
 *
 * (b)		Input: arr[] = {8, 1, 2, 12, 7, 6}
 * 			Output: 15
 * 
 * 			The subarray {1, 2, 12} has maximum XOR value
 * 
 * (c)		Input: arr[] = {4, 6}
 * 			Output: 6
 * 
 * 			The subarray {6} has maximum XOR value
 * 
 * link : http://www.geeksforgeeks.org/find-the-maximum-subarray-xor-in-a-given-array/
 * 
 */
public class MaximumSubarrayXORInArray extends AbstractCustomTestRunner {
	
	private static MaximumSubarrayXORInArray _instance = new MaximumSubarrayXORInArray();
	
	private MaximumSubarrayXORInArray() {}
	
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
	
	public static int maxSubarrayXOR(int arr[], int n) {
		
		return 0;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2, 3, 4 }, 7);
		_instance.runTest(new int[] { 8, 1, 2, 12, 7, 6 }, 15);
		_instance.runTest(new int[] { 4, 6 }, 6);
	}

	public void runTest(final int[] input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
