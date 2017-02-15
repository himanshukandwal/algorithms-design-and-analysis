package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 503. Next Greater Element II
 * 
 * Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. 
 * The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly 
 * to find its next greater number. If it doesn't exist, output -1 for this number.
 * 
 * Example 1:
 * 		Input: [1,2,1]
 * 		Output: [2,-1,2]
 * 
 * 		Explanation: The first 1's next greater number is 2;
 * 					 The number 2 can't find next greater number;
 * 					 The second 1's next greater number needs to search circularly, which is also 2.
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class NextGreaterElementII extends AbstractCustomTestRunner {
	
	private static NextGreaterElementII _instance = new NextGreaterElementII();
	
	private NextGreaterElementII() {}
	
	// double the initial array, with Queue
	public static int[] _nextGreaterElementsDoubleWithQueue(int[] nums) {
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        
        int n = nums.length;
        for (int i = 0; i < 2 * n; i++) {
            while (!stack.isEmpty() && stack.peek() < nums[i % n]) {
                if (!map.containsKey(stack.peek())) map.put(stack.peek(), new LinkedList<Integer>());
                map.get(stack.pop()).offer(nums[i % n]);
            }
            stack.push(nums[i % n]);
        }
        
        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = map.containsKey(nums[i]) ? map.get(nums[i]).poll() : -1;
        return res;
    }
	
	// double the initial array, without Queue
	public static int[] _nextGreaterElementsDoubleWithoutQueue(int[] nums) {
        int n = nums.length, next[] = new int[n];
        Arrays.fill(next, -1);
        
        Stack<Integer> stack = new Stack<>(); // index stack
        for (int i = 0; i < n * 2; i++) {
            int num = nums [i % n]; 
            while (!stack.isEmpty() && nums [stack.peek()] < num)
                next [stack.pop()] = num;
            if (i < n) stack.push(i);
        }   
        return next;
    }
	
	// case : utilize both stack and map.
	public static int[] _nextGreaterElements(int[] nums) {
    	Map<Integer, Queue<Integer>> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        
        for (int num : nums) {
        	while (!stack.isEmpty() && stack.peek() < num) { 
        		if (map.containsKey(stack.peek())) 
        			map.get(stack.pop()).offer(num);
        		else 
        			map.put(stack.pop(), new LinkedList() {{ offer(num); }});
        	}
        	stack.push(num);
        }
        
        int idx = 0;
        while (! stack.isEmpty()) {
        	int poppedNum = stack.pop();
        	
        	while (idx < nums.length) {
        		int num = nums [idx];
        		
				if (num > poppedNum) {
					if (map.containsKey(poppedNum)) 
	        			map.get(poppedNum).offer(num);
	        		else 
	        			map.put(poppedNum, new LinkedList() {{ offer(num); }});
					
					break;
				} else idx ++;
			}
        	
        	if (idx >= nums.length) map.put(poppedNum, new LinkedList() {{ offer(-1); }});
        }
        
        for (idx = 0; idx < nums.length; idx ++) {
        	int ans = map.get(nums [idx]).poll();
        	if (map.get(nums [idx]).size() == 0) map.remove(nums [idx]);
        	nums [idx] = ans;
        } 
        
        return nums;
    }
    
	// driver method
	public static void main(String[] args) {
//		_instance.runTest(new int[] { 1, 2, 1 }, new int[] { 2, -1, 2 });
//		_instance.runTest(new int[] { 5, 4, 3, 2, 1 }, new int[] { -1, 5, 5, 5, 5 });
		_instance.runTest(new int[] { 100, 1, 11, 1, 120, 111, 123, 1, -1, -100 }, new int[] { 120, 11, 120, 120, 123, 123, -1, 100, 100, 100 });
	}

	public void runTest(final int[] nums, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	

}
