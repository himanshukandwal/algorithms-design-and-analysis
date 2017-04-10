package maximal.matching.graph.algorithms.model;

/**
 * a simple enumeration class, listing all the debug level which can be supplied to the system. 
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public enum DebugLevel {

	ERROR(3),					
	VERBOSE(2),
	MINIMAL(1);
	
	private int priority;		// when a debug level is set, it prints all the low priority messages as well. 
								// example : if ERROR is set, we will be able to see VERBOSE and MINIMAL error messages too.
	
	private DebugLevel(int priority) {
		this.priority = priority;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public boolean isErrorEnabled() {
		return this == ERROR || this == VERBOSE;
	}
	
	public boolean isVerboseEnabled() {
		return this == VERBOSE;
	}
	
}
