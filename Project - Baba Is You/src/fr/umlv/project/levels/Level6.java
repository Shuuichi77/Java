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

public class Level6 {	
	public static void main(String[] args) throws IOException {
		int totalLine = 14, totalColumn = 24; 
		var nouns = new NounMap(totalLine, totalColumn);
		var words = new WordList(totalLine, totalColumn);
		var properties = new PropertyMap();
		var board = new Board(totalLine, totalColumn, nouns, words, properties);
		// All nouns list
		var babaList = new NounList(NounName.BABA, totalLine, totalColumn);
		var flagList = new NounList(NounName.FLAG, totalLine, totalColumn);
		var rockList = new NounList(NounName.ROCK, totalLine, totalColumn);
		var skullList = new NounList(NounName.SKULL, totalLine, totalColumn);
		var wallList = new NounList(NounName.WALL, totalLine, totalColumn);
		
		nouns.add(wallList);
		nouns.add(skullList);
		nouns.add(rockList);
		nouns.add(flagList);
		nouns.add(babaList);
		
		/* ------------------ NOUNS ------------------ */
		/* ------------ Single nouns ------------ */
		babaList.add(new Noun(NounName.BABA, 6, 8));
		flagList.add(new Noun(NounName.FLAG, 3, 17));
		
		/* ------------ Horizontal lines nouns ------------ */
		/* Rock : Horizontal line of rock at the top-left corner */
		for (int i = 0 ; i <= 3 ; i++) {
			rockList.add(new Noun(NounName.ROCK, 6, i));
		}
		
		/* Skull : Horizontal line of skull */
		for (int i = 12 ; i <= 23 ; i++) {
			skullList.add(new Noun(NounName.SKULL, 7, i));
		}
		
		/* Wall : First horizontal line */
		for (int i = 11 ; i <= 18 ; i++) {
			if (i != 12 && i != 15) {
				wallList.add(new Noun(NounName.WALL, 1, i));
			}
			
		}
		
		/* Wall : Second and Fifth horizontal lines */
		for (int i = 7 ; i <= 9 ; i++) {
			wallList.add(new Noun(NounName.WALL, 4, i));
			wallList.add(new Noun(NounName.WALL, 8, i));
		}
		
		/* Wall : Third horizontal line */
		for (int i = 16 ; i <= 18 ; i++) {
			wallList.add(new Noun(NounName.WALL, 5, i));
		}

		/* Wall : Fourth horizontal line */
		for (int i = 13 ; i <= 14 ; i++) {
			wallList.add(new Noun(NounName.WALL, 6, i));
		}
		
		/* Wall : Sixth horizontal line */
		for (int i = 11 ; i <= 14 ; i++) {
			wallList.add(new Noun(NounName.WALL, 11, i));
		}
		
		/* ------------ Vertical lines nouns ------------ */
		/* Rock : Vertical line of rock at the top-left corner */
		for (int i = 0 ; i <= 6 ; i++) {
			rockList.add(new Noun(NounName.ROCK, i, 4));
		}
		
		/* Skull : Horizontal line of skull */
		for (int i = 0 ; i <= 6 ; i++) {
			skullList.add(new Noun(NounName.SKULL, i, 12));
		}
		
		/* Wall : First vertical line */
		for (int i = 4 ; i <= 8 ; i++) {
			wallList.add(new Noun(NounName.WALL, i, 6));
		}
		
		/* Wall : Second vertical line */
		for (int i = 1 ; i <= 11 ; i++) {
			if (i != 6) {
				wallList.add(new Noun(NounName.WALL, i, 10));
			}
		}
		
		/* Wall : Third vertical line */
		for (int i = 1 ; i <= 11 ; i++) {
			if (i != 3 && i != 7) {
				wallList.add(new Noun(NounName.WALL, i, 15));
			}
		}
		
		/* Wall : Fourth vertical line */
		for (int i = 1 ; i <= 5 ; i++) {
			wallList.add(new Noun(NounName.WALL, i, 19));
		}
		
		/* ------------------ WORDS ------------------ */
		words.add(new Property(NounName.ROCK, 0, 0));
		words.add(new Operator(OperatorName.IS, 0, 1));
		words.add(new Property(PropertyName.STOP, 0, 2));
		
		words.add(new Property(NounName.SKULL, 2, 0));
		words.add(new Operator(OperatorName.IS, 2, 1));
		words.add(new Property(PropertyName.DEFEAT, 2, 2));
		
		words.add(new Property(NounName.FLAG, 4, 0));
		words.add(new Operator(OperatorName.IS, 4, 1));
		words.add(new Property(PropertyName.WIN, 4, 2));
		
		words.add(new Property(NounName.BABA, 10, 8));
		words.add(new Operator(OperatorName.IS, 11, 8));
		words.add(new Property(PropertyName.YOU, 12, 8));
		
		words.add(new Property(NounName.WALL, 9, 12));
		words.add(new Operator(OperatorName.IS, 9, 13));
		words.add(new Property(PropertyName.STOP, 9, 14));
		
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
