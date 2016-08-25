package me.hxkandwal.daily.hackerrank.challanges;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ServerDistribution {

	public static void main(String[] args) {
		// runInput1();
		System.out.println( "---------");
		//runInput2();
		System.out.println( "---------");
		runInput3();
	}
	
	static void runInput1() {
		int[][] out = serverFarm(new int[] {15, 30, 15, 5, 10}, 3);
		for (int i = 0 ; i < out.length; i ++) {
			for (int j = 0 ; j < out[i].length; j ++) {
				System.out.print(out[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void runInput2() {
		int[][] out = serverFarm(new int[] {}, 8);
		for (int i = 0 ; i < out.length; i ++) {
			for (int j = 0 ; j < out[i].length; j ++) {
				System.out.print(out[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void runInput3() {
		int[][] out = serverFarm(new int[] {8, 7, 15, 15, 13, 6, 18, 4, 16, 1, 2, 19, 2, 15, 18, 
											6, 20, 16, 10, 7, 3, 7, 9, 7, 12, 1, 16, 15, 7, 12, 
											20, 17, 17, 4, 20, 15, 20, 6, 15, 3, 5, 17, 5, 5, 19, 
											17, 4, 15, 2, 7}, 
								9);
		
		for (int i = 0 ; i < out.length; i ++) {
			for (int j = 0 ; j < out[i].length; j ++) {
				System.out.print(out[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static int[][] serverFarm(int[] jobs, int servers) {

	    Map<Integer, Integer> jobsMap = new HashMap<>();
	    
	    for (int idx = 0; idx < jobs.length; idx ++) 
	        jobsMap.put(idx, jobs[idx]);
	    
	    List<Map.Entry<Integer, Integer>> entryList = new LinkedList<> (jobsMap.entrySet());
	    
	    Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>> () {
	        
	        public int compare (Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
	            if (o1.getValue() == o2.getValue()) {
	                return o1.getKey().compareTo(o2.getKey());
	            } else {
	                return o2.getValue().compareTo(o1.getValue());
	            }
	        }
	    });
	    
	    Map<Integer, Integer> linkedJobsMap = new LinkedHashMap<>();
	    for (Map.Entry<Integer, Integer> entry : entryList) 
	        linkedJobsMap.put(entry.getKey(), entry.getValue());
	    
	    
	    int[] serverInfo = new int [servers];
	    Arrays.fill (serverInfo, -1);
	    
	    List<List<Integer>> outputList = new LinkedList<>();
	    
	    int idx = 0;
	    for (final Map.Entry<Integer, Integer> entry : linkedJobsMap.entrySet()) {
	        if (idx < servers && serverInfo[idx] == -1) {
	            serverInfo[idx] = entry.getValue();
	            outputList.add(idx ++, new LinkedList<Integer>() {{ add(entry.getKey()); }}); 
	        } else {
	            idx = 0;
	            int firstMinValue = serverInfo[0];
	            int firstMinIdx = 0;
	            
	            for (int internalIdx = 0; internalIdx < serverInfo.length; internalIdx ++) {
	                if (firstMinValue > serverInfo[internalIdx]) {
	                    firstMinValue = serverInfo[internalIdx];
	                    firstMinIdx = internalIdx;
	                }
	            }
	            
	            idx = firstMinIdx;
	            serverInfo[idx] += entry.getValue();
	            outputList.get(idx ++).add(entry.getKey());
	        }
	    }
	    
	    int[][] output = new int[servers][];
	    for (int id = 0; id < servers; id ++) {
	        if (id < outputList.size())
	            output[id] = convert(outputList.get(id).toArray (new Integer [0]));
	        else 
	            output[id] = new int[0];
	    }
	    return output;
	}

	static int[] convert(Integer[] list) {
	    int[] array = new int [list.length];
	    for (int idx = 0; idx < list.length; idx ++) {
	        array[idx] = list[idx];
	    }
	    return array;
	}
	
}
