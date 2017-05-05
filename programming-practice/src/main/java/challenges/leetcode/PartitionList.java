package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 86. Partition List
 * 
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * 
 * For example,
 * 		Given 	:	1 -> 4 -> 3 -> 2 -> 5 -> 2 and x = 3,
 * 		return 	:	1 -> 2 -> 2 -> 4 -> 3 -> 5.
 * 
 * @author Hxkandwal
 */
public class PartitionList extends AbstractCustomTestRunner {
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
    public ListNode _partition (ListNode head, int x) {
    	if (head == null || head.next == null) return head;
    	
        ListNode lesser = null, traverser = head, more = null, lHead = null, mHead = null;
        while (traverser != null) {
        	if (traverser.val < x) {
        		if (lesser == null) { lHead = lesser = traverser; }
        		else { lesser.next = traverser; lesser = lesser.next; }
        		traverser = traverser.next;
        		lesser.next = null;
        	} else {
        		if (more == null) { mHead = more = traverser; }
        		else { more.next = traverser; more = more.next; }
        		traverser = traverser.next;
        		more.next = null;
        	}
        }
        if (lHead == null) lHead = mHead;
        else lesser.next = mHead;
        return lHead;
    }

}
