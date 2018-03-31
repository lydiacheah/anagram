
public class Word  {
	int letterCount[] = new int[26];  // count of each letter in the word
	int numLetters;  // number of letters in the word
	String word;  // the word

	public Word(String s) { // construct an entry from a string
		int ch;
		word = s;
		numLetters = 0;
		s = s.toLowerCase();
		for (int i = 'a'; i <= 'z'; i++) letterCount[i-'a'] = 0;

		for (int i = s.length()-1; i >= 0; i--) {
			ch = s.charAt(i) - 'a';
			if (ch >= 0 && ch < 26) {
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
		
		return (word).compareTo(t.word);
	}
}

