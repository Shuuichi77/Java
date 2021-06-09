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

public class Level3 {	
	public static void main(String[] args) throws IOException {
		int totalLine = 16, totalColumn = 22; 
		var nouns = new NounMap(totalLine, totalColumn);
		var words = new WordList(totalLine, totalColumn);
		var properties = new PropertyMap();
		var board = new Board(totalLine, totalColumn, nouns, words, properties);
		// All nouns list
		var babaList = new NounList(NounName.BABA, totalLine, totalColumn);
		var flagList = new NounList(NounName.FLAG, totalLine, totalColumn);
		var rockList = new NounList(NounName.ROCK, totalLine, totalColumn);
		var waterList = new NounList(NounName.WATER, totalLine, totalColumn);
		var wallList = new NounList(NounName.WALL, totalLine, totalColumn);
		
		nouns.add(wallList);
		nouns.add(waterList);
		nouns.add(rockList);
		nouns.add(flagList);
		nouns.add(babaList);
		
		/* ------------------ NOUNS ------------------ */
		/* ------------ Single nouns ------------ */
		
		babaList.add(new Noun(NounName.BABA, 3, 9));
		wallList.add(new Noun(NounName.WALL, 11, 9));
		rockList.add(new Noun(NounName.ROCK, 3, 12));
		rockList.add(new Noun(NounName.ROCK, 5, 12));
		flagList.add(new Noun(NounName.FLAG, 13, 5));
		
		/* ------------ Horizontal lines nouns ------------ */
		/* Water : All the water near the flag near the bottom-left corner */
		for (int i = 5 ; i <= 7 ; i++) {
			waterList.add(new Noun(NounName.WATER, 11, i));
			waterList.add(new Noun(NounName.WATER, 12, i));
			
			if (i != 5) {
				waterList.add(new Noun(NounName.WATER, 13, i));
			}
		}
		
		/* Water : All the water near the flag near the bottom-left corner */
		for (int i = 8 ; i <= 10 ; i++) {
			waterList.add(new Noun(NounName.WATER, 7, i));
		}
		
		/* Wall : Top-left corner */
		for (int i = 0 ; i <= 2 ; i++) {
			wallList.add(new Noun(NounName.WALL, 3, i));
		}
		
		/* Wall : first row */
		for (int i = 7 ; i <= 14 ; i++) {
			wallList.add(new Noun(NounName.WALL, 1, i));
		}
		
		/* Wall : second row (left part) */
		for (int i = 4 ; i <= 7 ; i++) {
			wallList.add(new Noun(NounName.WALL, 7, i));
		}
		
		/* Wall : second row (right part) */
		for (int i = 11 ; i <= 17 ; i++) {
			wallList.add(new Noun(NounName.WALL, 7, i));
		}
		
		/* Wall : third row */
		for (int i = 4 ; i <= 17 ; i++) {
			wallList.add(new Noun(NounName.WALL, 14, i));
		}
		
		/* ------------ Vertical lines nouns ------------ */
		/* Wall : Top-left corner */
		for (int i = 0 ; i <= 2 ; i++) {
			wallList.add(new Noun(NounName.WALL, i, 2));
		}
		
		/* Wall : First raw, both vertical lines*/
		for (int i = 2 ; i <= 6 ; i++) {
			wallList.add(new Noun(NounName.WALL, i, 7));
			wallList.add(new Noun(NounName.WALL, i, 14));
		}
		
		/* Wall : Second raw, in order : left, middle (except 1) and right vertical lines*/
		for (int i = 8 ; i <= 13 ; i++) {
			wallList.add(new Noun(NounName.WALL, i, 4));
			
			if (i != 11) {
				wallList.add(new Noun(NounName.WALL, i, 11));
			}
			
			wallList.add(new Noun(NounName.WALL, i, 17));
		}
		
		/* ------------------ WORDS ------------------ */
		words.add(new Property(NounName.BABA, 0, 0));
		words.add(new Operator(OperatorName.IS, 1, 0));
		words.add(new Property(PropertyName.YOU, 2, 0));
		
		words.add(new Property(NounName.WALL, 0, 1));
		words.add(new Operator(OperatorName.IS, 1, 1));
		words.add(new Property(PropertyName.STOP, 2, 1));
		
		words.add(new Property(NounName.WATER, 4, 6));
		words.add(new Operator(OperatorName.IS, 5, 6));
		words.add(new Property(PropertyName.SINK, 6, 6));
		
		words.add(new Property(NounName.ROCK, 9, 13));
		words.add(new Operator(OperatorName.IS, 9, 14));
		words.add(new Property(PropertyName.PUSH, 9, 15));
		
		words.add(new Property(NounName.FLAG, 12, 13));
		words.add(new Operator(OperatorName.IS, 12, 14));
		words.add(new Property(PropertyName.WIN, 12, 15));
		
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
