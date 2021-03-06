import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Usage: java Anagram string [[min-len] wordfile] Java Anagram program, Peter
 * van der Linden Jan 7, 1996. Feel free to pass this program around, as long
 * as this header stays intact.
 */

public class Anagram extends Word implements UsefulConstants {
	// for testing
	private static BufferedWriter writer;
	// default minimum length of anagram is 3 letters
	private int minLength = 3; 
	// Word array for all anagram candidates
	private Word[] candidateArr;
	// total number of anagram candidates 
	private int numCandidates = 0; 

	/**
	 * Constructor extends Word superclass. 
	 * @param s
	 */
	public Anagram(String s, int length) {
		super(s);
		minLength = length;
	}

	public void findAnagrams(WordList wordList) throws IOException {
		// writer for testing
		writer = new BufferedWriter(new FileWriter("refactored.txt"));
		
		// find and print candidates
		getCandidates(this, wordList);
		printCandidate();

		// find and print anagrams
		o.println("Anagrams of " + stringRepresentation + ":");
		writer.append("Anagrams of " + stringRepresentation + ":");
		writer.newLine(); 
		getAnagrams(this);

		// end
		o.println("----" + stringRepresentation + "----");
		writer.append("----" + stringRepresentation + "----");
		writer.newLine(); 
		writer.close();
	}	

	/**
	 * Finds and prints all the anagrams of the given word
	 * @param word given word
	 * @throws IOException
	 */
	private void getAnagrams(Word word) throws IOException {
		// index of the candidate in Candidate array
		// that contains the least appeared alphabet
		int rootIndexEnd = sortCandidates(word);
		findAnagram(word, new String[50], 0, 0, rootIndexEnd); 
	}

	/**
	 * Finds all possible anagram candidates and initializes the candidate array.
	 * @param word given word
	 * @param wordList WordList that contains all the words to read
	 */
	private void getCandidates(Word word, WordList wordList) {
		int totalWords = wordList.getTotalWords();
		Word[] dictionary = wordList.getDict(); 
		candidateArr = new Word[MAX_WORDS];

		// go through each word in dictionary
		for (int i = 0; i < totalWords; i++) {
			Word currWord = dictionary[i];
			if (isACandidate(word, currWord)) {
				candidateArr[numCandidates++] = currWord;
			}
		}
		
		assert wellFormed();
	}

	/**
	 * Checks if the word from the dictionary is a candidate of the given word
	 * @param word given word
	 * @param currWord word from dictionary
	 * @return
	 */
	private boolean isACandidate(Word word, Word currWord) {
		return (currWord.numLetters >= minLength)
				&& (currWord.numLetters + minLength <= word.numLetters
				||  currWord.numLetters == word.numLetters)
				&& (hasFewerofEachLetter(word.letterCount, currWord.letterCount));
	}

	/**
	 * Checks if the dictionary word has fewer of each letter than the given word
	 * @param anagCount array containing the count of each letter in the given word
	 * @param entryCount array containing the count of each letter in the dictionary word entry
	 * @return true if the dictionary word entry has fewer of each letter than the given word, false if more
	 */
	private boolean hasFewerofEachLetter(int anagCount[], int entryCount[]) {
		// the total number of each letter in the word from the dictionary
		// must be less than the given word 
		for (int i = NUM_ALPHABETS - 1; i >= 0; i--)
			if (entryCount[i] > anagCount[i]) 
				return false;
		return true;
	}

