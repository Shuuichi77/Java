package fr.umlv.project;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import fr.umlv.zen5.ApplicationContext;

public class Board {
	private int poison = -1;
	private boolean win = false;
	private final int maxLine;
	private final int maxColumn;
	private NounMap nounMap;
	private WordList wordList;
	private PropertyMap propertyMap;
	
	/* Constructor */
	public Board(int maxLine, int maxColumn, NounMap nounMap, WordList wordList, PropertyMap propertyMap) {
		if (maxLine < 0) 
			throw new IllegalArgumentException("maxLine < 0");
		if (maxColumn < 0) 
			throw new IllegalArgumentException("maxColumn < 0");
	
		this.maxLine = maxLine;
		this.maxColumn = maxColumn;
		this.nounMap = Objects.requireNonNull(nounMap);
		this.wordList = Objects.requireNonNull(wordList);
		this.propertyMap = Objects.requireNonNull(propertyMap);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Total Line = " + maxLine + ", Total Column = " + maxColumn).append("\nNounMap :\n" + nounMap).append("\nWordList :\n" + wordList).append("\nPropertyMap :\n" + propertyMap);
		return builder.toString();
	}
	
	/**
	 * Check if nextNoun does have the Win property. 
	 * If he does, set the win field to true, and return true.
	 * @param nextNoun The Noun we are going on with a You Noun
	 * @return true if nextNoun has the Win property, otherwise false
	 */
	public boolean isNextNounWin(Noun nextNoun) {
		Objects.requireNonNull(nextNoun);
		if (nextNoun.isProperty(propertyMap, PropertyName.WIN)) {
			win = true;
			return true;
		}
		
		return false;
	}	
	
