package challenges.assorted;

import java.util.*;

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
//		System.out.println(t.getOrder(Arrays.asList(new UserSession(0, 7), new UserSession(1, 2), new UserSession(1, 4),
//													new UserSession(4, 7), new UserSession(5, 8), new UserSession(2, 3))));

//		System.out.print(t.reorder(5, Arrays.asList("a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo")));
		System.out.print(t.reorder(Arrays.asList("a1 9 2 3 1", "g0 act car a", "g0 act car", "g2 act car", "go1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo")));
	}

	public List<String> reorder(List<String> lines) {
		List<String> reorderedLines = new ArrayList<>();
		for (String l : lines) if (Character.isAlphabetic(l.split(" ")[1].charAt(0))) reorderedLines.add(l);

		Collections.sort(reorderedLines, (x, y) -> {
			int cval = x.substring(x.indexOf(" ")).compareTo(y.substring(y.indexOf(" ")));

			return (cval == 0) ? x.substring(0, x.indexOf(" ")).compareTo(y.substring(0, y.indexOf(" "))) : cval;
		});

		for (String l : lines) if (Character.isDigit(l.split(" ")[1].charAt(0))) reorderedLines.add(l);
		return reorderedLines;
	}

}
