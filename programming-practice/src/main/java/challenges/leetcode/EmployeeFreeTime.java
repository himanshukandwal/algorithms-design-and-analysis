package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static challenges.leetcode.EmployeeFreeTime.Interval.fromString;
import static com.google.common.truth.Truth.assertThat;
import static java.util.Arrays.asList;

/**
 * 759. Employee Free Time
 *
 * We are given a list schedule of employees, which represents the working time for each employee.
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 *
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 *
 * Example 1:
 *          Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 *          Output: [[3,4]]
 *          Explanation: There are a total of three employees, and all common free time intervals would be [-inf, 1], [3, 4], [10, inf].
 *                       We discard any intervals that contain inf as they aren't finite.
 *
 * Example 2:
 *          Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 *          Output: [[5,6],[7,9]]
 *
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
 * For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)
 *
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 * Note:
 *  schedule and schedule[i] are lists with lengths in range [1, 50].
 *  0 <= schedule[i].start < schedule[i].end <= 10^8.
 *
 * @author Hxkandwal
 */
public class EmployeeFreeTime extends AbstractCustomTestRunner {

    private static EmployeeFreeTime _instance = new EmployeeFreeTime();

    public static class Interval {
        int start;
        int end;
        public Interval() { start = 0; end = 0; }
        public Interval(int s, int e) { start = s; end = e; }

        public static Interval fromString(String str) {
            String[] parts = str.split(":");
            return new Interval(Integer.valueOf(parts [0]), Integer.valueOf(parts [1]));
        }

        public String toString() {
            return this.start + ":" + this.end;
        }
    }

    public List<Interval> _employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> list = new ArrayList<>();
        for (int idx = schedule.size() - 1; idx >= 0; idx --) {
            for (Interval i : schedule.get(idx))
                addSchedule (list, i);
        }

