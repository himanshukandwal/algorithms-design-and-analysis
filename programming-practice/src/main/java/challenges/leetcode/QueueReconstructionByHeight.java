package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import challenges.AbstractCustomTestRunner;

/**
 * 406. Queue Reconstruction by Height Add to List
 * 
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), 
 * where h is the height of the person and k is the number of people in front of this person who have a height greater 
 * than or equal to h. Write an algorithm to reconstruct the queue.
 * 
 * Note: The number of people is less than 1,100.
 * 
 * Example:
 * 		Input: 	[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 		Output: [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * 
 * @author Hxkandwal
 */
public class QueueReconstructionByHeight extends AbstractCustomTestRunner {
	
	private static QueueReconstructionByHeight _instance = new QueueReconstructionByHeight();

	/**
	 * Pick out tallest group of people and sort them in a subarray (S). 
	 * Since there's no other groups of people taller than them, therefore each guy's index will be just as same as his k value.
	 * For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.
	 */
	public int[][] _reconstructQueueFaster(int[][] people) {
		Arrays.sort (people, (a, b) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]);
		List<int[]> ans = new LinkedList<>();
		for (int[] p : people) ans.add(p[1], p);
		return ans.toArray(new int[0][0]);
	}
	
	public int[][] _reconstructQueue(int[][] people) {
		if (people.length == 0) return people;
		
        Arrays.sort (people, new Comparator<int []>() {
            public int compare (int [] o1, int [] o2) {
                return (o1 [1] == o2 [1]) ? o1 [0] - o2 [0] : o1 [1] - o2 [1];
            }
        });
        
        LinkedList <int []> ans = new LinkedList <>();
        LinkedList <int []> avail = new LinkedList <>();
        for (int [] p : people) avail.offer (p);
        NavigableMap<Integer, Set<Integer>> map = new TreeMap <>();
        
        while (!avail.isEmpty()) {
            int [] item = avail.poll ();
            while (item [1] < map.tailMap (item [0]).entrySet().stream().mapToInt(e -> e.getValue().size()).sum()) {
                int [] removedItem = ans.pollLast();
                map.get (removedItem [0]).remove(removedItem [1]);
                if (map.get (removedItem [0]).size() == 0) map.remove(removedItem [0]);
                avail.offer (removedItem);
            }
            if (item [1] > map.tailMap (item [0]).entrySet().stream().mapToInt(e -> e.getValue().size()).sum()) { avail.offer(item); continue; }
            ans.offer (item); 
            if (!map.containsKey(item [0])) map.put (item [0], new HashSet<>());
            map.get(item [0]).add(item [1]);
        }
         
        int [][] res = new int [people.length][people [0].length];
        int idx = 0;
        for (int [] entry : ans) {
        	res [idx][0] = entry [0]; res [idx][1] = entry [1];
            idx ++;
        }
        return res;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[][] { new int[] { 7, 0 }, new int[] { 4, 4 }, new int[] { 7, 1 }, 
										new int[] { 5, 0 }, new int[] { 6, 1 }, new int[] { 5, 2 }},
						  new int[][] { new int[] { 5, 0 }, new int[] { 7, 0 }, new int[] { 5, 2 },
										new int[] { 6, 1 }, new int[] { 4, 4 }, new int[] { 7, 1 }});
	}

	public void runTest(final int [][] people, final int [][] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { people });

		for (Object answer : answers) {
			int [][] arrAnswer = (int [][]) answer;
			for (int idx = 0; idx < people.length; idx ++)
				assertThat((int []) arrAnswer [idx]).isEqualTo(expectedOutput [idx]); 
		}	
		
		System.out.println("ok!");
	} 
	
}
