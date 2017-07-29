package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 155. Min Stack
 * 
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * 
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * 
 * Example:
 * 		MinStack minStack = new MinStack();
 * 		minStack.push(-2);
 * 		minStack.push(0);
 * 		minStack.push(-3);
 * 		minStack.getMin();   --> Returns -3.
 * 		minStack.pop();
 * 		minStack.top();      --> Returns 0.
 * 		minStack.getMin();   --> Returns -2.
 * 
 * @author Hxkandwal
 */
public class MinStack extends AbstractCustomTestRunner {
	
	public static class Implementation1 {
		private Node head;
		
		public void push(int x) {
			head = (head == null) ? new Node(x, x) : new Node(x, Math.min(x, head.min), head);
		}
		
		public void pop() { head = head.next; }
		public int top() { return head.val; }
		public int getMin() { return head.min; }
		
		private class Node {
			int val, min;
			Node next;
			
			public Node(int val, int min) { this (val, min, null); }
			public Node(int val, int min, Node next) { this.val = val; this.min = min; this.next = next; }
		}
	}
    
	public static class Implementation2 {
		public Stack<Integer> stack = new Stack<> ();
	    public int min = Integer.MAX_VALUE;
	    
	    public void push(int x) {
	        if (x <= min) { stack.push (min); min = x; }  
	        stack.push (x);
	    }
	    
	    public void pop() { if (stack.pop () == min) min = stack.pop (); }
	    public int top() { return stack.peek(); }
	    public int getMin() { return min; }
	}
	
	public static class Implementation3 {
		public Stack<Long> stack = new Stack<> ();
	    public Long min = null;
	    
	    public void push(int x) {
	        if (min == null || x <= min.intValue()) { stack.push (min); min = Long.valueOf (x); }  
	        stack.push (x - min);
	    }
	    
	    public void pop() {
	        if (stack.isEmpty ()) return;
	        long val = stack.pop ();
	        if (val == 0) min = stack.pop ();
	    }
	    
	    public int top() { return (int) (stack.peek() + min); }
	    
	    public int getMin() { return min.intValue (); }
	}
	
	
	// driver method
	public static void main(String[] args) {
		Implementation2 impl = new Implementation2 ();
		impl.push(512); impl.push(-1024);
		impl.push(-1024); impl.push(512);
		
		impl.pop();
		assertThat(impl.getMin()).isEqualTo(-1024);
		impl.pop();
		assertThat(impl.getMin()).isEqualTo(-1024);
		impl.pop();
		assertThat(impl.getMin()).isEqualTo(512);
		
		System.out.println("ok!");
	}
		
}
