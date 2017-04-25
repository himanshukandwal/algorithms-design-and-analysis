package challenges.leetcode;

import java.util.HashMap;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 138. Copy List with Random Pointer
 * 
 * A linked list is given such that each node contains an additional random pointer 
 * which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 * 
 * @author Hxkandwal
 */
public class CopyListWithRandomPointer extends AbstractCustomTestRunner {

	class RandomListNode {
		int label;
		RandomListNode next, random;
		RandomListNode(int x) { this.label = x; }
	};
	
	// O (1) space
	public RandomListNode copyRandomListBetter(RandomListNode head) {
        if (head == null) return null;
        RandomListNode node = head;
        while (node != null) {
            RandomListNode copy = new RandomListNode (node.label);
            copy.next = node.next;
            node.next = copy;
            node = copy.next;
        }
        node = head;
        while (node != null) {
            if (node.random != null) node.next.random = node.random.next;
            node = node.next.next;
        }
        node = head;
        RandomListNode newHead = null, traverser = null;
        while (node != null) {
            if (newHead == null) traverser = newHead = node.next;
            else traverser = traverser.next = node.next;
            RandomListNode future = node.next.next;
            node = node.next = future;
        }
        return newHead;
    }
	
	// O (n) space
	public RandomListNode copyRandomList(RandomListNode head) {
        return copyDeepRandomList (head, new HashMap<>());
    }
    
    private RandomListNode copyDeepRandomList(RandomListNode node, Map<RandomListNode, RandomListNode> seen) {
        if (node == null) return null;
        if (seen.containsKey(node)) return seen.get (node);
        
        RandomListNode copyNode = new RandomListNode (node.label);
        seen.put(node, copyNode);
        copyNode.next = copyDeepRandomList (node.next, seen);
        copyNode.random = copyDeepRandomList (node.random, seen);
        return copyNode;
    }
    
}