	/**
	 * Check if we won by returning Win field, 
	 * and checking all You Noun property (in case they have Win property)
	 * @return True if we won, otherwise false
	 */
	public boolean isWin() {
		if (win) {
			return true;
		}
		
		// Check if any of the You noun has the Win property
		for (NounName nounName : nounMap.you()) {
			if (propertyMap.properties().get(nounName) != null) {
				for (PropertyName property : propertyMap.properties().get(nounName)) {
					if (property == PropertyName.WIN) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Check if we have at least a noun that is You, 
	 * and if all You Noun aren't empty (in case all You have been destroyed even though we still have "NOUN IS YOU")
	 * @return True if we lost, otherwise false
	 */
	public boolean isDefeat() {
		return nounMap.youIsEmpty() || nounMap.youNounsListEmpty() || poison == 0;
	}
	
	/**
	 * nounMap's getter
	 * @return nounMap field
	 */
	public NounMap nounMap() {
	    return nounMap;
	}
	
	/**
	 * wordList's getter
	 * @return wordList field
	 */
	public WordList wordList() {
	    return wordList;
	}
	
	/**
	 * propertyMap's getter
	 * @return propertyMap field
	 */
	public PropertyMap propertyMap() {
	    return propertyMap;
	}

	/**
	 * If "You" has been poisoned, decrease poison field by 1
	 */
	public void poisonEffect() {
		if (poison != -1) {
			poison--;
		}
	}
	
	/**
	 * If "You" has been poisoned, set the field poison to 10 :
	 * player has 10 movements left before dying
	 */
	public void hasBeenPoisoned() {
		if (poison == -1) {
			poison = 10;
		}
	}
	
	/**
	 * Check if the coordinates (line, column) in parameters are out of the board
	 * @param line The line coordinate
	 * @param column The column coordinate
	 * @return True if the coordinates (line, column) is out of the board, otherwise false
	 */
	public boolean isOutOfBoard(int line, int column) {
		return line < 0 || column < 0 || line >= maxLine || column >= maxColumn;
	}
	
	/**
	 * Draw all the noun in the nounMap, and the words in wordList
	 * @param context The application context
	 * @param width The width of the window of the game
	 * @param height The height of the window of the game
	 * @param size Size of a word in the game
	 * @param horizontalPadding Space we add horizontally to center the screen
	 * @param verticalPadding Space we add vertically to center the screen
	 */
	public void drawAllWords(ApplicationContext context, float width, float height, float size, float horizontalPadding, float verticalPadding) {
		Objects.requireNonNull(context);
		if (width < 0) 
			throw new IllegalArgumentException("width < 0");
		if (height < 0) 
			throw new IllegalArgumentException("height < 0");
		if (size < 0) 
			throw new IllegalArgumentException("cellSize < 0");
		if (horizontalPadding < 0) 
			throw new IllegalArgumentException("horizontalPadding < 0");
		if (verticalPadding < 0) 
			throw new IllegalArgumentException("verticalPadding < 0");
		
		context.renderFrame(graphics -> {
			/* Dark blue screen to erase all the screen */
	        graphics.setColor(new Color(21, 24, 31));
	        graphics.fill(new Rectangle2D.Float(0, 0, width, height));
	        
	        /* Black screen for the level's board */
	        graphics.setColor(Color.BLACK);
	        graphics.fill(new Rectangle2D.Float(horizontalPadding + 1, verticalPadding + 1, size * maxColumn - 2, size * maxLine - 2));
	        
	        /* White rectangle to see the board's borders */
	        graphics.setColor(Color.WHITE);
	        graphics.draw(new Rectangle2D.Float(horizontalPadding, verticalPadding, size * maxColumn - 1, size * maxLine - 1));
		});
		
		nounMap.drawNounTab(context, size, horizontalPadding, verticalPadding);
		wordList.drawWordList(context, size, horizontalPadding, verticalPadding);
	}
	
	/** TODO
	 * @param keyPressed The key pressed by the player (Up, Down, Left, Right)
	 * @param context The application context
	 * @param width The width of the window of the game
	 * @param height The height of the window of the game
	 * @param size Size of a word in the game
	 * @param horizontalPadding Space we add horizontally to center the screen
	 * @param verticalPadding Space we add vertically to center the screen
	 */
	public void youIsMoving(String keyPressed, ApplicationContext context, float width, float height, float size, float horizontalPadding, float verticalPadding) {
		Objects.requireNonNull(keyPressed);
		Objects.requireNonNull(context);
		if (width < 0) 
			throw new IllegalArgumentException("width < 0");
		if (height < 0) 
			throw new IllegalArgumentException("height < 0");
		if (size < 0) 
			throw new IllegalArgumentException("cellSize < 0");
		if (horizontalPadding < 0) 
			throw new IllegalArgumentException("horizontalPadding < 0");
		if (verticalPadding < 0) 
			throw new IllegalArgumentException("verticalPadding < 0");
		
		/* For all You noun, we'll check each of their noun in their list */
		for (NounName youNoun : nounMap.you()) {
			var nounMoved = false;
			ArrayList<Noun> nounsToRemove = new ArrayList<Noun>(); // For nouns that will be erased (sink, hot + melt)
			
			for(Noun noun : nounMap.nounMap().get(youNoun).nounList()) {
				try {
					if (noun.moveWord(this, keyPressed, nounsToRemove)) {
						noun.changeCoordinates(keyPressed);	
						nounMoved = true;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			/* Remove all noun from nounToRemove */
			for (Noun nounToRemove : nounsToRemove) {
				nounMap.nounMap().get(nounToRemove.wordName()).remove(nounToRemove.line(), nounToRemove.column());
			}
			
			/* Redraw all the nouns if we moved a noun */
			if (nounMoved) {
				drawAllWords(context, width, height, size, horizontalPadding, verticalPadding);  
				// If You has been poisoned and moved, poison will decrease and if it's 0, you will lose
				poisonEffect();
			}
		}
	}
	
	/**
	 * Check if there's a noun on coordinates (nextLine, nextColumn).
	 * If there is, check all its properties, and if we can go on its cell
	 * @param word The word going on coordinates (nextLine, nextColumn).
	 * @param direction The direction word takes
	 * @param nounsToRemove All nouns that needs to be remove in case we need to (sink, defeat, etc.)
	 * @param nextLine word's next line if he moves
	 * @param nextColumn word's next column if he moves
	 * @return true if we can go on the cell, false otherwise
	 * @throws IOException
	 */
	public boolean isThereANoun(Word word, String direction, ArrayList<Noun> nounsToRemove, int nextLine, int nextColumn) throws IOException {
		for (Map.Entry<NounName, NounList> entry : nounMap.nounMap().entrySet()) {
			Noun nextNoun = entry.getValue().getNounAtCoordinates(nextLine, nextColumn);
	
			/* If we find a noun, we check if it has any property, one by one */
			if (nextNoun != null) {
				/* If we can't go on nextNoun cell */
				if (!nextNoun.checkNounProperties(word, this, direction, nounsToRemove)) {
					return false;
				}
			}
		}
		
		/* If we didn't find any Noun on coordinates (nextLine, nextColumn), word can move*/
		return true;
	}
	
	/**
	 * Check if there's a noun on coordinates (nextLine, nextColumn).
	 * If there is, check if it can move
	 * @param nextLine word's next line if he moves
	 * @param nextColumn word's next column if he moves
	 * @param direction The direction word takes
	 * @param nounsToRemove All nouns that needs to be remove in case we need to (sink, defeat, etc.)
	 * @return true if we can go on the cell, false otherwise
	 * @throws IOException
	 */
	public boolean isThereAWord(int nextLine, int nextColumn, String direction, ArrayList<Noun> nounsToRemove) throws IOException {
		Word nextWord = wordList.getWordAtCoordinates(nextLine, nextColumn);
		if (nextWord != null) {			
			if (nextWord.moveWord(this, direction, nounsToRemove)) {
				nextWord.changeCoordinates(direction);
				
				/* Since a word moved, we erase all the previous properties and check it again (in case they changed) */
				propertyMap.checkProperties(wordList, nounMap);
				
				/* We also check if the You noun changed */
				nounMap.setYou(propertyMap.getYouNoun());
			}
			
			// If it can't move, the current word/noun can't move either
			else {
				return false;
			}	
		}
		
		/* If there wasn't any word or if we could move word, we return true : it mean "this" can move */
		return true;
	}

	/** 
	 * Display the level's results, whether the player won or lost,
	 * and close the window after X seconds
	 * @param context Application context
	 * @param height The height of the window of the game
	 * @param width The width of the window of the game
	 */
	public void gameResults(ApplicationContext context, float height, float width) {
		long start = System.currentTimeMillis();
		int seconds = 20;
		long end = start + seconds * 1000; 
		long timer = System.currentTimeMillis();
		
		context.renderFrame(graphics -> {
			graphics.setColor(new Color(21, 24, 31));
	        graphics.fill(new Rectangle2D.Float(width * 3 / 8, height * 3 / 8, width * 2 / 8, height * 2 / 8));
			
	        graphics.setColor(Color.WHITE);
	        graphics.draw(new Rectangle2D.Float(width * 3 / 8, height * 3 / 8, width * 2 / 8, height * 2 / 8));
	        
			graphics.setFont(graphics.getFont().deriveFont(graphics.getFont().getSize() * 3F));
			graphics.drawString(isDefeat() ? "You lost..." : "You won !", width * 7 / 16, height * 33 / 64);

		});
		
		// To automatically close the program in X seconds 
		while (timer < end) {				
			if (timer == end - (seconds - 1) * 1000) {
				System.out.println(seconds - 1 + " seconds before closing window...");
				seconds--;
			}
			
			timer = System.currentTimeMillis();
		}
			
		System.exit(0);	
	}
}
