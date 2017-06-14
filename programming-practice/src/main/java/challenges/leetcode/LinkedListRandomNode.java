package challenges.leetcode;

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

	// read : https://github.com/twitter/commons/blob/master/src/java/com/twitter/common/stats/ReservoirSampler.java
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	private ListNode head;
    private Random rand = new Random();
    
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public LinkedListRandomNode(ListNode head) {
        this.head = head;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode tr = head, ans = null;
        ListNode [] buffer = new ListNode [100];
        
        for (int before = 0; tr != null; before += 100) {
            int now = 0;
            while (tr != null && now < 100) {
                buffer [now ++] = tr;
                tr = tr.next;
            }
            int rIdx = rand.nextInt (before + now);
            
            if (rIdx < now) ans = buffer [rIdx];
        }
        return ans.val;
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
   	}
    
}
