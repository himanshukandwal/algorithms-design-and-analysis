package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 253. Meeting Rooms II
 * 
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
 * find the minimum number of conference rooms required.
 * 
 * For example,
 * 		Given [[0, 30],[5, 10],[15, 20]],
 * 		return 2.
 *  
 * @author Hxkandwal
 */
public class MeetingRoomsII extends AbstractCustomTestRunner {
	
	private static MeetingRoomsII _instance = new MeetingRoomsII();

	public static class Interval {
		int start;
		int end;

		public Interval() { start = 0; end = 0; }
		public Interval(int s, int e) { start = s; end = e; }
		public String toString() { return "(" + start + ":" + end + ")"; }
	}
	
	public int _minMeetingRooms(Interval[] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<Interval>() {
           public int compare (Interval i1, Interval i2) {
               return i1.end - i2.end;
           }
        });
        
        LinkedList<Integer> endTimes = new LinkedList<>();
        for (Interval i : intervals) {
            for (Iterator<Integer> endTimeIterator = endTimes.iterator(); endTimeIterator.hasNext();) {
                Integer endTime = endTimeIterator.next ();
                if (i.start >= endTime) {
                    endTimeIterator.remove();
                    break;
                }
            }
            endTimes.addFirst (i.end);    
        }
        return endTimes.size();
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new Interval[] { new Interval(2, 15), new Interval(36, 45), new Interval(9, 29), new Interval(16, 23),  new Interval(4, 9) }, 2);
	}

	public void runTest(final Interval[] intervals, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { intervals });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
}
