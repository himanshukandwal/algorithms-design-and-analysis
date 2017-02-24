package me.hxkandwal.daily.challanges.leetcode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 251. Flatten 2D Vector
 * 
 * Implement an iterator to flatten a 2d vector.
 * 
 * For example,
 * 		Given 2d vector =
 * 			[
 * 		  		[1,2],
 * 		  		[3],
 * 		  		[4,5,6]
 * 			]
 * 
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
 * 
 * @author Hxkandwal
 */
public class Flatten2DVector extends AbstractCustomTestRunner implements Iterator<Integer> {
	
	Queue<Iterator<Integer>> queue = new LinkedList<>();
    
    public Flatten2DVector(List<List<Integer>> vec2d) {
        for (List<Integer> list : vec2d) if (list.size() > 0) queue.offer (list.iterator());
    }

    @Override
    public Integer next() {
        Integer res = queue.peek().next();    
        if (!queue.peek().hasNext()) queue.poll();
        return res;
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

}
