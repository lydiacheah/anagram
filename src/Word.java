
public class Word  {
	int count[] = new int[26];  // count of each letter in the word
	int total;  // number of letters in the word
	String aword;  // the word

	public Word(String s) { // construct an entry from a string
		int ch;
		aword = s;
		total = 0;
		s = s.toLowerCase();
		for (int i = 'a'; i <= 'z'; i++) count[i-'a'] = 0;

		for (int i = s.length()-1; i >= 0; i--) {
			ch = s.charAt(i) - 'a';
			if (ch >= 0 && ch < 26) {
				total++;
				count[ch]++;
			}
		}
	}

	public boolean containsLetter(int j){
		return count[j] != 0;
	}

	public int MultiFieldCompare(Word t, int LeastCommonIndex)
	{
		if ( (containsLetter(LeastCommonIndex) ) &&  !(t.containsLetter(LeastCommonIndex)) )
			return 1;
		
		if ( !(containsLetter(LeastCommonIndex) ) &&  (t.containsLetter(LeastCommonIndex)) )
			return -1;
		
		if ( t.total != total )
			return (t.total - total);
		
		return (aword).compareTo(t.aword);
	}
}

