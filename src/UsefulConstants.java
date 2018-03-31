/**
 * 
 */

import java.io.*;

/**
 * @author Peter van der Linden
 * @version Jan 7, 1996
 *
 */
public interface UsefulConstants {
	public static final int NUM_ALPHABETS = 26; 
	public static final int MAX_WORDS = 100000;
	public static final int MAX_WORD_LENGTH = 30;
	public static final int EOF = -1;
	
	// shorter alias for I/O streams
	public static final PrintStream o = System.out;
	public static final PrintStream e = System.err;
}
