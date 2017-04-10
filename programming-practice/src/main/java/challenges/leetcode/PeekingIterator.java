package challenges.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 284. Peeking Iterator
 * 
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support 
 * the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().
 * 
 * Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
 * 
 * Call next() gets you 1, the first element in the list.
 * 
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 * 
 * You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.
 * 
 * Follow up: How would you extend your design to be generic and work with all types, not just integer?
 * 
 * @author Hxkandwal
 */
public class PeekingIterator implements Iterator<Integer> {

	List<Integer> items;
    int index = -1;
    
	public PeekingIterator(Iterator<Integer> iterator) {
	    items = new ArrayList<>();
	    while (iterator.hasNext())
	        items.add (iterator.next());
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return (index + 1 < items.size() ? items.get (index + 1) : null);
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    return items.get (++ index) ;
	}

	@Override
	public boolean hasNext() {
	    return index + 1 < items.size();
	}
	
}

// can also be done by caching next element.
class PeekingIteratorOther implements Iterator<Integer> {

    Iterator<Integer> iterator;
    Integer nextCache = null;
    
	public PeekingIteratorOther(Iterator<Integer> iterator) {
	    this.iterator = iterator;
	    if (iterator.hasNext())
	        nextCache = iterator.next();
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return nextCache;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    Integer toReturn = nextCache;
	    nextCache = (iterator.hasNext()) ? iterator.next() : null;
	    return toReturn;
	}

	@Override
	public boolean hasNext() {
	    return nextCache != null;
	}
}
