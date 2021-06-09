package fr.umlv.project;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Objects;

import javax.imageio.ImageIO;

import fr.umlv.zen5.ApplicationContext;

public abstract class Word {
	private final WordName wordName;
	private final BufferedImage wordImg;
	private int line;
	private int column;
	
	/* Constructor */
	public Word(WordName wordName, int line, int column) throws IOException {
		String extension;
		
		if (line < 0) 
			throw new IllegalArgumentException("word's line < 0");
		if (column < 0) 
			throw new IllegalArgumentException("word's column < 0");
	
		this.wordName = Objects.requireNonNull(wordName);
		/* If this is a noun, we get the noun image (Baba can be a Noun, but also the word "Baba" (the property Baba) */
		extension = (this instanceof Noun) ? "_noun.png" : ".png";
		this.wordImg = Objects.requireNonNull(ImageIO.read(getClass().getResource("/img/" + wordName.toString().toLowerCase() + extension)));
		this.line = line;
		this.column = column;
	}
	
	@Override
	public String toString() {
		return "(" + wordName.toString() + " : " + line + ", " + column + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Noun)) {
			return false;
		}
		
		Word other = (Word) obj;
		return other.wordName == this.wordName && other.line == this.line && other.column == this.column;
	}
	
	@Override
	public int hashCode() {
		return wordName.hashCode() + line + column;
	}
	
	/**
	 * wordName's getter
	 * @return wordName field
	 */
	public WordName wordName() {
		return wordName;
	}
	
	/**
	 * line's getter
	 * @return line field
	 */
	public int line() {
		return line;
	}
	
	/**
	 * column's getter
	 * @return column field
	 */
	public int column() {
		return column;
	}

	/**
	 * Check if the fields line and column of the word are the same as the line and column in the parameters
	 * @param line The value of the line we want our field to be equals to
	 * @param column The value of the column we want our field to be equals to
	 * @return True if this.line == line and this.column == column, otherwise false 
	 */
	public boolean hasSameCoordinates(int line, int column) {
		// We don't control if line < 0 or line > maxLine, it doesn't matter
		return this.line == line && this.column == column;
	}
	
	/**
	 * Draw the word on the board at the coordinates (column, line) with a size = cellSize
	 * @param context ApplicationContext
	 * @param line The line we are drawing the word at
	 * @param column The column we are drawing the word at
	 * @param cellSize The word size
	 */
	public void drawWord(ApplicationContext context, float line, float column, float cellSize) {
		Objects.requireNonNull(context);
		if (line < 0) 
			throw new IllegalArgumentException("line < 0");
		if (column < 0) 
			throw new IllegalArgumentException("column < 0");
		if (cellSize < 0) 
			throw new IllegalArgumentException("cellSize < 0");

		context.renderFrame(graphics -> {
			graphics.drawImage(wordImg, (int) column, (int) line, (int) cellSize, (int) cellSize, null);
		});
	}
	
	/**
	 * Check if the noun can move in the board based on the direction it's going
	 * @param board The board of the game
	 * @param direction The direction of the noun (UP, DOWN, LEFT, RIGHT)
	 * @param nounsToRemove List of noun we need to remove from the board after calling this function
	 * @return True if the noun can move, otherwise false
	 * @throws IOException 
	 */
	public boolean moveWord(Board board, String direction, ArrayList<Noun> nounsToRemove) throws IOException {
		Objects.requireNonNull(board);
		Objects.requireNonNull(direction);
		Objects.requireNonNull(nounsToRemove);
		if (!direction.equals("UP") && !direction.equals("DOWN") && !direction.equals("LEFT") && !direction.equals("RIGHT")) 
			throw new IllegalArgumentException("direction is not valid");
		
		// Word future coordinates if he moves
		int wordNextLine = line, wordNextColumn = column;
		switch(direction) {
			case "UP" : wordNextLine--; break;
			case "DOWN" : wordNextLine++; break;
			case "LEFT" : wordNextColumn--; break;
			case "RIGHT" : wordNextColumn++; break;
		}
		
		// If the future coordinates are out of the board, we won't be able to move the word
		if (board.isOutOfBoard(wordNextLine, wordNextColumn)) {
			return false;
		}
		
		/* For each NounList in the nounMap, we check if there's a noun in the next cell. */
		if (!board.isThereANoun(this, direction, nounsToRemove, wordNextLine, wordNextColumn)) {
			return false;
		}
	
		/* Then, we check if there's a word (property/operator) in the next cell 
		 * NB : A word and a noun can both be on the same cell, that's why we don't return true even if we moved a noun */
		return board.isThereAWord(wordNextLine, wordNextColumn, direction, nounsToRemove);		
	}
	
	/**
	 * Change a word's coordinates based on the direction he's going in
	 * @param direction The direction of the word
	 */
	public void changeCoordinates(String direction) {
		Objects.requireNonNull(direction);
		
		if (!direction.equals("UP") && !direction.equals("DOWN") && !direction.equals("LEFT") && !direction.equals("RIGHT")) {
			throw new IllegalArgumentException("direction is not valid");
		}
		
		switch (direction) {
			case "UP" : line--; break;
			case "DOWN" : line++; break;
			case "LEFT" : column--; break;
			case "RIGHT" : column++; break;
		}
	}	
}
