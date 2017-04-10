package shortest.path.graph.algorithms.model;

import java.util.Comparator;

//Ver 1.0:  Wec, Feb 3.  Initial description.

/**
 * An indexed heap representation of priority queue.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 */

public class IndexedHeap<T extends Index> extends BinaryHeap<T> {
	
	public IndexedHeap(int n, Comparator<T> comp) {
		super(n, comp);
	}

	/** Build a priority queue with a given array q */
	public IndexedHeap(T[] q, Comparator<T> comp) {
		super(q, comp);
	}
	
	/** restore heap order property after the priority of x has decreased */
	public void decreaseKey(T x) {  
		percolateUp(x.getIndex());   
	}
	
	@Override
	public void percolateUp(int i) {
		assign(0, i);
		super.percolateUp(i);
		assign(0, null);
	}
	
	@Override
	public void associateIndex(T item, int index) {
		super.associateIndex(item, index);
		if (item != null)
			((Index) item).putIndex(index);
	}
	
	@Override
	public void associateIndex(T item, T from) {
		super.associateIndex(item, from);
		if (from != null)
			((Index) item).putIndex(from.getIndex());
	}

}
