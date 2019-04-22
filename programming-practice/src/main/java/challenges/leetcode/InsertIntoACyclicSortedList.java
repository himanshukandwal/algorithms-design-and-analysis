package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 708. Insert into a Cyclic Sorted List
 *
 * Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list.
 * The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.
 *
 * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic list should remain sorted.
 *
 * If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node.
 * Otherwise, you should return the original given node.
 *
 * @author Hxkandwal
 */
public class InsertIntoACyclicSortedList extends AbstractCustomTestRunner {

    class Node {
        public int val;
        public Node next;

        public Node() {}
        public Node(int _val,Node _next) { val = _val; next = _next; }
    }

    public Node _insert(Node head, int x) {
        Node ins = new Node();
        ins.val = x;

        if (head == null) return ins.next = ins;
        Node curr = head;
        while (true) {
            if (curr.val < curr.next.val) {
                if (curr.val <= x && x <= curr.next.val) {
                    insert (curr, ins);
                    break;
                }
            } else if (curr.val > curr.next.val) {
                if (curr.val <= x || x <= curr.next.val) {
                    insert (curr, ins);
                    break;
                }
            } else {
                if (curr.next == head) {
                    insert (curr, ins);
                    break;
                }
            }
            curr = curr.next;
        }
        return head;
    }

    private void insert (Node a, Node b) {
        Node next = a.next;
        a.next = b;
        b.next = next;
    }

}
