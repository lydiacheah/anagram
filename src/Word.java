/**
 * Describes a word. 
 * Keeps track of its length and the number of times each letter appears in it.
 * 
 * @author Lydia Cheah and Sofia Lis
 */
public class Word implements UsefulConstants {
	// count of each letter in the word
	protected int letterCount[] = new int[NUM_ALPHABETS];
	// number of letters in the word
	protected int numLetters = 0; 
	// the word
	protected String stringRepresentation; 

	/**
	 * Construct a Word from a String.
	 * @param s String representation of the Word.
	 */
	public Word(String s) { 
		stringRepresentation = s.toLowerCase(); 
		// Fills the letterCount array and counts the total number of letters
		for (int i = s.length() - 1; i >= 0; i--) {
			int ch = s.charAt(i) - 'a';
			if (ch >= 0 && ch < NUM_ALPHABETS) {
				numLetters++;
				letterCount[ch]++;
			}
		}
	}

	/**
	 * Checks whether the word contains the specified letter
	 * @param j the 
	 * @return true if the word contains the letter
	 */
	public boolean containsLetter(int letter) { 
		return letterCount[letter] != 0;
	}

	/**
	 * 
	 * @param t
	 * @param leastCommonIndex
	 * @return
	 */
	public int multiFieldCompare(Word t, int leastCommonIndex) { // ========================= bracket, comments? 
		if ((containsLetter(leastCommonIndex)) && !(t.containsLetter(leastCommonIndex))) {
			return 1;
		} else if (!(containsLetter(leastCommonIndex)) && (t.containsLetter(leastCommonIndex))) {
			return -1;
		} else if (t.numLetters != numLetters) {
			return (t.numLetters - numLetters);
		}
		return (stringRepresentation).compareTo(t.stringRepresentation);
	}
}

