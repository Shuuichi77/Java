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

public class Level2 {	
	public static void main(String[] args) throws IOException {
		int totalColumn = 24, totalLine = 18;
		var nouns = new NounMap(totalLine, totalColumn);
		var words = new WordList(totalLine, totalColumn);
		var properties = new PropertyMap();
		var board = new Board(totalLine, totalColumn, nouns, words, properties);
		// All nouns list
		var babaList = new NounList(NounName.BABA, totalLine, totalColumn);
		var flagList = new NounList(NounName.FLAG, totalLine, totalColumn);
		var wallList = new NounList(NounName.WALL, totalLine, totalColumn);
		
		nouns.add(wallList);
		nouns.add(flagList);
		nouns.add(babaList);
		
		/* --------- NOUNS --------- */
		/* ------------ Single nouns ------------ */
		wallList.add(new Noun(NounName.WALL, 13, 14));
		
		/* ------------ Horizontal lines nouns ------------ */
		/* Flag : The three first row right part */
		for (int i = 10 ; i <= 17 ; i++) {
			flagList.add(new Noun(NounName.FLAG, 2, i));
			flagList.add(new Noun(NounName.FLAG, 10, i));
		}
		
		/* Flag : The second and third row left part */
		for (int i = 6 ; i <= 9 ; i++) {
			flagList.add(new Noun(NounName.FLAG, 6, i));
			flagList.add(new Noun(NounName.FLAG, 10, i));
		}
		
		/* Flag : The fouth row */
		for (int i = 14 ; i <= 16 ; i++) {
			flagList.add(new Noun(NounName.FLAG, 15, i));
		}
		
		/* Flag : The last row */
		for (int i = 10 ; i <= 14 ; i++) {
			flagList.add(new Noun(NounName.FLAG, 16, i));
		}
		
		/* ------------ Vertical lines nouns ------------ */
		/* Flag : First row vertical lines of wall at the top of the screen */
		for (int i = 3 ; i <= 6 ; i++) {
			flagList.add(new Noun(NounName.FLAG, i, 10));
			flagList.add(new Noun(NounName.FLAG, i, 17));
		}
		
		/* Flag : Second row two vertical lines of wall at the top of the screen */
		for (int i = 7 ; i <= 9 ; i++) {
			flagList.add(new Noun(NounName.FLAG, i, 6));
			flagList.add(new Noun(NounName.FLAG, i, 17));
		}
		
		/* Flag : Third row two vertical lines of wall at the top of the screen */
		for (int i = 11 ; i <= 15 ; i++) {
			flagList.add(new Noun(NounName.FLAG, i, 10));
			flagList.add(new Noun(NounName.FLAG, i, 17));
		}
		
		/* --------- WORDS --------- */
		words.add(new Property(NounName.WALL, 12, 7));
		words.add(new Operator(OperatorName.IS, 13, 7));
		words.add(new Property(PropertyName.YOU, 14, 7));
		
		words.add(new Property(NounName.BABA, 8, 8));
		words.add(new Operator(OperatorName.IS, 4, 12));
		words.add(new Property(PropertyName.WIN, 6, 15));
		
		words.add(new Property(NounName.FLAG, 12, 12));
		words.add(new Operator(OperatorName.IS, 13, 12));
		words.add(new Property(PropertyName.STOP, 14, 12));
		
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
