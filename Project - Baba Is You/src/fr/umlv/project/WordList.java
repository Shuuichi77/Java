package fr.umlv.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import fr.umlv.zen5.ApplicationContext;

public class WordList {
	private final int maxLine;
	private final int maxColumn;
	private List<Word> wordList = new ArrayList<>();
	
	/* Constructor */
	public WordList(int maxLine, int maxColumn) {
		if (maxLine < 0) 
			throw new IllegalArgumentException("maxLine < 0");
		if (maxColumn < 0) 
			throw new IllegalArgumentException("maxColumn < 0");
		
		this.maxLine = maxLine;
		this.maxColumn = maxColumn;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (Word word : wordList) {
			builder.append(word + " ");
		}
		
		return builder.append("\n").toString();
	}
	
	/**
	 * Return an unmodifiableList of wordList (to perform an iteration on the list)
	 * @return An unmodifiableList version of wordList 
	 */
	public Iterable<Word> wordList() {
	    return Collections.unmodifiableList(wordList);
	}
	
	/**
	 * Add a word in the wordList
	 * @param noun The word we are adding in the wordList
	 */
	public void add(Word word) {
		Objects.requireNonNull(word);
		if (word.line() >= maxLine) 
			throw new IllegalArgumentException("noun line > maxLine");
		if (word.column() >= maxColumn) 
			throw new IllegalArgumentException("noun column > maxColumn");
		
		/* Contrary to Nouns, Words (Operators and Properties) can't overlap.
		 * So we check if word overlap a word in wordList */
		for (Word other : wordList) {
			if (word.hasSameCoordinates(other.line(), other.column())) {
				throw new IllegalArgumentException("the word we want to add has same coordinates as a word in the wordList");
			}
		}
		
		wordList.add(word);
	}
	

	/**
	 * Remove a word from wordList that has same coordinates as (line, column)
	 * @param line Line of the word we are looking for
	 * @param column Column of the word we are looking for
	 * @return True if we removed a word, false otherwise
	 */
	public boolean remove(int line, int column) {		
		if (line < 0) 
			throw new IllegalArgumentException("line < 0");
		if (column < 0) 
			throw new IllegalArgumentException("column < 0");
		if (line > maxLine) 
			throw new IllegalArgumentException("line > maxLine");
		if (column > maxColumn) 
			throw new IllegalArgumentException("column > maxColumn");
		
		for (Word word : wordList) {
			if (word.hasSameCoordinates(line, column)) {
				wordList.remove(word);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Return the word at the coordinates (line, column) if it exists
	 * @param line The value of the word line we are looking for
	 * @param column The value of the word's column we are looking for
	 * @return The word we were looking for, null if it's not in the list
	 */
	public Word getWordAtCoordinates(int line, int column) {
		// We don't control if line < 0 or line > maxLine, it doesn't matter
		for (Word word : wordList) {
			if (word.hasSameCoordinates(line, column)) {
				return word;
			}
		}
		
		return null;
	}
	
	/**
	 * Draw all the word in wordList
	 * @param context ApplicationContext
	 * @param cellSize Size of a Noun
	 * @param horizontalPadding Space we add horizontally to center the screen
	 * @param verticalPadding Space we add vertically to center the screen
	 */
	public void drawWordList(ApplicationContext context, float cellSize, float horizontalPadding, float verticalPadding) {
		Objects.requireNonNull(context);
		if (cellSize < 0) 
			throw new IllegalArgumentException("cellSize < 0");
		if (horizontalPadding < 0) 
			throw new IllegalArgumentException("horizontalPadding < 0");
		if (verticalPadding < 0) 
			throw new IllegalArgumentException("verticalPadding < 0");

		for (Word word: wordList) {
			word.drawWord(context, word.line() * cellSize + verticalPadding, word.column() * cellSize + horizontalPadding, cellSize);
		}
	}
}
