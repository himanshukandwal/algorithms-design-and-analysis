package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

public class MakeSmallestNumber extends AbstractCustomTestRunner {
	
	private static MakeSmallestNumber _instance = new MakeSmallestNumber();
	
	private MakeSmallestNumber() {}
	
	// method : remove that point from the upward hill from where the down-slope starts.
	public static String _smallestNumber(String coordinates, int remove) {
		if (coordinates.length() <= remove)
			return "0";
		
		StringBuilder sb = new StringBuilder(coordinates);
		
		int deletions = 0;
		while (deletions < remove) {
			int idx = 0;
			for (; idx < sb.length() -1; idx ++) 
				if (sb.charAt(idx) > sb.charAt(idx + 1)) 
					break;
			
			sb.delete(idx, idx + 1);
			deletions ++;
		}
		
		return sb.toString();
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("746209249", 5, "0249");
		_instance.runTest("64738929", 0, "64738929");
		_instance.runTest("64738929", 100, "0");
		_instance.runTest("83597658", 2, "357658");
	}

	public void runTest(final String coordinates, final int remove, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { coordinates, remove });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
