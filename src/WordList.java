import java.io.*;

/**
 * 
 * 
 * @author Lydia Cheah and Sofia Lis
 *
 */
public class WordList implements UsefulConstants {
	// The dictionary for the current anagram
	private Word[] dictionary = new Word[MAX_WORDS];
	// Total number of words in the list
	private int totalWords = 0;

	/**
	 * Initializes the dictionary from a file name
	 * @param f the name of the file containing the dictionary
	 */
	public void readDict(String f) { 
		FileInputStream fis;
		// read file
		try {
			fis = new FileInputStream (f);
		}
		catch (FileNotFoundException fnfe) {
			e.println("Cannot open the file of words '" + f + "'");
			throw new RuntimeException();
		}
		e.println ("reading dictionary...");
		
		addToDict(fis);
		
		e.println("main dictionary has " + totalWords + " entries.");
	}

	/**
	 * Adds each word to the dictionary array.
	 * @param fis file to read from
	 */
	private void addToDict(FileInputStream fis) {
		// each word must be less than 30 characters
		char buffer[] = new char[MAX_WORD_LENGTH];
		String word;
		int character = 0;
		while (character != EOF) {
			int letterCount = 0;
			try {
				// read one word in from the word file
				while ((character = fis.read()) != EOF) {
					if (character == '\n') break;
					buffer[letterCount++] = (char) character;
				}
			} catch (IOException ioe) {
				e.println("Cannot read the file of words ");
				throw new RuntimeException();
			}
			// add the word to Dictionary array
			word = new String(buffer, 0, letterCount);
			dictionary[totalWords] = new Word(word);
			totalWords++; // increase the total count of words
		}
	}
	
	/**
	 * Returns the dictionary
	 * @return word[]
	 */
	public Word[] getDict() { 
		return dictionary;
	}
	
	/**
	 * Returns the total number of words in the dictionary
	 * @return totalWords
	 */
	public int getTotalWords() { 
		return totalWords;
	}
}
