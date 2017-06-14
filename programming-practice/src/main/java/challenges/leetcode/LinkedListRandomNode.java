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
    private int numItemsSeen = 0;
    
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public LinkedListRandomNode(ListNode head) {
        this.head = head;
    }
    
    /** Returns a random node's value. */
    // read : https://github.com/twitter/commons/blob/master/src/java/com/twitter/common/stats/ReservoirSampler.java
    public int getRandom() {
        if (head != null) {
            if (reservoir.size() < max) reservoir.add (head.val);
            else {
                int val = head.val; 
                int rIndex = rand.nextInt(numItemsSeen + 1);
                if (rIndex < max) { reservoir.set(rIndex, val); return val; } 
            }
            head = head.next;  
            numItemsSeen ++; 
        }
        return reservoir.get (rand.nextInt (reservoir.size()));
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
