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

public class Level0 {	
	public static void main(String[] args) throws IOException {
		int totalColumn = 18, totalLine = 15;
		var nouns = new NounMap(totalLine, totalColumn);
		var words = new WordList(totalLine, totalColumn);
		var properties = new PropertyMap();
		var board = new Board(totalLine, totalColumn, nouns, words, properties);
		// All nouns list
		var babaList = new NounList(NounName.BABA, totalLine, totalColumn);
		var flagList = new NounList(NounName.FLAG, totalLine, totalColumn);
		var rockList = new NounList(NounName.ROCK, totalLine, totalColumn);
		var wallList = new NounList(NounName.WALL, totalLine, totalColumn);
		
		nouns.add(wallList);
		nouns.add(rockList);
		nouns.add(flagList);
		nouns.add(babaList);
		
		/* ------------------ NOUNS ------------------ */
		babaList.add(new Noun(NounName.BABA, 7, 5));
		flagList.add(new Noun(NounName.FLAG, 7, 12));
		
		for (int i = 6 ; i <= 8 ; i++) {
			rockList.add(new Noun(NounName.ROCK, i, 8));
		}
					
		for (int i = 3 ; i <= 13 ; i++) {
			wallList.add(new Noun(NounName.WALL, 5, i));
			wallList.add(new Noun(NounName.WALL, 9, i));
		}
		
		/* ------------------ WORDS ------------------ */
		words.add(new Property(NounName.BABA, 3, 3));
		words.add(new Operator(OperatorName.IS, 3, 4));
		words.add(new Property(PropertyName.YOU, 3, 5));
		
		words.add(new Property(NounName.FLAG, 3, 11));
		words.add(new Operator(OperatorName.IS, 3, 12));
		words.add(new Property(PropertyName.WIN, 3, 13));
		
		words.add(new Property(NounName.WALL, 11, 3));
		words.add(new Operator(OperatorName.IS, 11, 4));
		words.add(new Property(PropertyName.STOP, 11, 5));
		
		words.add(new Property(NounName.ROCK, 11, 11));
		words.add(new Operator(OperatorName.IS, 11, 12));
		words.add(new Property(PropertyName.PUSH, 11, 13));
		
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
