package minimum.spanning.tree.graph.algorithms.util;

/**
 * Timer class for roughly calculating running time of programs
 * 
 * @author rbk 
 * 
 * Usage: Timer timer = new Timer(); timer.start(); timer.end();
 *         System.out.println(timer); // output statistics
 */

public class Timer {
	
	private long startTime, endTime, elapsedTime, memAvailable, memUsed;

	Timer() {
		startTime = System.currentTimeMillis();
	}

	public void start() {
		startTime = System.currentTimeMillis();
	}

	public Timer end() {
		endTime = System.currentTimeMillis();
		elapsedTime = endTime - startTime;
		memAvailable = Runtime.getRuntime().totalMemory();
		memUsed = memAvailable - Runtime.getRuntime().freeMemory();
		return this;
	}

	public String toString() {
		return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed / 1000000) + " MB / "
				+ (memAvailable / 1000000) + " MB.";
	}

}
