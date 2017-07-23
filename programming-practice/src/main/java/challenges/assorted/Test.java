package challenges.assorted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
	
	public static class Result {
		int item, count;
		public Result (int item) { this.item = item; }
		@Override
		public String toString() {
			return "{" + item + "," + count + "}";
		}
	}

	public static class UserSession {
		int start, end;
		public UserSession (int start, int end) { this.start = start; this.end = end; }
		@Override
		public String toString() {
			return "{" + start + "," + end + "}";
		}
	}
	
	public List<Result> getOrder (List<UserSession> sessions) {
		Set<Integer> items = new HashSet<>();
		for (UserSession u : sessions) { items.add (u.start); items.add (u.end); }
		List<Result> ans = new ArrayList<>();
		for (int item : items) ans.add(new Result (item));
		Collections.sort (ans, (a, b) -> a.item - b.item);
		Collections.sort (sessions, (a, b) -> a.start == b.start ? b.end - a.end : a.start - b.start);
		for (UserSession u : sessions) {
			ans.get(u.start).count ++;
			if (u.end + 1 < ans.size()) ans.get(u.end + 1).count --;
		}
		int sum = 0;
		for (Result r : ans) {
			sum += r.count;
			r.count = sum;
		}
		return ans;
	}
	
	public static void main(String[] args) {
		Test t = new Test ();
		System.out.println(t.getOrder(Arrays.asList(new UserSession(0, 7), new UserSession(1, 2), new UserSession(1, 4), 
													new UserSession(4, 7), new UserSession(5, 8), new UserSession(2, 3))));
	}

}
