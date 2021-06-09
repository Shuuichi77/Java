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

public class LevelCustomed {	
	public static void main(String[] args) throws IOException {
		int totalLine = 10, totalColumn = 19; 
		var nouns = new NounMap(totalLine, totalColumn);
		var words = new WordList(totalLine, totalColumn);
		var properties = new PropertyMap();
		var board = new Board(totalLine, totalColumn, nouns, words, properties);
		// All nouns list
		var babaList = new NounList(NounName.BABA, totalLine, totalColumn);
		var flagList = new NounList(NounName.FLAG, totalLine, totalColumn);
		var rockList = new NounList(NounName.ROCK, totalLine, totalColumn);
		var virusList = new NounList(NounName.VIRUS, totalLine, totalColumn);
		var wallList = new NounList(NounName.WALL, totalLine, totalColumn);
		
		nouns.add(wallList);
		nouns.add(rockList);
		nouns.add(virusList);
		nouns.add(flagList);
		nouns.add(babaList);
		
		/* ------------------ NOUNS ------------------ */
		/* ------------ Single nouns ------------ */
		babaList.add(new Noun(NounName.BABA, 7, 6));
		flagList.add(new Noun(NounName.FLAG, 5, 17));
		virusList.add(new Noun(NounName.VIRUS, 4, 8));
		virusList.add(new Noun(NounName.VIRUS, 6, 8));
		rockList.add(new Noun(NounName.ROCK, 7, 8));
		rockList.add(new Noun(NounName.ROCK, 5, 11));
		rockList.add(new Noun(NounName.ROCK, 6, 9));
		rockList.add(new Noun(NounName.ROCK, 6, 10));
		
		/* ------------ Horizontal lines nouns ------------ */
		/* Wall : Top and bottom line of the square */
		for (int i = 4 ; i <= 15 ; i++) {
			wallList.add(new Noun(NounName.WALL, 0, i));
			wallList.add(new Noun(NounName.WALL, 9, i));
		}
		
		/* Rock : Top-left corner */
		for (int i = 0 ; i <= 2 ; i++) {
			rockList.add(new Noun(NounName.ROCK, 3, i));
		}

		/* ------------ Vertical lines nouns ------------ */
		/* Wall : Left and Right line of the square */
		for (int i = 1 ; i <= 8 ; i++) {
			wallList.add(new Noun(NounName.WALL, i, 4));
			wallList.add(new Noun(NounName.WALL, i, 15));
		}
	
		/* Rock : Top-left corner */
		for (int i = 0 ; i <= 3 ; i++) {
			rockList.add(new Noun(NounName.ROCK, i, 3));
		}
		
		/* ------------------ WORDS ------------------ */
		words.add(new Property(NounName.BABA, 0, 0));
		words.add(new Operator(OperatorName.IS, 0, 1));
		words.add(new Property(PropertyName.YOU, 0, 2));
		
		words.add(new Property(NounName.VIRUS, 1, 0));
		words.add(new Operator(OperatorName.IS, 1, 1));
		words.add(new Property(PropertyName.POISON, 1, 2));
		
		words.add(new Property(NounName.ROCK, 2, 0));
		words.add(new Operator(OperatorName.IS, 2, 1));
		words.add(new Property(PropertyName.STOP, 2, 2));
		
		words.add(new Property(NounName.FLAG, 2, 10));
		words.add(new Operator(OperatorName.IS, 2, 11));
		words.add(new Property(PropertyName.WIN, 2, 12));
		
		words.add(new Property(NounName.WALL, 5, 8));
		words.add(new Operator(OperatorName.IS, 5, 9));
		words.add(new Property(PropertyName.STOP, 5, 10));
		
		
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
