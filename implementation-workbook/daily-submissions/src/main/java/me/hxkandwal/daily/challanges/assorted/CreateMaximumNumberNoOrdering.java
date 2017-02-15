package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Create Maximum Number
 * 
 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. 
 * Return an array of the k digits. You should try to optimize your time and space complexity.
 *  
 * @author Hxkandwal
 */
public class CreateMaximumNumberNoOrdering extends AbstractCustomTestRunner {
	
	private static CreateMaximumNumberNoOrdering _instance = new CreateMaximumNumberNoOrdering();
	
	private CreateMaximumNumberNoOrdering() {}
	
	public static int[] _maxNumber(int[] nums1, int[] nums2, int k) {
		Stack<Integer> stack = new Stack<>();
		Queue<Integer> kickoutQueue = new LinkedList<>(); 
		
		int idx1 = 0, idx2 = 0;
		while (idx1 < nums1.length || idx2 < nums2.length) {
			int num;
			if (idx1 < nums1.length && idx2 < nums2.length)
				num = (nums1 [idx1] > nums2 [idx2]) ? nums1 [idx1 ++] : nums2 [idx2 ++];
			else if (idx1 < nums1.length)
				num = nums1 [idx1 ++];
			else 
				num = nums2 [idx2 ++];
			
			while (!stack.isEmpty() && stack.peek() < num) kickoutQueue.offer(stack.pop());
			stack.push(num);
		}
		
		boolean changed = true;
		while (changed) {
			changed = false;
			int size = kickoutQueue.size();
			while (size -- > 0) {
				int qItem = kickoutQueue.poll();
				while (!stack.isEmpty() && stack.peek() < qItem)  { kickoutQueue.offer(stack.pop()); changed = true; }
				stack.push(qItem);
			}
		}
		
		int [] ans = new int [k];
		for (int idx = 0; idx < k; idx ++) ans [idx] = stack.get(idx);
        return ans;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 6, 7 }, new int [] { 6, 0, 4 }, 5, new int [] { 7, 6, 6, 4, 0 });
		_instance.runTest(new int [] { 3, 4, 6, 5 }, new int [] { 9, 1, 2, 5, 8, 3 }, 5, new int [] { 9, 8, 6, 5, 5 });
	}

	public void runTest(final int[] nums1, final int[] nums2, final int k, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums1, nums2, k });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
