package challenges;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

import challenges.assorted.tree.model.BinaryTree;

/**
 * A common class holding frequently used utility methods.
 * 
 * @author Hxkandwal
 *
 */
public class Utilities {
	
	public static <T> String print(Collection<T> collection) {
		StringBuilder sb = new StringBuilder();
		
		for (Iterator<T> collectorIterator = collection.iterator(); collectorIterator.hasNext();) {
			sb.append(collectorIterator.next());
			
			if (collectorIterator.hasNext())
				sb.append(",");
		}
		
		return sb.toString();
	}
	
	public static <T> String print(Collection<T> collection, String seperator) {
		StringBuilder sb = new StringBuilder();
		
		for (Iterator<T> collectorIterator = collection.iterator(); collectorIterator.hasNext();) {
			sb.append(collectorIterator.next());
			
			if (collectorIterator.hasNext())
				sb.append(seperator);
		}
		
		return sb.toString();
	}
	
	public static <T> Stack<T> copyStack(Stack<T> stack) {
		Stack<T> copyStack = new Stack<>();
		
		for (T item : stack) 
			copyStack.push(item);
		
		return copyStack;
	}
	
	public static BinaryTree makeBST(int[] array) {
		if (array == null || array.length == 0)
			return null;
		
		return makeBSTInner(array, 0, array.length - 1);
	}
	
	private static BinaryTree makeBSTInner(int[] array, int startIdx, int endIdx) {
		if (startIdx > endIdx)
			return null;
		
		int midIdx = ((endIdx - startIdx + 1) % 2 != 0) ? ((endIdx + startIdx) / 2) 
															: ((endIdx + startIdx + 1) / 2); 
		
		BinaryTree centerNode = new BinaryTree(array [midIdx]);
		centerNode.setLeft(makeBSTInner(array, startIdx, midIdx - 1));
		centerNode.setRight(makeBSTInner(array, midIdx + 1, endIdx));
		
		return centerNode;
	}

	public static class SegmentTreeRMQ {
		public int M, H, N;
		public int[] st;

		public SegmentTreeRMQ(int n)
		{
			N = n;
			M = Integer.highestOneBit(Math.max(N-1, 1))<<2;
			H = M>>>1;
			st = new int[M];
			Arrays.fill(st, 0, M, Integer.MAX_VALUE);
		}

		public SegmentTreeRMQ(int[] a)
		{
			N = a.length;
			M = Integer.highestOneBit(Math.max(N-1, 1))<<2;
			H = M>>>1;
			st = new int[M];
			for(int i = 0;i < N;i++){
				st[H+i] = a[i];
			}
			Arrays.fill(st, H+N, M, Integer.MAX_VALUE);
			for(int i = H-1;i >= 1;i--)propagate(i);
		}

		public void update(int pos, int x)
		{
			st[H+pos] = x;
			for(int i = (H+pos)>>>1;i >= 1;i >>>= 1)propagate(i);
		}

		private void propagate(int i)
		{
			st[i] = Math.min(st[2*i], st[2*i+1]);
		}

		public int min(int l, int r){ return l >= r ? 0 : min(l, r, 0, H, 1);}

		private int min(int l, int r, int cl, int cr, int cur)
		{
			if(l <= cl && cr <= r){
				return st[cur];
			}else{
				int mid = cl+cr>>>1;
				int ret = Integer.MAX_VALUE;
				if(cl < r && l < mid){
					ret = Math.min(ret, min(l, r, cl, mid, 2*cur));
				}
				if(mid < r && l < cr){
					ret = Math.min(ret, min(l, r, mid, cr, 2*cur+1));
				}
				return ret;
			}
		}

		public int firstle(int l, int v) {
			int cur = H+l;
			while(true){
				if(st[cur] <= v){
					if(cur < H){
						cur = 2*cur;
					}else{
						return cur-H;
					}
				}else{
					cur++;
					if((cur&cur-1) == 0)return -1;
					if((cur&1)==0)cur>>>=1;
				}
			}
		}

		public int lastle(int l, int v) {
			int cur = H+l;
			while(true){
				if(st[cur] <= v){
					if(cur < H){
						cur = 2*cur+1;
					}else{
						return cur-H;
					}
				}else{
					if((cur&cur-1) == 0)return -1;
					cur--;
					if((cur&1)==1)cur>>>=1;
				}
			}
		}
	}

}
