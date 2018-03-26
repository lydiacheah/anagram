
public class Word  {
	private int count[] = new int[UsefulConstants.ALPHABETLENGTH];  // count of each letter in the word
	private int total;  // number of letters in the word
	private String word;  // the word

	public Word(String s) { // construct an entry from a string
		int ch;
		word = s;
		total = 0;
		s = s.toLowerCase();
		for (int i = 'a'; i <= 'z'; i++) count[i-'a'] = 0;

		for (int i = s.length() - 1; i >= 0; i--) {
			ch = s.charAt(i) - 'a';
			if (ch >= 0 && ch < 26) {
				total++;
				count[ch]++;
			}
		}
	}

	public boolean containsLetter(int j) {
		return count[j] != 0;
	}

	public int multiFieldCompare(Word t, int LeastCommonIndex) {
		if ((containsLetter(LeastCommonIndex)) && !(t.containsLetter(LeastCommonIndex)))
			return 1;
		
		if (!(containsLetter(LeastCommonIndex)) && (t.containsLetter(LeastCommonIndex)))
			return -1;
		
		if (t.total != total)
			return (t.total - total);
		
		return (word).compareTo(t.word);
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public String getWord() {
		return word;
	}
	
	public int getLetterCount(int letter) {
		return count[letter];
	}
	
	public void setLetterCount(int letter, int count) {
		this.count[letter] = count;
	}
	
	public int[] getCount() {
		return count;
	}
}

