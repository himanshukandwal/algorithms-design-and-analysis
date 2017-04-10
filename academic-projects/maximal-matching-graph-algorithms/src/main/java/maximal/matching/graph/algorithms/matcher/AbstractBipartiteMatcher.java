package maximal.matching.graph.algorithms.matcher;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import maximal.matching.graph.algorithms.model.Graph;
import maximal.matching.graph.algorithms.model.Matching;

/**
 * an abstract class holding the common threading logic that is common across all the classes.
 *  
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public abstract class AbstractBipartiteMatcher {

	private static final int THREAD_POOL_SIZE = 100;												// thread-pool size.
	protected Graph graph;																			// inner graph.
	private ExecutorService threadTasksMaster = Executors.newFixedThreadPool(THREAD_POOL_SIZE); 	// executor service : acting as a managed thread-pool agent.
	
	/* concurrentHashMap is implemented for higher throughput, for case where high concurrency is expected. */
	private ConcurrentHashMap<Integer, Matching> idMatchingMap;	
	
	/* a set mimicking the behavior of 'wait-for' table that we have in database. This is needed to eliminate the scenarios leading to dead-lock. */
	private Set<WaitForEntry> waitingForResourcesSet = new ConcurrentSkipListSet<>();
	
	/**
	 * constructor.
	 * 
	 * @param graph
	 */
	public AbstractBipartiteMatcher(Graph graph) {
		this.graph = graph;
	}
	
	/**
	 * getter method for inner graph.
	 * 
	 * @return
	 */
	public Graph getGraph() {
		return graph;
	}
	
	/**
	 * getter method for 'waitForSet' set.
	 * 
	 * @return
	 */
	public synchronized Set<WaitForEntry> getWaitingForResourcesSet() {
		// avoiding deadlocking (double locking)
		synchronized (this) {
			return waitingForResourcesSet;
		}
	}
	
	/**
	 * getter method for managed thread-pool 'threadTasksMaster'.
	 * @return
	 */
	public ExecutorService getThreadTasksMaster() {
		return threadTasksMaster;
	}
	
	/**
	 * shutdown executor service.
	 */
	public void shutdownTasksExecutor() {
		getThreadTasksMaster().shutdown();
	}
	
	/**
	 * getter method for map 'idMatchingMap'.
	 * 
	 * @return
	 */
	public ConcurrentHashMap<Integer, Matching> getIdMatchingMap() {
		if (idMatchingMap == null) 
			idMatchingMap = new ConcurrentHashMap<>();
		
		return idMatchingMap;
	}
	
	/**
	 * WaitForEntry class.  
	 * 
	 **/
	public class WaitForEntry implements Comparable<WaitForEntry> {
		
		private int requestingResourceId;
		private int requestedResourceId;
		
		/**
		 * constructor.
		 */
		public WaitForEntry(int requestingResourceId, int requestedResourceId) {
			this.requestingResourceId = requestingResourceId;
			this.requestedResourceId = requestedResourceId;
		}

		@Override
		public int compareTo(WaitForEntry otherWaitForEntry) {
			int result = -1;
			if ((otherWaitForEntry.requestingResourceId == requestingResourceId && otherWaitForEntry.requestedResourceId == requestedResourceId) 
					|| (otherWaitForEntry.requestingResourceId == requestedResourceId && otherWaitForEntry.requestedResourceId == requestingResourceId)) {
				result = 0; 
			}
			return result;
		}
		
		@Override
		public String toString() {
			return requestingResourceId + " : " + requestedResourceId;
		}
		
		@Override
		public int hashCode() {
			/* this is a very crude way of computing hash-code, however, resulting to this and 
			 * not using : Arrays.hashcode(new int [] {requestingResourceId, requestedResourceId}) as we have preserve the same value if 
			 * the values of requestingResourceId and requestedResourceId gets swapped. i.e (3,2) should be same as (2,3) 
			 * */
			return Integer.valueOf(requestingResourceId).hashCode() + Integer.valueOf(requestedResourceId).hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof WaitForEntry)) 
				return false;
			return (this.compareTo((WaitForEntry) obj) == 0 ? true : false);
		}
		
	}
	
}
