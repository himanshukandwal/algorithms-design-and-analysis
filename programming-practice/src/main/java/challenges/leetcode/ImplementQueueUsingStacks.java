package challenges.leetcode;

import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 232. Implement Queue using Stacks
 * 
 * Implement the following operations of a queue using stacks.
 * 
 * 		push(x) -- Push element x to the back of queue.
 * 		pop() -- Removes the element from in front of queue.
 * 		peek() -- Get the front element.
 * 		empty() -- Return whether the queue is empty.
 * 
 * Notes:
 * 		You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is 
 * 		empty operations are valid.
 * 		Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque 
 * 		(double-ended queue), as long as you use only standard operations of a stack.
 * 		You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 * 
 * @author Hxkandwal
 */
public class ImplementQueueUsingStacks extends AbstractCustomTestRunner {
	
	Stack<Integer> offer = new Stack<>();
    Stack<Integer> poll = new Stack<>();
    
    /** Initialize your data structure here. */
    public ImplementQueueUsingStacks() {}
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        offer.push (x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        maintain();
        return poll.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        maintain();
        return poll.peek();
    }
    
    private void maintain () {
        if (poll.isEmpty()) while (!offer.isEmpty()) poll.push (offer.pop());
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return offer.isEmpty() && poll.isEmpty();
    }

}
