package me.hxkandwal.daily.challanges.assorted;

import java.util.HashMap;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

public class LFUCacheFramework extends AbstractCustomTestRunner {
	
	private static LFUCacheFramework _instance = new LFUCacheFramework();
	
	private LFUCacheFramework() {}
	
	// data-structure to hold the properties of LFU (least-frequently used) cache.
	public class Cache {
		private int size;
		private Map<Integer, DataNode> metadata;
		private DataNode head;
		private DataNode tail;
		
		public Cache(int size) {
			this.size = size;
			this.metadata = new HashMap<>(size);
		}
		
		public int getSize() {
			return size;
		}
		
		public Map<Integer, DataNode> getMetadata() {
			return metadata;
		}
		
		// insert data to cache.
		public void add(int data) {
			DataNode node = null;
			
			if (metadata.size() < size) {
				node = new DataNode(data);
				
				if (tail == null) 
					head = tail = node;
				else {
					metadata.get(tail.data).following = node;
					node.ahead = metadata.get(tail.data);
					tail = node;
				}
				
				metadata.put(data, node);
			} else {
				if (metadata.containsKey(data)) {
					node = metadata.get(data);
					node.incrementFrequency();
					
					DataNode following = node.following;
					DataNode ahead = node.ahead;
					
					node.following = null;
					node.ahead = null;
					
					if (ahead == null) {
						if (following == ) {
							
						}
						following.ahead = null;
						head = following;
					}
					
					
				}
			}
			
		}
		
		// value holder data structure.
		public class DataNode {
			private int data;
			private int frequency;
			private DataNode ahead;
			private DataNode following;
			
			public DataNode(int data) {
				this.data = data;
				this.frequency = 1;
			}
			
			public void incrementFrequency() {
				this.frequency ++;
			}
			
			@Override
			public String toString() {
				return "(" + this.data + ":" + this.frequency + ")";
			}
		}
	}

}
