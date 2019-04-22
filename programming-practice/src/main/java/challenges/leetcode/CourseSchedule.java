package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

/**
 * 207. Course Schedule
 * 
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * 
 * For example:
 * 		2, [[1,0]]
 * 		There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 * 
 * 		2, [[1,0],[0,1]]
 * 		There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 
 * 		you should also have finished course 1. So it is impossible.
 * 
 * Note:
 * 		The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * 		You may assume that there are no duplicate edges in the input prerequisites.
 * 
 * @author Hxkandwal
 */
public class CourseSchedule extends AbstractCustomTestRunner {
	
	public class Node {
        int label;
        boolean seen;
        List<Node> prereq = new ArrayList<>();
        List<Node> dependent = new ArrayList<>();
        
        public Node (int label) { this.label = label; }
    }
    
    public boolean _canFinish(int numCourses, int[][] prerequisites) {
        Node [] nodes = new Node [numCourses];
        for (int idx = 0; idx < numCourses; idx ++) nodes [idx] = new Node (idx);
        for (int [] prerequisite : prerequisites) {
            nodes [prerequisite [0]].prereq.add (nodes [prerequisite [1]]);
            nodes [prerequisite [1]].dependent.add (nodes [prerequisite [0]]);
        }
        
        for (Node node : nodes)
            if (node.dependent.size() == 0 && !dfs (new HashSet<> (), node)) return false; 
        
        return nodes [0].seen;
    }
    
    private boolean dfs (Set<Node> chained, Node node) {
        if (node.seen) return true;
        if (chained.contains (node)) return false;
        chained.add (node);
        
        for (Node dependsOn : node.prereq)
            if (!dfs (chained, dependsOn)) return false;
            
        chained.remove (node);
        return node.seen = true;
    }

    // Iterative version of topological sort.
    public boolean _canFinishIterative(int numCourses, int[][] prerequisites) {
        int [] indeg = new int [numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int [] p : prerequisites) {
            map.computeIfAbsent(p [0], k -> new ArrayList<>()).add (p [1]);
            indeg [p [1]] ++;
        }

        // better way of doing topological sort (Iterative version)
        Queue<Integer> queue = new LinkedList<>();
        for (int idx = 0; idx < indeg.length; idx ++)
            if (indeg [idx] == 0) queue.offer (idx);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0) {
                int courseId = queue.poll();

                if (map.containsKey(courseId))
                    for (int pre : map.get(courseId))
                        if (-- indeg [pre] == 0) queue.offer (pre);

                numCourses --; // all the elemets in the queue were already resolved.
            }
        }

        return numCourses == 0;
    }
    
}
