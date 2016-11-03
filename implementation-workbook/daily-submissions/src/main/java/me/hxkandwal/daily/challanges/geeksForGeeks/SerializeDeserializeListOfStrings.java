package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Serialize a list of strings into a single string and Deserialize it back to original list of strings
 * 
 * If you are given a list of strings say {"a","","bc,","d"}. Write code for a function to Serialize the list to single string 
 * and for a function to deserialize that string back to original list of String {"a","","bc,","d"}. 
 * The list may have empty strings.
 * 
 * @author Hxkandwal
 *
 */
public class SerializeDeserializeListOfStrings extends AbstractCustomTestRunner {
	
	private static SerializeDeserializeListOfStrings _instance = new SerializeDeserializeListOfStrings();
	
	private SerializeDeserializeListOfStrings() {}

	public static String _serialize(String[] input) throws IOException {	
		File storageFile = File.createTempFile("SerializeDeserializeListOfStrings", "-testrun.txt");
		
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(storageFile));
		for (String inputString : input) {
			bufferedWriter.append(inputString).append("\n");
		}
		bufferedWriter.close();
		
		return storageFile.getAbsolutePath();
	}
	
	private String[] deserializeString(String input) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(input)));
		List<String> originalContent = new ArrayList<>();
		String line = null;
		
		while ((line = bufferedReader.readLine()) != null) 
			originalContent.add(line);
		
		bufferedReader.close();
		return originalContent.toArray(new String[0]);
	}

	// driver method
	public static void main(String[] args) throws IOException {
		_instance.runTest(new String[] { "a", "b" });
	}

	public void runTest(final String[] input) throws IOException {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((String[]) deserializeString((String) answer)).isEqualTo(input);
		
		System.out.println("ok!");
	}
	
}
