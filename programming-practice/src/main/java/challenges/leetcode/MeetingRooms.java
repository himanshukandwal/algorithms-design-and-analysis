package challenges.leetcode;

import java.util.Arrays;
import java.util.Comparator;

import challenges.AbstractCustomTestRunner;

/**
 * 252. Meeting Rooms
 * 
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine 
 * if a person could attend all meetings.
 * 
 * For example,
 * 		Given [[0, 30],[5, 10],[15, 20]],
 * 		return false.
 * 
 * @author Hxkandwal
 */
public class MeetingRooms extends AbstractCustomTestRunner {

	public static class Interval {
		int start;
		int end;

		public Interval() { start = 0; end = 0; }
		public Interval(int s, int e) { start = s; end = e; }
		public String toString() { return "(" + start + ":" + end + ")"; }
	}
	
	public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals.length <= 1) return true;
        Arrays.sort(intervals, new Comparator<Interval> () {
           public int compare (Interval i1, Interval i2) {
               return i1.end - i2.end;
           } 
        });
        
        int lastEndTime = -1;
        for (Interval i : intervals) {
            if (lastEndTime == -1) lastEndTime = i.end;
            else if (i.start >= lastEndTime) lastEndTime = i.end;
            else return false;
        }
        return true;
    }
	
}
