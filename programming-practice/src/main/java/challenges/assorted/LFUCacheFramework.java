package challenges.assorted;

import java.util.HashMap;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

public class LFUCacheFramework extends AbstractCustomTestRunner {
	
	private LFUCacheFramework() {}
	
	// data-structure to hold the properties of LFU (least-frequently used) cache.
	public static class Cache {
		
		private int size;
		private Map<Integer, DataNode> metadata;
		private DataNode head;
		private DataNode tail;
		
		public Cache(int size) {
			this.size = size;
			this.metadata = new HashMap<>(size);
		}
		
		// value holder data structure.
		public class DataNode {
			private int data;
			private DataNode ahead;
			private DataNode following;
			
			public DataNode(int data) {
				this.data = data;
			}
			
			@Override
			public String toString() {
				return "(" + this.data + ")";
			}
		}
		
		// insert data to cache.
		public void add(int data) {
			DataNode node = null;
			
			if (metadata.size() < size) {
				node = new DataNode(data);
				
				if (tail == null)  head = tail = node;
				else {
					tail.following = node; 
					node.ahead = tail;
					tail = node;
				}
				
				metadata.put(data, node);
			} else {
				if (metadata.containsKey(data)) {
					node = metadata.get(data);
					
					DataNode following = node.following;
					DataNode ahead = node.ahead;
					node.following = null;
					node.ahead = null;
					
					if (ahead != null) ahead.following = following;
					else  head = following;
					
					if (following != null) following.ahead = ahead;
					else tail = ahead;
				} else {
					System.out.println("removing least recently used :" + head.data);
					metadata.remove(head.data);
					head.following.ahead = null;
					head = head.following;
					
					node = new DataNode(data);
					metadata.put(data, node);
				}
				
				tail.following = node;
				node.ahead = tail;
				tail = node;
			}
		}
	}

	// driver method
	public static void main(String[] args) {
		Cache cache = new Cache(5);
		cache.add(1); cache.add(2); cache.add(3); cache.add(4); cache.add(5); 
		cache.add(1); cache.add(6); cache.add(3); cache.add(7); cache.add(8);
	}

}