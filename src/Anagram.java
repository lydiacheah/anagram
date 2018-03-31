import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Usage: java Anagram string [[min-len] wordfile] Java Anagram program, Peter
 * van der Linden Jan 7, 1996. Feel free to pass this program around, as long
 * as this header stays intact.
 */

public class Anagram extends WordList implements UsefulConstants {
	// for testing
	private static BufferedWriter writer;
	// Word array for all anagram candidates
	private static Word[] candidateArr = new Word[MAXWORDS];
	// total number of anagram candidates 
	private static int numCandidates = 0; 
	// default minimum length of anagram is 3 letters
	private static int anagMinLength = 3; 
	
	public static void main(String[] argv) throws IOException {
		// writer for testing
		writer = new BufferedWriter(new FileWriter("refactored.txt"));
		// no arguments given
		if (argv.length < 1 || argv.length > 3) {
			e.println("Usage: java anagram  string-to-anagram " + "[min-len [word-file]]");
			return;
		}
		
		// second argument is the minimum length of anagram 
		if (argv.length >= 2) {
			anagMinLength = Integer.parseInt(argv[1]);
		}

		// word filename is optional 3rd argument
		readDict(argv.length == 3 ? argv[2] : "words.txt");
		doAnagrams(argv[0]);		
		writer.close();
	}

	private static void doAnagrams(String anag) throws IOException {
		Word myAnagram = new Word(anag);

		// printing candidates
		getCandidates(myAnagram);
		printCandidate();

		// variable is the index of the candidate in Candidate 
		// that contains the least appeared alphabet
		int rootIndexEnd = sortCandidates(myAnagram);

		// printing anagrams
		o.println("Anagrams of " + anag + ":");
		writer.append("Anagrams of " + anag + ":");
		writer.newLine();
		findAnagram(myAnagram, new String[50],  0, 0, rootIndexEnd);

		// end
		o.println("----" + anag + "----");
		writer.append("----" + anag + "----");
		writer.newLine();
	}

	private static void getCandidates(Word word) {
		int totalWords = getTotalWords();
		Word[] dictionary = getDict(); 

		// go through each word in dictionary
		for (int i = 0; i < totalWords; i++) {
			Word currWord = dictionary[i];
			if ((currWord.numLetters >= anagMinLength)
					&& (currWord.numLetters + anagMinLength <= word.numLetters
					||  currWord.numLetters == word.numLetters)
					&& (hasFewerofEachLetter(word.letterCount, currWord.letterCount)))
				candidateArr[numCandidates++]= currWord;
		}

	}

	private static boolean hasFewerofEachLetter(int anagCount[], int entryCount[]) {
		// compares the total number of each letter in the entered word and the word from the dictionary
		// the total number of each letter in the word from the dictionary
		// must be less than the given word 
		for (int i = 25; i >= 0; i--)
			if (entryCount[i] > anagCount[i]) return false;
		return true;
	}

	private static void printCandidate() throws IOException {
		o.println("Candidate words:");
		writer.append("Candidate words:");
		writer.newLine();
		
		// print each candidate out
		for (int i = 0; i < numCandidates; i++) {
			o.print(candidateArr[i].word + ", ");
			writer.append(candidateArr[i].word + ", ");
			// only print 4 candidates on one line 
			if (i % 4 == 3) {
				o.print("\n");
				writer.newLine();
			} else {
				o.print(" ");
				writer.append(" ");
			}
		}
		
		o.println();
		writer.newLine();
		o.println();
		writer.newLine();
	}

	private static void findAnagram(Word word, String[] wordArr, int level, int startAt, int endAt) throws IOException {
		for (int i = startAt; i < endAt; i++) {
			Word candidate = candidateArr[i];
			if (candidateHasEnoughCommonLetters(word, candidate)) {
				Word wordToPass = new Word("");
				wordArr[level] = candidate.word;
				
				if (hasSameLetterCounts(word, candidate, wordToPass)) {
					/* Found a series of words! */
					for (int j = 0; j <= level; j++) {
						o.print(wordArr[j] + " ");
						writer.append(wordArr[j] + " ");
					}
					o.println();
					writer.newLine();
				} else if (wordToPass.numLetters >= anagMinLength){
					findAnagram(wordToPass, wordArr, level + 1, i, numCandidates);
				}
			}
		}
	}

