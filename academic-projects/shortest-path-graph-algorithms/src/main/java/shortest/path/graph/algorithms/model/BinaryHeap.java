package shortest.path.graph.algorithms.model;

import java.util.Arrays;
import java.util.Comparator;

//Ver 1.0:  Wec, Feb 3.  Initial description.

/**
 * @author rbk
 * @modified by G31 (Himanshu Kandwal and Dharmam Buch)
 */
public class BinaryHeap<T> implements PQ<T> {
	
	protected T[] pq;
	protected Comparator<T> c;
	protected Integer maxsize;
	
	/** Build a priority queue with a given array q */
	BinaryHeap(T[] q, Comparator<T> comp) {
		pq = q;
		c = comp;
		maxsize = q.length;
		buildHeap();
	}

	/** Create an empty priority queue of given maximum size */
	@SuppressWarnings("unchecked")
	BinaryHeap(int n, Comparator<T> comp) {
		maxsize = n + 1;
		c = comp;
		pq = (T []) new Object [1];
		pq [0] = null;
	}

	public BinaryHeap() {
	}
	
	public void insert(T x) {
		add(x);
	}

	public T deleteMin() {
		return remove();
	}

	public T min() {
		return peek();
	}

	public void add(T x) {
		if (pq.length + 1 > maxsize) {
			System.out.println("cannot add more elements. Priority queue reached max size [" + maxsize + "]");
		} else {
			pq = Arrays.copyOf(pq, pq.length + 1);
			
			pq [pq.length - 1] = x;
			pq [0] = pq [pq.length - 1];
			associateIndex(pq [pq.length - 1], pq.length - 1);
			
			percolateUp(pq.length - 1);
			pq [0] = null;
		}
	}

	public T remove() {
		T returnVal = null;
		if (pq.length > 1) {
			returnVal = pq [1];
			
			pq [1] = pq [pq.length - 1];
			associateIndex(pq [1], 1);
			
			pq = Arrays.copyOf (pq, pq.length - 1);

			percolateDown (1);
		}
		return returnVal;
	}

	public T peek() {
		return (pq.length <= 1 ? null : pq[1]);
	}

	/** pq[i] may violate heap order with parent */
	public void percolateUp (int i) {
		if (c.compare (pq [i], pq [i / 2]) < 0) {
			associateIndex(pq [i], i / 2);
			associateIndex(pq [i / 2], i);
			
			pq [i] = pq [i / 2];
			pq [i / 2] = pq [0];
			
			percolateUp (i / 2);
		}
	}
	
	/** pq[i] may violate heap order with children */
	public void percolateDown(int i) {
		if (i < pq.length && (2 * i < pq.length || (2 * i + 1) < pq.length)) {
			int index = -1;
			
			// case : two children
			if ((2 * i + 1) < pq.length)
				index = (c.compare (pq [2 * i], pq [2 * i + 1]) > 0 ? 2 * i + 1 : 2 * i);
			else // case : one child
				index = 2 * i;
				
			if (c.compare (pq [i], pq [index]) > 0) {
				associateIndex(pq [i], index);
				associateIndex(pq [index], i);
				
				pq [0] = pq [i];
				pq [i] = pq [index];
				pq [index] = pq [0];
				pq [0] = null;
				
				percolateDown (index);
			}
		}
	}

	void buildHeap() {
		for (int index = pq.length/2; index > 0; index --)
			heapify(index);
	}
	
	/*
	 * sort array A [1..n]. A [0] is not used. Sorted order depends on comparator used to build heap.
	 *    	min heap ==> descending order 
	 * 		max heap ==> ascending order
	 */
	public static <T> void heapSort(T[] A, Comparator<T> comp) {
		BinaryHeap<T> binaryHeap = new BinaryHeap<T>(A, comp);

		int index = 1;
		while (binaryHeap.size() > 1)
			A [index ++] = binaryHeap.remove();
	}
	
	public void heapify(int index) {
		int leftIndex = 2 * index;
		int rightIndex = 2 * index + 1;
		Integer minima = null;
		
		associateIndex(pq [index], index);
		if (leftIndex < pq.length) {
			if (c.compare(pq [leftIndex], pq [index]) < 0) {
				minima = leftIndex;
			} else {
				minima = index;
			}
		}
		
		if (rightIndex < pq.length) {
			if (c.compare(pq [rightIndex], pq [minima]) < 0) {
				minima = rightIndex;
			}
		}
		
		if (minima != null && minima != index) {
			associateIndex(pq [minima], index);
			associateIndex(pq [index], minima);
			
			pq [0] = pq [index];
			pq [index] = pq [minima];
			pq [minima] = pq [0];
			pq [0] = null;
			
			heapify (minima);
		}
	}
	
	public T[] getPq() {
		return (T[]) pq;
	}
	
	public int size() {
		return pq.length;
	}
	
	public void associateIndex(T item, int index) {
		// method meant to be overridden by indexed heap.
	}
	
	public void associateIndex(T item, T from) {
		// method meant to be overridden by indexed heap.
	}
	
	public void assign(int to, int from) {
		pq [to] = pq [from];
	}
	
	public void assign(int to, T value) {
		pq [to] = value;
	}

}