	/**
	 * Prints all the candidates in the candidate array.
	 * @throws IOException
	 */
	private void printCandidate() throws IOException {
		o.println("Candidate words:");
		writer.append("Candidate words:");
		writer.newLine();

		// print each candidate out
		for (int i = 0; i < numCandidates; i++) {
			o.print(candidateArr[i].stringRepresentation + ", ");
			writer.append(candidateArr[i].stringRepresentation + ", ");
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

	/**
	 * Finds all the anagrams of a specific word. 
	 * @param word the given word
	 * @param wordArr the array of words
	 * @param level 
	 * @param startAt start index 
	 * @param endAt end index
	 * @throws IOException
	 */
	private void findAnagram(Word word, String[] wordArr, int level, int startAt, int endAt) throws IOException {
		for (int i = startAt; i < endAt; i++) {
			Word candidate = candidateArr[i];
			if (candidateHasEnoughCommonLetters(word, candidate)) {
				Word wordToPass = new Word("");
				wordArr[level] = candidate.stringRepresentation;

				if (hasSameLetterCounts(word, candidate, wordToPass)) {
					// An anagram is found
					for (int j = 0; j <= level; j++) {
						o.print(wordArr[j] + " ");
						writer.append(wordArr[j] + " ");
					}
					o.println();
					writer.newLine();
				} 
				else if (wordToPass.numLetters >= minLength) {
					findAnagram(wordToPass, wordArr, level + 1, i, numCandidates);
				}
			}
		}
		assert wellFormed();
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
	private boolean hasSameLetterCounts(Word word, Word candidate, Word wordToPass) {
		// number of letters in wordToPass will result in 0 if they have the same letter counts
		for (int j = NUM_ALPHABETS - 1; j >= 0; j--) {
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
		for (int j = NUM_ALPHABETS - 1; j >= 0 && enoughCommonLetters; j--) {
			if (word.letterCount[j] < candidate.letterCount[j]) {
				enoughCommonLetters = false;
			}
		}
		return enoughCommonLetters;
	}

	/**
	 * Sorts the candidate array. 
	 * @param word
	 * @return
	 */
	private int sortCandidates(Word word) {
		// contains the total number of each alphabet in all candidates
		int[] masterCount = new int[NUM_ALPHABETS];		
		countTotalLetters(masterCount);

		// keeps track of the smallest number of times an alphabet appeared
		// as well as its index
		int indexOfLeastCommonLetter = 0;
		int leastCommonCount = MAX_WORDS * 5;

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
	private int findCandidateIndex(int indexOfLeastCommonLetter) {
		int candidateIndex = 0;
		for (int i = 0; i < numCandidates; i++) {
			Word candidate = candidateArr[i];
			if (candidate.containsLetter(indexOfLeastCommonLetter)) { 
				candidateIndex = i;
				break;
			}
		}
		return candidateIndex;
	}

	/**
	 * Counts the total number of each letter in every candidate word.
	 * @param masterCount
	 */
	private void countTotalLetters(int[] masterCount) {
		// counting the alphabets
		for (int i = 0; i < numCandidates; i++) {
			Word candidate = candidateArr[i];
			for (int j = 0; j < masterCount.length; j++) {
				masterCount[j] += candidate.letterCount[j];
			}
		}
	}

	/**
	 * Standard quicksort algorithm.
	 * @param left
	 * @param right
	 * @param leastCommonIndex
	 */
	private void quickSort(int left, int right, int leastCommonIndex) {
		// standard quicksort from any algorithm book
		if (left >= right) return;
		swap(left, (left + right)/2);
		int last = left;
		for (int i = left + 1; i <= right; i++) {  /* partition */
			if (candidateArr[i].multiFieldCompare(candidateArr[left], leastCommonIndex) == -1) {
				swap(++last, i);
			}
		}

		swap(last, left);
		quickSort(left, last - 1, leastCommonIndex);
		quickSort(last + 1, right, leastCommonIndex);
		
		assert wellFormed();
	}

	/**
	 * Swap the positions of two words in the candidate array
	 * @param d1 index of word to swap
	 * @param d2 index of other word to swap
	 */
	private void swap(int d1, int d2) {
		Word tmp = candidateArr[d1];
		candidateArr[d1] = candidateArr[d2];
		candidateArr[d2] = tmp;
		
		assert wellFormed();
	}
	
	/**
	 * Return true if the letterCount array and candidateArr 
	 * satisfy the class invariants.
	 */
	@Override
	public boolean wellFormed() {
		super.wellFormed();
		if (candidateArr == null) {
			return false; 
		}
		if (candidateArr.length == 0) {
			return false;
		}
		if (candidateArr.length > MAX_WORDS) {
			return false;
		}
		for (int i = 0; i < numCandidates; i++) {
			if (candidateArr[i] == null) {
				return false;
			}
		}
		return true; 
	}
}