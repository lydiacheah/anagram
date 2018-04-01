import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**• OutOfBounds fix
• a faster way to assert the tests
• minor type errors
 * Test program for original anagram and refactored anagram.
 * 
 * Authors: Lydia Cheah and Sofia Lis 
 */
public class AnagramTest {
	/**
	 * The main method to test the anagram finder
	 * 
	 * @param args the arguments
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// Arguments to test
		String[] argumentsDonkey = new String[] {"donkey"};
		String[] argumentsComputer = new String[] {"computer"};
		String[] argumentsComputerFour = new String[] {"computer", "4"};

		// Paths to the test files
		String donkeyPath = "testDonkeySample.txt";
		String computerPath = "testComputerSample.txt";
		String computerFourPath = "testComputerFourSample.txt";
		String refactoredPath = "refactored.txt";

		if (testWord(argumentsDonkey, donkeyPath, refactoredPath)
				&&	testWord(argumentsComputer, computerPath, refactoredPath)
				&& testWord(argumentsComputerFour, computerFourPath, refactoredPath)) 
			System.out.println("\nTests pass! Everything works!");
	}

	/**
	 * Tests if the program works correctly for the given arguments
	 * 
	 * @param args the original arguments
	 * @param originalFile the file to compare with
	 * @param testFile the test file
	 * @return true if the test was passed
	 * @throws IOException
	 */
	private static boolean testWord(String[] args, String originalFile, String testFile) throws IOException {
		// Two byte arrays to compare the files
		byte[] original, test;

		FindAnagram.main(args);
		original = Files.readAllBytes(Paths.get(originalFile));
		test = Files.readAllBytes(Paths.get(testFile));

		boolean hasWorked = Arrays.equals(original, test);

		if (hasWorked) 
			System.out.println(originalFile + " works!");
		else 
			System.out.println(originalFile + " does not work!");

		return hasWorked;
	}
}
