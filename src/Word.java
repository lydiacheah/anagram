
public class Word implements UsefulConstants {
	// count of each letter in the word
	protected int letterCount[] = new int[NUM_ALPHABETS];
	// number of letters in the word
	protected int numLetters = 0; 
	// the word
	protected String stringRep; 

	/**
	 * Construct a Word from a String.
	 * @param s String representation of the Word.
	 */
	public Word(String s) { 
		stringRep = s;
		s = s.toLowerCase();
		for (int i = 'a'; i <= 'z'; i++) {
			letterCount[i-'a'] = 0;
		}
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

	public int MultiFieldCompare(Word t, int LeastCommonIndex)
	{
		if ( (containsLetter(LeastCommonIndex) ) &&  !(t.containsLetter(LeastCommonIndex)) )
			return 1;
		
		if ( !(containsLetter(LeastCommonIndex) ) &&  (t.containsLetter(LeastCommonIndex)) )
			return -1;
		
		if ( t.numLetters != numLetters )
			return (t.numLetters - numLetters);
		
		return (stringRep).compareTo(t.stringRep);
	}
}

