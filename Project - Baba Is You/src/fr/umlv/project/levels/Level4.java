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

public class Level4 {	
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
		
		nouns.add(skullList);
		nouns.add(rockList);
		nouns.add(flagList);
		nouns.add(babaList);
		
		/* ------------------ NOUNS ------------------ */
		/* ------------ Single nouns ------------ */
		babaList.add(new Noun(NounName.BABA, 12, 6));
		flagList.add(new Noun(NounName.FLAG, 9, 18));
		
		/* ------------ Horizontal lines nouns ------------ */
		/* Skull : Top and bottom line of the right square */
		for (int i = 13 ; i <= 21 ; i++) {
			skullList.add(new Noun(NounName.SKULL, 3, i));
			skullList.add(new Noun(NounName.SKULL, 11, i));
		}
		
		/* Skull : 2 lines at the left of the screen*/
		for (int i = 3 ; i <= 9 ; i++) {
			if (i != 6) {
				skullList.add(new Noun(NounName.SKULL, 9, i));
			}
		}

		/* ------------ Vertical lines nouns ------------ */
		/* Rock : The only rocks on the screen */
		for (int i = 8 ; i <= 10 ; i++) {
			rockList.add(new Noun(NounName.ROCK, i, 6));
		}
	
		/* Skull : Left and right lines of the right square */ 
		for (int i = 4 ; i <= 10 ; i++) {
			skullList.add(new Noun(NounName.SKULL, i, 13));
			skullList.add(new Noun(NounName.SKULL, i, 21));
		}
		
		/* Skull : First row with 2 vertical lines at the left */ 
		for (int i = 7 ; i <= 8 ; i++) {
			skullList.add(new Noun(NounName.SKULL, i, 5));
			skullList.add(new Noun(NounName.SKULL, i, 7));
		}
		
		/* Skull : Second row with 2 vertical lines at the left */ 
		for (int i = 10 ; i <= 13 ; i++) {
			skullList.add(new Noun(NounName.SKULL, i, 3));
			skullList.add(new Noun(NounName.SKULL, i, 9));
		}
		
		/* ------------------ WORDS ------------------ */
		words.add(new Property(NounName.FLAG, 0, 0));
		words.add(new Operator(OperatorName.IS, 0, 1));
		words.add(new Property(PropertyName.WIN, 0, 2));
		
		words.add(new Property(NounName.BABA, 1, 0));
		words.add(new Operator(OperatorName.IS, 1, 1));
		words.add(new Property(PropertyName.YOU, 1, 2));
		
		words.add(new Property(NounName.ROCK, 5, 2));
		words.add(new Operator(OperatorName.IS, 5, 3));
		words.add(new Property(PropertyName.PUSH, 5, 4));
		
		words.add(new Property(NounName.SKULL, 5, 15));
		words.add(new Operator(OperatorName.IS, 6, 15));
		words.add(new Property(PropertyName.DEFEAT, 7, 15));
		
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
