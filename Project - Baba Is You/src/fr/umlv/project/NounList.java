package fr.umlv.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import fr.umlv.zen5.ApplicationContext;

public class NounList {
	private final int maxLine;
	private final int maxColumn;
	private final NounName nounName;
	private List<Noun> nounList = new ArrayList<Noun>();
	
	/* Constructor */
	public NounList(NounName nounName, int maxLine, int maxColumn) {
		Objects.requireNonNull(nounName);
		
		if (maxLine < 0) {
			throw new IllegalArgumentException("maxLine < 0");
		}
		
		if (maxColumn < 0) {
			throw new IllegalArgumentException("maxColumn < 0");
		}
		
		this.maxLine = maxLine;
		this.maxColumn = maxColumn;
		this.nounName = nounName;
	}
		
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
				
		for (Noun noun : nounList) {
			builder.append(noun + " ");
		}
		
		return builder.toString();
	}
	
	/**
	 * nounName's getter
	 * @return nounName field
	 */
	public NounName nounName() {
		return nounName;
	}
	
	/**
	 * Return an unmodifiableList of nounList (to perform an iteration on the list)
	 * @return An unmodifiableList version of nounList 
	 */
	public Iterable<Noun> nounList(){
	    return Collections.unmodifiableList(nounList);
	}
	
	
	/** Check if nounList is empty
	 * @return True if nounList is empty, otherwise false
	 */
	public boolean isEmpty() {
		return nounList.isEmpty();
	}
	
	/**
	 * Add a noun in the nounList
	 * @param noun The noun we are adding in the nounList
	 */
	public void add(Noun noun) {
		Objects.requireNonNull(noun);
		
		if (noun.line() >= maxLine) {
			throw new IllegalArgumentException("noun line > maxLine");
		}
		
		if (noun.column() >= maxColumn) {
			throw new IllegalArgumentException("noun column > maxColumn");
		}
		
		/* Check if the noun's NounName we are adding is the same as the nounList's NounName */
		if (!(noun.wordName().equals(nounName))) {
			throw new IllegalArgumentException("the noun we are trying to add is not the same as the nounList's NounName");
		}
		
		nounList.add(noun);
	}
	
	/**
	 * Add a noun with the values of the parameters of the method in the nounList
	 * @param nounName The nounName field of the noun we are going to add in the nounList
	 * @param line The line field of the noun we are going to add in the nounList
	 * @param column The column field of the noun we are going to add in the nounList
	 * @throws IOException 
	 */
	public void add(NounName nounName, int line, int column) throws IOException {
		Objects.requireNonNull(nounName);
		if (line < 0) 
			throw new IllegalArgumentException("line < 0");
		if (column < 0) 
			throw new IllegalArgumentException("column < 0");
		if (line >= maxLine) 
			throw new IllegalArgumentException("noun line > maxLine");
		if (column >= maxColumn) 
			throw new IllegalArgumentException("noun column > maxColumn");
		
		/* Check if the noun's NounName we are adding is the same as the nounList's NounName */
		if (!(nounName.equals(nounName))) {
			throw new IllegalArgumentException("the noun we are trying to add is not the same as the nounList's NounName");
		}
		
		/* Add it in the list */
		nounList.add(new Noun(nounName, line, column));
	}

	/**
	 * Remove a noun that has coordinates (line, column) in the nounList if it exists
	 * @param line The value of the noun's line we are looking for
	 * @param column The value of the noun's column we are looking for
	 * @return True if a noun has been removed, otherwise false
	 */
	public boolean remove(int line, int column) {
		if (line < 0) 
			throw new IllegalArgumentException("line < 0");
		if (column < 0) 
			throw new IllegalArgumentException("column < 0");
		if (line >= maxLine) 
			throw new IllegalArgumentException("line > maxLine");
		if (column >= maxColumn) 
			throw new IllegalArgumentException("column > maxColumn");
		
		for (Noun noun : nounList) {
			if (noun.hasSameCoordinates(line, column)) {
				nounList.remove(noun);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Return the noun at the coordinates (line, column) if it exists
	 * @param line The value of the noun's line we are looking for
	 * @param column The value of the noun's column we are looking for
	 * @return The noun we were looking for, null if it's not in the list
	 */
	public Noun getNounAtCoordinates(int line, int column) {
		if (line < 0) 
			throw new IllegalArgumentException("line < 0");
		if (column < 0) 
			throw new IllegalArgumentException("column < 0");
		if (line >= maxLine) 
			throw new IllegalArgumentException("line >= maxLine");
		if (column >= maxColumn) 
			throw new IllegalArgumentException("column >= maxColumn");
		
		for (Noun noun : nounList) {
			if (noun.hasSameCoordinates(line, column)) {
				return noun;
			}
		}
		
		return null;
	}
	
	/**
	 * Draw all the noun in nounList
	 * @param context ApplicationContext
	 * @param cellSize Size of a noun
	 * @param horizontalPadding Space we add horizontally to center the screen
	 * @param verticalPadding Space we add vertically to center the screen
	 */
	public void drawNounList(ApplicationContext context, float cellSize, float horizontalPadding, float verticalPadding) {
		Objects.requireNonNull(context);
		if (cellSize < 0) 
			throw new IllegalArgumentException("cellSize < 0");
		if (horizontalPadding < 0) 
			throw new IllegalArgumentException("horizontalPadding");
		if (verticalPadding < 0) 
			throw new IllegalArgumentException("verticalPadding < 0");
		
		for (Noun noun : nounList) {
			noun.drawWord(context, noun.line() * cellSize + verticalPadding, noun.column() * cellSize + horizontalPadding, cellSize);
		}
	}
	
	/**
	 * Take all of the noun's coordinates in this.nounList, and implements them in the nounList in parameters
	 * @param nounList The nounList in which we will add all of the noun's coordinate of this.nounList
	 * @throws IOException 
	 */
	public void replaceAllNoun(NounList nounList) throws IOException {
		Objects.requireNonNull(nounList);
		
		for (Noun noun : this.nounList) {
			System.out.println(nounList.nounName());
			nounList.add(nounList.nounName(), noun.line(), noun.column());
		}
	}
}
