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
	public static final int MAXWORDS = 100000;
	public static final int MAXWORDLEN = 30;
	public static final int EOF = -1;
	
	// shorter alias for I/O streams
	public static final PrintStream o = System.out;
	public static final PrintStream e = System.err;
}
