package fr.umlv.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import fr.umlv.zen5.ApplicationContext;

public class NounMap {
	private List<NounName> you = new ArrayList<>();
	private final int maxLine;
	private final int maxColumn;
	private Map<NounName, NounList> nounMap = new LinkedHashMap<>(); 
	/* LinkedHashMap and not HashMap to keep a certain order when adding the NounLists.
	 * Therefore, we can order which noun is going to be over the other noun 
	   when several nouns overlap (on the same cell) */
	
	/* Constructor */
	public NounMap(int maxLine, int maxColumn) {
		if (maxLine < 0 || maxColumn < 0) {
			throw new IllegalArgumentException("maxLine or colums < 0");
		}
		
		this.maxLine = maxLine;
		this.maxColumn = maxColumn;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
				
		for (Map.Entry<NounName, NounList> entry : nounMap.entrySet()) {
			builder.append(entry + "\n");
		}
		
		return builder.toString();
	}
	
	/**
	 * you's getter
	 * @return you's List (which nouns are "You" atm)
	 */
	public Iterable<NounName> you() {
		return Collections.unmodifiableCollection(you);
	}
	
	/**
	 * Check if the wordName (nounName) is among the You noun
	 * @param wordName
	 * @return True if wordName is one of the You noun
	 */
	public boolean isYou(WordName wordName) {
		Objects.requireNonNull(wordName);
		return you.contains(wordName);
	}
	
	/**
	 * Check if the you's list is empty (it would mean we don't have any You on the board) 
	 * @return True if you is empty, otherwise false
	 */
	public boolean youIsEmpty() {
		return you.isEmpty();
	}
	
	/**
	 * Check if the NounList of "You" nouns are empty (in case nouns have been erased)
	 * @return True if all the NounList of "You" are empty
	 */
	public boolean youNounsListEmpty() {
		for (NounName nounName : you) {
			if (nounMap.get(nounName).isEmpty()) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * you setter
	 * @param List of the nounName which are You
	 */
	public void setYou(List<NounName> youNouns) {
		Objects.requireNonNull(youNouns);
		this.you = youNouns;
	}
		
	/**
	 * Return an unmodifiableMap of nounMap (to perform an iteration on the map)
	 * @return An unmodifiableList version of nounMap 
	 */
	public Map<NounName, NounList> nounMap(){
	    return Collections.unmodifiableMap(nounMap);
	}
		
	/** 
	 * Add a nounList in the nounMap
	 * @param nounList The nounList we are adding
	 */
	public void add(NounList nounList) {
		Objects.requireNonNull(nounList);
		
		if (nounMap.containsKey(nounList.nounName())) {
			throw new IllegalArgumentException("nounMap already have a nounList that has the NounName as the on we are trying to add");
		}
		
		nounMap.put(nounList.nounName(), nounList);
	}
	
	/**
	 * Draw all the nouns in the nounList in the nounMap
	 * @param context Application context
	 * @param cellSize Size of a noun
	 * @param horizontalPadding Space we add horizontally to center the screen
	 * @param verticalPadding Space we add vertically to center the screen
	 */
	public void drawNounTab(ApplicationContext context, float cellSize, float horizontalPadding, float verticalPadding) {
		Objects.requireNonNull(context);
		if (cellSize < 0) 
			throw new IllegalArgumentException("cellSize < 0");
		if (horizontalPadding < 0) 
			throw new IllegalArgumentException("horizontalPadding < 0");
		if (verticalPadding < 0) 
			throw new IllegalArgumentException("verticalPadding < 0");
		
		for (Map.Entry<NounName, NounList> entry : nounMap.entrySet()) {
			entry.getValue().drawNounList(context, cellSize, horizontalPadding, verticalPadding);
		}
	}
	
	/**
	 * Add all the noun's coordinates in the list of formerNoun, in the list of newNoun
	 * @param formerNoun The noun from which we'll extract the coordinates from
	 * @param newNoun The name of the noun in which we'll add the coordinates from formerNoun list
	 * @throws IOException 
	 */
	public void changeNounName(NounName formerNoun, NounName newNoun) throws IOException {
		Objects.requireNonNull(formerNoun);
		Objects.requireNonNull(newNoun);		

		// We get all the noun in the list associated with formerNoun, and put them in the newNoun list
		nounMap.get(formerNoun).replaceAllNoun(nounMap.get(newNoun));
		
		// Then we create a new empty NounList in nounMap for formerNoun (to empty it)
		nounMap.put(formerNoun, new NounList(formerNoun, maxLine, maxColumn));
	}
}