	/**
	 * Checks if the given word and candidate have the exact same count
	 * for each alphabet. 
	 * 
	 * @param word given word 
	 * @param candidate candidate for anagram
	 * @param wordToPass 
	 * @return true if they have the same letter count, false if not
	 */
	private static boolean hasSameLetterCounts(Word word, Word candidate,
			Word wordToPass) {
		// number of letters in wordToPass will result in 0 if they have the same letter counts
		for (int j = 25; j >= 0; j--) {
			wordToPass.letterCount[j] = (byte) (word.letterCount[j] - candidate.letterCount[j]);
			if (wordToPass.letterCount[j] != 0) {
				wordToPass.numLetters += wordToPass.letterCount[j];
			}
		}
		return wordToPass.numLetters == 0;
	}

	/**
	 * Checks if a candidate has no more than the same count of each alphabet 
	 * with the given word. 
	 * @param word given word 
	 * @param candidate candidate for anagram
	 * @return true if the candidate has enough letters in common with the given word, false if it has more
	 */
	private static boolean candidateHasEnoughCommonLetters(Word word, Word candidate) {
		boolean enoughCommonLetters = true;
		for (int j = 25; j >= 0 && enoughCommonLetters; j--) {
			if (word.letterCount[j] < candidate.letterCount[j]) {
				enoughCommonLetters = false;
			}
		}
		return enoughCommonLetters;
	}

	private static int sortCandidates(Word word) {
		// contains the total number of each alphabet in all candidates
		int[] masterCount = new int[26];		
		countTotalLetters(masterCount);

		// keeps track of the smallest number of times an alphabet appeared
		// as well as its index
		int indexOfLeastCommonLetter = 0;
		int leastCommonCount = MAXWORDS * 5;
		for (int j = 0; j < masterCount.length; j++) {
			if (masterCount[j] != 0 
					&& masterCount[j] < leastCommonCount
					&& word.containsLetter(j)) {
				leastCommonCount = masterCount[j];
				indexOfLeastCommonLetter = j;
			}
		}

		// sorting Candidate array
		quickSort(0, numCandidates - 1, indexOfLeastCommonLetter);

		return findCandidateIndex(indexOfLeastCommonLetter);
	}

	/**
	 * Finds the new index of the sorted candidate array 
	 * that has the letter that appears the least.
	 * @param indexOfLeastCommonLetter
	 * @return
	 */
	private static int findCandidateIndex(int indexOfLeastCommonLetter) {
		int candidateIndex = 0;
		for (int i = 0; i < numCandidates; i++) {
			if (candidateArr[i].containsLetter(indexOfLeastCommonLetter)) { 
				candidateIndex = i;
				break;
			}
		}
		return candidateIndex;
	}

	/**
	 * @param masterCount
	 */
	private static void countTotalLetters(int[] masterCount) {
		// counting the alphabets
		for (int i = 0; i < numCandidates; i++) {
			for (int j = 0; j < masterCount.length; j++) {
				masterCount[j] += candidateArr[i].letterCount[j];
			}
		}
	}

	private static void quickSort(int left, int right, int LeastCommonIndex) {
		// standard quicksort from any algorithm book
		int i, last;
		if (left >= right) return;
		swap(left, (left + right)/2);
		last = left;
		for (i = left + 1; i <= right; i++)  /* partition */
			if (candidateArr[i].MultiFieldCompare (candidateArr[left], LeastCommonIndex) == -1)
				swap(++last, i);

		swap(last, left);
		quickSort(left, last-1, LeastCommonIndex);
		quickSort(last+1,right, LeastCommonIndex);
	}

	private static void swap(int d1, int d2) {
		Word tmp = candidateArr[d1];
		candidateArr[d1] = candidateArr[d2];
		candidateArr[d2] = tmp;
	}
}