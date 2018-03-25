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
		
		File refactoredFile1 = new File("refactoredDonkey.txt");
		File refactoredFile2 = new File("refactoredComputer.txt");
		
		if (FileUtils.contentEquals(originalFile1, refactoredFile1)) {
			
		}
		
		if (FileUtils.contentEquals(originalFile2, refactoredFile2)) {
			
		}
		
	}
}
