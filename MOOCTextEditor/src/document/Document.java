package document;

/** 
 * A class that represents a text document
 * @author UC San Diego Intermediate Programming MOOC team
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

	private String text;
	private static double C1 = 206.835;
	private static double C2 = 1.015;
	private static double C3 = 84.6;
	
	/** Create a new document from the given text.
	 * Because this class is abstract, this is used only from subclasses.
	 * @param text The text of the document.
	 */
	protected Document(String text)
	{
		this.text = text;
	}
	
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	/** This is a helper function that returns the number of syllables
	 * in a word.  You should write this and use it in your 
	 * BasicDocument class.
	 * 
	 * You will probably NOT need to add a countWords or a countSentences 
	 * method here.  The reason we put countSyllables here because we'll 
	 * use it again next week when we implement the EfficientDocument class.
	 * 
	 * For reasons of efficiency you should not create Matcher or Pattern 
	 * objects inside this method. Just use a loop to loop through the 
	 * characters in the string and write your own logic for counting 
	 * syllables.
	 * 
	 * @param word  The word to count the syllables in
	 * @return The number of syllables in the given word, according to 
	 * this rule: Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       You should consider y a vowel.
	 */
	protected int countSyllables(String word)
	{
		// TODO: Implement this method so that you can call it from the 
	    // getNumSyllables method in BasicDocument (module 2) and 
	    // EfficientDocument (module 3).
		if (word == null || word.isEmpty()) {
			return 0;
		}
		
		int syllCount = 0;
		String w = word.toLowerCase();
		boolean isPrevSyllable = false;
		for (int i = 0; i < w.length(); i++) {
			char c = w.charAt(i);
			
			if (isSyllable(c)) {
				// Skip count if the previous char is a syllable.
				if (isPrevSyllable) {
					continue;
				}
				
				// Last char is 'e'?
				if (i == w.length() - 1 && syllCount > 0 && c == 'e') {
					continue;
				}
				syllCount++;
				isPrevSyllable = true;
			}
			else {
				isPrevSyllable = false;
			}
		}
	    return syllCount;
	}
	
	// This is a helper function that returns the number of syllables
	// in a word.  You should write this and use it in your 
	// BasicDocument class.
	private static int countSyllablesFromCoursera(String word)
	{
	    //System.out.print("Counting syllables in " + word + "...");
		int numSyllables = 0;
		boolean newSyllable = true;
		String vowels = "aeiouy";
		char[] cArray = word.toCharArray();
		for (int i = 0; i < cArray.length; i++)
		{
		    if (i == cArray.length-1 && Character.toLowerCase(cArray[i]) == 'e' 
		    		&& newSyllable && numSyllables > 0) {
                numSyllables--;
            }
		    if (newSyllable && vowels.indexOf(Character.toLowerCase(cArray[i])) >= 0) {
				newSyllable = false;
				numSyllables++;
			}
			else if (vowels.indexOf(Character.toLowerCase(cArray[i])) < 0) {
				newSyllable = true;
			}
		}
		//System.out.println( "found " + numSyllables);
		return numSyllables;
	}
	
	/**
	 * Returns {@code true} if character {@code c} is a syllable.
	 * @param c - The character to check.
	 * @return {@code true} if the character is a syllable otherwise returns {@code false}.
	 */
	private static boolean isSyllable(char c) {
		if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y') {
			return true;
		}
		return false;
	}
	
	/** A method for testing
	 * 
	 * @param doc The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed.  False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences)
	{
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound 
					+ ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound 
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound 
					+ ", expected " + sentences);
			passed = false;
		}
		
		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}
	
	
	/** Return the number of words in this document */
	public abstract int getNumWords();
	
	/** Return the number of sentences in this document */
	public abstract int getNumSentences();
	
	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();
	
	/** Return the entire text of this document */
	public String getText()
	{
		return this.text;
	}
	
	/** return the Flesch readability score of this document */
	public double getFleschScore()
	{
		double numWords = (double) getNumWords();
		double numSyll = (double) getNumSyllables();
		double fleschScore = C1 - (C2 * ((numWords) / getNumSentences())) - (C3 * (numSyll / numWords));
	    return fleschScore;
	}
	
	
	
}
