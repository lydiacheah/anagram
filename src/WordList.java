import java.io.*;

public class WordList implements UsefulConstants {
	static Word[] Dictionary = new Word[MAXWORDS];
	static int totWords=0;

	static void ReadDict (String f) {
		FileInputStream fis;
		try {
			fis = new FileInputStream (f);
		}
		catch (FileNotFoundException fnfe) {
			e.println("Cannot open the file of words '" + f + "'");
			throw new RuntimeException();
		}
		e.println ("reading dictionary...");
		
		char buffer[] = new char[MAXWORDLEN];
		String s;
		int r =0;
		while (r!=EOF) {
			int i = 0;
			try {
				// read a word in from the word file
				while ( (r=fis.read()) != EOF ) {
					if ( r == '\n' ) break;
					buffer[i++] = (char) r;
				}
			} catch (IOException ioe) {
				e.println("Cannot read the file of words ");
				throw new RuntimeException();
			}
			
			s = new String(buffer,0,i);
			Dictionary[totWords] = new Word(s);
			totWords++;
		}
		
		e.println("main dictionary has " + totWords + " entries.");
	}
}
