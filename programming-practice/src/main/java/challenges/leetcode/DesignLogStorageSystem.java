package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 635. Design Log Storage System
 * 
 * You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: 
 * Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.
 * 
 * Design a log storage system to implement the following functions:
 * 
 * void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.
 * 
 * int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to 
 * end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. 
 * 
 * For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs 
 * within the range from Jan. 1st 2017 to Jan. 2nd 2017.
 * 
 * Example 1:
 * 	put(1, "2017:01:01:23:59:59");
 * 	put(2, "2017:01:01:22:59:59");
 * 	put(3, "2016:01:01:00:00:00");
 * 
 * 	retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
 * 	retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 
 * 																	 to 2017:01:01:23, where log 3 is left outside the range.
 * 
 * Note:
 * 	-	There will be at most 300 operations of Put or Retrieve.
 * 	-	Year ranges from [2000,2017]. Hour ranges from [00,23].
 * 	-	Output for Retrieve has no order required.
 * 
 * @author Hxkandwal
 */
public class DesignLogStorageSystem extends AbstractCustomTestRunner {

    private class Node {
        private List<Integer> ids = new ArrayList<>();		// shared resource (distributed) between all the children
        private Map<Integer, Node> children = new HashMap<>();
        
        public void add (String [] parts, int id, int idx) {
            if (idx >= parts.length) return;
            Integer part = Integer.valueOf(parts [idx]);
            children.put (part, children.getOrDefault (part, new Node ()));
            children.get(part).ids.add (id);
            children.get(part).add (parts, id, idx + 1);
        }
        
        public void find (List<Integer> ans, String [] parts1, String [] parts2, int idx, int level) {
        	if (idx == level) ans.addAll(ids);
        	else 
        		for (int p = Integer.valueOf(parts1 [idx]); p <= Integer.valueOf(parts2 [idx]); p ++)
        			if (children.containsKey (p)) children.get (p).find (ans, parts1, parts2, idx + 1, level);
        }
    }
    
    private Node root = new Node ();
    private Map<String, Integer> granularity = new HashMap<>();
    
    public DesignLogStorageSystem() {
        granularity.put ("Year", 1);
        granularity.put ("Month", 2);
        granularity.put ("Day", 3);
        granularity.put ("Hour", 4);
        granularity.put ("Minute", 5);
        granularity.put ("Second", 6);
    }
    
    public void put(int id, String timestamp) {
        String [] parts = timestamp.split(":");
        root.add (parts, id, 0);
    }
    
    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> ans = new ArrayList<> ();
        root.find (ans, s.split (":"), e.split (":"), 0, granularity.get (gra));
        return ans;
    }

    // driver method
   	public static void main(String[] args) {
   		testCase1 ();
		testCase2 ();
		testCase3 ();
		System.out.println ("ok!");
   	}

   	private static void testCase1() {
   		DesignLogStorageSystem fileSystem = new DesignLogStorageSystem();
   		fileSystem.put(1,"2017:01:01:23:59:59");
   		fileSystem.put(2,"2017:01:02:23:59:59");
   		List<Integer> ans = fileSystem.retrieve("2017:01:01:23:59:59","2017:01:02:23:59:59","Second");
   		assertThat(ans).isEqualTo(Arrays.asList(1, 2));
   	}
   	
   	private static void testCase2() {
   		DesignLogStorageSystem fileSystem = new DesignLogStorageSystem();
   		fileSystem.put(1, "2017:01:01:23:59:59");
   		fileSystem.put(2, "2017:01:01:22:59:59");
   		fileSystem.put(3, "2016:01:01:00:00:00");
   		assertThat(fileSystem.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year")).isEqualTo(Arrays.asList(3, 1, 2));
   		assertThat(fileSystem.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour")).isEqualTo(Arrays.asList(2, 1));
   	}
   	
   	private static void testCase3() {
   		DesignLogStorageSystem fileSystem = new DesignLogStorageSystem();
   		fileSystem.put(1,"2017:01:01:23:59:59");
   		fileSystem.put(2,"2017:01:02:23:59:59");
   		assertThat(fileSystem.retrieve("2017:01:01:23:59:58","2017:01:02:23:59:58","Second")).isEqualTo(Arrays.asList(1));
   	}
   	
/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */
}
