/*
 * Usage: java Anagram string [[min-len] wordfile] Java Anagram program, Peter
 * van der Linden Jan 7, 1996. Feel free to pass this program around, as long
 * as this header stays intact.
 */

public class anagram extends WordList implements UsefulConstants {
	static Word[] Candidate = new Word[MAXWORDS];
	static int totCandidates=0,
			   MinimumLength = 3;
	
	public static void main(String[] argv) {
		if (argv.length < 1 || argv.length > 3) {
			e.println("Usage: java anagram  string-to-anagram " + "[min-len [word-file]]");
			return;
		}
		
		if (argv.length >= 2)
			MinimumLength = Integer.parseInt(argv[1]);
		
		// word filename is optional 3rd argument
		ReadDict( argv.length==3? argv[2] : "words.txt" );
		DoAnagrams(argv[0]);
	}
	
	static void DoAnagrams(String anag)
	{
		Word myAnagram = new Word(anag);

		
		getCandidates(myAnagram);
		PrintCandidate();
		
		int RootIndexEnd = sortCandidates(myAnagram);
		
		o.println("Anagrams of " + anag + ":");
		FindAnagram(myAnagram, new String[50],  0, 0, RootIndexEnd);
		
		o.println("----" + anag + "----");
	}

	static void getCandidates(Word d) {
		for (int i = totCandidates = 0; i < totWords; i++)
			if (   (    Dictionary[i].total >= MinimumLength   )
				&& (    Dictionary[i].total + MinimumLength <= d.total
					||  Dictionary[i].total == d.total)
				&& ( fewerOfEachLetter(d.count, Dictionary[i].count) )  )
				Candidate[totCandidates++]=Dictionary[i];
		
	}
	
	static boolean fewerOfEachLetter(int anagCount[], int entryCount[])
	{
		for (int i = 25; i >=0; i--)
			if (entryCount[i] > anagCount[i]) return false;
		return true;
	}
	
	static void PrintCandidate()
	{
		o.println("Candiate words:");
		for (int i=0; i < totCandidates; i++)
			o.print( Candidate[i].aword + ", " + ((i%4 ==3) ?"\n":" " ) );
		o.println("");
		o.println();
	}

	static void FindAnagram(
		Word d,
		String WordArray[],
		int Level, int StartAt, int EndAt) 
	{
		int i, j;
		boolean enoughCommonLetters;
		Word WordToPass = new Word("");
		
		for (i = StartAt; i < EndAt; i++) {
			enoughCommonLetters = true;
			for (j = 25; j >= 0 && enoughCommonLetters; j--)
				if (d.count[j] < Candidate[i].count[j])
					enoughCommonLetters = false;
			
			if (enoughCommonLetters) {
				WordArray[Level] = Candidate[i].aword;
				WordToPass.total = 0;
				for (j = 25; j >= 0; j--) {
					WordToPass.count[j] = (byte) (d.count[j] - Candidate[i].count[j] );
					if ( WordToPass.count[j] != 0 ) {
						WordToPass.total += WordToPass.count[j];
					}
				}
				if (WordToPass.total == 0) {
					/* Found a series of words! */
					for (j = 0; j <= Level; j++)
						o.print(WordArray[j] + " ");
					o.println();
				} else if (WordToPass.total < MinimumLength) {
					; /* Don't call again */
				} else {
					FindAnagram(WordToPass, WordArray, Level+1,i, totCandidates);
				}
			}
		}
	}

	static int sortCandidates(Word d)
	{
		int[] MasterCount=new int[26];
		int LeastCommonIndex=0, LeastCommonCount;
		int i, j;
		
		for (j = 25; j >= 0; j--) MasterCount[j] = 0;
		for (i = totCandidates-1; i >=0; i--)
			for (j = 25; j >=0; j--)
				MasterCount[j] += Candidate[i].count[j];
		
		LeastCommonCount = MAXWORDS * 5;
		for (j = 25; j >= 0; j--)
			if (    MasterCount[j] != 0
				 && MasterCount[j] < LeastCommonCount
				 && d.containsLetter(j)  ) {
				LeastCommonCount = MasterCount[j];
				LeastCommonIndex = j;
			}
		
		quickSort(0, totCandidates-1, LeastCommonIndex );
		
		for (i = 0; i < totCandidates; i++)
			if (Candidate[i].containsLetter(LeastCommonIndex))
				break;
		
		return i;
	}

	static void quickSort(int left, int right, int LeastCommonIndex)
	{
		// standard quicksort from any algorithm book
		int i, last;
		if (left >= right) return;
		swap(left, (left+right)/2);
		last = left;
		for (i=left+1; i <=right; i++)  /* partition */
			if (Candidate[i].MultiFieldCompare ( Candidate[left], LeastCommonIndex ) ==  -1 )
				swap( ++last, i);
		
		swap(last, left);
		quickSort(left, last-1, LeastCommonIndex);
		quickSort(last+1,right, LeastCommonIndex);
	}
	
	static void swap(int d1, int d2) {
		Word tmp = Candidate[d1];
		Candidate[d1] = Candidate[d2];
		Candidate[d2] = tmp;
	}
}