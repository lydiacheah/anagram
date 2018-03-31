import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 * Test program for original anagram and refactored anagram.
 * 
 * Authors: Lydia Cheah and Sofia Lis 
 */
public class AnagramTest {
	public static void main(String[] args) throws IOException {
		File originalFile1 = new File("testDonkeySample.txt");
		File originalFile2 = new File("testComputerSample.txt");
				
		String[] argumentsDonkey = new String[] { "donkey" };
		String[] argumentsComputer = new String[] { "computer" };
		
		boolean firstTestPass = false; 
		boolean secondTestPass = false;
		
		Anagram.main(argumentsDonkey);
		File refactoredFile1 = new File("refactored.txt");
		if (FileUtils.contentEquals(originalFile1, refactoredFile1)) {
			System.out.println("First test passed!");
			System.out.println();
			firstTestPass = true;
		}

		Anagram.main(argumentsComputer);
		File refactoredFile2 = new File("refactored.txt");
		if (FileUtils.contentEquals(originalFile2, refactoredFile2)) {
			System.out.println("Second test passed!");
			System.out.println();
			secondTestPass = true;
		}
		
		if (firstTestPass && secondTestPass) {
			System.out.println("Tests pass! Everything works!");
		}
	}
}