        List<Interval> ans = new ArrayList<>();
        for (int idx = 0, start = -1; idx < list.size(); idx ++) {
            Interval i = list.get (idx);
            if (start != -1 && i.start - start > 0)
                ans.add (new Interval(start, i.start));
            start = i.end;
        }
        return ans;
    }

    private void addSchedule(List<Interval> list, Interval i) {
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int mid = l + (r - l)/2;
            Interval m = list.get(mid);
            if (m.start <= i.start && m.end >= i.end) return;
            if (m.start > i.end) r = mid - 1;
            else if (m.end < i.start) l = mid + 1;
            else {
                // overlapping (multi)
                List<Interval> toDelete = new ArrayList<>();
                int prev = mid, next = mid;
                while (prev - 1 >= 0 && list.get(prev - 1).end > i.start) prev --;
                while (next + 1 < list.size() && list.get(next + 1).start < i.end) next ++;

                Interval overlapped = new Interval(
                        Math.min(i.start, list.get(prev).start),
                        Math.max(i.end, list.get(next).end)
                );
                list.add (prev, overlapped);

                for (int idx = prev + 1; idx <= next + 1; idx ++) toDelete.add(list.get(idx));
                list.removeAll(toDelete);
                return;
            }
        }
        if (l == list.size())  list.add (i);
        else list.add (l, i);
    }

    /**
     * another variation of addSchedule method
     *
     * We don't need to create a list to remove, we can just remove the elements of size (next - prev + 1) times, as we need to clear prev and next both
     * inclusively. Then later we can add to the prev location itself. (as it shifts the arrays towards the left)
     * Example:
     *      prev = 1, next = 3, list = [1, 2, 3, 4, 5, 6]
     *      size = 3 - 1 + 1 = 3
     *      index :         0  1  2  3  4  5
     *      itr 1:  list = [1, 3, 4, 5, 6]      [prev = 1]
     *      itr 2:  list = [1, 4, 5, 6]         [prev = 1]
     *      itr 3:  list = [1, 5, 6]            [prev = 1]
     *
     *      value to insert (overlapped variable) say : 7
     *      itr 3:  list = [1, 7, 5, 6]         [prev = 1]
     *
     * Hence, this is how we cleared the range [1 - 3], and inserted a new element at index 1.
     */
    private void addScheduleAnother (List<Interval> list, Interval i) {
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int mid = l + (r - l)/2;
            Interval m = list.get(mid);
            if (m.start <= i.start && m.end >= i.end) return;
            if (m.start > i.end) r = mid - 1;
            else if (m.end < i.start) l = mid + 1;
            else {
                int prev = mid, next = mid;
                while (prev - 1 >= 0 && list.get(prev - 1).end > i.start) prev --;
                while (next + 1 < list.size() && list.get(next + 1).start < i.end) next ++;

                Interval overlapped = new Interval (
                        Math.min(list.get(prev).start, i.start),
                        Math.max(list.get(next).end, i.end)
                );
                int size = next - prev + 1;
                while (size -- > 0) list.remove (prev);
                list.add(prev, overlapped);
                return;
            }
        }
        if (l == list.size()) list.add (i);
        else list.add(l, i);
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(
                asList(
                        asList(fromString("1:2"), fromString("5:6")), asList(fromString("1:3")),asList(fromString("4:10"))
                ),
                asList(fromString("3:4"))
        );
        _instance.runTest(
                asList(
                        asList(fromString("7:24"), fromString("29:33"), fromString("45:57"), fromString("66:69"), fromString("94:99")),
                        asList(fromString("6:24"), fromString("43:49"), fromString("56:59"), fromString("61:75"), fromString("80:81")),
                        asList(fromString("5:16"), fromString("18:26"), fromString("33:36"), fromString("39:57"), fromString("65:74")),
                        asList(fromString("9:16"), fromString("27:35"), fromString("40:55"), fromString("68:71"), fromString("78:81")),
                        asList(fromString("0:25"), fromString("29:31"), fromString("40:47"), fromString("57:87"), fromString("91:94"))
                ),
                asList(fromString("26:27"), fromString("36:39"), fromString("87:91"))
        );
        _instance.runTest(
                asList(
                        asList(fromString("0:1"), fromString("12:19"), fromString("42:54"), fromString("78:80"), fromString("89:93")),
                        asList(fromString("5:10"), fromString("30:62"), fromString("69:73"), fromString("80:83"), fromString("90:100")),
                        asList(fromString("13:21"), fromString("23:29"), fromString("42:43"), fromString("86:91"), fromString("97:100")),
                        asList(fromString("0:11"), fromString("24:25"), fromString("30:58"), fromString("76:93"), fromString("94:97")),
                        asList(fromString("1:3"), fromString("46:50"), fromString("56:58"), fromString("71:73"), fromString("77:99"))
                ),
                asList(fromString("11:12"), fromString("21:23"), fromString("29:30"), fromString("62:69"), fromString("73:76"))
        );
    }

    // shorter solution. (slow perf)
    public List<Interval> _employeeFreeTimeShorter(List<List<Interval>> schedule) {
        List<Interval> ans = new ArrayList<>();
        List<Interval> timeline = new ArrayList<>();
        for (List<Interval> s : schedule) timeline.addAll(s);

        Collections.sort(timeline, (a, b) -> a.start - b.start);

        Interval t = null;
        for (int idx = 0; idx < timeline.size(); idx ++) {
            Interval curr = timeline.get(idx);

            if (t == null) t = curr;
            else {
                if (t.end < curr.start) {
                    ans.add (new Interval(t.end, curr.start));
                    t = curr;
                } else t.end = Math.max (t.end, curr.end);
            }
        }
        return ans;
    }

    public void runTest(final List<List<Interval>> schedule, final List<Interval> expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { schedule });
        List<String> stringifyAnswer = expectedOutput.stream().map(Interval::toString).collect(Collectors.toList());

        for (Object answer : answers) {
            List<Interval> actualAnswer = (List<Interval>) answer;
            List<String> stringifyActualAnswer = actualAnswer.stream().map(Interval::toString).collect(Collectors.toList());
            assertThat(stringifyActualAnswer).isEqualTo(stringifyAnswer);
        }

        System.out.println("ok!");
    }

}
