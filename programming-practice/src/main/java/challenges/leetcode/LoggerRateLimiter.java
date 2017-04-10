package challenges.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import challenges.AbstractCustomTestRunner;

/**
 * 359. Logger Rate Limiter
 * 
 * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only 
 * if it is not printed in the last 10 seconds.
 * 
 * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
 * It is possible that several messages arrive roughly at the same time.
 * 
 * @author Hxkandwal
 */
public class LoggerRateLimiter extends AbstractCustomTestRunner {

	TreeMap<Integer, Set<String>> dict;

	/** Initialize your data structure here. */
    public LoggerRateLimiter() {
        dict = new TreeMap<>();
    }

	/**
	 * Returns true if the message should be printed in the given timestamp,
	 * otherwise returns false. If this method returns false, the message will
	 * not be printed. The timestamp is in seconds granularity.
	 */
	public boolean shouldPrintMessage(int timestamp, String message) {
		boolean status = false;
		for (Set<String> c : dict.subMap(timestamp - 10, false, timestamp, true).values())
			if (c.contains(message)) {
				status = true;
				break;
			}

		if (!status) {
			dict.put(timestamp, dict.getOrDefault(timestamp, new HashSet<>()));
			dict.get(timestamp).add(message);
		}

		return !status;
	}
    
}

class Logger {

    private Map<String, Integer> ok = new HashMap<>();

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (timestamp < ok.getOrDefault(message, 0))
            return false;
        ok.put(message, timestamp + 10);
        return true;
    }
}

