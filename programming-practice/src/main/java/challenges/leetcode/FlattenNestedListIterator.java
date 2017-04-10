package challenges.leetcode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 341. Flatten Nested List Iterator
 * 
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * 
 * Example 1:
 * 		Given the list [[1,1],2,[1,1]],
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * 
 * Example 2:
 * 		Given the list [1,[4,[6]]],
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 * 
 * @author Hxkandwal
 */
public class FlattenNestedListIterator extends AbstractCustomTestRunner implements Iterator<Integer> {

	// This is the interface that allows for creating nested lists.
	// You should not implement it, or speculate about its implementation
	public class NestedInteger {
		
		// @return true if this NestedInteger holds a single integer, rather than a nested list.
		public boolean isInteger() { return false; }

		// @return the single integer that this NestedInteger holds, if it holds a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger() { return null; }
		
		// @return the nested list that this NestedInteger holds, if it holds a nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList() { return null; }
	 }
	
	Queue<NestedInteger> queue = new LinkedList<>();
    
    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        for (NestedInteger item : nestedList) queue.offer (item);
        
        int size = queue.size();
        while (size -- > 0) {
            NestedInteger ni = queue.poll();
            if (ni.isInteger()) queue.offer (ni);
            else {
                Stack<Iterator<NestedInteger>> stk = new Stack<>();
                stk.push (ni.getList().iterator());
                
                while (! stk.isEmpty()) {
                    Iterator<NestedInteger> iterator = stk.pop();
                    while (iterator.hasNext()) {
                        NestedInteger oni = iterator.next();
                        if (oni.isInteger()) queue.offer (oni);
                        else {
                            stk.push (iterator);
                            stk.push (oni.getList().iterator());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public Integer next() {
        return queue.poll().getInteger();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
	
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */