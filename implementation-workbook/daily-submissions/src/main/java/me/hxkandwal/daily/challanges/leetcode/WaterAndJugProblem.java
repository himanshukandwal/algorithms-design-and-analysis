package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 365. Water and Jug Problem
 * 
 * You are given two jugs with capacities x and y litres. There is an infinite amount of water supply 
 * available. You need to determine whether it is possible to measure exactly z litres using these two jugs.
 * 
 * If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.
 * 
 * Operations allowed:
 * 		1. 	Fill any of the jugs completely with water.
 * 		2.	Empty any of the jugs.
 * 		3. 	Pour water from one jug into another till the other jug is completely 
 * 			full or the first jug itself is empty.
 * 
 * Example 1: 
 * 		
 * (a)		(From the famous "Die Hard" example)
 * 			
 * 			Input: x = 3, y = 5, z = 4
 * 			Output: True
 * 
 * (b)		Input: x = 2, y = 6, z = 5
 * 			Output: False			
 * 
 * @author Hxkandwal
 *
 */
public class WaterAndJugProblem extends AbstractCustomTestRunner {
	
	private static WaterAndJugProblem _instance = new WaterAndJugProblem();
	
	private WaterAndJugProblem() {}
	
	// this data structure is created to ensure we do not traverse same state twice. 
	public static class State {
		int xfill;
		int yfill;
		
		public State(int xfill, int yfill) {
			this.xfill = xfill;
			this.yfill = yfill;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof State)
				return ((State) obj).xfill == this.xfill && ((State) obj).yfill == this.yfill;
			return false;
		}
		
		@Override
		public int hashCode() {
			return xfill * 31 + yfill;
		}
	}

	public boolean _canMeasureWater(int x, int y, int z) {
		if (x % 2 == 0 && y % 2 == 0 && z % 2 != 0)
			return false;
		
		Set<State> cachedStates = new HashSet<>();
		State initialState = new State(0, 0);
		cachedStates.add(initialState);
		
		return (x + y == z) ? true : canMeasureWaterRecursively(x, y, z, initialState, cachedStates);
	}

	public boolean canMeasureWaterRecursively(int x, int y, int z, State state, Set<State> cachedStates) {
		// base case. (avoid looping)
		if (cachedStates.contains(state))
			return false;
		
		if (state.xfill + state.yfill == z) 
			return true;
		
		
		return false;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(3, 5, 4, true);
		_instance.runTest(2, 6, 5, false);
	}
	
	public void runTest(final int x, final int y, final int z, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { x, y, z });
		
		for (Object answer : answers) 
			assertThat((boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
