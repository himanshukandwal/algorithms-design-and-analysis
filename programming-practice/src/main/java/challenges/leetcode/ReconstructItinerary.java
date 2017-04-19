package challenges.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import challenges.AbstractCustomTestRunner;

/**
 * 332. Reconstruct Itinerary
 * 
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. 
 * All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 * 
 * Note: 
 * 1. If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 * 		For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * 2. All airports are represented by three capital letters (IATA code).
 * 3. You may assume all tickets form at least one valid itinerary.
 * 
 * Example 1:
 * 	tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * 	Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 *
 * Example 2:
 * 	tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 	Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 * 
 * 	Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 * 
 * @author Hxkandwal
 */
public class ReconstructItinerary extends AbstractCustomTestRunner {

	/**
	 * 		 > B
	 * 		/	 \>
	 * 	   D <--  C
	 * 	   ^  \/  ^
	 * 	   | </\> |
	 * 	  JFK --> A
	 * 
	 * From JFK we first visit JFK -> A -> C -> D -> A. There we're stuck, so we write down A as the end of the route and retreat back to D. 
	 * There we see the unused ticket to B and follow it: D -> B -> C -> JFK -> D. Then we're stuck again, retreat and write down the airports 
	 * while doing so: Write down D before the already written A, then JFK before the D, etc. When we're back from our cycle at D, the written 
	 * route is D -> B -> C -> JFK -> D -> A. Then we retreat further along the original path, prepending C, A and finally JFK to the route, 
	 * ending up with the route JFK -> A -> C -> D -> B -> C -> JFK -> D -> A.
	 */
	// pseudo-topological ordering.
	public List<String> findItinerary(String[][] tickets) {
        Map <String, PriorityQueue<String>> map = new HashMap<> ();
        for (String [] ticket : tickets) 
        	map.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
        
        LinkedList<String> ans = new LinkedList<> ();
        dfs (map, ans, "JFK");
        return ans;
    }
    
    private void dfs (Map<String, PriorityQueue <String>> map, LinkedList<String> ans, String start) {
        while (map.containsKey (start) && !map.get (start).isEmpty())
            dfs (map, ans, map.get (start).poll ());
        ans.addFirst (start);
    }
}
