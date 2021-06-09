package fr.umlv.project.levels;

import java.awt.Color;
import java.io.IOException;

import fr.umlv.project.Board;
import fr.umlv.project.Noun;
import fr.umlv.project.NounList;
import fr.umlv.project.NounMap;
import fr.umlv.project.NounName;
import fr.umlv.project.Operator;
import fr.umlv.project.OperatorName;
import fr.umlv.project.Property;
import fr.umlv.project.PropertyMap;
import fr.umlv.project.PropertyName;
import fr.umlv.project.WordList;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;

public class Level5 {	
	public static void main(String[] args) throws IOException {
		int totalLine = 18, totalColumn = 33; 
		var nouns = new NounMap(totalLine, totalColumn);
		var words = new WordList(totalLine, totalColumn);
		var properties = new PropertyMap();
		var board = new Board(totalLine, totalColumn, nouns, words, properties);
		// All nouns list
		var babaList = new NounList(NounName.BABA, totalLine, totalColumn);
		var flagList = new NounList(NounName.FLAG, totalLine, totalColumn);
		var lavaList = new NounList(NounName.LAVA, totalLine, totalColumn);
		var rockList = new NounList(NounName.ROCK, totalLine, totalColumn);
		var wallList = new NounList(NounName.WALL, totalLine, totalColumn);
		
		nouns.add(wallList);
		nouns.add(lavaList);
		nouns.add(rockList);
		nouns.add(flagList);
		nouns.add(babaList);
		
		/* ------------------ NOUNS ------------------ */
		/* ------------ Single nouns ------------ */
		babaList.add(new Noun(NounName.BABA, 1, 14));
		flagList.add(new Noun(NounName.FLAG, 12, 26));
		lavaList.add(new Noun(NounName.LAVA, 3, 0));
		
		lavaList.add(new Noun(NounName.LAVA, 17, 11));
		lavaList.add(new Noun(NounName.LAVA, 0, 26));
		lavaList.add(new Noun(NounName.LAVA, 17, 30));
		
		
		rockList.add(new Noun(NounName.ROCK, 5, 12));
		wallList.add(new Noun(NounName.WALL, 4, 7));
		wallList.add(new Noun(NounName.WALL, 4, 11));

		/* ------------ Horizontal lines nouns ------------ */
		/* Lava : Top-left corner, first line */
		for (int i = 3 ; i <= 4 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, 0, i));
		}
		
		/* Lava : Top-left corner, 2nd line */
		for (int i = 0 ; i <= 3 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, 1, i));
		}
		
		/* Lava : Top-left corner, 3rd line */
		for (int i = 0 ; i <= 1 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, 2, i));
		}
		
		/* Wall : Top structure, 1st row */
		for (int i = 13 ; i <= 15 ; i++) {
			wallList.add(new Noun(NounName.WALL, 3, i));
		}
		
		/* Wall : Top structure, 2nd row */
		for (int i = 7 ; i <= 12 ; i++) {
			if (i != 8) {
				wallList.add(new Noun(NounName.WALL, 6, i));
			}
		}
		
		/* Wall : Bottom structure, both row */
		for (int i = 8 ; i <= 10 ; i++) {
			wallList.add(new Noun(NounName.WALL, 13, i));
			wallList.add(new Noun(NounName.WALL, 15, i));
		}
		
		/* ------------ Vertical lines nouns ------------ */
		/* Lava : Sea of lava : 1st line (& bottom-right corner, 2nd line) */
		for (int i = 16 ; i <= 17 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, i, 12));
			lavaList.add(new Noun(NounName.LAVA, i, 31));
		}
		
		/* Lava : Sea of lava : 2nd line (& bottom-right corner, 3rd line) */
		for (int i = 15 ; i <= 17 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, i, 13));
			lavaList.add(new Noun(NounName.LAVA, i, 32));
		}
		
		/* Lava : Sea of lava : 3rd line */
		for (int i = 13 ; i <= 17 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, i, 14));
		}
		
		/* Lava : Sea of lava : 4th line */
		for (int i = 11 ; i <= 17 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, i, 15));
		}
		
		/* Lava : Sea of lava : 5th line */
		for (int i = 9 ; i <= 17 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, i, 16));
		}
		
		/* Lava : Sea of lava : 6th line */
		for (int i = 6 ; i <= 17 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, i, 17));
		}
		
		/* Lava : Sea of lava : 7th line */
		for (int i = 3 ; i <= 15 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, i, 18));
		}
		
		/* Lava : Sea of lava : 8th line */
		for (int i = 1 ; i <= 13 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, i, 19));
		}
		
		/* Lava : Sea of lava : 9th line */
		for (int i = 0 ; i <= 12 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, i, 20));
		}
		
		/* Lava : Sea of lava : 10th line */
		for (int i = 0 ; i <= 10 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, i, 21));
		}
		
		/* Lava : Sea of lava : 11th line */
		for (int i = 0 ; i <= 9 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, i, 22));
		}
		
		/* Lava : Sea of lava : 12th line */
		for (int i = 0 ; i <= 6 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, i, 23));
		}
		
		/* Lava : Sea of lava : 13th line */
		for (int i = 0 ; i <= 3 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, i, 24));
		}
		
		/* Lava : Sea of lava : 14th line */
		for (int i = 0 ; i <= 1 ; i++) {
			lavaList.add(new Noun(NounName.LAVA, i, 25));
		}
		
		/* Wall : Top structure, 1st vertical line */
		for (int i = 0 ; i <= 7 ; i++) {
			wallList.add(new Noun(NounName.WALL, i, 6));
		}
		
		/* Wall : Top structure, 2nd vertical line */
		for (int i = 6 ; i <= 7 ; i++) {
			wallList.add(new Noun(NounName.WALL, i, 8));
		}
		
		/* Wall : Top structure, 3rd vertical line */
		for (int i = 0 ; i <= 4 ; i++) {
			if (i != 1) {
				wallList.add(new Noun(NounName.WALL, i, 12));
			}
		}
		
		/* Wall : Top structure, 4th vertical line */
		for (int i = 0 ; i <= 3 ; i++) {
			wallList.add(new Noun(NounName.WALL, i, 16));
		}
		
		/* Wall : Bottom structure, both vertical line */
		for (int i = 12 ; i <= 15 ; i++) {
			wallList.add(new Noun(NounName.WALL, i, 7));
			wallList.add(new Noun(NounName.WALL, i, 11));
		}
		
		/* ------------------ WORDS ------------------ */
		words.add(new Property(NounName.WALL, 0, 0));
		words.add(new Operator(OperatorName.IS, 0, 1));
		words.add(new Property(PropertyName.STOP, 0, 2));
		
		words.add(new Property(NounName.BABA, 3, 8));
		words.add(new Operator(OperatorName.IS, 3, 9));
		words.add(new Property(PropertyName.YOU, 3, 10));
		
		words.add(new Property(NounName.ROCK, 7, 7));
		words.add(new Operator(OperatorName.IS, 8, 7));
		words.add(new Property(PropertyName.PUSH, 9, 7));
		
		words.add(new Property(NounName.LAVA, 10, 12));
		
		words.add(new Property(NounName.BABA, 12, 8));
		words.add(new Operator(OperatorName.IS, 12, 9));
		words.add(new Property(PropertyName.MELT, 12, 10));
		
		words.add(new Property(NounName.LAVA, 14, 8));
		words.add(new Operator(OperatorName.IS, 14, 9));
		words.add(new Property(PropertyName.HOT, 14, 10));
		
		words.add(new Property(NounName.FLAG, 14, 25));
		words.add(new Operator(OperatorName.IS, 14, 26));
		words.add(new Property(PropertyName.WIN, 14, 27));
		
		// Checking current properties and setting You noun
		properties.checkProperties(words, nouns);
		nouns.setYou(properties.getYouNoun());
		
		Application.run(Color.BLACK, context -> {
			ScreenInfo screenInfo = context.getScreenInfo();
			float width = screenInfo.getWidth(),
				  height = screenInfo.getHeight();
			float cellSize = (height / totalLine > width / totalColumn) ? width / totalColumn :
				  														  height / totalLine;
			float horizontalPadding = (width - cellSize * totalColumn) / 2,
				  verticalPadding = (height - cellSize * totalLine) / 2;
			
			board.drawAllWords(context, width, height, cellSize, horizontalPadding, verticalPadding);  
			
			// While the you's list isn't empty, or we didn't touch a win noun
			while (!board.isDefeat() && !board.isWin()) {
				Event event = context.pollOrWaitEvent(2);
		        if (event == null) {  // no event
		          continue;
		        }
		        
		        Action action = event.getAction();
		        
		        if (action == Action.KEY_PRESSED) {
		        	String keyPressed = event.getKey().toString();
		        	
		        	if (keyPressed == "UP" || keyPressed == "DOWN" || keyPressed == "LEFT" || keyPressed == "RIGHT") {
		        		board.youIsMoving(keyPressed, context, width, height, cellSize, horizontalPadding, verticalPadding);
		        	}
		        }		        
			}
			
			board.gameResults(context, height, width);
		});
		
	} // fin main
}
