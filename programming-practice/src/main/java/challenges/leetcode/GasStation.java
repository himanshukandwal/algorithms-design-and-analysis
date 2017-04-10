package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 134. Gas Station
 * 
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next 
 * station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * 
 * Note: The solution is guaranteed to be unique.
 * 
 * @author Hxkandwal
 */
public class GasStation extends AbstractCustomTestRunner {
	
    public int _canCompleteCircuit(int[] gas, int[] cost) {
    	int start = 0, gas_needed = 0, gas_left = 0;
        for (int idx = 0; idx < gas.length; idx ++) {
            gas_left += gas [idx] - cost [idx];
            if (gas_left < 0) {
                gas_needed += -gas_left;
                gas_left = 0;
                start = idx + 1;
            }
        }
        return (gas_left >= gas_needed) ? start : -1;
    }
    
}
