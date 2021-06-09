package fr.umlv.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PropertyMap {
	private Map<NounName, List<PropertyName>> properties = new HashMap<NounName, List<PropertyName>>();
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (Map.Entry<NounName, List<PropertyName>> entry : properties.entrySet()) {
			builder.append(entry.getKey() + " :");
			for (PropertyName property : entry.getValue()) {
				builder.append(" " + property);
			}
			
			builder.append("\n");
		}
		
		return builder.append("\n").toString();
	}
	
	/**
	 * Return an unmodifiableMap of the map properties (to perform an iteration on the map)
	 * @return An unmodifiableList version of properties 
	 */
	public Map<NounName, List<PropertyName>> properties(){
	    return Collections.unmodifiableMap(properties);
	}
	
	/**
	 * Perform the appropriate action if operator do form a vertical sentence
	 * @param operator The operator we are method on
	 * @param wordList List of all the words (noun's name/properties/operators) we check to see if there's a sentence formed
	 * @param nounMap The nounMap, in case there's sentence like "Rock Is Wall". In this case, we need to change all the Rock noun into Wall noun
	 * @throws IOException 
	 */
	public void verticalProperty(Operator operator, WordList wordList, NounMap nounMap) throws IOException {
		Objects.requireNonNull(operator);
		Objects.requireNonNull(wordList);
		Objects.requireNonNull(nounMap);
		
		switch (operator.checkVerticalProperty(wordList)) {
			case 1: // We have a sentence of type : NOUN IS PROPERTY -> We add the property for the Noun in the propertyMap 
				add((NounName) wordList.getWordAtCoordinates(operator.line() - 1, operator.column()).wordName(), 
					(PropertyName) wordList.getWordAtCoordinates(operator.line() + 1, operator.column()).wordName());
				break;
			
			case 2: // We have a sentence of type : NOUN IS NOUN -> We add all the first noun coordinates in the second noun list
				nounMap.changeNounName((NounName) wordList.getWordAtCoordinates(operator.line() - 1, operator.column()).wordName(),
									   (NounName) wordList.getWordAtCoordinates(operator.line() + 1, operator.column()).wordName());
				break;
		}
	}
	
	/**
	 * Perform the appropriate action if operator does form an horizontal sentence
	 * @param operator The operator we are method on
	 * @param wordList List of all the words (noun's name/properties/operators) we check to see if there's a sentence formed
	 * @param nounMap The nounMap, in case there's sentence like "Rock Is Wall". In this case, we need to change all the Rock noun into Wall noun
	 * @throws IOException 
	 */
	public void horizontalProperty(Operator operator, WordList wordList, NounMap nounMap) throws IOException {
		Objects.requireNonNull(operator);
		Objects.requireNonNull(wordList);
		Objects.requireNonNull(nounMap);
		
		switch (operator.checkHorizontalProperty(wordList)) {
			case 1: // Same as vertical method
				add((NounName) wordList.getWordAtCoordinates(operator.line(), operator.column() - 1).wordName(), 
					(PropertyName) wordList.getWordAtCoordinates(operator.line(), operator.column() + 1).wordName());
				break;
			
			case 2: // Same as vertical method
				nounMap.changeNounName((NounName) wordList.getWordAtCoordinates(operator.line(), operator.column() - 1).wordName(),
						   			   (NounName) wordList.getWordAtCoordinates(operator.line(), operator.column() + 1).wordName());
				break;
		}	
	}
	
	/**
	 * Check all word in the wordList to see if a sentence is formed. Add them to the appropriate map (ex. : Noun Operator Property)
	 * @param wordList List of all the words (noun's name/properties/operators) we check to see if there's a sentence formed
	 * @param nounMap The nounMap, in case there's sentence like "Rock Is Wall". In this case, we need to change all the Rock noun into Wall noun
	 * @throws IOException 
	 */
	public void checkProperties(WordList wordList, NounMap nounMap) throws IOException {	    
		Objects.requireNonNull(wordList);
		Objects.requireNonNull(nounMap);
		
		/* Since we are going to check properties in wordList, it means a word moved in the list (or we initialize it) : 
		 * then, we clean the propertyMap in case a former sentence is break because one word moved */
		properties.clear();
		
		/* For each "IS" operator, we check if there's a sentence formed horizontally or vertically
		 * NB : If other operators are implemented, check for each operator instead of only the "IS" operator
		 */
		for (Word word : wordList.wordList()) {
			if (word.wordName() == OperatorName.IS) {				
				Operator operator = (Operator) word;
				
				/* We check if the operator "IS" form a vertical or an horizontal sentence. */
				verticalProperty(operator, wordList, nounMap);
				horizontalProperty(operator, wordList, nounMap);
			}
		}
	}
	
	/**
	 * Add propertyName in propertyMap, with key == nounName
	 * @param nounName The key which we add the property to
	 * @param propertyName The property we are adding in nounName's values
	 */
	public void add(NounName nounName, PropertyName propertyName) {
		Objects.requireNonNull(nounName);
		Objects.requireNonNull(propertyName);
		
		/* If properties already have the nounName key, we add the propertyName in its values */
		if (properties.containsKey(nounName)) {			
			properties.get(nounName).add(propertyName);
		}
		
		/* If the map "properties" doesn't have the key "nounName" yet, we need to create a list for its values, and then we add the property */
		else {
			List<PropertyName> list = new ArrayList<>();
			list.add(propertyName);
			
			properties.put(nounName, list);
		}
	}
	
	/**
	 * Return You's nounName. It's a list since there can be several You noun.
	 * @return List of all the You's noun
	 */
	public List<NounName> getYouNoun() {
		List<NounName> youNouns = new ArrayList<>();
		// Check all the active properties for each keys : if one of them has the You property, we add it to the list
		for (Map.Entry<NounName, List<PropertyName>> entry : properties.entrySet()) {
			for (PropertyName property : entry.getValue()) {
				if (property == PropertyName.YOU) {
					youNouns.add(entry.getKey());
				}
			}
		}
		
		return youNouns;
	}
}
