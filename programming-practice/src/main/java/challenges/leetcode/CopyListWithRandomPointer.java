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
