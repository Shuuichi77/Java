package fr.umlv.project;

import java.io.IOException;
import java.util.Objects;

public final class Operator extends Word {
	/* Constructor */
	public Operator(WordName wordName, int line, int column) throws IOException {
		super(wordName, line, column);
		
		if (!(wordName instanceof OperatorName)) {
			throw new IllegalArgumentException("Operator's wordName isn't an OperatorName");
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Operator)) {
			return false;
		}
		
		Operator other = (Operator) obj;
		return other.wordName() == this.wordName() && other.line() == this.line() && other.column() == this.column();
	}
	
	@Override
	public int hashCode() {
		return wordName().hashCode() + line() + column();
	}
	
	/**
	 * Check if there's a vertical sentence formed for the operator
	 * @param wordList List of all the word and their coordinates
	 * @return 	0 : There is no sentence formed vertically for this operator
	 * 			1 : There is sentence of type : NOUN OPERATOR PROPERTY (ex. : Rock is Push)
	 * 			2 : There is sentence of type : NOUN OPERATOR NOUN (ex. : Rock Is Wall)
	 */
	public int checkVerticalProperty(WordList wordList) {
		Objects.requireNonNull(wordList);
		
		Word wordAbove = wordList.getWordAtCoordinates(this.line() - 1, this.column());
		/* If we found a word above the operator's coordinates, we first check if it's a noun */
		if (wordAbove != null) {
			if (wordAbove.wordName() instanceof NounName) {
				
				/* Since we found a noun above operator, we check if there's a noun or a property below :
				 * that would mean a sentence if formed */
				Word wordBelow = wordList.getWordAtCoordinates(this.line() + 1, this.column());
				if (wordBelow != null) {
					if (wordBelow.wordName() instanceof PropertyName) {
						return 1;
					}
					
					if (wordBelow.wordName() instanceof NounName) {
						return 2;
					}
				}
			}
		}
		
		return 0;
	}
	
	/**
	 * Check if there's a vertical sentence formed for the operator
	 * @param wordList List of all the word and their coordinates
	 * @return 	0 : There is no sentence formed vertically for this operator
	 * 			1 : There is sentence of type : NOUN OPERATOR PROPERTY (ex. : Rock is Push)
	 * 			2 : There is sentence of type : NOUN OPERATOR NOUN (ex. : Rock Is Wall)
	 */
	public int checkHorizontalProperty(WordList wordList) {
		/* The method works the same as checkVerticalProperty,
		   except that we check if there's a noun/property on the left and right of the operator,
		   instead of above and below it */
		Objects.requireNonNull(wordList);
		
		Word wordLeft = wordList.getWordAtCoordinates(this.line(), this.column() - 1);
		
		if (wordLeft != null) {
			if (wordLeft.wordName() instanceof NounName) {
				Word wordRight = wordList.getWordAtCoordinates(this.line(), this.column() + 1);
				if (wordRight != null) {
					if (wordRight.wordName() instanceof PropertyName) {
						return 1;
					}
					
					if (wordRight.wordName() instanceof NounName) {
						return 2;
					}
				}
			}
		}
		
		return 0;
	}
}
