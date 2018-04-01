import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FindAnagram implements UsefulConstants {
	private static final int DEFAULT_LENGTH = 3;

	/**
	 * Main method takes in up to 3 arguments. 
	 * The first argument is the word on which the anagrams are based. 
	 * The second optional argument is the minimum length of the anagram. 
	 * The third optional argument is the file containing words from which to read. 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// no or too many arguments given
		if (args.length < 1 || args.length > 3) {
			e.println("1, 2 or 3 arguments expected"); 
			return;
		}

		int anagramLength = DEFAULT_LENGTH;

		// second argument is the minimum length of anagram 
		if (args.length >= 2) {
			try {
				anagramLength = Integer.parseInt(args[1]); 
			}
			catch (NumberFormatException nfe) {
				e.println("Second argument must be an integer"); 
				return;
			}
		}
		
		Anagram anagram = new Anagram(args[0], anagramLength);

		// WordList for the given filename
		WordList wordList = new WordList();
		wordList.readDict(args.length == 3 ? args[2] : "words.txt");

		anagram.findAnagrams(wordList);		
	}
}
