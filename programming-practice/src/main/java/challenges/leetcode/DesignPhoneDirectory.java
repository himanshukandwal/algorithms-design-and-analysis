package challenges.leetcode;

import java.util.BitSet;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

import challenges.AbstractCustomTestRunner;

/**
 * 379. Design Phone Directory
 * 
 * Design a Phone Directory which supports the following operations:
 * 
 * 		get		: Provide a number which is not assigned to anyone.
 * 		check	: Check if a number is available or not.
 * 		release	: Recycle or release a number.
 * 
 * Example:
 * 		// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 * 		PhoneDirectory directory = new PhoneDirectory(3);
 * 
 * 		// It can return any available phone number. Here we assume it returns 0.
 * 		directory.get();
 * 
 * 		// Assume it returns 1.
 * 		directory.get();
 * 
 * 		// The number 2 is available, so return true.
 * 		directory.check(2);
 * 
 * 		// It returns 2, the only number that is left.
 * 		directory.get();
 * 
 * 		// The number 2 is no longer available, so return false.
 * 		directory.check(2);
 * 
 * 		// Release number 2 back to the pool.
 * 		directory.release(2);
 * 
 * 		// Number 2 is available again, return true.
 * 		directory.check(2);
 * 
 * @author Hxkandwal
 */
public class DesignPhoneDirectory extends AbstractCustomTestRunner {
	
	Set<Integer> dict = new HashSet<>();
    PriorityQueue<Integer> numbers = new PriorityQueue<>();
    
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public DesignPhoneDirectory(int maxNumbers) {
        for (int idx = 0; idx < maxNumbers; idx ++) numbers.offer (idx);
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
    	if (numbers.size() == 0) return -1;
        dict.add (numbers.peek ());
        return numbers.poll ();
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
    	return !dict.contains (number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
    	if (dict.contains (number)) {
            dict.remove (number);
            numbers.offer (number);
        }
    }

}

@SuppressWarnings("serial")
class PhoneDirectoryBetter extends TreeSet<Integer> {

    public PhoneDirectoryBetter (int maxNumbers) {
		for (int i = 0; i < maxNumbers; i++) add(i);
    }
    
    public int get() {
        return isEmpty() ? -1 : pollFirst();
    }
    
    public boolean check(int number) {
        return contains(number);
    }
    
    public void release(int number) {
        add(number);
    }
}

// another very good approach
class PhoneDirectory {

    BitSet bitset;
    int max; 				// max limit allowed
    int smallestFreeIndex; 	// current smallest index of the free bit

    public PhoneDirectory(int maxNumbers) {
        this.bitset = new BitSet(maxNumbers);
        this.max = maxNumbers;
    }

    public int get() {
        // handle bitset fully allocated
		if (smallestFreeIndex == max) return -1;
		
        int num = smallestFreeIndex;
        bitset.set(smallestFreeIndex);
        
        //Only scan for the next free bit, from the previously known smallest free index
        smallestFreeIndex = bitset.nextClearBit(smallestFreeIndex);
        return num;
    }

    public boolean check(int number) {
        return bitset.get(number) == false;
    }

    public void release(int number) {
        //handle release of unallocated ones
		if (bitset.get(number) == false) return;
        bitset.clear(number);
		if (number < smallestFreeIndex) smallestFreeIndex = number;
    }
}
