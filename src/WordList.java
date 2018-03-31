import java.io.*;

public class WordList implements UsefulConstants {
	private static Word[] dictionary = new Word[MAXWORDS];
	private static int totalWords = 0;

	public static void readDict(String f) {
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
	private static void addToDict(FileInputStream fis) {
		// each word must be less than 30 characters
		char buffer[] = new char[MAXWORDLEN];
		String s;
		int r = 0;
		while (r != EOF) {
			int i = 0;
			try {
				// read one word in from the word file
				while ((r = fis.read()) != EOF ) {
					if (r == '\n') break;
					buffer[i++] = (char) r;
				}
			} catch (IOException ioe) {
				e.println("Cannot read the file of words ");
				throw new RuntimeException();
			}
			// add the word to Dictionary array
			s = new String(buffer,0,i);
			dictionary[totalWords] = new Word(s);
			totalWords++; // increase the total count of words
		}
	}
	
	public static Word[] getDict() {
		return dictionary;
	}
	
	public static int getTotalWords() {
		return totalWords;
	}
}
