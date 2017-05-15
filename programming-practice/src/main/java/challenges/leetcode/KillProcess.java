package challenges.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 582. Kill Process
 * 
 * Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
 * Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one 
 * process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.
 * 
 * We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list 
 * contains the corresponding PPID.
 * 
 * Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. 
 * You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.
 * 
 * Example 1:
 * 	Input:
 * 		pid =  [1, 3, 10, 5]
 * 		ppid = [3, 0, 5, 3]
 * 		kill = 5
 * 
 * 	Output: [5,10]
 * 
 * Explanation:
 *            3
 *          /   \
 *         1     5
 *              /
 *             10
 *             
 * Kill 5 will also kill 10.
 * 
 * Note:
 * 	-	The given kill id is guaranteed to be one of the given PIDs.
 * 	-	n >= 1.
 * 
 * @author Hxkandwal
 */
public class KillProcess extends AbstractCustomTestRunner {
	
	class Node {
        int val;
        List<Node> children = new ArrayList<>();
        
        public Node (int val) { this.val = val; }
    }
    
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, Node> index = new HashMap<>();
        for (Integer p : pid)  index.putIfAbsent (p, new Node (p));
        for (Integer p : ppid) index.putIfAbsent (p, new Node (p));
        
        for (int idx = 0; idx < pid.size(); idx ++) 
            index.get (ppid.get (idx)).children.add (index.get (pid.get (idx)));
        
        List<Integer> ans = new ArrayList<>();
        if (!index.containsKey (kill)) return ans;
        
        ans.add (kill);
        dfs (index.get (kill), ans);
        return ans;
    }
    
    private void dfs (Node node, List<Integer> ans) {
        for (Node childNode : node.children) {
            ans.add (childNode.val);
            dfs (childNode, ans);
        }
    }
    
    /**
     *  using Maps only.
     */
	public List<Integer> killProcessBetter(List<Integer> pid, List<Integer> ppid, int kill) {
		// Store process tree as an adjacency list
		Map<Integer, List<Integer>> adjacencyLists = new HashMap<>();
		for (int i = 0; i < ppid.size(); i++) {
			adjacencyLists.putIfAbsent(ppid.get(i), new LinkedList<>());
			adjacencyLists.get(ppid.get(i)).add(pid.get(i));
		}

		// Kill all processes in the subtree rooted at process "kill"
		List<Integer> res = new LinkedList<>();
		Stack<Integer> stack = new Stack<>();
		stack.add(kill);
		while (!stack.isEmpty()) {
			int cur = stack.pop();
			res.add(cur);
			List<Integer> adjacencyList = adjacencyLists.get(cur);
			if (adjacencyList == null) continue;
			stack.addAll(adjacencyList);
		}
		return res;
	}
	
}
