package me.hxkandwal.daily.challanges.leetcode;

import java.util.Iterator;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 281. Zigzag Iterator
 * 
 * Given two 1d vectors, implement an iterator to return their elements alternately.
 * For example, given two 1d vectors:
 * 		v1 = [1, 2]
 * 		v2 = [3, 4, 5, 6]
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
 * 
 * Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
 * 
 * Clarification for the follow up question - Update (2015-09-18):
 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". 
 * 
 * For example, given the following input:
 * 		[1,2,3]
 * 		[4,5,6,7]
 * 		[8,9]
 * 
 * It should return [1,4,8,2,5,9,3,6,7].
 * 
 * @author Hxkandwal
 */
public class ZigzagIterator extends AbstractCustomTestRunner {

	Iterator <Integer> fiterator;
    Iterator <Integer> siterator;
    boolean toggle;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        fiterator = v1.iterator();
        siterator = v2.iterator();
    }

    public int next() {
        if (toggle = !toggle)
            return (fiterator.hasNext()) ? fiterator.next() : siterator.next();
        else
            return (siterator.hasNext()) ? siterator.next() : fiterator.next();
    }

    public boolean hasNext() {
        return (fiterator.hasNext() || siterator.hasNext());
    }
    
}
