package challenges.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import challenges.AbstractCustomTestRunner;

/**
 * 382. Linked List Random Node
 * 
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of 
 * being chosen.
 * 
 * Follow up:	What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently 
 * 				without using extra space?
 * 
 * Example:
 * 
 * 		// Init a singly linked list [1,2,3].
 * 		   	ListNode head = new ListNode(1);
 * 		   	head.next = new ListNode(2);
 * 			head.next.next = new ListNode(3);
 * 
 * 			Solution solution = new Solution(head);
 * 
 * 		// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 * 			solution.getRandom();
 * 
 * @author Hxkandwal
 */
public class LinkedListRandomNode extends AbstractCustomTestRunner {

	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	private ListNode head;
    private List <Integer> reservoir = new ArrayList<>();
    private final int max = 1000;
    private Random rand = new Random();
    
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public LinkedListRandomNode(ListNode head) {
        this.head = head;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        if (reservoir.size() < max) {
            if (head != null) { reservoir.add (head.val); head = head.next; }
            return reservoir.get (rand.nextInt (reservoir.size()));
        } else {
            if (head == null) return reservoir.get (rand.nextInt (reservoir.size()));
            else {
                int val = head.val; 
                head = head.next;
                if (rand.nextDouble() < (1d/max)) return val;
                else {
                    int idx = rand.nextInt (reservoir.size());
                    int ret = reservoir.get (idx);
                    reservoir.set (idx, val);
                    return ret;   
                }
            }
        }
    }

   	// driver method
   	public static void main(String[] args) {
   		ListNode head = new ListNode(1);
   		head.next = new ListNode(2);
   		head.next.next = new ListNode(3);
   		
   		LinkedListRandomNode node = new LinkedListRandomNode(head);
   		System.out.println(node.getRandom());
   		System.out.println(node.getRandom());
   		System.out.println(node.getRandom());
   		System.out.println(node.getRandom());
   		System.out.println(node.getRandom());
   	}
    
}
