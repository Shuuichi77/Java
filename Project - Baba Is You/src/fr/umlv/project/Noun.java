package fr.umlv.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

public final class Noun extends Word {
	/* Constructor */
	public Noun(WordName wordName, int line, int column) throws IOException {
		super(wordName, line, column);
		if (!(wordName instanceof NounName)) {
			throw new IllegalArgumentException("Noun's wordName isn't a NounName");
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Noun)) {
			return false;
		}
		
		Noun other = (Noun) obj;
		return other.wordName() == this.wordName() && other	.line() == this.line() && other.column() == this.column();
	}
	
	@Override
	public int hashCode() {
		return wordName().hashCode() + line() + column();
	}
	
	/**
	 * Check if noun has a certain property active (the property "propertyName")
	 * @param propertyMap The map of all the active property for each noun
	 * @param propertyName The property we are looking for
	 * @return True if noun do have the property, otherwise false
	 */
	public boolean isProperty(PropertyMap propertyMap, PropertyName propertyName) {
		Objects.requireNonNull(propertyMap);
		Objects.requireNonNull(propertyName);
		
		// We check if the wordName is a propertyMap's key : that would mean the noun have at least 1 active property		
		for (PropertyName property : Optional.ofNullable(propertyMap.properties().get(this.wordName()))
				 							 .orElse(Collections.emptyList())) {
				if (property == propertyName) {
					return true;
			}
		}

		return false;
	}
	
	/**
	 * Check all noun properties
	 * @param prevWord The word that wants to go over noun's cell
	 * @param board The level's board
	 * @param direction The direction noun has to go in if it needs to move
	 * @param nounsToRemove List of nouns to remove if we'll be able to move words
	 * @return false = noun can't move, therefore prevWord won't be able to move
	 * 		   true = we can move/go over noun
	 * @throws IOException
	 */
	public boolean checkNounProperties(Word prevWord, Board board, String direction, ArrayList<Noun> nounsToRemove) throws IOException {
		boolean hasBeenPushed = false;
		Objects.requireNonNull(prevWord);
		Objects.requireNonNull(board);
		Objects.requireNonNull(direction);
		Objects.requireNonNull(nounsToRemove);
		
		if (isStop(board.propertyMap())) {
			return false;
		}
		
		switch (canBePushed(board, direction, nounsToRemove)) {
		case -1:
			return false;
			break;
		case 1:
			hasBeenPushed = true;
			break;
		}
		
		/* If noun hasn't been pushed, we check if it has the next properties
		 * If it has been pushed, we don't need to, it's not on the next cell anymore
		 */
		if (!hasBeenPushed) {
			isPoison(prevWord, board);
			isSink(prevWord, board, nounsToRemove);
			isHot(prevWord, board.propertyMap(), nounsToRemove);	
			isMelt(prevWord, board.propertyMap(), nounsToRemove);
			isWin(prevWord, board);
			isDefeat(prevWord, board, nounsToRemove);
		}
		
		
		return true;
	}

	/** 
	 * Check if noun has property isStop
	 * @param propertyMap Map with all properties/operators
	 * @return true if noun has stop property, otherwise false
	 */
	private boolean isStop(PropertyMap propertyMap) {
		Objects.requireNonNull(propertyMap);
		
		return this.isProperty(propertyMap, PropertyName.STOP);
	}
	
	/**
	 * Check if noun can be pushed
	 * @param board The level's board
	 * @param direction The direction noun has to go if it can be pushed
	 * @param nounsToRemove All nouns to remove if a noun moved
	 * @return  -1 if noun has push property, but couldn't move
	 * 			 0 if noun doesn't have push property 
	 * 			 1 if it has push property, and could be moved
	 * @throws IOException
	 */
	private int canBePushed(Board board, String direction, ArrayList<Noun> nounsToRemove) throws IOException {
		Objects.requireNonNull(board);
		Objects.requireNonNull(direction);
		Objects.requireNonNull(nounsToRemove);
		
		if (this.isProperty(board.propertyMap(), PropertyName.PUSH)) {
			// If noun can be pushed, we need to check if it can move
			if (this.moveWord(board, direction, nounsToRemove)) {
				this.changeCoordinates(direction);
				return 1;
			}
			
			else {
				return -1;
			}
		}
		
		return 0;
	}
	
	/**
	 * Check if a noun with fields equal to "line" and "column" is in list "list"
	 * @param list The list we are searching in
	 * @param line The line of the noun we are looking for
	 * @param column The column of the noun we are looking for
	 * @return True if there's a noun with the same coordinates, otherwise false
	 */
	private boolean nounInList(ArrayList<Noun> list, int line, int column) {		
		Objects.requireNonNull(list);
		if (line < 0)
			throw new IllegalArgumentException("line < 0");
		if (column < 0)
			throw new IllegalArgumentException("column < 0");
		
		for (Noun noun : list) {
			if (noun.line() == line && noun.column() == column) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Check if noun has property poison. 
	 * If he does, we poison the player, and then have 10 movements left
	 * @param prevWord The word that wants to go on "this" cell
	 * @param board The level's board
	 */
	private void isPoison(Word prevWord, Board board) {
		Objects.requireNonNull(prevWord);
		Objects.requireNonNull(board);
		
		if (board.nounMap().isYou(prevWord.wordName()) && this.isProperty(board.propertyMap(), PropertyName.POISON)) {
			// If prevWord is a Noun, we add it in nounsToRemove, EVEN IF IT'S ALREADY IN !
			board.hasBeenPoisoned();
			
		}
	}
	
	/**
	 * Check if noun a "SINK" property
	 * If it does, add "this" and prevWord to nounsToRemove, since they are going to erase each other
	 * @param prevWord The word that wants to go on "this" cell 
	 * @param board The level's board
	 * @param nounsToRemove List of nouns to remove if we'll be able to move words
	 */
	private void isSink(Word prevWord, Board board, ArrayList<Noun> nounsToRemove) {
		Objects.requireNonNull(prevWord);
		Objects.requireNonNull(board);
		Objects.requireNonNull(nounsToRemove);
		
		if (this.isProperty(board.propertyMap(), PropertyName.SINK)) {
			// If prevWord is a Noun, we add it in nounsToRemove if it's not already in
			if (prevWord instanceof Noun) {
				if (!nounInList(nounsToRemove, this.line(), this.column())) {
					nounsToRemove.add((Noun) prevWord);
				}
			}
			
			// If prevWord is a property/operator, we remove it from wordList 
			else {
				if (!board.wordList().remove(prevWord.line(), prevWord.column())) {
					throw new IllegalArgumentException("couldn't find word to remove in wordList");
				}
			}
			
			if (!nounsToRemove.contains(this)) {
				nounsToRemove.add(this);
			}
		}
	}
	
	/**
	 * Check if noun has property Hot.
	 * If he does, if prevWord has Melt property, add it to nounsToRemove
	 * @param prevWord The word that wants to go on "this" cell
	 * @param propertyMap Map with all properties/operators
	 * @param nounsToRemove List of nouns to remove if we'll be able to move words
	 */
	private void isHot(Word prevWord, PropertyMap propertyMap, ArrayList<Noun> nounsToRemove) {
		Objects.requireNonNull(prevWord);
		Objects.requireNonNull(propertyMap);
		Objects.requireNonNull(nounsToRemove);
		
		// If "this" is HOT, and prevWord is a Noun which has property MELT, prevWord has to be removed since it will melt */
		if (this.isProperty(propertyMap, PropertyName.HOT)) {
			if (prevWord instanceof Noun) {
				Noun prevNoun = (Noun) prevWord;
				if (prevNoun.isProperty(propertyMap, PropertyName.MELT)) {
					nounsToRemove.add(prevNoun);
				}
			}
		}
	}
	
	/**
	 * Check if noun has property Melt.
	 * If he does, if prevWord has Hot property, add noun to nousToRemove
	 * @param prevWord The word that wants to go on "this" cell
	 * @param propertyMap Map with all properties/operators
	 * @param nounsToRemove List of nouns to remove if we'll be able to move words
	 */
	private void isMelt(Word prevWord, PropertyMap propertyMap, ArrayList<Noun> nounsToRemove) {
		Objects.requireNonNull(prevWord);
		Objects.requireNonNull(propertyMap);
		Objects.requireNonNull(nounsToRemove);
		
		// If "this" is MELT, and prevWord is a Noun which has property HOT, "this" has to be removed since it will melt */
		if (this.isProperty(propertyMap, PropertyName.MELT)) {
			if (prevWord instanceof Noun) {
				Noun prevNoun = (Noun) prevWord;
				if (prevNoun.isProperty(propertyMap, PropertyName.HOT)) {
					nounsToRemove.add(this);
				}
			}
		}
	}
	
	/**
	 * Check if noun has "WIN" property.
	 * If it does, set board field (win) to true
	 * @param prevWord The word that wants to go on "this" cell
	 * @param board The level's board
	 */
	private void isWin(Word prevWord, Board board) {
		Objects.requireNonNull(prevWord);
		Objects.requireNonNull(board);
		
		if (board.nounMap().isYou(prevWord.wordName()) && board.isNextNounWin(this)) {
			return ;
		}
	}
	

	/**
	 * Check if noun has "DEFEAT" property
	 * If it does, add prevWord to nounsToRemove
	 * @param prevWord The word that wants to go on "this" cell
	 * @param board The level's board
	 * @param nounsToRemove List of nouns to remove if we'll be able to move words
	 */
	private void isDefeat(Word prevWord, Board board, ArrayList<Noun> nounsToRemove) {
		Objects.requireNonNull(prevWord);
		Objects.requireNonNull(board);
		
		
		if (board.nounMap().isYou(prevWord.wordName()) &&
				this.isProperty(board.propertyMap(), PropertyName.DEFEAT) &&
				prevWord instanceof Noun) {
			// If prevWord is a Noun, we add it in nounsToRemove, EVEN IF IT'S ALREADY IN !
			nounsToRemove.add((Noun) prevWord);
			}
		}
	}	
}
