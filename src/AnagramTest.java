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
	public static void main(String[] args) throws IOException {
		// Arguments for the tests
		String[] argumentsDonkey = new String[] {"donkey"};
		String[] argumentsComputer = new String[] {"computer"};
		String[] argumentsComputerFour = new String[] {"computer", "4"};

		// Paths to the test files
		String donkeyPath = "testDonkeySample.txt";
		String computerPath = "testComputerSample.txt";
		String computerFourPath = "testComputerFourSample.txt";
		String refactoredPath = "refactored.txt";

		// Two byte arrays to compare the files
		byte[] originalFile, testFile;

		Anagram.main(argumentsDonkey);				
		originalFile = Files.readAllBytes(Paths.get(donkeyPath));
		testFile = Files.readAllBytes(Paths.get(refactoredPath));
		if (Arrays.equals(originalFile, testFile)) 
			System.out.println("Donkey works!");
		else 
			System.out.println("Donkey went wrong!");

		Anagram.main(argumentsComputer);
		originalFile = Files.readAllBytes(Paths.get(computerPath));
		testFile = Files.readAllBytes(Paths.get(refactoredPath));
		if (Arrays.equals(originalFile, testFile)) 
			System.out.println("Computer works!");
		else 
			System.out.println("Computer went wrong!");
		
		Anagram.main(argumentsComputerFour);
		originalFile = Files.readAllBytes(Paths.get(computerFourPath));
		testFile = Files.readAllBytes(Paths.get(refactoredPath));
		if (Arrays.equals(originalFile, testFile)) 
			System.out.println("Computer with minLength 4 works!");
		else 
			System.out.println("Computer with minLength 4 went wrong!");
	}
}
