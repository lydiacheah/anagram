
public class Word implements UsefulConstants {
	// count of each letter in the word
	protected int letterCount[] = new int[NUM_ALPHABETS];
	// number of letters in the word
	protected int numLetters = 0; 
	// the string representation of the word
	protected String stringRep; 

	/**
	 * Construct a Word from a String.
	 * @param s String representation of the Word.
	 */
	public Word(String s) { 
		stringRep = s;
		s = s.toLowerCase();
//		for (int i = 'a'; i <= 'z'; i++) {
//			letterCount[i-'a'] = 0;
//		}
		for (int i = s.length() - 1; i >= 0; i--) {
			int ch = s.charAt(i) - 'a';
			if (ch >= 0 && ch < NUM_ALPHABETS) {
				numLetters++;
				letterCount[ch]++;
			}
		}
	}

	public boolean containsLetter(int j){
		return letterCount[j] != 0;
	}

	public int multiFieldCompare(Word t, int leastCommonIndex) {
		if ((containsLetter(leastCommonIndex)) && !(t.containsLetter(leastCommonIndex))) {
			return 1;
		} else if (!(containsLetter(leastCommonIndex)) && (t.containsLetter(leastCommonIndex))) {
			return -1;
		} else if (t.numLetters != numLetters) {
			return (t.numLetters - numLetters);
		}
		return (stringRep).compareTo(t.stringRep);
	}
	
	/**
	 * Return true if the letterCount array satisfies the class invariants
	 */
	public boolean wellFormed() {
		if (letterCount == null) {
			return false; 
		}
		if (letterCount.length == 0) {
			return false;
		}
		if (letterCount.length > NUM_ALPHABETS) {
			return false;
		}
		return true;
	}
}

